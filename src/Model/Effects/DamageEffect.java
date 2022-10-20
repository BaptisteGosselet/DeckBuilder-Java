package Model.Effects;

import Controller.*;
import Model.*;
import Model.Cards.*;
import View.*;

public class DamageEffect implements Effect{
    private String name;
    private String description;
    private int pv;

    public DamageEffect(int _pv, String _name, String _description){
        this.name = _name;
        this.description = _description;
        this.pv = _pv;
    }

    public DamageEffect(Displayer d, int _pv){
        this(_pv,d.getDamageEffectName(),d.getDamageEffectDescrition(_pv));
    }


    public void applyEffect(GameController gc, FighterCard c, Displayer displayer, Player mainPlayer, Player opponent){
        displayer.displayEffectActivation(c, c.getEffect());
        displayer.displayDamage(mainPlayer, opponent, this.pv);
        opponent.withdrawHp(this.pv);
    }

    public String getName(){
        return this.name;
    }

    public String description(){
        return this.description;
    }
}