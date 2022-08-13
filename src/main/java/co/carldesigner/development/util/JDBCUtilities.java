package co.carldesigner.development.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtilities {


    // DAO -> Data Access Object -> metodos para gestionar la persistencia de los objetos

    // DTO -> Data Transfer Object
    // VO -> Value Object

    private static final String DATABASE = "corrientazo.db";

    public static Connection getConnection() throws SQLException {
        var url = "jdbc:sqlite:" + DATABASE;
        return DriverManager.getConnection(url);
    }
}