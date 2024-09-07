package kakao.com.moongserver.controller;

import kakao.com.moongserver.dto.AIBotDTO;
import kakao.com.moongserver.model.AIBot;
import kakao.com.moongserver.service.AIBotService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import javax.xml.transform.OutputKeys;
import java.util.ArrayList;

@RestController
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

    //
    @DeleteMapping("/{chatroomId}")
    public ResponseEntity<Boolean> deleteBot(@PathVariable Long chatroomId) {
        Boolean result = aiBotService.deleteBot(chatroomId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/{chatroomId}")
    public ResponseEntity<AIBot> findBot(@PathVariable Long chatroomId) {
        AIBot aiBot = aiBotService.findBotById(chatroomId);

        return ResponseEntity.status(HttpStatus.OK)
                .body(aiBot);
    }

    @GetMapping
    public ResponseEntity<ArrayList<AIBot>> findAllBots() {
        ArrayList<AIBot> bots = aiBotService.findAllBots();

        return ResponseEntity.status(HttpStatus.OK)
                .body(bots);
    }

    @PutMapping("/{chatroomId}")
    public ResponseEntity<AIBot> updateBotPrompt(@PathVariable Long chatroomId, @RequestBody AIBotDTO aiBotDTO) {
        AIBot bot = aiBotService.updateBotPrompt(chatroomId,aiBotDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(bot);
    }
}
