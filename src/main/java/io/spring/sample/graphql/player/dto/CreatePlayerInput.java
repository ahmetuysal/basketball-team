package io.spring.sample.graphql.player.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

public class CreatePlayerInput {
    @NotBlank
    @Size(max=40)
    private String name;

    @NotBlank
    @Size(max=40)
    private String surname;

    private PlayerPosition position;

    public CreatePlayerInput() {
    }

    public CreatePlayerInput(String name, String surname, PlayerPosition position) {
        this.name = name;
        this.surname = surname;
        this.position = position;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public PlayerPosition getPosition() {
        return position;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public void setPosition(PlayerPosition position) {
        this.position = position;
    }
}
