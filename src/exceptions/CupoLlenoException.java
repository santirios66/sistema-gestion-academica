package exceptions;

/**
 * Excepción lanzada cuando se intenta inscribir a un estudiante
 * en una materia que ya alcanzó su cupo máximo.
 * Usada en: GestorInscripciones → inscribirEstudiante() / Materias → cuposMaximos
 */
public class CupoLlenoException extends Exception {

    private String mensaje;

    public CupoLlenoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
