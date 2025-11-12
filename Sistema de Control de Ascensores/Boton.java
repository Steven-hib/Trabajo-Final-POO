public abstract class Boton {
    protected boolean iluminado;

    public void presionar() {
        iluminar();
    }
    public void iluminar() { iluminado = true; }
    public void apagar() { iluminado = false; }
    public boolean estaIluminado() { return iluminado; }
}
