package Model.Cards;

public class AttackCard implements Card{
    private String name;
    private int price;
    private int power;

    public AttackCard(String _name, int _price, int _power){
        this.name = _name;
        this.price = _price;
        this.power = _power;
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

    public String toString(){
        return "[" + this.name + " Atk " + this.power + "]";
    }

}