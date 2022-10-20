package Model.Cards;

import Model.Type;
import Model.Effects.*;

public class FighterCard implements Card{
    private String name;
    private int price;
    private int power;
    private int heal;
    private Type type;
    private Effect effect;
    private boolean effectActivation = false;

    public FighterCard(String _name, int _price, int _power, int _heal, Type _type, Effect _effect){
        this.name = _name;
        this.price = _price;
        this.power = _power;
        this.heal = _heal;
        this.type = _type;
        this.effect = _effect;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public int getPower(){
        return this.power;
    }

    public int getHeal(){
        return this.heal;
    }

    public String toString(){
        return "[" + this.name + " - " + this.type + ", Atk " + this.power + ", Def " + this.heal + ". " + this.effect.description() + "]";
    }

    public Type getType(){
        return this.type;
    }

    public void setEffectActivation(boolean b){
        this.effectActivation = b;
    }

    public boolean getEffectActivation(){
        return this.effectActivation;
    }

    public Effect getEffect(){
        return this.effect;
    }

}