package exceptions;

/**
 * Excepción lanzada cuando GestorReportes intenta procesar
 * un archivo batch inválido, corrupto o con formato incorrecto.
 * Usada en: GestorReportes → procesarBatch()
 */
public class ArchivoInvalidoException extends Exception {

    private String mensaje;

    public ArchivoInvalidoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
