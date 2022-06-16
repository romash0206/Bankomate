package bankomate.exceptions;

public class CardBlockException extends RuntimeException {
    public CardBlockException() {
        super("Попыток не осталось. Проследуйте в отделение вашего банка");
    }

}
