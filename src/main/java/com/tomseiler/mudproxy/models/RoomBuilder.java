package com.tomseiler.mudproxy.models;

import java.util.Set;

public class RoomBuilder {
    private String name;
    private Set<String> exits;

    public RoomBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RoomBuilder exits(Set<String> exits) {
        this.exits = exits;
        return this;
    }

    public Room createRoom() {
        return new Room(name, exits);
    }
}