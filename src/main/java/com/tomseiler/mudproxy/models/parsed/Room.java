package com.tomseiler.mudproxy.models.parsed;

import java.util.List;
import java.util.Set;

public class Room {
    private final String name;
    private final Set<String> exits;

    public Room(String name, Set<String> exits) {
        this.name = name;
        this.exits = exits;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", exits=" + exits +
                '}';
    }

    public Set<String> getExits() {
        return exits;
    }
}
