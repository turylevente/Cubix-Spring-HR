package hu.cubix.hr.tlevi.dtos;

public class ErrorDto {
    private final String status;
    private final String message;

    public ErrorDto(String status, String message) {
        this.status = status;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
