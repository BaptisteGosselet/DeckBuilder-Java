package Model.Effects;

import Controller.*;
import Model.*;
import Model.Cards.*;
import View.*;

public interface Effect{
    public String getName();
    public void applyEffect(GameController gc, FighterCard c, Displayer displayer, Player mainPlayer, Player opponent);
    public String description();
}