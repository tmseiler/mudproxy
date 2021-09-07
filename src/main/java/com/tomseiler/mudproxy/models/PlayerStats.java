package com.tomseiler.mudproxy.models;

public class PlayerStats {
    private String firstName;
    private String lastName;

    private int lives;
    private int cp;

    // todo enum
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


    public PlayerStats(String firstName, String lastName, int lives, int cp, String playerClass, int exp, int level, int currentHits, int maxHits, int currentMana, int maxMana, int ac, int dr, int sc, int strength, int intellect, int willpower, int agility, int health, int charm, int perception, int stealth, int thievery, int traps, int picklocks, int tracking, int martialArts, int magicRes) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.lives = lives;
        this.cp = cp;
        this.playerClass = playerClass;
        this.exp = exp;
        this.level = level;
        this.currentHits = currentHits;
        this.maxHits = maxHits;
        this.currentMana = currentMana;
        this.maxMana = maxMana;
        this.ac = ac;
        this.dr = dr;
        this.sc = sc;
        this.strength = strength;
        this.intellect = intellect;
        this.willpower = willpower;
        this.agility = agility;
        this.health = health;
        this.charm = charm;
        this.perception = perception;
        this.stealth = stealth;
        this.thievery = thievery;
        this.traps = traps;
        this.picklocks = picklocks;
        this.tracking = tracking;
        this.martialArts = martialArts;
        this.magicRes = magicRes;
    }
}
