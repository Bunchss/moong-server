package kakao.com.moongserver.controller;

import kakao.com.moongserver.dto.AIBotDTO;
import kakao.com.moongserver.model.AIBot;
import kakao.com.moongserver.service.AIBotService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/bot")
@RequiredArgsConstructor
public class AIBotController {
    private final AIBotService aiBotService;

    @PostMapping
    public ResponseEntity<AIBot> createBot(@RequestBody AIBotDTO aiBotDTO) {
        AIBot aiBot = aiBotService.createBot(aiBotDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(aiBot);
    }
}
