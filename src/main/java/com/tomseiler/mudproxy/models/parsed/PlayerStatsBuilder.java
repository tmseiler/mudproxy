package com.tomseiler.mudproxy.models.parsed;

public class PlayerStatsBuilder {
    private String firstName;
    private String lastName;
    private int lives;
    private int cp;
    private String race;
    private String playerClass;
    private int exp;
    private int level;
    private int currentHits;
    private int maxHits;
    private int currentMana;
    private int maxMana;
    private int ac;
    private int dr;
    private int sc;
    private int strength;
    private int intellect;
    private int willpower;
    private int agility;
    private int health;
    private int charm;
    private int perception;
    private int stealth;
    private int thievery;
    private int traps;
    private int picklocks;
    private int tracking;
    private int martialArts;
    private int magicRes;

    public PlayerStatsBuilder firstName(String firstName) {
        this.firstName = firstName;
        return this;
    }

    public PlayerStatsBuilder lastName(String lastName) {
        this.lastName = lastName;
        return this;
    }

    public PlayerStatsBuilder lives(int lives) {
        this.lives = lives;
        return this;
    }

    public PlayerStatsBuilder cp(int cp) {
        this.cp = cp;
        return this;
    }

    public PlayerStatsBuilder playerClass(String playerClass) {
        this.playerClass = playerClass;
        return this;
    }

    public PlayerStatsBuilder exp(int exp) {
        this.exp = exp;
        return this;
    }

    public PlayerStatsBuilder level(int level) {
        this.level = level;
        return this;
    }

    public PlayerStatsBuilder currentHits(int currentHits) {
        this.currentHits = currentHits;
        return this;
    }

    public PlayerStatsBuilder maxHits(int maxHits) {
        this.maxHits = maxHits;
        return this;
    }

    public PlayerStatsBuilder currentMana(int currentMana) {
        this.currentMana = currentMana;
        return this;
    }

    public PlayerStatsBuilder maxMana(int maxMana) {
        this.maxMana = maxMana;
        return this;
    }

    public PlayerStatsBuilder ac(int ac) {
        this.ac = ac;
        return this;
    }

    public PlayerStatsBuilder dr(int dr) {
        this.dr = dr;
        return this;
    }

    public PlayerStatsBuilder sc(int sc) {
        this.sc = sc;
        return this;
    }

    public PlayerStatsBuilder strength(int strength) {
        this.strength = strength;
        return this;
    }

    public PlayerStatsBuilder intellect(int intellect) {
        this.intellect = intellect;
        return this;
    }

    public PlayerStatsBuilder willpower(int willpower) {
        this.willpower = willpower;
        return this;
    }

    public PlayerStatsBuilder agility(int agility) {
        this.agility = agility;
        return this;
    }

    public PlayerStatsBuilder health(int health) {
        this.health = health;
        return this;
    }

    public PlayerStatsBuilder charm(int charm) {
        this.charm = charm;
        return this;
    }

    public PlayerStatsBuilder perception(int perception) {
        this.perception = perception;
        return this;
    }

    public PlayerStatsBuilder stealth(int stealth) {
        this.stealth = stealth;
        return this;
    }

    public PlayerStatsBuilder thievery(int thievery) {
        this.thievery = thievery;
        return this;
    }

    public PlayerStatsBuilder traps(int traps) {
        this.traps = traps;
        return this;
    }

    public PlayerStatsBuilder picklocks(int picklocks) {
        this.picklocks = picklocks;
        return this;
    }

    public PlayerStatsBuilder tracking(int tracking) {
        this.tracking = tracking;
        return this;
    }

    public PlayerStatsBuilder martialArts(int martialArts) {
        this.martialArts = martialArts;
        return this;
    }

    public PlayerStatsBuilder magicRes(int magicRes) {
        this.magicRes = magicRes;
        return this;
    }

    public PlayerStatsBuilder race(String race) {
        this.race = race;
        return this;
    }

    public PlayerStats build() {
        return new PlayerStats(firstName, lastName, lives, cp, race, playerClass, exp, level, currentHits, maxHits, currentMana, maxMana, ac, dr, sc, strength, intellect, willpower, agility, health, charm, perception, stealth, thievery, traps, picklocks, tracking, martialArts, magicRes);
    }
}