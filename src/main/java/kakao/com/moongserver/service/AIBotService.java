package kakao.com.moongserver.service;

import kakao.com.moongserver.dto.AIBotDTO;
import kakao.com.moongserver.model.AIBot;
import kakao.com.moongserver.repository.AIBotMemoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AIBotService {
    private final AIBotMemoryRepository aiBotMemoryRepository;

    public AIBot createBot(AIBotDTO aiBotDTO) {
        return aiBotMemoryRepository.save(aiBotDTO.toEntity());
    }
}
