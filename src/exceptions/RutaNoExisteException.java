package exceptions;

public class RutaNoExisteException extends Exception {
    private String mensaje;

    public RutaNoExisteException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
    
}
