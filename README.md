
Sistema de Control de Ascensores (POO)


# Indice
- Resumen
- Objetivo
- Alcance Y Requisitos
- Diseño POO
- Pruebas Realizadas
- Justificación frente a la rúbrica
- Créditos y uso de IA

# Resumen
Aplicación de consola que simula el control de un ascensor en un edificio de N pisos, gestionando solicitudes desde los pisos y desde la cabina, optimizando el movimiento por dirección, y controlando la apertura/cierre de puertas con sensor de seguridad.​

El proyecto incluye diseño orientado a objetos, diagrama de clases, código Java en archivos individuales sin paquetes y pruebas de ejecución para validar requisitos funcionales.​

# Objetivo
Modelar un sistema de ascensor que atienda solicitudes de subida/bajada en pisos y selección de piso en cabina, priorizando paradas en la dirección actual antes de invertirla.​
Demostrar encapsulamiento, herencia y polimorfismo con una arquitectura clara y justificable frente a la rúbrica de POO.

# Alcance y requisitos
Botones por piso: subir y bajar, con iluminación al presionar y cancelación al atender la solicitud.​

Botones de cabina: selección de piso destino con registro de parada y confirmación visual simple.​

Dirección y movimiento: optimización para atender primero en la dirección de marcha y cambio solo si no hay más solicitudes en esa dirección.​

Puertas: apertura automática al llegar y cierre seguro con sensor que impide bloquear con obstáculos.​

Alertas: registro básico de fallas y eventos para mantenimiento y trazabilidad.​

# Diseño POO
Entidades principales: SistemaControl, Ascensor, Piso, Puerta, SensorPuerta, Solicitud, Notificador y jerarquía de Boton.​

Herencia: Boton es clase abstracta y la especializan BotonPisoSubir, BotonPisoBajar y BotonCabina para unificar contratos y variar comportamiento.​

Encapsulamiento: atributos privados y acceso controlado mediante métodos públicos para estado y acciones.

# Pruebas realizadas
Prueba 1: Solicitud externa en piso 5 (subir) y en piso 2 (bajar), más selecciones de cabina a 7 y 3, verificando priorización por dirección y apertura de puertas al llegar.​

Prueba 2: Simulación de obstáculo en puerta en t=8 y liberación en t=10 para evidenciar bloqueo y desbloqueo seguro antes de continuar.​

Evidencias: la consola imprime t, piso actual, dirección, conjunto de paradas y estado de puerta, permitiendo verificar correctitud paso a paso.

# Justificación frente a la rúbrica
Diseño de Clases: cada clase tiene responsabilidad específica; SistemaControl orquesta, Ascensor ejecuta movimiento, Piso modela botones externos y Puerta encapsula seguridad, lo que cubre organización y estructura esperadas.​

Principios de POO: encapsulamiento en atributos privados, herencia en la jerarquía Boton*, y polimorfismo al tratarlos por el tipo base en operaciones genéricas.​

Atributos y Métodos: métodos para mover, abrir/cerrar, iluminar/apagar, y construir solicitudes, alineados con las acciones del dominio del ascensor.​

Botones: modelados por piso y cabina con iluminación al presionar y cancelación al atender, tal como el ejemplo académico de ascensor.​

Dirección y Movimiento: se prioriza la dirección actual y se cambia solo si no quedan solicitudes por atender en ese sentido, cumpliendo optimización básica.​

Puertas y Sensores: cierre condicionado por sensor con estado BLOQUEADA y mecanismo de desbloqueo, conforme a seguridad solicitada.​

Alertas y Registro: Notificador emite mensajes de alerta y log para fallas y seguimiento de operación.

# Créditos y uso de IA
Una parte del proceso de documentación y ayuda se realizó con el acompañamiento de una IA, siguiendo buenas prácticas y apoyo pedagógico. El desarrollo y las pruebas finales fueron realizados manualmente, asegurando comprensión y dominio del tema.
