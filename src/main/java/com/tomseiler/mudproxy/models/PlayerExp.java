package com.tomseiler.mudproxy.models;

public class PlayerExp {
    private int currentTotal;
    private int level;
    private int needed;
    private int nextLevelTotal;
    private int percentage;

    @Override
    public String toString() {
        return "PlayerExp{" +
                "currentTotal=" + currentTotal +
                ", level=" + level +
                ", needed=" + needed +
                ", nextLevelTotal=" + nextLevelTotal +
                ", percentage=" + percentage +
                '}';
    }

    public PlayerExp(int currentTotal, int level, int needed, int nextLevelTotal, int percentage) {
        this.currentTotal = currentTotal;
        this.level = level;
        this.needed = needed;
        this.nextLevelTotal = nextLevelTotal;
        this.percentage = percentage;
    }
}
