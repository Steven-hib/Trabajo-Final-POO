import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SistemaControl {
    private final Ascensor ascensor;
    private final List<Piso> pisos;
    private final Queue<Solicitud> cola = new LinkedList<>();
    private final Notificador notificador = new Notificador();

    public SistemaControl(int cantidadPisos, int pisoInicial) {
        this.ascensor = new Ascensor(pisoInicial);
        this.pisos = new ArrayList<>();
        for (int i = 1; i <= cantidadPisos; i++) pisos.add(new Piso(i));
    }

    public Ascensor getAscensor() { return ascensor; }
    public List<Piso> getPisos() { return pisos; }

    public void solicitarDesdePiso(int piso, Direccion dir) {
        if (piso < 1 || piso > pisos.size()) { notificador.alerta("Piso inválido: " + piso); return; }
        cola.add(new Solicitud(piso, dir, true));
        pisos.get(piso-1).getBtnSubir().iluminar(); // marcamos cualquier botón; es indicativo
        ascensor.agregarParada(piso);
        notificador.log("Solicitud externa en piso " + piso + " (" + dir + ")");
    }

    public void solicitarDesdeCabina(int destino) {
        if (destino < 1 || destino > pisos.size()) { notificador.alerta("Destino inválido: " + destino); return; }
        cola.add(new Solicitud(destino, null, false));
        ascensor.agregarParada(destino);
        notificador.log("Solicitud interna a piso " + destino);
    }

    public void optimizarRuta() {
        if (ascensor.getParadas().isEmpty()) {
            ascensor.getPuerta().cerrar();
            return;
        }
        if (ascensor.getDireccion() == Direccion.DETENIDO) {
            Integer objetivo = ascensor.paradaMasCercana();
            if (objetivo == null) return;
            if (objetivo > ascensor.getPisoActual()) {
                // set dirección a subiendo
                // se fija al moverse
            } else if (objetivo < ascensor.getPisoActual()) {
                // se fija al moverse
            }
        } else {
            Integer enDir = ascensor.proximaParadaEnDireccion();
            if (enDir == null) {
                // cambiar dirección si no hay paradas adelante
                if (ascensor.getDireccion() == Direccion.SUBIENDO) {
                    // revisar abajo
                    // dirección cambiará al moverse
                } else if (ascensor.getDireccion() == Direccion.BAJANDO) {
                    // revisar arriba
                }
            }
        }
    }

    public void atenderSiguiente() {
        optimizarRuta();
        Integer targetArriba = ascensor.getParadas().ceiling(ascensor.getPisoActual());
        Integer targetAbajo  = ascensor.getParadas().floor(ascensor.getPisoActual());

        // Selección simple: si detenido, ir al más cercano; si moviendo, priorizar esa dirección
        if (ascensor.getDireccion() == Direccion.DETENIDO) {
            Integer cercano = ascensor.paradaMasCercana();
            if (cercano == null) return;
            if (cercano > ascensor.getPisoActual()) ascensor.moverArriba();
            else if (cercano < ascensor.getPisoActual()) ascensor.moverAbajo();
            else ascensor.pararEn(cercano);
        } else if (ascensor.getDireccion() == Direccion.SUBIENDO) {
            if (targetArriba != null && targetArriba > ascensor.getPisoActual()) ascensor.moverArriba();
            else if (targetArriba != null && targetArriba == ascensor.getPisoActual()) ascensor.pararEn(targetArriba);
            else if (targetAbajo != null) ascensor.moverAbajo();
            else ascensor.getPuerta().abrir();
        } else if (ascensor.getDireccion() == Direccion.BAJANDO) {
            if (targetAbajo != null && targetAbajo < ascensor.getPisoActual()) ascensor.moverAbajo();
            else if (targetAbajo != null && targetAbajo == ascensor.getPisoActual()) ascensor.pararEn(targetAbajo);
            else if (targetArriba != null) ascensor.moverArriba();
            else ascensor.getPuerta().abrir();
        }
    }

    public void registrarFalla(String msg) { notificador.alerta("Falla: " + msg); }
}
