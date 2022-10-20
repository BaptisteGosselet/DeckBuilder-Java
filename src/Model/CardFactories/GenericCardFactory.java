package Model.CardFactories;

import java.util.ArrayList;
import java.util.Collections;

import Model.Type;
import Model.Cards.*;
import Model.Effects.*;
import View.Displayer;

public class GenericCardFactory implements CardFactory{
    Displayer displayer;

    Type typeBleu = new Type("Faction de la guilde");
    Type typeRouge = new Type("Faction de Nécros");
    Type typeVert = new Type("Faction Impériale");
    Type typeJaune = new Type("Faction Sauvage");

    public GenericCardFactory(Displayer d){
        this.displayer = d;
    }

    public Card[] getInitialDeck(){
        Card[] cards = new Card[]{
            new AttackCard("Dague", 0, 1),
            new AttackCard("Dague", 0, 1),
            new AttackCard("Dague", 0, 1),
            new AttackCard("Dague", 0, 1),
            new AttackCard("Epée courte", 0, 2), 
            new MoneyCard("Pièce d'or", 0, 1),
            new MoneyCard("Pièce d'or", 0, 1),
            new MoneyCard("Pièce d'or", 0, 1),
            new MoneyCard("Pièce d'or", 0, 1),
            new MoneyCard("Pièce d'or", 0, 1),
            new MoneyCard("Pièce d'or", 0, 1),
            new MoneyCard("Pièce d'or", 0, 1),            
            new MoneyCard("Rubis", 0, 2)
        };
        return cards;
    }

