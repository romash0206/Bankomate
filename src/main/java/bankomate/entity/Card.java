package bankomate.entity;
public class Card {
    private long cardNumber;
    private int pin;
    private String currency;
    private int cashAmount;

    public Card(long cardNumber, int pin, String currency,int cashAmount) {
        this.cardNumber = cardNumber;
        this.pin = pin;
        this.currency = currency;
        this.cashAmount = cashAmount;
    }

    public long getCardNumber() {
        return cardNumber;
    }

    public int getPin() {
        return pin;
    }

    public String getCurrency() {
        return currency;
    }

    public void setPin(int pin) {
        this.pin = pin;
    }

    public int getCashAmount() {
        return cashAmount;
    }

    public void setCashAmount(int cashAmount) {
        this.cashAmount = cashAmount;
    }
}
