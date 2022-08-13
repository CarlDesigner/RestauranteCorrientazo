package co.carldesigner.development.model;

import java.util.Objects;

public class OpcionCarne {
    private String nombre;
    private Integer id;

    public OpcionCarne(String nombre) {
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
        return nombre;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 23 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final OpcionCarne other = (OpcionCarne) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}