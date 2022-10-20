package Controller;

public class NotEnoughInitialCardsException extends Exception{

    public NotEnoughInitialCardsException(){
        super("The number of cards dealt at the beginning of the game is less than the number of usable cards per turn.");
    }

}