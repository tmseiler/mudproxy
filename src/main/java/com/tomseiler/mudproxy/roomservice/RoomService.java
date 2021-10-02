package com.tomseiler.mudproxy.roomservice;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.sqlobject.SqlObjectPlugin;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class RoomService {
    public static void main(String[] args) throws SQLException {
        Connection conn = DriverManager.getConnection("jdbc:ucanaccess://src/main/resources/data-v1.11p.mdb");

        Jdbi jdbi = Jdbi.create(conn);
        jdbi.installPlugin(new SqlObjectPlugin());

        jdbi.useHandle(handle -> {
            RoomDao roomDao = handle.attach(RoomDao.class);
            Room room = roomDao.getRoom(1, 11);
            System.out.println("room = " + room);
        });
    }
}
