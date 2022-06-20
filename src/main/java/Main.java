import bankomate.entity.Bankomate;
import bankomate.entity.Card;

public class Main {

    public static void main(String[] args) {
        Card card = new Card(4132, 7777, "BYN", 100);
        Bankomate bankomate = new Bankomate(card);
        bankomate.run();
    }
}
