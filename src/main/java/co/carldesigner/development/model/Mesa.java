package co.carldesigner.development.model;

import java.util.ArrayList;
import java.util.List;


public class Mesa {
    private String numero;
    private List<Pedido> pedidos;
    private Integer id;
    public Mesa(String numero) {
        this.numero = numero;
        pedidos = new ArrayList<>();
    }
    public void setId(Integer id) {
        this.id = id;
    }
    public Integer getId() {
        return id;
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


    @Override
    public String toString() {
        return "Mesa # " + numero;
    }
    public void limpiarPedidos() {
        pedidos.clear();
    }
}