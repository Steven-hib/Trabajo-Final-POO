import java.util.NavigableSet;
import java.util.TreeSet;

public class Ascensor {
    private int pisoActual;
    private Direccion direccion = Direccion.DETENIDO;
    private final Puerta puerta = new Puerta();
    private final NavigableSet<Integer> paradas = new TreeSet<>();

    public Ascensor(int pisoInicial) { this.pisoActual = pisoInicial; }

    public int getPisoActual() { return pisoActual; }
    public Direccion getDireccion() { return direccion; }
    public Puerta getPuerta() { return puerta; }
    public NavigableSet<Integer> getParadas() { return paradas; }

    public void agregarParada(int piso) { paradas.add(piso); }

    public void moverArriba() {
        if (puerta.getEstado() == EstadoPuerta.ABIERTA) puerta.cerrar();
        if (puerta.getEstado() != EstadoPuerta.BLOQUEADA) {
            pisoActual++;
            direccion = Direccion.SUBIENDO;
        }
    }

    public void moverAbajo() {
        if (puerta.getEstado() == EstadoPuerta.ABIERTA) puerta.cerrar();
        if (puerta.getEstado() != EstadoPuerta.BLOQUEADA) {
            pisoActual--;
            direccion = Direccion.BAJANDO;
        }
    }

    public void pararEn(int piso) {
        if (pisoActual == piso) {
            puerta.abrir();
            paradas.remove(piso);
            direccion = paradas.isEmpty() ? Direccion.DETENIDO : direccion;
        }
    }

    public Integer proximaParadaEnDireccion() {
        if (direccion == Direccion.SUBIENDO) {
            return paradas.ceiling(pisoActual);
        } else if (direccion == Direccion.BAJANDO) {
            return paradas.floor(pisoActual);
        } else {
            return null;
        }
    }

    public Integer paradaMasCercana() {
        Integer arriba = paradas.ceiling(pisoActual);
        Integer abajo  = paradas.floor(pisoActual);
        if (arriba == null) return abajo;
        if (abajo == null)  return arriba;
        int da = Math.abs(arriba - pisoActual);
        int db = Math.abs(abajo - pisoActual);
        return da <= db ? arriba : abajo;
    }
}
