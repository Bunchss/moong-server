package kakao.com.moongserver.controller;

import kakao.com.moongserver.dto.MessageDto;
import kakao.com.moongserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
//    @MessageMapping(~~) -> client가 send를 해당 ~~로 보내면 해당 method가 처리됨.
//    @SendTo(~~) 는 메시지를 어디로 다시 broadcast할 지 결정함. 즉, topic으로 데이터를 보냄
//      ~~를 client가 구독하고 있으면 메시지를 수신할 수 있음.

    @MessageMapping("/chat")
    public void sendMessage(MessageDto messageDto) {
        messageService.convertAndSendMessage(messageDto);
//        messageService.sendMessagetoExternal(messageDto);
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
