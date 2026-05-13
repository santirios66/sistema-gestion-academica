package exceptions;

public class EdificioNoEncontradoException extends Exception {

    private String mensaje;

    public EdificioNoEncontradoException(String mensaje) {
        super(mensaje);
        this.mensaje = mensaje;
    }

    @Override
    public String getMessage() {
        return mensaje;
    }

}
