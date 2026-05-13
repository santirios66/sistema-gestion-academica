package exceptions;

/**
 * Excepción lanzada cuando se intenta procesar la cola de espera
 * de inscripciones y esta se encuentra vacía.
 * Usada en: GestorInscripciones → colaEspera (Queue)
 */
public class ColaDesperaVaciaException extends Exception {

    private String mensaje;

    public ColaDesperaVaciaException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
