package io.spring.sample.graphql.player;

import java.util.Optional;
import io.spring.sample.graphql.common.ResolvedGlobalId;
import io.spring.sample.graphql.common.exceptions.NotFoundException;
import io.spring.sample.graphql.player.dto.CreatePlayerInput;
import io.spring.sample.graphql.player.dto.CreatePlayerPayload;
import io.spring.sample.graphql.player.dto.Player;
import io.spring.sample.graphql.player.dto.PlayerPosition;
import io.spring.sample.graphql.player.exceptions.TeamCapacityIsFullException;
import io.spring.sample.graphql.player.repository.PlayerEntity;
import io.spring.sample.graphql.player.repository.PlayerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;

import static org.assertj.core.api.BDDAssertions.then;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PlayerServiceTest {
    @InjectMocks
    private PlayerService playerService;

    @Mock
    private PlayerRepository playerRepository;

    @Value( "${team.max-size}" )
    private long maxTeamSize;

    @Test
    void it_should_not_accept_global_id_of_different_type_on_get_player() {
        // given
        String invalidGlobalId = ResolvedGlobalId.toGlobalId("User", "1");

        // when
        assertThrows(NotFoundException.class, () ->
                playerService.getPlayer(invalidGlobalId));

        // then
        Mockito.verifyNoInteractions(playerRepository);
    }

    @Test
    void it_should_throw_not_found_if_player_with_id_does_not_exist() {
        // given
        int userId = 2;
        String userOpaqueId = ResolvedGlobalId.toGlobalId("Player", String.valueOf(userId));
        given(playerRepository.findById(userId)).willReturn(Optional.empty());

        // when
        assertThrows(NotFoundException.class, () ->
                playerService.getPlayer(userOpaqueId));

        // then
        Mockito.verify(playerRepository,  Mockito.times(1))
                .findById(userId);
    }

    @Test
    void it_should_throw_team_capacity_is_full_exception_if_team_is_full() {
        // given
        String name = "Shaquille";
        String surname = "O'Neal";
        PlayerPosition position = PlayerPosition.CENTER;
        CreatePlayerInput input = new CreatePlayerInput(name, surname, position);
        given(playerRepository.count()).willReturn(maxTeamSize);

        // when
        assertThrows(TeamCapacityIsFullException.class, () ->
                 playerService.createPlayer(input));

        // then
        Mockito.verify(playerRepository, Mockito.never())
                .save(any());
    }

    @Test
    void it_should_create_player_if_team_is_not_full() {
        // given
        String name = "Shaquille";
        String surname = "O'Neal";
        PlayerPosition position = PlayerPosition.CENTER;
        CreatePlayerInput input = new CreatePlayerInput(name, surname, position);

        PlayerEntity newPlayerEntity = new PlayerEntity(name, surname, position);

        given(playerRepository.count()).willReturn(maxTeamSize - 1);


        // when
        CreatePlayerPayload payload = playerService.createPlayer(input);

        // then
        then(payload.getUserErrors()).isNull();
        then(payload.getPlayer()).isEqualTo(new Player(newPlayerEntity));

        Mockito.verify(playerRepository, Mockito.times(1))
                .save(new PlayerEntity(input.getName(),
                        input.getSurname(), input.getPosition()));
    }
}