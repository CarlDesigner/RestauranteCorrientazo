package co.carldesigner.development.model;

public class OpcionEnsalada {
    private String nombre;
    private Integer id;

    public OpcionEnsalada(String nombre) {
        this.nombre = nombre;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }
    @Override
    public String toString() {
        return "Ensalada de " + nombre;
    }
}