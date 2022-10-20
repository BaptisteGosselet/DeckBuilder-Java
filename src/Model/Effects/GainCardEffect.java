package Model.Effects;

import Controller.*;
import Model.*;
import Model.Cards.*;
import View.*;

public class GainCardEffect implements Effect{
    private String name;
    private String description;
    private Card card;

    public GainCardEffect(String _name, String _description, Card _card){
        this.name = _name;
        this.description = _description;
        this.card = _card;
    }

    public GainCardEffect(Displayer d, Card _card){
        this(d.getGainEffectName(),d.getGainEffectDescrition(_card),_card);
    }

    public GainCardEffect(Displayer d){
        this(d,null);
    }

    public void applyEffect(GameController gc, FighterCard fc, Displayer displayer, Player mainPlayer, Player opponent){
        
        Card c;
        if(this.card != null){
            c = this.card;
        }
        else{
            c = gc.pickACard();
        }
        if(c != null){
            displayer.displayEffectActivation(fc,fc.getEffect());
            mainPlayer.addCard(c);
        }
        
    }

    public String getName(){
        return this.name;
    }

    public String description(){
        return this.description;
    }
}