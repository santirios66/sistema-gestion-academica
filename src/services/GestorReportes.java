package services;

import exceptions.PilaDeshacerVaciaException;
import exceptions.SemestreInvalidoException;
import exceptions.SinNotasException;
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
     * Lanza excepción si el estudiante no tiene ninguna nota registrada.
     *
     * @throws SinNotasException si el estudiante no tiene notas
     */
    public void generarReporte(Estudiante estudiante) throws SinNotasException {
        // Verifica que tenga al menos una nota
        if (estudiante.calcularPromedioAcumulado() == 0) {
            throw new SinNotasException(
                "El estudiante " + estudiante.getNombre() + " no tiene notas registradas.");
        }

        pilaNavegacion.push(estudiante.getId());

        System.out.println("=== REPORTE ACADÉMICO ===");
        System.out.println("Estudiante: " + estudiante.getNombre());
        System.out.println("ID: " + estudiante.getId());
        System.out.println("------------------------");

        Double[][] notas = estudiante.getNotas();

        for (int i = 0; i < 10; i++) {
            boolean tieneNotas = false;
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
     * Genera reporte de materias reprobadas (nota menor a 3.0).
     * Lanza excepción si el estudiante no tiene notas registradas.
     *
     * @throws SinNotasException si el estudiante no tiene notas
     */
    public void reporteReprobadas(Estudiante estudiante) throws SinNotasException {
        if (estudiante.calcularPromedioAcumulado() == 0) {
            throw new SinNotasException(
                "El estudiante " + estudiante.getNombre() + " no tiene notas registradas.");
        }

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
     * Lanza excepción si el semestre está fuera del rango 0-9.
     *
     * @throws SemestreInvalidoException si el semestre es inválido
     */
    public double promedioSemestre(Estudiante estudiante, int semestre)
            throws SemestreInvalidoException {
        if (semestre < 0 || semestre > 9) {
            throw new SemestreInvalidoException(
                "El semestre debe estar entre 1 y 10.");
        }
        return estudiante.calcularPromedio(semestre);
    }

    /**
     * Calcula el promedio acumulado del estudiante.
     * Lanza excepción si no tiene notas registradas.
     *
     * @throws SinNotasException si el estudiante no tiene notas
     */
    public double promedioAcumulado(Estudiante estudiante) throws SinNotasException {
        if (estudiante.calcularPromedioAcumulado() == 0) {
            throw new SinNotasException(
                "El estudiante " + estudiante.getNombre() + " no tiene notas registradas.");
        }
        return estudiante.calcularPromedioAcumulado();
    }

    /**
     * Navega al reporte anterior usando la pila de navegación.
     * Lanza excepción si no hay reportes anteriores.
     *
     * @throws PilaDeshacerVaciaException si la pila está vacía
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