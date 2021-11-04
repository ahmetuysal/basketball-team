package io.spring.sample.graphql.player;

import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.graphql.data.GraphQlRepository;

@GraphQlRepository
public interface PlayerRepository extends CrudRepository<PlayerEntity, Integer>,
        QuerydslPredicateExecutor<PlayerEntity> {
}
