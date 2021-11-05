package io.spring.sample.graphql.player.dto;

import io.spring.sample.graphql.common.ResolvedGlobalId;
import io.spring.sample.graphql.player.repository.PlayerEntity;

public class Player {
    private String id;
    private String name;
    private String surname;
    private PlayerPosition position;

    public Player(PlayerEntity entity) {
        this.id = ResolvedGlobalId.toGlobalId("Player", String.valueOf(entity.getId()));
        this.name = entity.getName();
        this.surname = entity.getSurname();
        this.position = entity.getPosition();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }
}
