package View;

import java.util.ArrayList;

import Model.*;
import Model.Cards.*;
import Model.Effects.*;

public interface Displayer{
    public String askPlayerName(Player player1);
    public void displayResetUsedCards(Player p);
    public void displayPlayerTurn(Player p);
    public void displayGetMoney(Player p, int amount, ArrayList<MoneyCard> cards);
    public int askPurchaseCards(ArrayList<Card> market, int amount);
    public void displayPurchaseCard(Card c, boolean b);
    public void displayAttackCards(ArrayList<AttackCard> cards);
    public void displayFighterCards(ArrayList<FighterCard> cards);
    public void displayDamage(Player p1, Player p2, int damage);
    public void displayHeal(Player p, int heal);
    public void displayEffectActivation(FighterCard c, Effect e);
    public void displayGetCard(Player p, Card c);
    public int askCardToThrow(ArrayList<Card> cards);
    public void displayThrowCard(Player p, Card c);
    public void pressToContinue();
    public void displayWinner(Player winner, Player looser);
    public void clean();
    public String getDamageEffectName();
    public String getHealEffectName();
    public String getGainEffectName();
    public String getThrowEffectName();
    public String getNullEffectName();
    public String getDamageEffectDescrition(int hp);
    public String getHealEffectDescrition(int hp);
    public String getGainEffectDescrition(Card c);
    public String getThrowEffectDescrition();
    
}
