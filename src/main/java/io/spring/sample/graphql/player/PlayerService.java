package io.spring.sample.graphql.player;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import io.spring.sample.graphql.common.ResolvedGlobalId;
import io.spring.sample.graphql.common.exceptions.NotFoundException;
import io.spring.sample.graphql.player.dto.CreatePlayerInput;
import io.spring.sample.graphql.player.dto.CreatePlayerPayload;
import io.spring.sample.graphql.player.dto.DeletePlayerPayload;
import io.spring.sample.graphql.player.dto.Player;
import io.spring.sample.graphql.player.exceptions.TeamCapacityIsFullException;
import io.spring.sample.graphql.player.repository.PlayerEntity;
import io.spring.sample.graphql.player.repository.PlayerRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {

    @Value("${team.max-size}")
    private long maxTeamSize;

    private final PlayerRepository playerRepository;

    public PlayerService(PlayerRepository playerRepository) {
        this.playerRepository = playerRepository;
    }

    public Player getPlayer(String playerOpaqueId) {
        int playerDatabaseId = getDatabaseIdFromOpaqueId(playerOpaqueId);

        Optional<PlayerEntity> playerEntity = playerRepository.findById(playerDatabaseId);

        if (playerEntity.isEmpty()) {
            throw new NotFoundException("Player", playerOpaqueId);
        }

        return new Player(playerEntity.get());
    }

    public Iterable<Player> getAllPlayers() {
        return StreamSupport.stream(playerRepository.findAll().spliterator(), true)
                .map(Player::new)
                .collect(Collectors.toList());
    }

    public CreatePlayerPayload createPlayer(CreatePlayerInput input) {
        long playerCount = playerRepository.count();
        if (playerCount >= maxTeamSize) {
            throw new TeamCapacityIsFullException(maxTeamSize);
        }

        PlayerEntity newPlayer = new PlayerEntity(input.getName(),
                input.getSurname(), input.getPosition());

        playerRepository.save(newPlayer);

        return new CreatePlayerPayload(new Player(newPlayer), null);
    }

    public DeletePlayerPayload deletePlayer(String playerOpaqueId) {
        int playerDatabaseId = getDatabaseIdFromOpaqueId(playerOpaqueId);
        try {
            playerRepository.deleteById(playerDatabaseId);
        } catch (Exception e) {
            if (e instanceof DataAccessException) {
                throw new NotFoundException("Player", playerOpaqueId);
            } else throw e;
        }

        return new DeletePlayerPayload(null);
    }


    private static int getDatabaseIdFromOpaqueId(String playerOpaqueId) {
        try {
            ResolvedGlobalId resolvedGlobalId = ResolvedGlobalId.fromGlobalId(playerOpaqueId);
            if (!resolvedGlobalId.getType().equals("Player")) {
                throw new NotFoundException("Player", playerOpaqueId);
            }
            return Integer.parseInt(resolvedGlobalId.getId());
        } catch (IllegalArgumentException e) {
            throw new NotFoundException("Player", playerOpaqueId);
        }
    }
}
