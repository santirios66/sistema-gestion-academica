package exceptions;

public class AulaNoEncontradaException extends Exception {
    
    private String mensaje;

    public  AulaNoEncontradaException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
