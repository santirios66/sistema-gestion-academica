package services;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;
import java.util.TreeMap;

import exceptions.MateriaNoEncontradaException;
import exceptions.MateriaYaExisteException;
import exceptions.PreRequisitoNoAprobadoException;
import model.Estudiante;

import model.Materia;

import model.SolicitudInscripcion;

public class GestorMaterias {

    // Atributos
    private HashMap<String, Materia> materias; // Hashmap para buscar materias por código

    private Queue<SolicitudInscripcion> colaBatch; // Queue para procesar solicitudes de inscripción

    // Construtor que inicializa las estructuras vacias
    public GestorMaterias(TreeMap<String, Materia> materias, Queue<SolicitudInscripcion> colaBatch) {
        this.materias = new HashMap<>();
        this.colaBatch = new LinkedList<>();
    }

    /*
     * crea una materia
     * si ya existe lanza una excepcion
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
     * busca la materia por codigo , si no la encuentra lanza una excepcion.
     * si la encuentra la retorna
     * 
     * 
     */
    public Materia buscarMateria(String codigo) throws MateriaNoEncontradaException {
        Materia materia = materias.get(codigo);
        if (materia == null) {
            throw new MateriaNoEncontradaException("No existe materia con codigo: " + codigo);
        }
        return materia;
    }

    // Inscribe un estudiante a una materia:
    // 1. Verifica que la materia exista.
    // 2. Verifica prerequisitos → lanza PreRequisitoNoAprobadoException si no los cumple.
    // 3. Si hay cupo → inscribe directamente.
    //    Si no hay cupo → agrega a la cola de espera.

    public void inscribirEstudiante(Estudiante estudiante, String codigoMateria)
            throws MateriaNoEncontradaException, PreRequisitoNoAprobadoException {
        Materia materia = buscarMateria(codigoMateria); // lanza MateriaNoEncontradaException si no existe

        // Verificar prerequisitos antes de inscribir
        if (!materia.verificarPrerequisitos(estudiante)) {
            throw new PreRequisitoNoAprobadoException(
                "El estudiante " + estudiante.getNombre() +
                " no cumple los prerequisitos de la materia " + codigoMateria);
        }

        // inscribir() maneja internamente:
        // - Si hay cupo → agrega a estudiantesInscritos y sube cuposActuales
        // - Si no hay cupo → agrega a colaEspera
        materia.inscribir(estudiante);

        if (materia.estaLlena()) {
            System.out.println("Sin cupo. " + estudiante.getNombre() +
                " fue agregado a la cola de espera de " + codigoMateria);
        } else {
            System.out.println("Estudiante inscrito exitosamente en " + codigoMateria);
        }
    }

}
