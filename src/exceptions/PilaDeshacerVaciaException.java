package exceptions;

/**
 * Excepción lanzada cuando se llama a deshacer() o rehacer()
 * pero la pila (Stack) correspondiente está vacía.
 * Usada en: GestorEstudiantes → deshacer() / rehacer()
 *           GestorInscripciones → deshacer() / rehacer()
 */
public class PilaDeshacerVaciaException extends Exception {

    private String mensaje;

    public PilaDeshacerVaciaException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
