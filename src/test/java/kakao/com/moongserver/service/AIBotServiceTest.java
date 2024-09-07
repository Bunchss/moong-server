package kakao.com.moongserver.service;

import kakao.com.moongserver.dto.AIBotDTO;
import kakao.com.moongserver.model.AIBot;
import kakao.com.moongserver.repository.AIBotMemoryRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import static org.mockito.ArgumentMatchers.any;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

import org.mockito.junit.jupiter.MockitoExtension;

import javax.swing.text.html.Option;
import java.util.Optional;


@ExtendWith(MockitoExtension.class)
class AIBotServiceTest {

    @InjectMocks
    private AIBotService aiBotService;

    @Mock
    private AIBotMemoryRepository aiBotMemoryRepository;

    @Test
    void createBot() {
        AIBotDTO mock = new AIBotDTO("ten", "ten은 무뚝뚝한 사람이야");
        AIBot expected = new AIBot(1L, "ten", "ten은 무뚝뚝한 사람이야");

        when(aiBotMemoryRepository.save(any(AIBot.class))).thenReturn(expected);

        AIBot createdBot = aiBotService.createBot(mock);

        assertThat(createdBot.getChatroomId()).isEqualTo(1L);
        assertThat(createdBot.getName()).isEqualTo("ten");
        verify(aiBotMemoryRepository, times(1)).save(any(AIBot.class));
    }


    @Test
    void findBotByIdSuccess() {
        Long id = 1L;
        AIBot aiBot = new AIBot(id, "ten", "ten은 무뚝뚝한 사람이야");

        when(aiBotMemoryRepository.findBotById(id)).thenReturn(aiBot);

        AIBot findBot = aiBotService.findBotById(id);

        assertThat(findBot.getChatroomId()).isEqualTo(id);
        assertThat(findBot.getName()).isEqualTo(aiBot.getName());
        verify(aiBotMemoryRepository, times(1)).findBotById(id);
    }
}
