package View;

import java.util.ArrayList;
import java.util.Scanner;

import Model.*;
import Model.Cards.*;
import Model.Effects.*;

public class FrenchTextDisplayer extends TextDisplayer{
  
    Scanner in = new Scanner(System.in);

    public FrenchTextDisplayer(){

    }

    public String askPlayerName(Player player1){
        
        if(player1 == null){
            System.out.println("Joueur 1, veuillez entrer votre nom : ");
        }     
        else{
            System.out.println("Joueur 2, veuillez entrer votre nom : "); 
        }    
    
        String player_name = in.nextLine();    

        if(player1 != null && player1.getName().toLowerCase().equals(player_name.toLowerCase())){
            System.out.println("Ce nom est déjà pris.");
            return askPlayerName(player1);
        }

        if(player_name.equals("")){
            System.out.println("Le nom ne peut pas être vide.");
            return askPlayerName(player1);
        }

        if(player_name.length() > 12){
            System.out.println("Le nom doit faire moins de 12 caractères.");
            return askPlayerName(player1);
        }

        if(!Character.isLetter(player_name.charAt(0))){
            System.out.println("Le nom doit commencer par une lettre.");
            return askPlayerName(player1);
        }

        player_name = player_name.substring(0, 1).toUpperCase() + player_name.substring(1).toLowerCase();
        return player_name;

    }

    public void pressToContinue(){
        System.out.println("Appuyer sur ENTREE pour continuer...");
        in.nextLine();
        System.out.println("");
    }

    public void displayResetUsedCards(Player p){
        System.out.println("Les cartes de " + p.getName() + " ont été mélangées dans la fosse.\n");
    }

    public void displayPlayerTurn(Player p){
        System.out.println("-- TOUR DE " + p.getName() + " : " + ANSI_GREEN + p.getHp() + ANSI_WHITE  + " PV."+" --");

    }

    public void displayGetMoney(Player p, int amount, ArrayList<MoneyCard> cards){
        if(amount>0){     
            for(int i=0; i<cards.size();i++) System.out.println(ANSI_GREEN + cards.get(i) + " " + ANSI_YELLOW + cards.get(i).getAmount() + ANSI_WHITE);
            if(amount==1) System.out.println(p.getName() + " reçoit " + ANSI_YELLOW + "1" + ANSI_WHITE + " pièce.");
            else System.out.println(p.getName() + " reçoit " + ANSI_YELLOW + "" + amount + "" + ANSI_WHITE + " pièces.");
            
        }
        else{
            System.out.println(ANSI_YELLOW + p.getName() + " ne reçoit pas d'argent." + ANSI_WHITE);
        }        
        System.out.println("");
    }

    public void displayAttackCards(ArrayList<AttackCard> cards){
        if(cards.size() == 0){
            System.out.println("Aucune carte Attaque n'a été tirée.");
        }
        else{
            System.out.println("Liste des cartes d'attaque tirées : ");
            for(int i=0; i<cards.size();i++) System.out.println(ANSI_BLUE + cards.get(i) + "\t" + ANSI_RED+ cards.get(i).getPower() + ANSI_WHITE);
        }
        System.out.println("");
        
    }

    public void displayFighterCards(ArrayList<FighterCard> cards){
        if(cards.size() == 0){
            System.out.println("Aucune carte Champion n'a été tirée.");
        }
        else{
            System.out.println("Liste des Champions tirés : ");
            for(int i=0; i<cards.size();i++) System.out.println(ANSI_CYAN + cards.get(i) + "\t" + ANSI_RED+ cards.get(i).getPower() + " " + ANSI_GREEN + cards.get(i).getHeal() + ANSI_WHITE);
        }
        System.out.println("");
        
    }

