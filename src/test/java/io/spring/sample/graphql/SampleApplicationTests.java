package io.spring.sample.graphql;

import io.spring.sample.graphql.common.ResolvedGlobalId;
import io.spring.sample.graphql.player.dto.Player;
import io.spring.sample.graphql.player.dto.PlayerPosition;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.graphql.boot.test.tester.AutoConfigureWebGraphQlTester;
import org.springframework.graphql.test.tester.WebGraphQlTester;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureWebGraphQlTester
class SampleApplicationTests {

	@Autowired
	private WebGraphQlTester graphQlTester;

	@Test
	void should_throw_validation_exception_if_name_is_empty_on_create_player() {
		graphQlTester.queryName("createPlayer")
				.variable("name", "")
				.variable("surname", "Uysal")
				.variable("position", "CENTER")
				.execute()
				.errors()
				.satisfy(graphQLErrors -> {
					assertThat(graphQLErrors.size()).isEqualTo(1);
					assertThat(graphQLErrors.get(0).getMessage()).contains("playerCreate.createPlayerInput.name");
				});
	}

	@Test
	void should_throw_validation_exception_if_surname_is_empty_on_create_player() {
		graphQlTester.queryName("createPlayer")
				.variable("name", "Ahmet")
				.variable("surname", "")
				.variable("position", "CENTER")
				.execute()
				.errors()
				.satisfy(graphQLErrors -> {
					assertThat(graphQLErrors.size()).isEqualTo(1);
					assertThat(graphQLErrors.get(0).getMessage()).contains("playerCreate.createPlayerInput.surname");
				});
	}


	@Test
	void should_create_player() {
		graphQlTester.queryName("createPlayer")
				.variable("name", "Ahmet")
				.variable("surname", "Uysal")
				.variable("position", "CENTER")
				.execute()
				.path("playerCreate.player")
				.entity(Player.class).satisfies(player -> {
					assertThat(player.getName()).isEqualTo("Ahmet");
					assertThat(player.getSurname()).isEqualTo("Uysal");
					assertThat(player.getPosition()).isEqualTo(PlayerPosition.CENTER);
					assertThat(ResolvedGlobalId.fromGlobalId(player.getId()).getType()).isEqualTo("Player");
				});
	}



}