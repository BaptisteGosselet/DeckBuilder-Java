package Controller;

import java.util.ArrayList;

import View.*;
import Model.*;
import Model.CardFactories.*;
import Model.Cards.*;

public class GameController{
    private Displayer displayer;
    private CardFactory cardFactory;

    private Player player_1;
    private Player player_2;

    public static final int NUMBER_OF_CARDS_DURING_TURN = 5;
    static final int SIZE_OF_MARKET = 4;

    private ArrayList<Card> globalPick = new ArrayList<Card>();
    private ArrayList<Card> market = new ArrayList<Card>();

    public GameController(Displayer _displayer, CardFactory _cardFactory){
        this.displayer = _displayer;
        this.cardFactory = _cardFactory;
        this.globalPick = cardFactory.generateCards();
        this.player_1 = new Player(displayer);
        this.player_2 = new Player(displayer);
    }

    public void newGame() throws NotEnoughInitialCardsException{
        this.initialize();
        this.playTurn(player_1,player_2);
    }

    public void initialize() throws NotEnoughInitialCardsException{
        
        //initialize the two player
        String player_name = displayer.askPlayerName(null);
        this.player_1.setName(player_name);


        player_name = displayer.askPlayerName(this.player_1);
        this.player_2.setName(player_name);

        //distribute the cards
        this.globalPick = cardFactory.generateCards();
        Card[] initialDeck = cardFactory.getInitialDeck();

        if(initialDeck.length < NUMBER_OF_CARDS_DURING_TURN){
            throw new NotEnoughInitialCardsException();
        }

        for(int i=0;i<initialDeck.length;i++){
            player_1.addCard(initialDeck[i]);
            player_2.addCard(initialDeck[i]);
        }

    }

    public void updateMarket(){
        while(market.size()<SIZE_OF_MARKET){
            if(globalPick.size() > 0){ //If there are any cards left in the global pick
                market.add(globalPick.get(0));
                globalPick.remove(0);
            }
            else {
                //Prévenir dans le displayer ?
                break;
            }
        }
    }

    public int marketMinimumPrice(){
        if(market.size() <= 0) return -1;
        int minPrice = market.get(0).getPrice();
        for(int i=0; i<market.size();i++){
            if(market.get(i).getPrice() < minPrice) minPrice = market.get(i).getPrice();
        }
        return minPrice;
    }

    public Card pickACard(){
        if(this.globalPick.size()>0){
            Card c = globalPick.get(0);
            globalPick.remove(0);
            return c;
        }
        return null;
    }

    public void playTurn(Player mainPlayer, Player opponent){
        
        displayer.displayPlayerTurn(mainPlayer);

        Card[] playerCards = mainPlayer.getTurnCards();
    
        ArrayList<MoneyCard> moneyCards = new ArrayList<MoneyCard>();
        ArrayList<AttackCard> attackCards = new ArrayList<AttackCard>();
        ArrayList<FighterCard> fighterCards = new ArrayList<FighterCard>();
        
        for(int i=0;i<playerCards.length;i++){
            if(playerCards[i] instanceof MoneyCard){
                moneyCards.add((MoneyCard)playerCards[i]);
            }
            if(playerCards[i] instanceof AttackCard){
                attackCards.add((AttackCard)playerCards[i]);
            }
            if(playerCards[i] instanceof FighterCard){
                fighterCards.add((FighterCard)playerCards[i]);
            }
        }

        //Market
        this.updateMarket();
        if(market.size()>0){

            int amount = 0;
            for(int i=0; i<moneyCards.size();i++){
                amount += moneyCards.get(i).getAmount();
            }
            
            displayer.displayGetMoney(mainPlayer, amount, moneyCards);

            while(true){
                int index = displayer.askPurchaseCards(market, amount);
                if(index == -1) break;

                if(amount >= market.get(index).getPrice()){
                    mainPlayer.addCard(market.get(index));
                    displayer.displayPurchaseCard(market.get(index), true);
                    amount -= market.get(index).getPrice();
                    market.remove(index);
                    
                }
                else{
                    displayer.displayPurchaseCard(market.get(index), false);
                }
                
                if(market.size()<=0 || amount < marketMinimumPrice()) break;

            }       
        }

        
        //Fighters 
        int fighterDamage = 0;
        int fighterHeal = 0;

        for(int i=0;i<fighterCards.size();i++){
            fighterCards.get(i).setEffectActivation(false);
            fighterDamage += fighterCards.get(i).getPower();
            fighterHeal += fighterCards.get(i).getHeal();
        }
        
        displayer.displayFighterCards(fighterCards);
        
        if(fighterDamage > 0){
            displayer.displayDamage(mainPlayer, opponent, fighterDamage);
            opponent.withdrawHp(fighterDamage);
        }

        if(fighterHeal > 0){
            displayer.displayHeal(mainPlayer, fighterHeal);
            mainPlayer.healHp(fighterHeal);
        }
        //Find figthers' effects to activate
        for(int i=0; i<fighterCards.size();i++){
            for(int j=i+1; j<fighterCards.size();j++){
                if(fighterCards.get(i).getType().equals(fighterCards.get(j).getType())){
                    fighterCards.get(i).setEffectActivation(true);
                    fighterCards.get(j).setEffectActivation(true);
                }
            }
        }

        //Effects
        for(int i=0; i<fighterCards.size();i++){
            if(fighterCards.get(i).getEffectActivation()){
                fighterCards.get(i).getEffect().applyEffect(this,fighterCards.get(i), displayer, mainPlayer, opponent);
            }
        }

        //Faire le throwCardEffect et un gainCardEffect qui donne une carte
        //Rentrer les vraies cartes
        //Avoir un joueur ordinateur


        int attackDamage = 0;
        for(int i=0;i<attackCards.size();i++){
            attackDamage += attackCards.get(i).getPower();
        }
        if(attackDamage > 0){
            displayer.displayAttackCards(attackCards);
            displayer.displayDamage(mainPlayer, opponent, attackDamage);
        }

        opponent.withdrawHp(attackDamage);

        //next turn
        displayer.pressToContinue();
        if(opponent.getHp() <= 0){
            finish(mainPlayer, opponent);
        } 
        else{
            displayer.clean();
            playTurn(opponent, mainPlayer);
        }
    }

    public void finish(Player winner, Player looser){
        //montrer les pv avec un displayer
        displayer.displayWinner(winner, looser);
    }


}