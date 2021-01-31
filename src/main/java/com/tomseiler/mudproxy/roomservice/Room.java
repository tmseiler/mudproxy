package com.tomseiler.mudproxy.roomservice;

import org.jdbi.v3.core.mapper.reflect.ColumnName;

public class Room {
    private int mapNumber;
    private int roomNumber;
    private String name;
    private int shop;

    @Override
    public String toString() {
        return "Room{" +
                "mapNumber=" + mapNumber +
                ", roomNumber=" + roomNumber +
                ", name='" + name + '\'' +
                '}';
    }

    private int npc;
    private int cmd;
    private int spell;
    private String lair;
    private String placed;
    private String n;
    private String s;
    private String e;
    private String w;
    private String ne;
    private String nw;
    private String se;
    private String sw;
    private String u;
    private String d;

    public Room() {
    }

    public Room(int mapNumber, int roomNumber, String name, int shop, int npc, int cmd, int spell, String lair, String placed, String n, String s, String e, String w, String ne, String nw, String se, String sw, String u, String d) {
        this.mapNumber = mapNumber;
        this.roomNumber = roomNumber;
        this.name = name;
        this.shop = shop;
        this.npc = npc;
        this.cmd = cmd;
        this.spell = spell;
        this.lair = lair;
        this.placed = placed;
        this.n = n;
        this.s = s;
        this.e = e;
        this.w = w;
        this.ne = ne;
        this.nw = nw;
        this.se = se;
        this.sw = sw;
        this.u = u;
        this.d = d;
    }

    public int getMapNumber() {
        return mapNumber;
    }

    public void setMapNumber(int mapNumber) {
        this.mapNumber = mapNumber;
    }

    public int getRoomNumber() {
        return roomNumber;
    }

    public void setRoomNumber(int roomNumber) {
        this.roomNumber = roomNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getShop() {
        return shop;
    }

    public void setShop(int shop) {
        this.shop = shop;
    }

    public int getNpc() {
        return npc;
    }

    public void setNpc(int npc) {
        this.npc = npc;
    }

    public int getCmd() {
        return cmd;
    }

    public void setCmd(int cmd) {
        this.cmd = cmd;
    }

    public int getSpell() {
        return spell;
    }

    public void setSpell(int spell) {
        this.spell = spell;
    }

    public String getLair() {
        return lair;
    }

    public void setLair(String lair) {
        this.lair = lair;
    }

    public String getPlaced() {
        return placed;
    }

    public void setPlaced(String placed) {
        this.placed = placed;
    }

    public String getN() {
        return n;
    }

    public void setN(String n) {
        this.n = n;
    }

    public String getS() {
        return s;
    }

    public void setS(String s) {
        this.s = s;
    }

    public String getE() {
        return e;
    }

    public void setE(String e) {
        this.e = e;
    }

    public String getW() {
        return w;
    }

    public void setW(String w) {
        this.w = w;
    }

    public String getNe() {
        return ne;
    }

    public void setNe(String ne) {
        this.ne = ne;
    }

    public String getNw() {
        return nw;
    }

    public void setNw(String nw) {
        this.nw = nw;
    }

    public String getSe() {
        return se;
    }

    public void setSe(String se) {
        this.se = se;
    }

    public String getSw() {
        return sw;
    }

    public void setSw(String sw) {
        this.sw = sw;
    }

    public String getU() {
        return u;
    }

    public void setU(String u) {
        this.u = u;
    }

    public String getD() {
        return d;
    }

    public void setD(String d) {
        this.d = d;
    }
}
