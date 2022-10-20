package Model.CardFactories;

import Model.Cards.*;
import java.util.ArrayList;

public interface CardFactory{
    public Card[] getInitialDeck();
    public ArrayList<Card> generateCards();
}