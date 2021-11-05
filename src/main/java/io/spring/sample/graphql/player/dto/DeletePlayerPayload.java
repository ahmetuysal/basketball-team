package io.spring.sample.graphql.player.dto;

import io.spring.sample.graphql.common.dto.UserError;

public class DeletePlayerPayload {
    private final Iterable<UserError> userErrors;

    public DeletePlayerPayload(Iterable<UserError> userErrors) {
        this.userErrors = userErrors;
    }

    public Iterable<UserError> getUserErrors() {
        return userErrors;
    }
}
