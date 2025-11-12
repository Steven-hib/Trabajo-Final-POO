public class Solicitud {
    private final Integer piso;        // piso donde atender (parada)
    private final Direccion direccion; // para llamadas externas, puede ser null en cabina
    private final boolean desdePiso;   // true: llamada externa; false: cabina

    public Solicitud(Integer piso, Direccion direccion, boolean desdePiso) {
        this.piso = piso;
        this.direccion = direccion;
        this.desdePiso = desdePiso;
    }

    public Integer getPiso() { return piso; }
    public Direccion getDireccion() { return direccion; }
    public boolean isDesdePiso() { return desdePiso; }
}
