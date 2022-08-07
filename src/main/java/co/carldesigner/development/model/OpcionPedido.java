package co.carldesigner.development.model;

public abstract class OpcionPedido {
    private Integer precio;

    private OpcionPrincipio principio;
    private OpcionCarne carne;
    private OpcionEnsalada ensalada;
    private OpcionJugo jugo;

    public OpcionPedido(Integer precio) {
        this.precio = precio;
    }

    public OpcionPedido(Integer precio, OpcionPrincipio principio, OpcionCarne carne, OpcionEnsalada ensalada,
            OpcionJugo jugo) {
        this.precio = precio;
        this.principio = principio;
        this.carne = carne;
        this.ensalada = ensalada;
        this.jugo = jugo;
    }

    public OpcionPedido(Integer precio, OpcionPrincipio principio, OpcionCarne carne, OpcionJugo jugo) {
        this.precio = precio;
        this.principio = principio;
        this.carne = carne;
        this.jugo = jugo;
    }

    public Integer getPrecio() {
        return precio;
    }

    public OpcionPrincipio getPrincipio() {
        return principio;
    }

    public void setPrincipio(OpcionPrincipio principio) {
        this.principio = principio;
    }

    public OpcionCarne getCarne() {
        return carne;
    }

    public void setCarne(OpcionCarne carne) {
        this.carne = carne;
    }

    public OpcionEnsalada getEnsalada() {
        return ensalada;
    }

    public void setEnsalada(OpcionEnsalada ensalada) {
        this.ensalada = ensalada;
    }

    public OpcionJugo getJugo() {
        return jugo;
    }

    public void setJugo(OpcionJugo jugo) {
        this.jugo = jugo;
    }
}
