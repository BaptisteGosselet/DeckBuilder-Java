package Model.Cards;

public class MoneyCard implements Card{
    private String name;
    private int price;
    private int amount;

    public MoneyCard(String _name, int _price, int _amount){
        this.name = _name;
        this.price = _price;
        this.amount = _amount;
    }

    public String getName(){
        return this.name;
    }

    public int getPrice(){
        return this.price;
    }

    public int getAmount(){
        return this.amount;
    }

    public String toString(){
        return "[" + this.name + " $ " + this.amount + "]";
    }

}