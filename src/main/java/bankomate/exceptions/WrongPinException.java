package bankomate.exceptions;

public class WrongPinException extends Exception {
    public WrongPinException(int triesCount) {
        super("Неверный пинкод. Осталось попыток: " + triesCount);
    }

}
