import Controller.*;
import Model.CardFactories.*;
import View.*;

public class Main{

    public static void main(String[] args) throws NotEnoughInitialCardsException{
        FrenchTextDisplayer td = new FrenchTextDisplayer();
        
        CardFactory cf = new GenericCardFactory(td);
    
        GameController gc = new GameController(td, cf);
        gc.newGame();
    }
}