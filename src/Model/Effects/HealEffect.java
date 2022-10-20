package Model.Effects;

import Controller.*;
import Model.*;
import Model.Cards.*;
import View.*;

public class HealEffect implements Effect{
    private String name;
    private String description;
    private int pv;

    public HealEffect(int _pv, String _name, String _description){
        this.name = _name;
        this.description = _description;
        this.pv = _pv;
    }

    public HealEffect(Displayer d, int _pv){
        this(_pv, d.getHealEffectName(), d.getHealEffectDescrition(_pv));
    }

    public void applyEffect(GameController gc, FighterCard c, Displayer displayer, Player mainPlayer, Player opponnent){
        displayer.displayEffectActivation(c, c.getEffect());
        displayer.displayHeal(mainPlayer, this.pv);
        mainPlayer.healHp(this.pv);
    }

    public String getName(){
        return this.name;
    }

    public String description(){
        return this.description;
    }
}