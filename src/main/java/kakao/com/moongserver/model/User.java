package kakao.com.moongserver.model;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class User {
    private final Long id;
    private final String name;
}
