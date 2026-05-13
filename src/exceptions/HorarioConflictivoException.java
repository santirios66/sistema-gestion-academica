package exceptions;

/**
 * Excepción lanzada cuando se intenta asignar un aula en un horario
 * que ya está ocupado por otra clase.
 * Usada en: GestorHorarios → agregarAula() / liberarAula()
 */
public class HorarioConflictivoException extends Exception {

    private String mensaje;

    public HorarioConflictivoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
