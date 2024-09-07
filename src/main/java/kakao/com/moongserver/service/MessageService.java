package kakao.com.moongserver.service;

import kakao.com.moongserver.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final WebClient webClient;
    private final SimpMessagingTemplate simpMessagingTemplate;
    private final static String AI_SERVER_PATH = "query_with_prompt/";

    public void convertAndSendMessage(MessageDto messageDto) {
        Long roomId = messageDto.chatRoomId();

        simpMessagingTemplate.convertAndSend("/topic/chat/room/" + roomId, messageDto.convertMessageTopic());
    }

    public Mono<MessageDto> sendMessageToExternal(MessageDto messageDto) {
        return webClient.post()
                .uri(AI_SERVER_PATH)
                .bodyValue(messageDto)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException("5xx server Error")))
                .bodyToMono(MessageDto.class)
                .doOnSuccess(this::convertAndSendMessage);
    }
}
