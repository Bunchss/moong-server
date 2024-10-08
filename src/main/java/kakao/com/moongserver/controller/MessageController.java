package kakao.com.moongserver.controller;

import kakao.com.moongserver.dto.MessageDto;
import kakao.com.moongserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @MessageMapping("/chat")
    public void sendMessage(MessageDto messageDto) {
        messageService.convertAndSendMessage(messageDto);
        Mono<MessageDto> messageDtoMono = messageService.sendMessageToExternal(messageDto);

        messageDtoMono.block();
    }


//    Ai 서버에서 보내준 응답을 토픽으로 저장.
    @PostMapping("/chat/external")
    public void convertAndSendExternalResponse(MessageDto messageDto) {
        messageService.convertAndSendMessage(messageDto);
    }



//    사용자가 topic으로 메시지를 sub

//    사용자가 topic으로부터 메시지를 pub

//    ai server가 전달한 message를 topic으로 pub

}
