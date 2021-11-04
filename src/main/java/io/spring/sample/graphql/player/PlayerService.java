package io.spring.sample.graphql.player;

import java.util.Collections;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class PlayerService {

	public List<Player> getAllPlayers() {
		return Collections.singletonList(new Player("1", "Foo", "Bar", PlayerPosition.CENTER));
	}

}
