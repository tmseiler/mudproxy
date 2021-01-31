package com.tomseiler.mudproxy.roomservice;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.BeanMapper;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;

public class RoomService {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/data-v1.11p.mdb");

        Jdbi jdbi = Jdbi.create(conn);

        List<Room> rooms = jdbi.withHandle(handle -> {
            handle.registerRowMapper(BeanMapper.factory(Room.class));
            return handle
                    .createQuery("select * from rooms")
                    .mapTo(Room.class)
                    .list();
        });
        
    }
}
