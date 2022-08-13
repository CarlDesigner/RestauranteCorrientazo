package co.carldesigner.development.view.gui.tablemodel;


import co.carldesigner.development.model.EstadoPedido;
import co.carldesigner.development.model.Pedido;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author ROG
 */
public class PedidoTableModel extends AbstractTableModel {

    private List<Pedido> pedidos;

    public PedidoTableModel() {
        setPedidos(new ArrayList<>());
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
        fireTableDataChanged();
    }

    public void addPedido(Pedido pedido) {
        this.pedidos.add(pedido);
        var row = getRowCount() - 1;
        fireTableRowsInserted(row, row);
    }

    public Pedido getPedido(Integer row) {
        return this.pedidos.get(row);
    }

    public void setPedido(Integer row, Pedido pedido) {
        this.pedidos.set(row, pedido);
        fireTableRowsUpdated(row, row);
    }

    public Pedido deletePedido(Integer row) {
        var pedido = this.pedidos.remove(row.intValue());
        fireTableRowsDeleted(row, row);
        return pedido;
    }

    @Override
    public int getRowCount() {
        return pedidos.size();
    }

    @Override
    public int getColumnCount() {
        return 2;
    }

    @Override
    public Object getValueAt(int row, int column) {
        var pedido = pedidos.get(row);
        switch (column) {
            case 0: // Cliente
                return pedido.getCliente();
            case 1: // Entregado
                return pedido.getEstado() == EstadoPedido.PENDIENTE_COBRAR;
            default:
                throw new AssertionError();
        }
    }

    @Override
    public String getColumnName(int column) {
        switch (column) {
            case 0: // Cliente
                return "Cliente";
            case 1: // Entregado
                return "Entregado";
            default:
                throw new AssertionError();
        }
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch (columnIndex) {
            case 0: // Cliente
                return String.class;
            case 1: // Entregado
                return Boolean.class;
            default:
                throw new AssertionError();
        }
    }
    
    

}