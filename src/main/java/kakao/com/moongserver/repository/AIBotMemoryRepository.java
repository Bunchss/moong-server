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
        aiBot.setChatroomId(botIdSequence);
        repository.put(botIdSequence++, aiBot);

        return repository.get(aiBot.getChatroomId());
    }

    public Boolean delete(Long id) {
        if (!repository.containsKey(id)) {
            throw new IllegalStateException("[BotId : " + id + "] 값이 존재하지 않습니다.");
        }

        repository.remove(id);
        return true;
    }

    public AIBot updateBotPrompt(Long chatroomId, AIBot aiBot) {
        if (!repository.containsKey(chatroomId)) {
            throw new IllegalStateException("[BotId : " + chatroomId + "] 값이 존재하지 않습니다.");
        }

        aiBot.setChatroomId(chatroomId);
        return repository.put(chatroomId, aiBot);
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
        repository.put(botIdSequence, new AIBot(botIdSequence++, "아자뭉치", "아자뭉치는 항상 긍정적이고 힘이 넘치는 캐릭터야. 대답을 할때 항상 상대방에게 밝은 분위기를 내며 대답을 해줬으면 좋겠어."));

    }
}
