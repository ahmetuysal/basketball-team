package io.spring.sample.graphql.player.repository;

import io.spring.sample.graphql.player.dto.PlayerPosition;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.stream.Stream;
import static org.assertj.core.api.BDDAssertions.then;

@ExtendWith(MockitoExtension.class)
class PlayerPositionConverterTest {
    @InjectMocks
    private PlayerPositionConverter playerPositionConverter;

    private static Stream<Arguments> provideEnumToStringArguments() {
        return Stream.of(
                Arguments.of(PlayerPosition.POINT_GUARD, "PG"),
                Arguments.of(PlayerPosition.SHOOTING_GUARD, "SG"),
                Arguments.of(PlayerPosition.SMALL_FORWARD, "SF"),
                Arguments.of(PlayerPosition.POWER_FORWARD, "PF"),
                Arguments.of(PlayerPosition.CENTER, "C")
                );
    }

    @ParameterizedTest
    @MethodSource("provideEnumToStringArguments")
    void should_convert_to_correct_database_column(PlayerPosition position, String correspondingValue) {
        // given

        // when
        String databaseColumn = playerPositionConverter.convertToDatabaseColumn(position);

        // then
        then(databaseColumn).isEqualTo(correspondingValue);
    }

    private static Stream<Arguments> provideStringToEnumArguments() {
        return Stream.of(
                Arguments.of("PG", PlayerPosition.POINT_GUARD),
                Arguments.of("SG", PlayerPosition.SHOOTING_GUARD),
                Arguments.of("SF", PlayerPosition.SMALL_FORWARD),
                Arguments.of("PF", PlayerPosition.POWER_FORWARD),
                Arguments.of("C", PlayerPosition.CENTER)
        );
    }

    @ParameterizedTest
    @MethodSource("provideStringToEnumArguments")
    void should_convert_to_correct_enum_value(String databaseColumn, PlayerPosition correspondingPosition) {
        // given

        // when
        PlayerPosition playerPosition = playerPositionConverter.convertToEntityAttribute(databaseColumn);

        // then
        then(playerPosition).isEqualTo(correspondingPosition);
    }
}