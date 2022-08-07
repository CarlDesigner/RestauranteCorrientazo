package co.carldesigner.development.model;

import java.util.ArrayList;
import java.util.List;

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

    public void adicionarPedido(Pedido pedido) {
        this.pedidos.add(pedido);
    }

    public Integer calcularValorPagar() {
        return 0;
    }

    public Integer pagar(Integer efectivo) {
        return 0;
    }
}