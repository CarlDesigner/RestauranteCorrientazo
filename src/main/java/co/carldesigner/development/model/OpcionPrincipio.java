package co.carldesigner.development.model;

public class OpcionPrincipio {
    private String nombre;
    private Integer id;

    public OpcionPrincipio(String nombre) {
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
        return "Principio de " + nombre;
    }
}