package exceptions;

public class SinNotasException extends Exception{
    private String mensaje;

    public SinNotasException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
