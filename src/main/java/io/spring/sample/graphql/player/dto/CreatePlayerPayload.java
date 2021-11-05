package io.spring.sample.graphql.player.dto;

import io.spring.sample.graphql.common.dto.UserError;

public class CreatePlayerPayload {
    private Player player;
    private Iterable<UserError> userErrors;

    public CreatePlayerPayload(Player player, Iterable<UserError> userErrors) {
        this.player = player;
        this.userErrors = userErrors;
    }
}