    public ArrayList<Card> generateCards(){

        ArrayList<Card> cards = new ArrayList<Card>();

        //Money
        for(int i=0; i<16;i++) cards.add(new MoneyCard("Gemme de feu", 2, 2)); 
        for(int i=0; i<2;i++) cards.add(new MoneyCard("Don Elfique", 2, 2));
        for(int i=0; i<3;i++) cards.add(new MoneyCard("Bénéfice", 1, 2));
        for(int i=0; i<3;i++) cards.add(new MoneyCard("Influence", 2, 3));
        for(int i=0; i<3;i++) cards.add(new MoneyCard("Pot-de-Vin", 3, 3));
        cards.add(new MoneyCard("Myros, Mage de la Guilde", 5, 3));
        cards.add(new MoneyCard("Sombre Récompense", 5, 3));
        cards.add(new MoneyCard("Fourberie", 5, 5));
        
        
        //Attack

        cards.add(new AttackCard("Menace de Mort", 3, 1));
        for(int i=0; i<3;i++) cards.add(new AttackCard("Etincelle", 1, 3));
        for(int i=0; i<2;i++) cards.add(new AttackCard("Intimidation", 2, 5));
        for(int i=0; i<2;i++) cards.add(new AttackCard("La Putréfaction", 3, 5));
        cards.add(new AttackCard("Forme de Loup", 5, 8));
        cards.add(new AttackCard("Casser et Piller", 6, 6));
        for(int i=0; i<2;i++) cards.add(new AttackCard("Sauvagerie", 6, 6));
        cards.add(new AttackCard("Mise à prix", 4, 7));
        cards.add(new AttackCard("Energie Sombre", 4, 7));
        cards.add(new AttackCard("Bombe Incendairaire", 8, 8));
        cards.add(new AttackCard("Drain de vie", 6, 8));


        //Rouge
        for(int i=0; i<2;i++) cards.add(new FighterCard("Contact Mortel", 1, 2, 0, typeRouge, new DamageEffect(displayer,2)));
        for(int i=0; i<2;i++) cards.add(new FighterCard("Cultiste de la Mort", 2, 2, 4, typeRouge, new NullEffect(displayer)));
        for(int i=0; i<2;i++) cards.add(new FighterCard("Prêtre du Culte", 3, 1, 4, typeRouge, new DamageEffect(displayer,4)));
        cards.add(new FighterCard("Rayla Tisseuse de Fins", 4, 3, 4, typeRouge, new GainCardEffect(displayer)));
        cards.add(new FighterCard("Varrick le Nécromancien", 5, 0, 3, typeRouge, new GainCardEffect(displayer)));
        cards.add(new FighterCard("Lys l'Inapparent", 6, 2, 5, typeRouge, new DamageEffect(displayer,2)));
        cards.add(new FighterCard("Krythos le Maître Vampire", 7, 3, 6, typeRouge, new DamageEffect(displayer,3)));
        cards.add(new FighterCard("Tyrannor le Dévoreur", 8, 6, 6, typeRouge, new GainCardEffect(displayer)));

        
        //Vert
        for(int i=0; i<2;i++) cards.add(new FighterCard("Shamane des Loups", 2, 2, 4, typeVert, new DamageEffect(displayer,1)));
        for(int i=0; i<2;i++) cards.add(new FighterCard("Grognard Orque", 3, 2, 3, typeVert, new GainCardEffect(displayer)));
        for(int i=0; i<2;i++) cards.add(new FighterCard("Malédiction Elfique", 3, 6, 0, typeVert, new DamageEffect(displayer,3)));
        cards.add(new FighterCard("Don de la Nature", 4, 0, 0, typeVert, new ThrowCardEffect(displayer)));
        cards.add(new FighterCard("Broelyn Tisseuse de Savoir", 4, 0, 6, typeVert, new ThrowCardEffect(displayer)));
        cards.add(new FighterCard("Loup Terrifiant", 5, 3, 5, typeVert, new DamageEffect(displayer,4)));
        cards.add(new FighterCard("Cron le Berseker", 6, 5, 6, typeVert, new GainCardEffect(displayer)));
        cards.add(new FighterCard("Torgen Brise-Pierre", 7, 4, 7, typeVert, new ThrowCardEffect(displayer)));
        cards.add(new FighterCard("Grick le Géant de la Tempête", 8, 6, 7, typeVert, new ThrowCardEffect(displayer)));
        

        //Jaune
        for(int i=0;i<3;i++) cards.add(new FighterCard("Recrutement", 2, 0, 3, typeJaune, new NullEffect(displayer)));
        cards.add(new FighterCard("Percepteur de Dîme", 2, 0, 3, typeJaune, new HealEffect(displayer,1)));
        cards.add(new FighterCard("Serrer les rangs", 3, 5, 0, typeJaune, new HealEffect(displayer,6)));
        for(int i=0;i<2;i++) cards.add(new FighterCard("Homme d'Armes", 3, 2, 4, typeJaune, new DamageEffect(displayer,1)));
        cards.add(new FighterCard("Maitre Weyan", 4, 3, 4, typeJaune, new DamageEffect(displayer,1)));
        cards.add(new FighterCard("Ralliement des Troupes", 4, 5, 5, typeJaune, new NullEffect(displayer)));
        cards.add(new FighterCard("Darian le Mage de Guerre", 4, 4, 5, typeJaune, new HealEffect(displayer,4)));   
        cards.add(new FighterCard("Commandement", 5, 3, 5, typeJaune, new NullEffect(displayer)));
        cards.add(new FighterCard("Cristov le Juste",5,2,8,typeJaune,new GainCardEffect(displayer)));
        cards.add(new FighterCard("Parole de Puissance", 6, 0, 5, typeJaune, new DamageEffect(displayer,5)));
        cards.add(new FighterCard("Kraka le Grand Prêtre", 6, 0, 8, typeJaune, new HealEffect(displayer,2)));
        cards.add(new FighterCard("Domination", 7, 6, 6, typeJaune, new NullEffect(displayer)));  
        cards.add(new FighterCard("Rake, le Maître Assassin", 7, 4, 7, typeBleu, new NullEffect(displayer)));
        cards.add(new FighterCard("Arkus le Dragon Impérial", 8, 5, 6, typeJaune, new HealEffect(displayer,6)));
        

        //Bleu
        for(int i=0;i<2;i++) cards.add(new FighterCard("Bandit des rues", 3, 2, 4, typeBleu, new GainCardEffect(displayer,new MoneyCard("Pièce volée", 0, 1))));
        cards.add(new FighterCard("Rasmus le Contrebandier", 4, 0, 5, typeBleu, new GainCardEffect(displayer,new MoneyCard("Gemme volée", 0, 2))));
        cards.add(new FighterCard("Parov l'Executeur", 5, 3, 5, typeBleu, new GainCardEffect(displayer)));
        cards.add(new FighterCard("Borg Mercenaire Ogre", 6, 4, 6, typeBleu, new NullEffect(displayer)));


        Collections.shuffle(cards);
        return cards;
    }

}