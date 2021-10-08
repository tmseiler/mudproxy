package com.tomseiler.mudproxy.models;

public class PlayerExp {
    private final int currentTotal;
    private final int level;
    private final int needed;
    private final int nextLevelTotal;
    private final int percentage;

    public PlayerExp(int currentTotal, int level, int needed, int nextLevelTotal, int percentage) {
        this.currentTotal = currentTotal;
        this.level = level;
        this.needed = needed;
        this.nextLevelTotal = nextLevelTotal;
        this.percentage = percentage;
    }

    public int getCurrentTotal() {
        return currentTotal;
    }

    public int getLevel() {
        return level;
    }

    public int getNeeded() {
        return needed;
    }

    public int getNextLevelTotal() {
        return nextLevelTotal;
    }

    public int getPercentage() {
        return percentage;
    }

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
}
