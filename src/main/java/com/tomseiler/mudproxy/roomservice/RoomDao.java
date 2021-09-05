package com.tomseiler.mudproxy.roomservice;

import org.jdbi.v3.sqlobject.config.RegisterRowMapper;
import org.jdbi.v3.sqlobject.customizer.Bind;
import org.jdbi.v3.sqlobject.statement.SqlQuery;

import java.util.List;

public interface RoomDao {
    @SqlQuery("SELECT * FROM rooms WHERE `Map Number` = :mapNumber AND `Room Number` = :roomNumber")
    @RegisterRowMapper(RoomMapper.class)
    Room getRoom(@Bind("mapNumber") int mapNumber, @Bind("roomNumber") int roomNumber);

    @SqlQuery("SELECT * FROM rooms")
    @RegisterRowMapper(RoomMapper.class)
    List<Room> getAllRooms();

}
