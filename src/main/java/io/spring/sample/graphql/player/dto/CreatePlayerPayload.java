package io.spring.sample.graphql.player.dto;

import io.spring.sample.graphql.common.dto.UserError;

public class CreatePlayerPayload {
    private final Player player;
    private final Iterable<UserError> userErrors;

    public CreatePlayerPayload(Player player, Iterable<UserError> userErrors) {
        this.player = player;
        this.userErrors = userErrors;
    }

    public Player getPlayer() {
        return player;
    }

    public Iterable<UserError> getUserErrors() {
        return userErrors;
    }
}
