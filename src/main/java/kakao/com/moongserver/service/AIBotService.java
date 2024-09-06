package kakao.com.moongserver.service;

import kakao.com.moongserver.dto.AIBotDTO;
import kakao.com.moongserver.model.AIBot;
import kakao.com.moongserver.repository.AIBotMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;

import java.util.*;


@Service
@RequiredArgsConstructor
public class AIBotService {
    private final AIBotMemoryRepository aiBotMemoryRepository;
    private final WebClient webClient;

    public AIBot createBot(AIBotDTO aiBotDTO) {
//        sendAIBotDataToExternal(aiBotDTO.toEntity());

        return aiBotMemoryRepository.save(aiBotDTO.toEntity());
    }

    public Boolean deleteBot(Long deleteId) {
        return aiBotMemoryRepository.delete(deleteId);
    }

    public AIBot findBotById(Long id) {
        return aiBotMemoryRepository.findBotById(id);
    }

    public ArrayList<AIBot> findAllBots() {
        return aiBotMemoryRepository.findAllBots();
    }


    public AIBot updateBotPrompt(Long id, AIBotDTO aiBotDTO) {
//        sendAIBotDataToExternal(aiBotDTO.toEntity());

        return aiBotMemoryRepository.updateBotPrompt(id, aiBotDTO.toEntity());

    }

    private Mono<String> sendAIBotDataToExternal(AIBot bot) {
        return webClient.post()
                .uri("외부 엔드포인트")        // /ai/bot
                .bodyValue(bot)
                .retrieve()
                .bodyToMono(String.class);
    }

}
