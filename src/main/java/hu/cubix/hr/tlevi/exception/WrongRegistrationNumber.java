package hu.cubix.hr.tlevi.exception;

public class WrongRegistrationNumber extends RuntimeException {
    public WrongRegistrationNumber() {
        super("Registration number is taken");
    }
}
