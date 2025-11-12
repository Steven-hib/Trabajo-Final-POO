public class Main {
    public static void main(String[] args) {
        SistemaControl sistema = new SistemaControl(8, 1);

        // Llamadas externas (desde piso)
        sistema.solicitarDesdePiso(5, Direccion.SUBIENDO);
        sistema.solicitarDesdePiso(2, Direccion.BAJANDO);

        // Selecciones internas (en cabina)
        sistema.solicitarDesdeCabina(7);
        sistema.solicitarDesdeCabina(3);

        // Simulación de pasos
        for (int t = 0; t < 30; t++) {
            System.out.printf("t=%02d | piso=%d | dir=%s | paradas=%s | puerta=%s%n",
                    t,
                    sistema.getAscensor().getPisoActual(),
                    sistema.getAscensor().getDireccion(),
                    sistema.getAscensor().getParadas().toString(),
                    sistema.getAscensor().getPuerta().getEstado());

            sistema.atenderSiguiente();

            // ejemplo de obstáculo aleatorio que bloquea puerta y luego se libera
            if (t == 8) sistema.getAscensor().getPuerta().getSensor().setObstaculo(true);
            if (t == 10) {
                sistema.getAscensor().getPuerta().getSensor().setObstaculo(false);
                sistema.getAscensor().getPuerta().desbloquear();
            }
        }

        System.out.println("Simulación terminada.");
    }
}
