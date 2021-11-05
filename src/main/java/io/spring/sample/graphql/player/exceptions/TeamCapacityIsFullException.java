package io.spring.sample.graphql.player.exceptions;

public class TeamCapacityIsFullException extends RuntimeException {
    private final long capacity;

    public TeamCapacityIsFullException(long capacity) {
        super("Max team capacity of " + capacity + " is exceeded.");
        this.capacity = capacity;
    }

    public long getCapacity() {
        return capacity;
    }
}
