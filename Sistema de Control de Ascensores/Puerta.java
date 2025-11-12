public class Puerta {
    private EstadoPuerta estado = EstadoPuerta.CERRADA;
    private final SensorPuerta sensor = new SensorPuerta();

    public EstadoPuerta getEstado() { return estado; }
    public SensorPuerta getSensor() { return sensor; }

    public void abrir() {
        if (estado != EstadoPuerta.BLOQUEADA) estado = EstadoPuerta.ABIERTA;
    }
    public void cerrar() {
        if (sensor.hayObstaculo()) {
            estado = EstadoPuerta.BLOQUEADA;
            return;
        }
        estado = EstadoPuerta.CERRADA;
    }
    public void desbloquear() {
        if (!sensor.hayObstaculo()) estado = EstadoPuerta.CERRADA;
    }
}
