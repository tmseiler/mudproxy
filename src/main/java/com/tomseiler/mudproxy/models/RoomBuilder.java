package com.tomseiler.mudproxy.models;

import java.util.List;
import java.util.Set;

public class RoomBuilder {
    private String name;
    private List<String> occupants;
    private List<String> contents;
    private Set<String> exits;

    public RoomBuilder name(String name) {
        this.name = name;
        return this;
    }

    public RoomBuilder occupants(List<String> occupants) {
        this.occupants = occupants;
        return this;
    }

    public RoomBuilder contents(List<String> contents) {
        this.contents = contents;
        return this;
    }

    public RoomBuilder exits(Set<String> exits) {
        this.exits = exits;
        return this;
    }

    public Room createRoom() {
        return new Room(name, occupants, contents, exits);
    }
}