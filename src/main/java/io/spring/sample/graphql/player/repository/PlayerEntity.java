package io.spring.sample.graphql.player.repository;

import io.spring.sample.graphql.player.dto.PlayerPosition;

import javax.persistence.*;
import java.util.Objects;

@Entity(name = "player")
public class PlayerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;

    private String surname;

    @Convert(converter = PlayerPositionConverter.class)
    private PlayerPosition position;

    public PlayerEntity() {
    }

    public PlayerEntity(String name, String surname, PlayerPosition position) {
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public PlayerEntity(int id, String name, String surname, PlayerPosition position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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
        PlayerEntity that = (PlayerEntity) o;
        return id == that.id && name.equals(that.name) && surname.equals(that.surname) && position == that.position;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, surname, position);
    }
}