    public int askPurchaseCards(ArrayList<Card> market, int amount){
        
        System.out.println("-- Marché aux cartes --");
        System.out.println("Vous possedez " + ANSI_YELLOW + amount + ANSI_WHITE + " pièces.");
        for(int i=0;i<market.size();i++){
            if(market.get(i) instanceof MoneyCard) System.out.print(ANSI_YELLOW + market.get(i).getPrice() + "$" + ANSI_GREEN + market.get(i) + "\t"+ ANSI_WHITE);
            else if(market.get(i) instanceof AttackCard) System.out.print(ANSI_YELLOW + market.get(i).getPrice() + "$" + ANSI_BLUE + market.get(i) + "\t" + ANSI_WHITE);
            else if(market.get(i) instanceof FighterCard) System.out.print(ANSI_YELLOW + market.get(i).getPrice() + "$" + ANSI_PURPLE + market.get(i) + "\t" + ANSI_WHITE);

            System.out.println("[" + (i+1) + "]" + ".");
            
        }


        while(true){
            System.out.println("Entrez le numéro de la carte qui vous intéresse (ou '0' pour ne pas acheter) :");
            
            String input = in.nextLine(); 

            try{
                int n = Integer.parseInt(input);

                if(0 <= n && n <= market.size()){
                    System.out.println("");
                    return n-1;
                }
                else{
                    System.out.println(input + " n'est pas un nombre valide.");
                }

            }
            catch(Exception numberFormatException){
                System.out.println("\'" + input + "\' n'est pas un nombre.");
            }
            
        }
    }

    public void displayPurchaseCard(Card c, boolean b){
        if (b) System.out.println(ANSI_GREEN + "L'achat de " + c.getName() + " s'est bien déroulé.\n" + ANSI_WHITE);
        else System.out.println(ANSI_RED + "L'achat de " + c.getName() + " ne s'est pas déroulé.\n" + ANSI_WHITE);
    }

    public void displayDamage(Player p1, Player p2, int damage){
        System.out.println(ANSI_PURPLE + p1.getName() + " inflige " + damage + " PV à " + p2.getName() + " !");
        System.out.println(p2.getName() + " possède maintenant " + (p2.getHp()-damage) + " PV." + "\n" + ANSI_WHITE);
    }    
    
    public void displayHeal(Player p, int heal){
        System.out.println(ANSI_GREEN + p.getName() + " récupère " + heal + " PV.");
        System.out.println(p.getName() + " possède maintenant " + (p.getHp()+heal) + " PV." + "\n" + ANSI_WHITE);
    }
    
    public void displayEffectActivation(FighterCard c, Effect e){
        System.out.println(ANSI_CYAN + c.getName() + ANSI_WHITE + " active son effet secondaire " + ANSI_YELLOW + e.getName() + ANSI_WHITE);
    }

    public void displayGetCard(Player p, Card c){
        System.out.println(p.getName() + " pioche la carte " + c.getName() + "\n");
    }

    public int askCardToThrow(ArrayList<Card> cards){
        System.out.println("-- Vos Cartes --");

        for(int i=0;i<cards.size();i++){
            System.out.print(cards.get(i));
            System.out.println("\t[" + (i+1) + "]" + ".");
        }

        while(true){
            System.out.println(ANSI_RED+"\nEntrez le numéro de la carte à jeter (ou '0' pour ne rien faire) :"+ANSI_WHITE);
            String input = in.nextLine(); 
            try{
                int n = Integer.parseInt(input);
                if(0 <= n && n <= cards.size()){
                    System.out.println("");
                    return n-1;
                }
                else System.out.println(input + " n'est pas un nombre valide.");
            }
            catch(Exception numberFormatException){
                System.out.println("\'" + input + "\' n'est pas un nombre.");
            }
            
        }
    }

    public void displayThrowCard(Player p, Card c){
        System.out.println(ANSI_RED+p.getName()+" jette la carte "+ c.getName() + "\n"+ANSI_WHITE);
    }

    public void displayWinner(Player winner, Player looser) {
        System.out.println("FIN. " + winner.getName() + " a gagné !");
    }

    public String getDamageEffectName() {
        return "Dégat Bonus";
    }

    public String getHealEffectName() {
        return "Soin Bonus";
    }

    public String getGainEffectName() {
        return "Pioche";
    }

    public String getThrowEffectName() {
        return "Jet de Carte";
    }

    public String getDamageEffectDescrition(int hp) {
        return "Inflige " + hp + ".";
    }

    public String getHealEffectDescrition(int hp) {
        return "Soigne de " + hp + "PV.";
    }

    public String getGainEffectDescrition(Card c) {
        if(c==null){
            return "Pioche une carte.";
        }
        else{
            return "Pioche la carte " + c.getName() + ".";
        }
    }

    public String getThrowEffectDescrition() {
        return "Permet de jeter une carte hors du deck.";
    }

    public String getNullEffectName() {
        return "Aucun Effet.";
    }


}