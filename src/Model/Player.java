package Model;

import java.util.ArrayList;
import java.util.Collections;

import View.*;
import Controller.*;
import Model.Cards.*;

public class Player{
    static final int HP_MAX = 80;
    
    private Displayer displayer;

    private String name;
    private int hp = HP_MAX;

    private ArrayList<Card> unusedCards = new ArrayList<Card>();
    private ArrayList<Card> usedCards = new ArrayList<Card>();

    public Player(Displayer _displayer){
        this.displayer = _displayer;
    }

    public int getHp(){
        return this.hp;
    }

    public void setName(String _name){
        this.name = _name;
    }

    public String getName(){
        return this.name;
    }

    public void addCard(Card c){
        displayer.displayGetCard(this, c);
        this.usedCards.add(c);
    }

    public Card[] getTurnCards(){

        if(unusedCards.size() < GameController.NUMBER_OF_CARDS_DURING_TURN){
            this.resetUsedCard();
        }

        Card[] turnCards = new Card[5];

        for(int i=0;i<5;i++){
            usedCards.add(unusedCards.get(0));
            turnCards[i] = unusedCards.get(0);
            unusedCards.remove(0);
        }
        
        return turnCards;
    }

    public void resetUsedCard(){
        for(int i=0; i<usedCards.size();i++){
            unusedCards.add(usedCards.get(i));
        }
        usedCards.removeAll(usedCards);
        Collections.shuffle(unusedCards);
        
        this.displayer.displayResetUsedCards(this);
    }

    public Displayer getDisplayer(){
        return this.displayer;
    }

    public void withdrawHp(int n){
        this.hp -= n;
    }

    public void healHp(int n){
        this.hp += n;
        if(hp > HP_MAX) hp = HP_MAX;
    }

    public ArrayList<Card> getUsedCards(){
        return this.usedCards;
    }

    public void throwFromUsedCard(Card c){
        this.usedCards.remove(c);
        displayer.displayThrowCard(this, c);
    }

}