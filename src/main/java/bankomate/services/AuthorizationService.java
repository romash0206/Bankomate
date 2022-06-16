package bankomate.services;

import bankomate.entity.Card;
import bankomate.exceptions.WrongPinException;

public interface AuthorizationService {


    void logIn(Card card,int pin)throws WrongPinException;
    void logOut();
}
