package kakao.com.moongserver.service;

import kakao.com.moongserver.dto.AIBotDTO;
import kakao.com.moongserver.model.AIBot;
import kakao.com.moongserver.repository.AIBotMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.*;


@Service
@RequiredArgsConstructor
public class AIBotService {
    private final WebClient webClient;
    private final AIBotMemoryRepository aiBotMemoryRepository;
    private final static String AI_SERVER_PATH = "query_with_prompt/";

    public AIBot createBot(AIBotDTO aiBotDTO) {
        AIBot bot = aiBotMemoryRepository.save(aiBotDTO.toEntity());
        sendAIBotDataToExternal(bot);

        return bot;
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


    public AIBot updateBotPrompt(Long chatroomId, AIBotDTO aiBotDTO) {
        AIBot bot = aiBotMemoryRepository.updateBotPrompt(chatroomId, aiBotDTO.toEntity());
        sendAIBotDataToExternal(bot);

        return bot;
    }

    private void sendAIBotDataToExternal(AIBot bot) {
        webClient.post()
                .uri(AI_SERVER_PATH)        // /bot/ai
                .bodyValue(bot)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, clientResponse ->
                        Mono.error(new RuntimeException("5xx Server Error")));
    }

}
