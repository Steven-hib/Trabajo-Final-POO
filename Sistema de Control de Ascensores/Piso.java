public class Piso {
    private final int numero;
    private final BotonPisoSubir btnSubir;
    private final BotonPisoBajar btnBajar;

    public Piso(int numero) {
        this.numero = numero;
        this.btnSubir = new BotonPisoSubir(numero);
        this.btnBajar = new BotonPisoBajar(numero);
    }
    public int getNumero() { return numero; }
    public BotonPisoSubir getBtnSubir() { return btnSubir; }
    public BotonPisoBajar getBtnBajar() { return btnBajar; }
}
