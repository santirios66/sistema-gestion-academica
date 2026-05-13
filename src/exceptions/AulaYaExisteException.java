package exceptions;

public class AulaYaExisteException extends Exception {
    
    private String mensaje;

    public  AulaYaExisteException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
