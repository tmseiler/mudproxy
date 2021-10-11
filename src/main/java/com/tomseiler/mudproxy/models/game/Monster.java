package com.tomseiler.mudproxy.models.game;

public class Monster {
    private final int number;
    private final String name;
    private final int armourClass;
    private final int damageResist;
    private final int hp;
    private final int exp;
    private final int expMulti;
    private final int magicRes;
    public Monster(int number,
                   String name,
                   int armourClass,
                   int damageResist,
                   int hp,
                   int exp,
                   int expMulti,
                   int magicRes) {
        this.number = number;
        this.name = name;
        this.armourClass = armourClass;
        this.damageResist = damageResist;
        this.hp = hp;
        this.exp = exp;
        this.expMulti = expMulti;
        this.magicRes = magicRes;
    }

    @Override
    public String toString() {
        return "Monster{" +
                "number=" + number +
                ", name='" + name + '\'' +
                ", armourClass=" + armourClass +
                ", damageResist=" + damageResist +
                ", hp=" + hp +
                ", exp=" + exp +
                ", expMulti=" + expMulti +
                ", magicRes=" + magicRes +
                '}';
    }
}
