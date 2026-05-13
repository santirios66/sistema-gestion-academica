package exceptions;

/**
 * Excepción lanzada cuando un estudiante intenta inscribir una materia
 * sin haber aprobado el prerrequisito necesario.
 * Usada en: GestorInscripciones → verificarPrerequisitos()
 */
public class PreRequisitoNoAprobadoException extends Exception {

    private String mensaje;

    public PreRequisitoNoAprobadoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
