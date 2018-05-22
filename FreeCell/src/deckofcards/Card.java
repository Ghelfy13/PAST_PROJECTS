//Copyright Amanda V. Harris 2017-present. All Rights Reserved.
package deckofcards;

/**
 *
 * @author Amanda
 */
public class Card {
    int value;
    Suit suit;
    public Card(int value, Suit suit){
        this.value = value;
        this.suit = suit;
    }
    public String getShortVersion(){
        Suit mySuit = this.getSuit();
        int myValue = this.getValue();
        if(myValue != 10){
            return getShortSuit(mySuit) + " " + getShortValue(myValue);
        }else{
            return getShortSuit(mySuit) + getShortValue(myValue);
        }
    }
    public String getShortValue(int value){
        String myValue = " ";
        if(value >1 && value < 11){
            myValue = Integer.toString(value);
        }else{
            switch(value){
                case 1:
                    myValue = "A";
                    break;
                case 11:
                    myValue = "J";
                    break;
                case 12:
                    myValue = "Q";
                    break;
                case 13:
                    myValue = "K";
                    break;
            }
        }
        return myValue;
    }
    
    public String getShortSuit(Suit mySuit){
        String myChar = " ";
        switch(mySuit){
            case HEART:
                myChar = "H";
                break;
            case SPADE:
                myChar = "S";
                break;
            case DIAMOND:
                myChar = "D";
                break;
            case CLUB:
                myChar = "C";
                break;
    }
        return myChar;
    }
    
    public int getValue(){
        return this.value;
    }
    
    public Suit getSuit(){
        return this.suit;
    }
    
    public String getColor(){
        if(this.getSuit() == Suit.CLUB || this.getSuit() == Suit.SPADE){
            return "Black";
        }else{
            return "Red";
        }
    }
    
    public String toString(){
        return suit.name() + " " + value;
    }
    //TODO: PRINT OUT SHORTnAME FOR CARDS
}
