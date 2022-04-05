package sk.tuke.kpi.kp.game.service;

public class GameStudioException extends RuntimeException {

    public GameStudioException(String message) {
        super(message);
    }

    public GameStudioException(String message, Throwable cause) {
        super(message, cause);
    }

    public GameStudioException(Throwable cause) {
        super(cause);
    }
}
