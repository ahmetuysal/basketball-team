package io.spring.sample.graphql.common;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.BDDAssertions.then;

class ResolvedGlobalIdTest {

    @Test
    void it_should_create_global_id_as_base64_encoded_type_plus_id() {
        // given
        String type = "Player";
        String id = String.valueOf(13);

        // when
        String globalId = ResolvedGlobalId.toGlobalId(type, id);

        // then
        then(globalId).isEqualTo("UGxheWVyOjEz"); // base 64 "Player:13"
    }

    @Test
    void it_should_throw_IllegalArgumentException_on_invalid_global_id() {
        // given
        String invalidGlobalId = "invalid";
        // when

        // then
        assertThrows(IllegalArgumentException.class, () ->
           ResolvedGlobalId.fromGlobalId(invalidGlobalId));
    }

    @Test
    void it_should_return_type_and_id_on_valid_global_id() {
        // given
        String globalId = "VXNlcjpjamxkMmNqeGgwMDAwcXpybW44MzFpN3Ju";
        String type = "User";
        String id = "cjld2cjxh0000qzrmn831i7rn";

        // when
        ResolvedGlobalId resolvedGlobalId = ResolvedGlobalId.fromGlobalId(globalId);

        // then
        then(resolvedGlobalId.getType()).isEqualTo(type);
        then(resolvedGlobalId.getId()).isEqualTo(id);
    }
}