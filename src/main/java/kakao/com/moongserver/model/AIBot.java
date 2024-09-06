package kakao.com.moongserver.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class AIBot {
    private final Long id;
    private final String name;
    private final String prompt;
}
