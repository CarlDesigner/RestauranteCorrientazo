package co.carldesigner.development.view;

import java.util.InputMismatchException;
import java.util.Scanner;


import co.carldesigner.development.controller.RestauranteControlador;
public class PrincipalVista {
    private Scanner scanner;
    private RestauranteControlador controlador; 
    public PrincipalVista() {
        scanner = new Scanner(System.in);
        controlador = new RestauranteControlador();
    }
    public void iniciarAplicacion() {
        var mostrarMenu = true;
        while (mostrarMenu) {
            limpiarPantalla();
            System.out.println(".: SISTEMA DE INFORMACION EL CORRIENTAZO :.");
            System.out.println("1 -> Gestion de pedidos");
            System.out.println("2 -> Gestion de tablas maestras");
            System.out.println("0 -> Salir de la aplicación");
            System.out.print("Cual es su elección?: ");
            try {
                var opcion = scanner.nextInt();
                scanner.nextLine();
                switch (opcion) {
                    case 0:
                        mostrarMenu = false;
                        System.out.println("Hasta luego!");
                        break;
                    case 1:
                        gestionPedidos();
                        break;
                    case 2:
                        gestionMaestras();
                        break;
                    default:
                        System.err.println("Opción inválida. Intente de nuevo.");
                }
            } catch (InputMismatchException ex) {
                System.err.println("Opción inválida. Intente de nuevo.");
                scanner.nextLine();
            }
            esperarEnter();
        }
    }
    public void esperarEnter() {
        System.out.print("Presione una tecla para continuar");
        scanner.nextLine();
    }
    private void gestionPedidos() {
        limpiarPantalla();
        System.out.println(".: GESTION DE PEDIDO :.");
        System.out.println("1 -> Agregar pedido a una mesa");
        System.out.println("2 -> Agregar adicional a pedido en una mesa");
        System.out.println("3 -> Entregar un pedido");
        System.out.println("4 -> Pagar la cuenta de una mesa");
        System.out.println("5 -> Consultar el estado de una mesa");
        System.out.println("0 -> Salir al menu principal");
        System.out.print("Cual es su elección?: ");
        try {
            var opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 0:
                    break;
                case 1:
                    controlador.agregarPedidoAMesa();
                    break;
                case 2:
                    controlador.agregarAdicionalAPedido();
                    break;
                case 3:
                    controlador.entregarPedidoDeMesa();
                    break;
                case 4:
                    controlador.pagarCuentaMesa();
                    break;
                case 5:
                    controlador.consultarEstadoMesa();
                    break;
                default:
                    System.err.println("Opción inválida.");
            }
        } catch (InputMismatchException ex) {
            System.err.println("Opción inválida.");
        }
    }
    private void gestionMaestras() {
        limpiarPantalla();
        System.out.println(".: GESTION DE TABLAS MAESTRAS :.");
        System.out.println("1 -> Agregar mesas");
        System.out.println("2 -> Agregar opcion sopa");
        System.out.println("3 -> Agregar opcion principio");
        System.out.println("4 -> Agregar opcion carne");
        System.out.println("5 -> Agregar opcion ensalada");
        System.out.println("6 -> Agregar opcion jugo");
        System.out.println("7 -> Agregar adicional");
        System.out.println("0 -> Salir al menu principal");
        System.out.print("Cual es su elección?: ");
        try {
            var opcion = scanner.nextInt();
            scanner.nextLine();
            switch (opcion) {
                case 0:
                    break;
                case 1:
                    controlador.agregarMesa();
                    break;
                case 2:
                    // TODO: Implementar
                    break;
                case 3:
                    // TODO: Implementar
                    break;
                case 4:
                    // TODO: Implementar
                    break;
                case 5:
                    // TODO: Implementar
                    break;
                case 6:
                    // TODO: Implementar
                    break;
                case 7:
                    // TODO: Implementar
                    break;
                default:
                    System.err.println("Opción inválida.");
            }
        } catch (InputMismatchException ex) {
            System.err.println("Opción inválida.");
        }
    }
    private void limpiarPantalla() {
        for(var i = 0; i < 50; i++){
            System.out.println();
        }
    }
}