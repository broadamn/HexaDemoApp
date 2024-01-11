package HexagonalArchitectureDemoApplication.application.domain.exception;

public class InsuficientStock extends RuntimeException {

    public InsuficientStock(String message) {
        super(message);
    }

}