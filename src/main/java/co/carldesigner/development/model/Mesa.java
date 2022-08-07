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
    public Integer pagar(Integer efectivo) throws EfectivoInsuficienteException {
        // Valido los datos
        var total = calcularValorPagar();
        if (efectivo < total) {
            // Devolver error de fondos insuficientes
            throw new EfectivoInsuficienteException("El valor entregado no cubre el total a pagar");
        }
        // Limpiar pedidos
        pedidos.clear();
        // Retorno la devuelta
        return efectivo - total;
    }

    @Override
    public String toString() {
        return "Mesa # " + numero;
    }
}