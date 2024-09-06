package kakao.com.moongserver.service;

import kakao.com.moongserver.dto.AIBotDTO;
import kakao.com.moongserver.model.AIBot;
import kakao.com.moongserver.repository.AIBotMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
@RequiredArgsConstructor
public class AIBotService {
    private final AIBotMemoryRepository aiBotMemoryRepository;

    public AIBot createBot(AIBotDTO aiBotDTO) {
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

    /*
    public Boolean updateBotPrompt(AIBotDTO aiBotDTO) {
        aiBotMemoryRepository.updateBotPrompt(aiBotDTO.toEntity());

//        webClient로 AI server에 프롬프트 변경 내용 및 어떤 id를 가진 character의 prompt를 변경할 것인지
    }

*/
}
