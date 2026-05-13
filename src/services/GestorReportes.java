package services;

import exceptions.EstudianteNoEncontradoException;
import exceptions.PilaDeshacerVaciaException;
import model.Estudiante;

import java.util.Stack;

/**
 * Servicio que gestiona los reportes académicos de los estudiantes.
 * Usa una pila para la navegación entre reportes (funcionalidad atrás).
 * - Stack pilaNavegacion: guarda los IDs de los reportes visitados
 */
public class GestorReportes {

    /**
     * Pila que guarda los IDs de los estudiantes cuyos reportes
     * se han consultado. Permite navegar hacia atrás.
     */
    private Stack<String> pilaNavegacion;

    /**
     * Constructor que inicializa la pila vacía.
     */
    public GestorReportes() {
        this.pilaNavegacion = new Stack<>();
    }

    /**
     * Genera el reporte académico completo de un estudiante.
     * Muestra las notas por semestre y el promedio de cada uno.
     * Guarda el reporte en la pila de navegación.
     *
     * @param estudiante estudiante del que se genera el reporte
     */
    public void generarReporte(Estudiante estudiante) {
        // Guarda en pila para poder navegar atrás
        pilaNavegacion.push(estudiante.getId());

        System.out.println("=== REPORTE ACADÉMICO ===");
        System.out.println("Estudiante: " + estudiante.getNombre());
        System.out.println("ID: " + estudiante.getId());
        System.out.println("------------------------");

        Double[][] notas = estudiante.getNotas();

        for (int i = 0; i < 10; i++) {
            boolean tieneNotas = false;

            // Verifica si el semestre tiene al menos una nota
            for (int j = 0; j < 20; j++) {
                if (notas[i][j] != null) {
                    tieneNotas = true;
                    break;
                }
            }

            if (tieneNotas) {
                System.out.println("Semestre " + (i + 1) + ":");
                for (int j = 0; j < 20; j++) {
                    if (notas[i][j] != null) {
                        System.out.println("  Materia " + (j + 1) + ": " + notas[i][j]);
                    }
                }
                System.out.println("  Promedio: " + estudiante.calcularPromedio(i));
            }
        }

        System.out.println("------------------------");
        System.out.println("Promedio acumulado: " + estudiante.calcularPromedioAcumulado());
    }

    /**
     * Genera un reporte de las materias reprobadas del estudiante.
     * Una materia está reprobada si la nota es menor a 3.0.
     *
     * @param estudiante estudiante del que se generan las reprobadas
     */
    public void reporteReprobadas(Estudiante estudiante) {
        System.out.println("=== MATERIAS REPROBADAS ===");
        System.out.println("Estudiante: " + estudiante.getNombre());

        Double[][] notas = estudiante.getNotas();
        int reprobadas = 0;

        for (int i = 0; i < 10; i++) {
            for (int j = 0; j < 20; j++) {
                if (notas[i][j] != null && notas[i][j] < 3.0) {
                    System.out.println("Semestre " + (i + 1) +
                        " - Materia " + (j + 1) +
                        ": " + notas[i][j]);
                    reprobadas++;
                }
            }
        }

        if (reprobadas == 0) {
            System.out.println("No tiene materias reprobadas.");
        } else {
            System.out.println("Total reprobadas: " + reprobadas);
        }
    }

    /**
     * Calcula el promedio de un semestre específico.
     *
     * @param estudiante estudiante a consultar
     * @param semestre semestre a calcular (0 a 9)
     */
    public double promedioSemestre(Estudiante estudiante, int semestre) {
        return estudiante.calcularPromedio(semestre);
    }

    /**
     * Calcula el promedio acumulado del estudiante.
     *
     * @param estudiante estudiante a consultar
     */
    public double promedioAcumulado(Estudiante estudiante) {
        return estudiante.calcularPromedioAcumulado();
    }

    /**
     * Navega al reporte anterior usando la pila de navegación.
     * Saca el reporte actual y retorna el ID del anterior.
     *
     * @return ID del estudiante del reporte anterior
     * @throws PilaDeshacerVaciaException si no hay reportes anteriores
     */
    public String atras() throws PilaDeshacerVaciaException {
        if (pilaNavegacion.isEmpty()) {
            throw new PilaDeshacerVaciaException(
                "No hay reportes anteriores para navegar.");
        }
        String id = pilaNavegacion.pop();
        System.out.println("Navegando al reporte anterior: " + id);
        return id;
    }
}