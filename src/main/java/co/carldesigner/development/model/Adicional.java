package co.carldesigner.development.model;

public class Adicional {
    private String nombre;
    private Integer precio;

    public Adicional(String nombre, Integer precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public String getNombre() {
        return nombre;
    }

    public void setPrecio(Integer precio) {
        this.precio = precio;
    }

    public Integer getPrecio() {
        return precio;
    }


}