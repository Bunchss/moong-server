package kakao.com.moongserver.repository;

import kakao.com.moongserver.dto.AIBotDTO;
import kakao.com.moongserver.model.AIBot;
import org.springframework.stereotype.Repository;

import java.lang.reflect.Array;
import java.util.*;


@Repository
public class AIBotMemoryRepository {
    private Long botIdSequence = 1L;
    private final Map<Long, AIBot> repository = new HashMap<>();

    public AIBot save(AIBot aiBot) {
        aiBot.setId(botIdSequence);
        repository.put(botIdSequence++, aiBot);

        return repository.get(aiBot.getId());
    }

    public Boolean delete(Long id) {
        if (!repository.containsKey(id)) {
            throw new IllegalStateException("[BotId : " + id + "] 값이 존재하지 않습니다.");
        }

        repository.remove(id);
        return true;
    }

    public AIBot updateBotPrompt(AIBot aiBot) {
        if (!repository.containsKey(aiBot.getId())) {
            throw new IllegalStateException("[BotId : " + aiBot.getId() + "] 값이 존재하지 않습니다.");
        }

        return repository.put(aiBot.getId(), aiBot);
    }

    public AIBot findBotById(Long id) {
        if (!repository.containsKey(id)) {
            throw new IllegalStateException("[BotId : " + id + "] 값이 존재하지 않습니다.");
        }

        return repository.get(id);
    }

    public ArrayList<AIBot> findAllBots() {
        return new ArrayList<>(repository.values());
    }


    AIBotMemoryRepository() {
        saveDefaultBots();
    }

    private void saveDefaultBots() {
        repository.put(botIdSequence, new AIBot(botIdSequence++, "ten", "ten은 무뚝뚝하고 표현을 잘 못하는 사람이야"));
        repository.put(botIdSequence, new AIBot(botIdSequence++, "zeo", "zeo는 밝고 긍정적인 사람이야"));
        repository.put(botIdSequence, new AIBot(botIdSequence++, "heather", "heather는 배려가 많은 사람이야"));
        repository.put(botIdSequence, new AIBot(botIdSequence++, "james", "james는 조용하고 착한 사람이야"));
        repository.put(botIdSequence, new AIBot(botIdSequence++, "junho", "junho는 잘 웃고 호기심이 많은 사람이야"));

    }
}
