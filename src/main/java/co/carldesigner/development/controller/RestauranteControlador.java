package co.carldesigner.development.controller;

import java.util.Arrays;
import java.util.List;

import co.carldesigner.development.exception.EfectivoInsuficienteException;
import co.carldesigner.development.model.Mesa;
import co.carldesigner.development.model.OpcionCarne;
import co.carldesigner.development.model.OpcionEnsalada;
import co.carldesigner.development.model.OpcionJugo;
import co.carldesigner.development.model.OpcionPrincipio;
import co.carldesigner.development.model.OpcionSopa;
import co.carldesigner.development.view.PedidoView;
import co.carldesigner.development.view.PrincipalVista;

public class RestauranteControlador {

    private PedidoView pedidoView;
    private List<Mesa> mesas = null;
    private List<OpcionSopa> sopas = null;
    private List<OpcionPrincipio> principios = null;
    private List<OpcionCarne> carnes = null;
    private List<OpcionEnsalada> ensaladas = null;
    private List<OpcionJugo> jugos = null;
    public RestauranteControlador() {
        this.pedidoView = new PedidoView();
    }
    public void agregarPedidoAMesa() {
        // Seleccionar una mesa
        var mesa = pedidoView.seleccionarMesa(listarMesas());
        // Pedir la informacion del pedido
        var pedido = pedidoView.pedirInformacionPedido(listarSopas(), listarPrincipios(), listarCarnes(),
                listarEnsaladas(), listarJugos());
        // Agregar el pedido a la mesa
        mesa.adicionarPedido(pedido);
        pedidoView.mostrarMensaje("Pedidio ingresado de forma correcta.");
    }
    private List<OpcionJugo> listarJugos() {
        if (jugos == null) {
            jugos = Arrays.asList(new OpcionJugo("Limonada"),
                    new OpcionJugo("Guayaba"),
                    new OpcionJugo("Mora"),
                    new OpcionJugo("Uva"));
        }
        return jugos;
    }
    private List<OpcionEnsalada> listarEnsaladas() {
        if (ensaladas == null) {
            ensaladas = Arrays.asList(new OpcionEnsalada("Solo tomate"),
                    new OpcionEnsalada("Tomate y cebolla"),
                    new OpcionEnsalada("Aguacate"),
                    new OpcionEnsalada("Rusa"));
        }
        return ensaladas;
    }
    private List<OpcionCarne> listarCarnes() {
        if (carnes == null) {
            carnes = Arrays.asList(new OpcionCarne("Res a la plancha"),
                    new OpcionCarne("Cerdo a la plancha"),
                    new OpcionCarne("Pechuga a la plancha"),
                    new OpcionCarne("Higado encebollado"));
        }
        return carnes;
    }
    private List<OpcionPrincipio> listarPrincipios() {
        if (principios == null) {
            principios = Arrays.asList(new OpcionPrincipio("Frijoles"),
                    new OpcionPrincipio("Lentejas"),
                    new OpcionPrincipio("Papa guisada"),
                    new OpcionPrincipio("Espaguetis"));
        }
        return principios;
    }
    private List<OpcionSopa> listarSopas() {
        if (sopas == null) {
            sopas = Arrays.asList(new OpcionSopa("Pasta"),
                    new OpcionSopa("Consomé"),
                    new OpcionSopa("Crema Zanahoria"),
                    new OpcionSopa("Caldo Costilla"),
                    new OpcionSopa("Verduras"));
        }
        return sopas;
    }
    private List<Mesa> listarMesas() {
        if (mesas == null) {
            mesas = Arrays.asList(new Mesa("VIP01"),
                    new Mesa("VIP02"),
                    new Mesa("P101"),
                    new Mesa("P102"),
                    new Mesa("P103"),
                    new Mesa("P201"),
                    new Mesa("P202"));
        }
        return mesas;
    }
    public void agregarAdicionalAPedido() {
    }

    public void entregarPedidoDeMesa() {
        // Seleccionar una mesa
        var mesa = pedidoView.seleccionarMesa(listarMesas());

        // Seleccionar pedido de la mesa a entregar
        var pedido = pedidoView.seleccionarPedidoEntrega(mesa);

        pedido.entregar();
    }

    public void pagarCuentaMesa() {
        // Seleccionar una mesa
        var mesa = pedidoView.seleccionarMesa(listarMesas());

        var total = mesa.calcularValorPagar();
        pedidoView.mostrarMensaje(String.format("La cuenta de mesa es: $ %,d.", total));

        var efectivo = pedidoView.leerEfectivo();

        try {
            // Valido los datos
            if (efectivo < total) {
                // Devolver error de fondos insuficientes
                throw new EfectivoInsuficienteException("El valor entregado no cubre el total a pagar");
            }

            // Limpiar pedidos
            mesa.limpiarPedidos();

            // Retorno la devuelta
            pedidoView.mostrarMensaje(String.format("La devuelta es: $ %,d.", efectivo - total));
        } catch (EfectivoInsuficienteException ex) {
            pedidoView.mostrarError("El valor ingresado no es suficiente para cubrir la deuda.");
        }
    }

    public void consultarEstadoMesa() {
        // Seleccionar una mesa
        var mesa = pedidoView.seleccionarMesa(listarMesas());
        pedidoView.mostrarEstadoMesa(mesa);
    }
}