package Model.Effects;

import java.util.ArrayList;

import Controller.*;
import Model.*;
import Model.Cards.*;
import View.*;

public class ThrowCardEffect implements Effect{
    private String name;
    private String description;

    public ThrowCardEffect(String _name, String _description){
        this.name = _name;
        this.description = _description;
    }

    public ThrowCardEffect(Displayer d){
        this(d.getThrowEffectName(),d.getThrowEffectDescrition());
    }

    public void applyEffect(GameController gc, FighterCard fc, Displayer displayer, Player mainPlayer, Player opponent){
        ArrayList<Card> listOfCards = mainPlayer.getUsedCards();
        if(listOfCards.size()>0){
            displayer.displayEffectActivation(fc,fc.getEffect());
            int indexToThrow = displayer.askCardToThrow(mainPlayer.getUsedCards());
            if(indexToThrow > -1) mainPlayer.throwFromUsedCard(listOfCards.get(indexToThrow));
        }
    }

    public String getName(){
        return this.name;
    }

    public String description(){
        return this.description;
    }
}