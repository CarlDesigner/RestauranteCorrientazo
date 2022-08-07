package co.carldesigner.development.model;

import java.util.ArrayList;
import java.util.List;

public class Pedido {
    private String cliente;
    private EstadoPedido estado;
    private List<Adicional> adicionales;
    private OpcionPedido opcion;

    public Pedido(String cliente) {
        this.cliente = cliente;

        this.estado = EstadoPedido.PENDIENTE_ENTREGAR;
        this.adicionales = new ArrayList<>();
    }

    public Pedido(String cliente, OpcionPedido opcion) {
        this.cliente = cliente;
        this.opcion = opcion;

        this.estado = EstadoPedido.PENDIENTE_ENTREGAR;
        this.adicionales = new ArrayList<>();
    }

    public String getCliente() {
        return cliente;
    }

    public EstadoPedido getEstado() {
        return estado;
    }

    public OpcionPedido getOpcion() {
        return opcion;
    }

    public void setOpcion(OpcionPedido opcion) {
        this.opcion = opcion;
    }

    public void entregar() {
        this.estado = EstadoPedido.PENDIENTE_COBRAR;
    }

    public void agregarAdicional(Adicional adicional) {
        this.adicionales.add(adicional);
    }

    public Integer calcularTotal() {
        return 0;
    }
}