package co.carldesigner.development.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;

import co.carldesigner.development.util.JDBCUtilities;

public class OpcionAlimentoDao<T> {

    private String tabla;

    public OpcionAlimentoDao(String tabla) {
        this.tabla = tabla;
    }

    public List<T> listar(Function<ResultSet, T> mapper) throws SQLException {
        var respuesta = new ArrayList<T>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            var sql = String.format("SELECT * FROM %s;", tabla);
            conn = JDBCUtilities.getConnection();
            stmt = conn.prepareStatement(sql);
            rset = stmt.executeQuery();
            while (rset.next()) {
                var opcion = mapper.apply(rset);

                respuesta.add(opcion);
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