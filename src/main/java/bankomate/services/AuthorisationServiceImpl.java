package bankomate.services;

import bankomate.entity.Card;
import bankomate.exceptions.CardBlockException;
import bankomate.exceptions.WrongPinException;

public class AuthorisationServiceImpl implements AuthorizationService{
    private int triesCount=3;

    public void logIn(Card card, int pin) throws WrongPinException {
        if(card.getPin() != pin) {
            triesCount--;
            throw new WrongPinException(triesCount);
        }
    }

    public void logOut() {
       if(triesCount<1){
           throw new CardBlockException();
       }
    }
}
