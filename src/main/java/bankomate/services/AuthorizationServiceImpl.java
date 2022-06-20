package bankomate.services;

import bankomate.entity.Card;
import bankomate.exceptions.CardBlockException;
import bankomate.exceptions.WrongPinException;

public class AuthorizationServiceImpl implements AuthorizationService {
    private int triesCount = 3;

    public void logIn(Card card, int pin) throws WrongPinException {
        checkTries();
        if (card.getPin() != pin) {
            triesCount--;
            throw new WrongPinException(triesCount);
        }
    }

    public void logOut() {
        checkTries();
    }

    private void checkTries() {
        if (triesCount < 1) {
            throw new CardBlockException();
        }
    }
}
