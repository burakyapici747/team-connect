package com.teamconnect.common.enumarator;

public enum ReactionType {
    LIKE("👍"),
    LOVE("❤️"),
    LAUGH("😄"),
    WOW("😲"),
    SAD("😢"),
    ANGRY("😠"),
    THUMBS_UP("👍"),
    THUMBS_DOWN("👎"),
    CUSTOM("custom");

    private final String emoji;

    ReactionType(String emoji) {
        this.emoji = emoji;
    }

    public String getEmoji() {
        return emoji;
    }
}
