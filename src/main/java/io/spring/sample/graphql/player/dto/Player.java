package io.spring.sample.graphql.player.dto;

import io.spring.sample.graphql.common.ResolvedGlobalId;
import io.spring.sample.graphql.player.repository.PlayerEntity;

import java.util.Objects;

public class Player {
    private String id;
    private String name;
    private String surname;
    private PlayerPosition position;

    public Player() {
    }

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Player player = (Player) o;
        return id.equals(player.id) && name.equals(player.name) && surname.equals(player.surname) && position == player.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, position);
    }

    @Override
    public String toString() {
        return "Player{" +
                "id='" + id + '\'' +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", position=" + position +
                '}';
    }
}
