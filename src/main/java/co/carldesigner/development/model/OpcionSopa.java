package co.carldesigner.development.model;

public class OpcionSopa {
    private String nombre;
    public OpcionSopa(String nombre) {
        this.nombre = nombre;
    }
    public String getNombre() {
        return nombre;
    }

    @Override
    public String toString() {
        return "Sopa de " + nombre;
    }
}