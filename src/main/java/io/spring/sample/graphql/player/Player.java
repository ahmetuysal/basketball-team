package io.spring.sample.graphql.player;

public class Player {
    private String id;
    private String name;
    private String surname;
    private PlayerPosition position;

    public Player(String id, String name, String surname, PlayerPosition position) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.position = position;
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
