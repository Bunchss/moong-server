package kakao.com.moongserver.service;

import kakao.com.moongserver.dto.MessageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MessageService {
    private final SimpMessagingTemplate simpMessagingTemplate;

    public void convertAndSendMessage(MessageDto messageDto) {
        Long roomId = messageDto.chatRoomId();

        simpMessagingTemplate.convertAndSend("/topic/chat/room/" + roomId, messageDto.convertMessageTopic());
    }
}
