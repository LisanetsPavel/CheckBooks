package checkbooks.exception;

public class NoIdException extends RuntimeException {

    private Throwable cause;

    public NoIdException() {
    }

    public NoIdException(Throwable cause) {
        super(cause);
    }

    @Override
    public Throwable getCause() {
        return cause;
    }

    public void setCause(Throwable cause) {
        this.cause = cause;
    }
}
