package bankomate.exceptions;

public class NoEnoughMoneyException extends Exception {
    public NoEnoughMoneyException() {
        super("Недостаточно средств");
    }
}
