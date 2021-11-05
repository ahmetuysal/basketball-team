package io.spring.sample.graphql.player;

import io.spring.sample.graphql.player.dto.CreatePlayerInput;
import io.spring.sample.graphql.player.dto.CreatePlayerPayload;
import io.spring.sample.graphql.player.dto.DeletePlayerPayload;
import io.spring.sample.graphql.player.dto.Player;
import org.springframework.graphql.data.method.annotation.Argument;
import org.springframework.graphql.data.method.annotation.MutationMapping;
import org.springframework.graphql.data.method.annotation.QueryMapping;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import javax.validation.Valid;


@Validated
@Controller
public class PlayerController {
    private final PlayerService playerService;

    public PlayerController(PlayerService playerService) {
        this.playerService = playerService;
    }

    @QueryMapping
    public Iterable<Player> players() {
        return this.playerService.getAllPlayers();
    }

    @QueryMapping
    public Player player(@Argument("id") String id) {
        return this.playerService.getPlayer(id);
    }

    @MutationMapping
    public CreatePlayerPayload playerCreate(@Valid @Argument("input") CreatePlayerInput createPlayerInput) {
        return this.playerService.createPlayer(createPlayerInput);
    }

    @MutationMapping
    public DeletePlayerPayload playerDelete(@Valid @Argument("id") String id) {
        return this.playerService.deletePlayer(id);
    }

}
