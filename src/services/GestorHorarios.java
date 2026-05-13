package services;

import java.util.TreeMap;

import exceptions.AulaNoEncontradaException;
import exceptions.AulaYaExisteException;
import exceptions.HorarioConflictivoException;
import model.Aula;

/**
 * Servicio que gestiona los horarios de las aulas.
 * Usa TreeMap para mantener las aulas ordenadas por nombre.
 */

public class GestorHorarios {

    // TreeMap ordena las aulas alfabéticamente por nombre automáticamente
    private TreeMap<String, Aula> aulas;

    public GestorHorarios() {
        this.aulas = new TreeMap<>();
    }

    public void agregarAula(Aula aula) throws AulaYaExisteException {
        if (aulas.containsKey(aula.getNombre())) {

            throw new AulaYaExisteException(
                    "Ya existe un aula con ese nombre." + aula.getNombre());
        }

        aulas.put(aula.getNombre(), aula);
        System.out.println("Aula agregada exitosamente.");
    }

    /**
     * Busca un aula por nombre, lanza excepción si no existe.
     */
    public Aula buscarAula(String nombre) throws AulaNoEncontradaException {

        Aula aula = aulas.get(nombre);
        if (aula == null) {
            throw new AulaNoEncontradaException("No existe aula con nombre: " + nombre);
        }
        return aula;
    }

    /**
     * Reserva un horario en un aula específica.
     */
    public void reservar(String nombreAula, int dia, int hora, int duracion)
            throws AulaNoEncontradaException, HorarioConflictivoException {
        Aula aula = buscarAula(nombreAula);
        // Verifica cada hora antes de reservar
        for (int i = hora; i < hora + duracion; i++) {
            if (!aula.consultarDisponibilidad(dia, hora)) {
                throw new HorarioConflictivoException("Horario ocupado en aula: " + nombreAula);
            }
            // Si todas las horas están libres realiza la reserva
            aula.reservar(dia, hora, duracion);

            System.out.println("Horario reservado exitosamente.");
        }
    }

    /**
     * Libera un horario previamente reservado.
     */
    public void liberar(String nombreAula, int dia, int hora, int duracion)
            throws AulaNoEncontradaException {
        Aula aula = buscarAula(nombreAula);

        aula.liberar(dia, hora, duracion);

        System.out.println(
                "Horario liberado exitosamente.");
    }

    /**
     * Consulta si una hora está disponible en un aula.
     */
    public boolean consultarDisponibilidad(String nombreAula, int dia, int hora)
            throws AulaNoEncontradaException {
        Aula aula = buscarAula(nombreAula);

        return aula.consultarDisponibilidad(
                dia,
                hora);
    }

    /**
     * Muestra el horario completo de un aula.
     */
    public void mostrarHorarioAula(String nombreAula) throws AulaNoEncontradaException {
        Aula aula = buscarAula(nombreAula);
        aula.mostrarHorario();
    }

}
