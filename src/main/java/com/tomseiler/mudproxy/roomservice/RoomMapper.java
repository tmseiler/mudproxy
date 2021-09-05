package com.tomseiler.mudproxy.roomservice;

import org.jdbi.v3.core.mapper.RowMapper;
import org.jdbi.v3.core.statement.StatementContext;

import java.sql.ResultSet;
import java.sql.SQLException;

public class RoomMapper implements RowMapper<Room> {
    @Override
    public Room map(ResultSet rs, StatementContext ctx) throws SQLException {
        return new Room(
                rs.getInt("Room Number"),
                rs.getInt("Map Number"),
                rs.getString("Name"),
                rs.getInt("Shop"),
                rs.getInt("NPC"),
                rs.getInt("CMD"),
                rs.getInt("Spell"),
                rs.getString("Lair"),
                rs.getString("Placed"),
                rs.getString("N"),
                rs.getString("S"),
                rs.getString("E"),
                rs.getString("W"),
                rs.getString("NE"),
                rs.getString("NW"),
                rs.getString("SE"),
                rs.getString("SW"),
                rs.getString("U"),
                rs.getString("D")
        );
    }
}
