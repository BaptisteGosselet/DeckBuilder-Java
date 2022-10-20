package Model.Effects;

import Controller.*;
import Model.*;
import Model.Cards.*;
import View.*;

public class NullEffect implements Effect{
    private String name;
    private String description;

    public NullEffect(String _name, String _description){
        this.name = _name;
        this.description = _description;
    }

    public NullEffect(Displayer d){
        this(d.getNullEffectName(),"");
    }

    public void applyEffect(GameController gc, FighterCard fc, Displayer displayer, Player mainPlayer, Player opponent){
        //Pas d'effet
    }

    public String getName(){
        return this.name;
    }

    public String description(){
        return this.description;
    }
}