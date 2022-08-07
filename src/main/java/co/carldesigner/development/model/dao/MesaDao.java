package co.carldesigner.development.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import co.carldesigner.development.model.Mesa;
import co.carldesigner.development.util.JDBCUtilities;

public class MesaDao {

    public void guardar(Mesa mesa) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtilities.getConnection();
            stmt = conn.prepareStatement("INSERT INTO Mesa (id, numero) VALUES (?, ?);");
            stmt.setInt(1, generarConsecutivo());
            stmt.setString(2, mesa.getNumero());
            stmt.executeUpdate();
        } finally {
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

    private Integer generarConsecutivo() throws SQLException {
        var respuesta = 0;
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            conn = JDBCUtilities.getConnection();
            stmt = conn.prepareStatement("SELECT MAX(id) AS id FROM Mesa;");
            rset = stmt.executeQuery();
            if (rset.next()) {
                respuesta = rset.getInt("id");
            }
            respuesta++;
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta;
    }

    public List<Mesa> listar() throws SQLException {
        var respuesta = new ArrayList<Mesa>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            conn = JDBCUtilities.getConnection();
            stmt = conn.prepareStatement("SELECT * FROM Mesa;");
            rset = stmt.executeQuery();
            while (rset.next()) {
                var mesa = new Mesa(rset.getString("numero"));
                mesa.setId(rset.getInt("id"));

                respuesta.add(mesa);
            }
        } finally {
            if (rset != null) {
                rset.close();
            }
            if (stmt != null) {
                stmt.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
        return respuesta;
    }

}