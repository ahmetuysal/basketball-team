package io.spring.sample.graphql.player;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.springframework.stereotype.Component;

@Component
public class PlayerService {

	private PlayerRepository playerRepository;

	public PlayerService(PlayerRepository playerRepository) {
		this.playerRepository = playerRepository;
	}

	public Iterable<Player> getAllPlayers() {
		return StreamSupport.stream(playerRepository.findAll().spliterator(), true)
				.map(Player::new)
				.collect(Collectors.toList());
	}

}
