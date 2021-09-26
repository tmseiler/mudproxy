package com.tomseiler.mudproxy.models;

import java.util.List;
import java.util.Set;

public class Room {
    private String name;
    private List<String> occupants; // TODO model
    private List<String> contents; // TODO model
    private Set<String> exits;

    public Room(String name, List<String> occupants, List<String> contents, Set<String> exits) {
        this.name = name;
        this.occupants = occupants;
        this.contents = contents;
        this.exits = exits;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Room{" +
                "name='" + name + '\'' +
                ", occupants=" + occupants +
                ", contents=" + contents +
                ", exits=" + exits +
                '}';
    }

    public List<String> getOccupants() {
        return occupants;
    }

    public List<String> getContents() {
        return contents;
    }

    public Set<String> getExits() {
        return exits;
    }
}
