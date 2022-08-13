package co.carldesigner.development.controller;

import co.carldesigner.development.exception.EfectivoInsuficienteException;
import co.carldesigner.development.model.EstadoPedido;
import co.carldesigner.development.model.Mesa;
import co.carldesigner.development.model.OpcionCarne;
import co.carldesigner.development.model.OpcionEnsalada;
import co.carldesigner.development.model.OpcionJugo;
import co.carldesigner.development.model.OpcionPrincipio;
import co.carldesigner.development.model.OpcionSopa;
import co.carldesigner.development.model.Pedido;
import co.carldesigner.development.model.dao.MesaDao;
import co.carldesigner.development.model.dao.OpcionAlimentoDao;
import co.carldesigner.development.model.dao.PedidoDao;
import java.sql.SQLException;
import java.util.List;

/**
 *
 * 
 */
public class ResturanteGUIControlador {

    private final MesaDao mesaDao;
    private final PedidoDao pedidoDao;
    private final OpcionAlimentoDao<OpcionSopa> sopaDao;
    private final OpcionAlimentoDao<OpcionPrincipio> principioDao;
    private final OpcionAlimentoDao<OpcionCarne> carneDao;
    private final OpcionAlimentoDao<OpcionEnsalada> ensaladaDao;
    private final OpcionAlimentoDao<OpcionJugo> jugoDao;

    public ResturanteGUIControlador() {
        this.mesaDao = new MesaDao();
        this.pedidoDao = new PedidoDao();
        this.sopaDao = new OpcionAlimentoDao<>("OpcionSopa");
        this.principioDao = new OpcionAlimentoDao<>("OpcionPrincipio");
        this.carneDao = new OpcionAlimentoDao<>("OpcionCarne");
        this.ensaladaDao = new OpcionAlimentoDao<>("OpcionEnsalada");
        this.jugoDao = new OpcionAlimentoDao<>("OpcionJugo");
    }

    public List<Mesa> listarMesas() throws SQLException {
        return mesaDao.listar();
    }

    public Integer calcularTotalMesa(Mesa mesa) throws SQLException {
        var pedidos = pedidoDao.listar(mesa);
        return pedidos.stream()
                .filter(p -> p.getEstado() == EstadoPedido.PENDIENTE_COBRAR)
                .map(p -> p.calcularTotal())
                .reduce((a, b) -> a + b)
                .orElse(0);
    }

    public List<Pedido> listarPedidosMesa(Mesa mesa) throws SQLException {
        return pedidoDao.listar(mesa);
    }

    public void entregarPedido(Pedido pedido) throws SQLException {
        pedido.entregar();
        pedidoDao.entregarPedido(pedido);
    }

    public List<OpcionJugo> listarJugos() throws SQLException {
        return jugoDao.listar(rset -> {
            try {
                var opcion = new OpcionJugo(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));

                return opcion;
            } catch (SQLException e) {
                return null;
            }
        });
    }

    public List<OpcionEnsalada> listarEnsaladas() throws SQLException {
        return ensaladaDao.listar(rset -> {
            try {
                var opcion = new OpcionEnsalada(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));

                return opcion;
            } catch (SQLException e) {
                return null;
            }
        });
    }

    public List<OpcionCarne> listarCarnes() throws SQLException {
        return carneDao.listar(rset -> {
            try {
                var opcion = new OpcionCarne(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));

                return opcion;
            } catch (SQLException e) {
                return null;
            }
        });
    }

    public List<OpcionPrincipio> listarPrincipios() throws SQLException {
        return principioDao.listar(rset -> {
            try {
                var opcion = new OpcionPrincipio(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));

                return opcion;
            } catch (SQLException e) {
                return null;
            }
        });
    }

    public List<OpcionSopa> listarSopas() throws SQLException {
        return sopaDao.listar(rset -> {
            try {
                var opcion = new OpcionSopa(rset.getString("nombre"));
                opcion.setId(rset.getInt("id"));

                return opcion;
            } catch (SQLException e) {
                return null;
            }
        });
    }

    public void agregarPedidoAMesa(Mesa mesa, Pedido pedido) throws SQLException {
        pedidoDao.adicionarPedidoMesa(mesa, pedido);
    }

    public Integer pagarCuentaMesa(Mesa mesa, Integer efectivo) throws SQLException, EfectivoInsuficienteException {
        var total = calcularTotalMesa(mesa);

        // Valido los datos
        if (efectivo < total) {
            // Devolver error de fondos insuficientes
            throw new EfectivoInsuficienteException("El valor entregado no cubre el total a pagar");
        }

        // Limpiar pedidos
        mesa.limpiarPedidos();
        pedidoDao.eliminarPedidosDeMesa(mesa);

        // Retorno la devuelta
        return efectivo - total;
    }

}
