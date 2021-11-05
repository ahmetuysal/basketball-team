package io.spring.sample.graphql.player.repository;

import io.spring.sample.graphql.player.dto.PlayerPosition;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter
public class PlayerPositionConverter implements AttributeConverter<PlayerPosition, String> {

    @Override
    public String convertToDatabaseColumn(PlayerPosition playerPosition) {
        switch (playerPosition) {
            case POINT_GUARD:
                return "PG";
            case SHOOTING_GUARD:
                return "SG";
            case SMALL_FORWARD:
                return "SF";
            case POWER_FORWARD:
                return "PF";
            case CENTER:
                return "C";
        }
        return null;
    }

    @Override
    public PlayerPosition convertToEntityAttribute(String s) {
        switch (s) {
            case "PG":
                return PlayerPosition.POINT_GUARD;
            case "SG":
                return PlayerPosition.SHOOTING_GUARD;
            case "SF":
                return PlayerPosition.SMALL_FORWARD;
            case "PF":
                return PlayerPosition.POWER_FORWARD;
            case "C":
                return PlayerPosition.CENTER;
        }
        return null;
    }
}
