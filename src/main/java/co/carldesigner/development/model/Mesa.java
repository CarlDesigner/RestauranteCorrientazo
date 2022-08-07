package co.carldesigner.development.model;

import java.util.ArrayList;
import java.util.List;

import co.carldesigner.development.exception.EfectivoInsuficienteException;

public class Mesa {
    private String numero;
    private List<Pedido> pedidos;
    public Mesa(String numero) {
        this.numero = numero;
        pedidos = new ArrayList<>();
    }
    public String getNumero() {
        return numero;
    }
    public List<Pedido> getPedidos() {
        return pedidos;
    }
    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }
    public Integer calcularValorPagar() {
        return pedidos.stream()
                .filter(p -> p.getEstado() == EstadoPedido.PENDIENTE_COBRAR)
                .map(p -> p.calcularTotal())
                .reduce((a, b) -> a + b)
                .orElse(0);
    }

    @Override
    public String toString() {
        return "Mesa # " + numero;
    }

    public void limpiarPedidos() {
        pedidos.clear();
    }
}