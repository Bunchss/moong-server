package kakao.com.moongserver.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@AllArgsConstructor
public class User {
    private final Long id;
    private final String name;
}
