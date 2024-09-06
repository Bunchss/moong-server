package kakao.com.moongserver.repository;

import kakao.com.moongserver.model.AIBot;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

@Repository
public class AIBotMemoryRepository {
    private Long botIdSequence = 1L;
    private final Map<Long, AIBot> botMap = new HashMap<>();

    public AIBot save(AIBot aiBot) {
        aiBot.setId(botIdSequence);
        botMap.put(botIdSequence++, aiBot);

        return botMap.get(aiBot.getId());
    }

}
