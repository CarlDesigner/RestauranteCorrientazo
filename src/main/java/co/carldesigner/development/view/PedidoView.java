package co.carldesigner.development.view;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import co.carldesigner.development.model.Bandeja;
import co.carldesigner.development.model.Completo;
import co.carldesigner.development.model.Mesa;
import co.carldesigner.development.model.OpcionCarne;
import co.carldesigner.development.model.OpcionEnsalada;
import co.carldesigner.development.model.OpcionJugo;
import co.carldesigner.development.model.OpcionPedido;
import co.carldesigner.development.model.OpcionPrincipio;
import co.carldesigner.development.model.OpcionSopa;
import co.carldesigner.development.model.Pedido;

public class PedidoView {

    private Scanner scanner;

    public PedidoView() {
        this.scanner = new Scanner(System.in);
    }

    public Mesa seleccionarMesa(List<Mesa> mesas) {
        return pedirOpcion(mesas, "Mesas");
    }

    public Pedido pedirInformacionPedido(List<OpcionSopa> sopas, List<OpcionPrincipio> principios,
            List<OpcionCarne> carnes, List<OpcionEnsalada> ensaladas, List<OpcionJugo> jugos) {
        // Pedir informacion del cliente
        System.out.print("Ingrese el nombre (descripcion) del cliente: ");
        var cliente = scanner.nextLine();

        // Pedir opcion de pedido (completo o bandeja)
        var opcion = pedirOpcionPedido();

        if (opcion instanceof Completo) {
            // Pedir Sopa
            ((Completo) opcion).setSopa(pedirOpcion(sopas, "Sopas"));
        }

        // Pedir Principio
        opcion.setPrincipio(pedirOpcion(principios, "Principios"));
        // Pedir Carne
        opcion.setCarne(pedirOpcion(carnes, "Carnes"));
        // Pedir Ensalada (si la desea)
        opcion.setEnsalada(pedirOpcion(ensaladas, "Ensaladas", true));
        // Pedir Jugo
        opcion.setJugo(pedirOpcion(jugos, "Jugos"));

        return new Pedido(cliente, opcion);
    }

    private <T> T pedirOpcion(List<T> opciones, String nombre) {
        return pedirOpcion(opciones, nombre, false);
    }

    private <T> T pedirOpcion(List<T> opciones, String nombre, Boolean opcional) {
        while (true) {
            // Listo las opciones existentes
            System.out.println(nombre + " existentes:");
            for (int i = 0; i < opciones.size(); i++) {
                System.out.printf("%d -> %s %n", (i + 1), opciones.get(i));
            }
            if (opcional) {
                System.out.printf("%d -> %s %n", 0, "Ninguno");
            }

            // Selecciono la mesa
            System.out.print("Cual es su elección: ");
            try {
                var opcion = scanner.nextInt();
                scanner.nextLine();
                if (opcional && opcion == 0) {
                    return null;
                } else if (opcion >= 1 && opcion <= opciones.size()) {
                    return opciones.get(opcion - 1);
                } else {
                    System.err.println("Opcion inválida. Intente de nuevo.");
                }
            } catch (InputMismatchException e) {
                System.err.println("Opcion inválida. Intente de nuevo.");
                scanner.nextLine();
            }
        }
    }

    private OpcionPedido pedirOpcionPedido() {
        while (true) {
            System.out.println("Opciones de pedido:\nC -> Almuerzo Completo\nB -> Bandeja");
            System.out.print("C / B: ");
            var opcion = scanner.nextLine();
            switch (opcion.toUpperCase()) {
                case "C":
                    return new Completo(12_000);
                case "B":
                    return new Bandeja(10_000);
                default:
                    System.err.println("Opcion inválida. Intenta de nuevo.");
            }
        }
    }

    public void mostrarEstadoMesa(Mesa mesa) {
        System.out.println(mesa);
        System.out.println("Pedidos:");
        mesa.getPedidos()
                .forEach(System.out::println);
    }

    public void mostrarMensaje(String mensaje) {
        System.out.println(mensaje);
    }

    public void mostrarError(String error) {
        System.out.println(error);
    }

}