package io.spring.sample.graphql.player;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import io.spring.sample.graphql.player.dto.CreatePlayerInput;
import io.spring.sample.graphql.player.dto.CreatePlayerPayload;
import io.spring.sample.graphql.player.dto.Player;
import io.spring.sample.graphql.player.exceptions.TeamCapacityIsFullException;
import io.spring.sample.graphql.player.repository.PlayerEntity;
import io.spring.sample.graphql.player.repository.PlayerRepository;
import org.springframework.stereotype.Component;

@Component
public class PlayerService {

	private static final long MAX_PLAYER_COUNT = 2L;

	private final PlayerRepository playerRepository;

	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public Iterable<Player> getAllPlayers() {
		return StreamSupport.stream(playerRepository.findAll().spliterator(), true)
				.map(Player::new)
				.collect(Collectors.toList());
	}

	public CreatePlayerPayload createPlayer(CreatePlayerInput input) {
		long playerCount = playerRepository.count();
		if (playerCount >= MAX_PLAYER_COUNT) {
			throw new TeamCapacityIsFullException(MAX_PLAYER_COUNT);
		}

		PlayerEntity newPlayer = new PlayerEntity();
		newPlayer.setName(input.getName());
		newPlayer.setSurname(input.getSurname());
		newPlayer.setPosition(input.getPosition());

		playerRepository.save(newPlayer);

		return new CreatePlayerPayload(new Player(newPlayer), null);
	}

}
