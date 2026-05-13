package exceptions;

public class SemestreInvalidoException extends Exception {
     private String mensaje;

    public SemestreInvalidoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }
}
