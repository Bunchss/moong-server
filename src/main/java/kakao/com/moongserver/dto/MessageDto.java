package kakao.com.moongserver.dto;

import lombok.Data;
import lombok.RequiredArgsConstructor;

public record MessageDto(Long chatRoomId, String name, String message) {
    public MessageTopic convertMessageTopic() {
        return new MessageTopic(this.name(), this.message());
    }

    static class MessageTopic {
        private final String name;
        private final String message;

        public MessageTopic(String name, String message) {
            this.name = name;
            this.message = message;
        }

        public String getName() {
            return name;
        }

        public String getMessage() {
            return message;
        }
    }
}