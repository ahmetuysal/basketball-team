package io.spring.sample.graphql.common.exceptions;

public class NotFoundException extends RuntimeException {
    private final String type;
    private final String id;

    public NotFoundException(String type, String id) {
        super("No " + type + " with id " + id + " exists.");
        this.type = type;
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public String getId() {
        return id;
    }
}