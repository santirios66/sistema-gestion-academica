package services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

import exceptions.EstudianteNoEncontradoEnMateriaException;
import exceptions.MateriaNoEncontradaException;
import exceptions.MateriaYaExisteException;
import exceptions.PreRequisitoNoAprobadoException;
import model.Estudiante;

import model.Materia;

import model.SolicitudInscripcion;

public class GestorMaterias {

    // Atributos principales del gestor

    // HashMap donde la clave es el código de la materia.
    // Permite buscar materias rápidamente usando el código.
    private HashMap<String, Materia> materias;

    // Cola FIFO que guarda solicitudes de inscripción
    // para procesarlas después en lote (batch).
    private Queue<SolicitudInscripcion> colaBatch;

    // Constructor que inicializa las estructuras vacias
    public GestorMaterias() {
        this.materias = new HashMap<>();
        this.colaBatch = new LinkedList<>();
    }

    /*
     * Crea una nueva materia en el sistema.
     * Antes de crearla verifica que no exista otra
     * con el mismo código.
     */

    public void crearMateria(String codigo, String nombre, int cupos, int creditos) throws MateriaYaExisteException {
        if (materias.containsKey(codigo)) {
            throw new MateriaYaExisteException("Ya existe una materia con codigo: " + codigo);
        }

        Materia materia = new Materia(codigo, nombre, cupos, creditos);
        materias.put(codigo, materia);

        System.out.println("Materia creada exitosamente.");
    }

    /*
     * Busca una materia usando su código.
     * Si no existe se lanza una excepción.
     * si existe la retorna.
     */
    public Materia buscarMateria(String codigo) throws MateriaNoEncontradaException {
        Materia materia = materias.get(codigo); // Busca la materia usando el código
        if (materia == null) {
            throw new MateriaNoEncontradaException("No existe materia con codigo: " + codigo);
        }
        return materia;
    }

    /*
     * Inscribe un estudiante en una materia.
     * Primero revisa que cumpla prerequisitos.
     * Si la materia tiene cupos se inscribe normal.
     * Si está llena se agrega a cola de espera.
     */
    public void inscribirEstudiante(Estudiante estudiante, String codigoMateria)
            throws MateriaNoEncontradaException, PreRequisitoNoAprobadoException {
        Materia materia = buscarMateria(codigoMateria); // lanza MateriaNoEncontradaException si no existe

        // Verificar prerequisitos antes de inscribir
        if (!materia.verificarPrerequisitos(estudiante)) { // Si no cumple prerequisitos no puede inscribirse
            throw new PreRequisitoNoAprobadoException(
                    "El estudiante " + estudiante.getNombre() +
                            " no cumple los prerequisitos de la materia " + codigoMateria);
        }

        // inscribir() maneja internamente:
        // Si hay cupo = agrega a estudiantesInscritos y sube cuposActuales
        // Si no hay cupo = agrega a colaEspera

        if (!materia.estaLlena()) { // Si todavía hay cupos disponibles se inscribe

            materia.inscribir(estudiante);

            System.out.println(
                    "Estudiante inscrito exitosamente.");

        } else { // Si la materia está llena entra a cola de espera

            materia.getColaEspera().add(estudiante);

            System.out.println(
                    "Sin cupos. Agregado a cola de espera.");
        }
    }

    /*
     * Cancela la inscripción de un estudiante.
     * Busca el estudiante dentro de la lista de inscritos.
     * Si no aparece se lanza una excepción.
     * Al cancelar se libera el cupo automáticamente.
     */

    public void cancelarInscripcion(
            String idEstudiante,
            String codigoMateria)

            throws MateriaNoEncontradaException,
            EstudianteNoEncontradoEnMateriaException {

        Materia materia = buscarMateria(codigoMateria);

        Estudiante estudianteEncontrado = null;

        // Buscar estudiante en la lista
        for (Estudiante e : materia.getEstudiantesInscritos()) {

            if (e.getId().equals(idEstudiante)) {

                estudianteEncontrado = e;
                break;
            }
        }

        // Si no existe en la materia
        if (estudianteEncontrado == null) {

            throw new EstudianteNoEncontradoEnMateriaException(
                    "El estudiante "
                            + idEstudiante
                            + " no está inscrito en la materia.");
        }

        // Cancelar inscripción
        materia.cancelarInscripcion(estudianteEncontrado);

        System.out.println("Inscripción cancelada exitosamente.");
    }

    // Mustra todas las materias registradas.
    public void mostrarMaterias() {

        if (materias.isEmpty()) {

            System.out.println(
                    "No hay materias registradas.");

            return;
        }

        for (Materia materia : materias.values()) {

            System.out.println(
                    materia.toString());

            System.out.println(
                    "----------------------");
        }
    }

    /*
     * Muestra todos los estudiantes que están
     * esperando un cupo en una materia.
     */
    public void mostrarColaEspera(
            String codigoMateria)

            throws MateriaNoEncontradaException {

        Materia materia = buscarMateria(codigoMateria);

        if (materia.getColaEspera().isEmpty()) {

            System.out.println(
                    "No hay estudiantes en cola.");

            return;
        }

        System.out.println(
                "Cola de espera:");

        for (Estudiante e : materia.getColaEspera()) {

            System.out.println(
                    e.getNombre());
        }

    }

    /*
     * Simula la carga de solicitudes desde un archivo.
     * Las solicitudes quedan listas para procesarse
     * después usando la cola batch.
     */
    public void cargarBatch(String archivo) {
        try {
            // Lee el archivo línea por línea
            java.io.BufferedReader br = new java.io.BufferedReader(
                    new java.io.FileReader(archivo));

            String linea;
            // Salta la primera línea que es el encabezado
            br.readLine();

            while ((linea = br.readLine()) != null) {
                String[] partes = linea.split(",");
                String idEstudiante = partes[0].trim();
                String codigoMateria = partes[1].trim();
                // Crea la solicitud y la mete a la cola
                colaBatch.add(new SolicitudInscripcion(idEstudiante, codigoMateria));
            }
            br.close();
            System.out.println("Se encolaron " + colaBatch.size() + " solicitudes.");

        } catch (Exception e) {
            System.out.println("Error leyendo archivo: " + e.getMessage());
        }
    }

    /*
     * Procesa todas las solicitudes almacenadas
     * en la cola batch usando el principio FIFO:
     * el primero en entrar es el primero en salir.
     */
    public void procesarBatch() {

        if (colaBatch.isEmpty()) {

            System.out.println(
                    "No hay solicitudes pendientes. ");

            return;
        }

        while (!colaBatch.isEmpty()) {

            SolicitudInscripcion solicitud = colaBatch.poll();

            System.out.println(
                    "Procesando solicitud de : "
                            + solicitud.getIdEstudiante());
        }
    }

}
