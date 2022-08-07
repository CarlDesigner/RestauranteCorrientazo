package co.carldesigner.development.model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;


import co.carldesigner.development.model.Bandeja;
import co.carldesigner.development.model.Completo;
import co.carldesigner.development.model.EstadoPedido;
import co.carldesigner.development.model.Mesa;
import co.carldesigner.development.model.OpcionCarne;
import co.carldesigner.development.model.OpcionEnsalada;
import co.carldesigner.development.model.OpcionJugo;
import co.carldesigner.development.model.OpcionPedido;
import co.carldesigner.development.model.OpcionPrincipio;
import co.carldesigner.development.model.OpcionSopa;
import co.carldesigner.development.model.Pedido;
import co.carldesigner.development.util.JDBCUtilities;
public class PedidoDao {
    public void adicionarPedidoMesa(Mesa mesa, Pedido pedido) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt1 = null;
        Statement stmt2 = null;
        try {
            pedido.setId(generarConsecutivo());
            conn = JDBCUtilities.getConnection();
            var sql = "INSERT INTO Pedido (id, cliente, estado, id_mesa)"
                    + " VALUES (?, ?, ?, ?)";
            stmt1 = conn.prepareStatement(sql);
            stmt1.setInt(1, pedido.getId());
            stmt1.setString(2, pedido.getCliente());
            stmt1.setString(3, pedido.getEstado().toString());
            stmt1.setInt(4, mesa.getId());
            stmt1.executeUpdate();
            stmt2 = conn.createStatement();
            sql = "INSERT INTO OpcionPedido (id_pedido, precio, tipo, id_sopa, id_carne, id_principio, id_ensalada, id_jugo)";
            sql += " VALUES (";
            sql += pedido.getId() + ", ";
            sql += pedido.getOpcion().getPrecio() + ", ";
            if (pedido.getOpcion() instanceof Completo) {
                sql += "'Completo', " + ((Completo) pedido.getOpcion()).getSopa().getId() + ", ";
            } else {
                sql += "'Bandeja', NULL, ";
            }
            sql += pedido.getOpcion().getCarne().getId() + ", ";
            sql += pedido.getOpcion().getPrincipio().getId() + ", ";
            if (pedido.getOpcion().getEnsalada() != null) {
                sql += pedido.getOpcion().getEnsalada().getId() + ", ";
            } else {
                sql += "NULL, ";
            }
            sql += pedido.getOpcion().getJugo().getId() + ");";
            stmt2.executeUpdate(sql);
        } finally {
            if (stmt2 != null) {
                stmt2.close();
            }
            if (stmt1 != null) {
                stmt1.close();
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
            stmt = conn.prepareStatement("SELECT MAX(id) AS id FROM Pedido;");
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
    public List<Pedido> listar(Mesa mesa) throws SQLException {
        List<Pedido> respuesta = new ArrayList<>();
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rset = null;
        try {
            conn = JDBCUtilities.getConnection();
            var sql = "select p.id, p.cliente, p.estado, op.precio, op.tipo, op.id_sopa, os.nombre as sopa,"
                    + "       op.id_principio, op2.nombre as principio, op.id_carne, oc.nombre as carne, op.id_ensalada, oe.nombre as ensalada,"
                    + "       op.id_jugo, oj.nombre as jugo "
                    + " from Pedido p"
                    + " join OpcionPedido op ON (p.id = op.id_pedido)"
                    + " join OpcionCarne oc on (op.id_carne = oc.id)"
                    + " join OpcionPrincipio op2 on (op.id_principio = op2.id)"
                    + " join OpcionJugo oj on (op.id_jugo = oj.id)"
                    + " left join OpcionSopa os on (op.id_sopa = os.id)"
                    + " left join OpcionEnsalada oe on (op.id_ensalada = oe.id)"
                    + " where id_mesa = ?;";
            stmt = conn.prepareStatement(sql);
            stmt.setInt(1, mesa.getId());
            rset = stmt.executeQuery();
            while (rset.next()) {
                var pedido = new Pedido(rset.getString("cliente"));
                pedido.setId(rset.getInt("id"));
                pedido.setEstado(EstadoPedido.valueOf(rset.getString("estado")));
                OpcionPedido opcion = null;
                if (rset.getString("tipo").equalsIgnoreCase("Completo")) {
                    var op = new Completo(rset.getInt("precio"));
                    var sopa = new OpcionSopa(rset.getString("sopa"));
                    sopa.setId(rset.getInt("id_sopa"));
                    op.setSopa(sopa);
                    opcion = op;
                } else {
                    opcion = new Bandeja(rset.getInt("precio"));
                }
                var principio = new OpcionPrincipio(rset.getString("principio"));
                principio.setId(rset.getInt("id_principio"));
                opcion.setPrincipio(principio);
                var carne = new OpcionCarne(rset.getString("carne"));
                carne.setId(rset.getInt("id_carne"));
                opcion.setCarne(carne);
                if (rset.getString("ensalada") != null) {
                    var ensalada = new OpcionEnsalada(rset.getString("ensalada"));
                    ensalada.setId(rset.getInt("id_ensalada"));
                    opcion.setEnsalada(ensalada);
                }
                var jugo = new OpcionJugo(rset.getString("jugo"));
                jugo.setId(rset.getInt("id_jugo"));
                opcion.setJugo(jugo);
                pedido.setOpcion(opcion);
                respuesta.add(pedido);
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
    public void entregarPedido(Pedido pedido) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        try {
            conn = JDBCUtilities.getConnection();
            stmt = conn.prepareStatement("UPDATE Pedido SET estado = ? WHERE id = ?;");
            stmt.setString(1, pedido.getEstado().toString());
            stmt.setInt(2, pedido.getId());
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

    public void eliminarPedidosDeMesa(Mesa mesa) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt1 = null;
        PreparedStatement stmt2 = null;
        try {
            conn = JDBCUtilities.getConnection();
            var sql = "DELETE FROM OpcionPedido"
                    + " WHERE id_pedido IN ("
                    + "    SELECT id"
                    + "    FROM Pedido"
                    + "    WHERE id_mesa = ?"
                    + ");";
            stmt1 = conn.prepareStatement(sql);
            stmt1.setInt(1, mesa.getId());
            stmt1.executeUpdate();

            sql = "DELETE FROM Pedido"
                    + " WHERE id_mesa = ?;";
            stmt2 = conn.prepareStatement(sql);
            stmt2.setInt(1, mesa.getId());
            stmt2.executeUpdate();

        } finally {
            if (stmt2 != null) {
                stmt2.close();
            }
            if (stmt1 != null) {
                stmt1.close();
            }
            if (conn != null) {
                conn.close();
            }
        }
    }

}