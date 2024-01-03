package hu.cubix.hr.tlevi.exception;

public class IncorrectIdException extends RuntimeException {
    public IncorrectIdException() {
        super("Id not found");
    }
}
