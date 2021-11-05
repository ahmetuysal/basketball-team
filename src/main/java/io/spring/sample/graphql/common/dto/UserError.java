package io.spring.sample.graphql.common.dto;

public class UserError {
    private String message;
    private Iterable<String> field;

    public UserError(String message, Iterable<String> field) {
        this.message = message;
        this.field = field;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Iterable<String> getField() {
        return field;
    }

    public void setField(Iterable<String> field) {
        this.field = field;
    }
}
