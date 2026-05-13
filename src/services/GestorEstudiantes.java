package services;

import exceptions.EstudianteNoEncontradoException;
import exceptions.EstudianteYaExisteException;
import exceptions.PilaDeshacerVaciaException;
import model.Estudiante;
import model.Facultad;
import model.Operacion;

import java.util.HashMap;
import java.util.Stack;

/**
 * Servicio principal para la gestión de estudiantes en el sistema .
 * Estructuras que usa:
 * - HashMap: permite buscar un estudiante por ID en tiempo constante O(1)
 * - Facultad[5]: arreglo fijo con las 5 facultades de la universidad
 * - Stack pilaDeshacer: guarda cada operación realizada para poder revertirla
 * - Stack pilaRehacer: guarda las operaciones deshechas para poder repetirlas
 *
 * Operaciones que se pueden deshacer y rehacer:
 * - Registrar estudiante
 * - Eliminar estudiante
 */
public class GestorEstudiantes {

    // HashMap<ID, Estudiante> para búsqueda rápida por ID
    private HashMap<String, Estudiante> estudiantes;

    // Arreglo fijo de exactamente 5 facultades de la universidad
    private Facultad[] facultades;

    /**
     * Pila que guarda cada operación realizada.
     * Cuando el usuario hace deshacer(), se saca la última operación de aquí.
     */
    private Stack<Operacion> pilaDeshacer;

    /**
     * Pila que guarda las operaciones deshechas.
     * Cuando el usuario hace rehacer(), se saca la última operación de aquí.
     */
    private Stack<Operacion> pilaRehacer;

    /**
     * Constructor que inicializa todas las estructuras vacías.
     * El HashMap arranca sin estudiantes y las pilas arrancas vacías.
     */
    public GestorEstudiantes() {
        this.estudiantes = new HashMap<>();
        this.facultades = new Facultad[5];
        this.pilaDeshacer = new Stack<>();
        this.pilaRehacer = new Stack<>();
    }

    /**
     * Registra un nuevo estudiante en el sistema.
     * Lo agrega al HashMap usando su ID como clave para búsqueda rápida.
     * Guarda la operación en pilaDeshacer para poder deshacerla.
     *
     * @param estudiante objeto Estudiante a registrar
     * @throws EstudianteYaExisteException si ya existe un estudiante con ese ID
     */
    public void registrarEstudiante(Estudiante estudiante) throws EstudianteYaExisteException {
        if (estudiantes.containsKey(estudiante.getId())) {
            throw new EstudianteYaExisteException(
                "Ya existe un estudiante con ID: " + estudiante.getId()
            );
        }
        estudiantes.put(estudiante.getId(), estudiante);
        pilaDeshacer.push(new Operacion(
            "REGISTRAR_ESTUDIANTE",
            "Registrar estudiante: " + estudiante.getNombre(),
            null,
            java.time.LocalDateTime.now().toString()
        ));
        System.out.println("Estudiante registrado exitosamente.");
    }

    /**
     * Busca un estudiante en el HashMap por su ID.
     * El HashMap permite esta búsqueda en tiempo O(1).
     *
     * @param id identificador único del estudiante
     * @return objeto Estudiante si existe
     * @throws EstudianteNoEncontradoException si no existe un estudiante con ese ID
     */
    public Estudiante buscarEstudiante(String id) throws EstudianteNoEncontradoException {
        Estudiante estudiante = estudiantes.get(id);
        if (estudiante == null) {
            throw new EstudianteNoEncontradoException("No existe estudiante con ID: " + id);
        }
        return estudiante;
    }

    /**
     * Elimina un estudiante del sistema.
     * Guarda el objeto Estudiante completo en pilaDeshacer como estadoAnterior
     * para poder restaurarlo si el usuario deshace la operación.
     *
     * @param id identificador del estudiante a eliminar
     * @throws EstudianteNoEncontradoException si no existe un estudiante con ese ID
     */
    public void eliminarEstudiante(String id) throws EstudianteNoEncontradoException {
        Estudiante estudiante = buscarEstudiante(id);
        estudiantes.remove(id);
        // Guarda el estudiante completo para restaurarlo si se deshace
        pilaDeshacer.push(new Operacion(
            "ELIMINAR_ESTUDIANTE",
            "Eliminar estudiante: " + estudiante.getNombre(),
            estudiante,
            java.time.LocalDateTime.now().toString() // Guarda la fecha y hora actual
        ));
        System.out.println("Estudiante eliminado exitosamente.");
    }

    /**
     * Lista todos los estudiantes registrados en el sistema.
     * Recorre los valores del HashMap e imprime la información de cada uno.
     */
    public void listarEstudiantes() {
        if (estudiantes.isEmpty()) {
            System.out.println("No hay estudiantes registrados.");
            return;
        }
        for (Estudiante e : estudiantes.values()) {
            System.out.println(e.mostrarInformacion());
            System.out.println("--------------------");
        }
    }

    /**
     * Deshace la última operación realizada.
     * Saca la operación del tope de pilaDeshacer, restaura el estado anterior
     * y mueve esa operación a pilaRehacer por si el usuario quiere rehacerla.
     *
     * @throws PilaDeshacerVaciaException si pilaDeshacer está vacía
     */
    public void deshacer() throws PilaDeshacerVaciaException {
        if (pilaDeshacer.isEmpty()) {
            throw new PilaDeshacerVaciaException("No hay operaciones para deshacer.");
        }
        Operacion operacion = pilaDeshacer.pop();
        pilaRehacer.push(operacion);

        if (operacion.getTipo().equals("ELIMINAR_ESTUDIANTE")) {
            Estudiante estudiante = (Estudiante) operacion.getEstadoAnterior();
            estudiantes.put(estudiante.getId(), estudiante);
            System.out.println("Operación deshecha: " + estudiante.getNombre() + " restaurado.");
        } else if (operacion.getTipo().equals("REGISTRAR_ESTUDIANTE")) {
            System.out.println("Operación deshecha: " + operacion.getDescripcion());
        }
    }

    /**
     * Rehace la última operación deshecha.
     * Saca la operación del tope de pilaRehacer y la vuelve a ejecutar.
     *
     * @throws PilaDeshacerVaciaException si pilaRehacer está vacía
     */
    public void rehacer() throws PilaDeshacerVaciaException {
        if (pilaRehacer.isEmpty()) {
            throw new PilaDeshacerVaciaException("No hay operaciones para rehacer.");
        }
        Operacion operacion = pilaRehacer.pop();
        pilaDeshacer.push(operacion);
        System.out.println("Operación rehecha: " + operacion.getDescripcion());
    }

    // Getters
    public HashMap<String, Estudiante> getEstudiantes() { return estudiantes; }
    public Facultad[] getFacultades() { return facultades; }
}