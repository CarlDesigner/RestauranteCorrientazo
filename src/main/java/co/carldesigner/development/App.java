package co.carldesigner.development;

import co.carldesigner.development.exception.EfectivoInsuficienteException;
import co.carldesigner.development.model.Adicional;
import co.carldesigner.development.model.Bandeja;
import co.carldesigner.development.model.Completo;
import co.carldesigner.development.model.Mesa;
import co.carldesigner.development.model.OpcionCarne;
import co.carldesigner.development.model.OpcionEnsalada;
import co.carldesigner.development.model.OpcionJugo;
import co.carldesigner.development.model.OpcionPrincipio;
import co.carldesigner.development.model.OpcionSopa;
import co.carldesigner.development.model.Pedido;
import co.carldesigner.development.view.PrincipalVista;

/**
 * Hello world!
 */
public final class App {
    public static void main(String[] args) {
        //prueba();
        var menu = new PrincipalVista();
        menu.iniciarAplicacion();
    }

    private static void prueba() {
        var mesa = new Mesa("01");

        var sopa = new OpcionSopa("Pasta");
        var principio = new OpcionPrincipio("Frijoles");
        var carne = new OpcionCarne("Res a la plancha");
        var ensalada = new OpcionEnsalada("Rusa");
        var jugo = new OpcionJugo("Limonada");
        var cesar = new Pedido("Cesar", new Completo(12_000, sopa, principio, carne, ensalada, jugo));
        cesar.agregarAdicional(new Adicional("Huevo", 1_000));
        cesar.agregarAdicional(new Adicional("Gaseosa", 2_000));
        var laura = new Pedido("Laura", new Bandeja(10_000, principio, carne, jugo));
        mesa.adicionarPedido(cesar);
        mesa.adicionarPedido(laura);
        // cesar.entregar();
        // laura.entregar();
        var total = mesa.calcularValorPagar();
        System.out.printf("La mesa %s debe $ %,d %n", mesa.getNumero(), total);
        try {
            var efectivo = 24_000;
            var devuelta = mesa.pagar(efectivo);
            System.out.printf("La mesa %s paga $ %,d y su devuelta es $ %,d. %n",
                    mesa.getNumero(), efectivo, devuelta);
        } catch (EfectivoInsuficienteException ex) {
            System.err.println(ex.getMessage());
        }
    }
}