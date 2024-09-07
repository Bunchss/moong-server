package kakao.com.moongserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class AIBot {
    private Long chatroomId;
    private String name;
    private String prompt;
}
