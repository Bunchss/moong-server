package kakao.com.moongserver.dto;

import kakao.com.moongserver.model.AIBot;

public record AIBotDTO(String name, String prompt) {
    public AIBot toEntity() {
        return new AIBot(null, this.name, this.prompt);
    }
}
