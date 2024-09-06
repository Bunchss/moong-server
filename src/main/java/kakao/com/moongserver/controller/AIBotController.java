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

@Controller
@RequestMapping("/bot")
@RequiredArgsConstructor
public class AIBotController {
    private final AIBotService aiBotService;
    private final WebClient webClient;

    @PostMapping
    public ResponseEntity<AIBot> createBot(@RequestBody AIBotDTO aiBotDTO) {
        AIBot aiBot = aiBotService.createBot(aiBotDTO);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(aiBot);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Boolean> deleteBot(@PathVariable Long id) {
//        return value type이 맞지 않다고 에러는 뜨는데, 삭제는 잘 되네
        Boolean result = aiBotService.deleteBot(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<AIBot> findBot(@PathVariable Long id) {
        AIBot aiBot = aiBotService.findBotById(id);

        return ResponseEntity.status(HttpStatus.OK)
                .body(aiBot);
    }

    @GetMapping
    public ResponseEntity<ArrayList<AIBot>> findAllBots() {
        ArrayList<AIBot> bots = aiBotService.findAllBots();

        return ResponseEntity.status(HttpStatus.OK)
                .body(bots);
    }

    @PutMapping("/{id}")
    public ResponseEntity<AIBot> updateBotPrompt(@PathVariable Long id, @RequestBody AIBotDTO aiBotDTO) {
        AIBot bot = aiBotService.updateBotPrompt(id, aiBotDTO);

        return ResponseEntity.status(HttpStatus.OK)
                .body(bot);
    }
}
