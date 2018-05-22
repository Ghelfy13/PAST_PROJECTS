//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package freecell;
import deckofcards.Card;

/**
 *
 * @author Amanda
 */
public class StorageCell {
    Card topCard;
    HomeCellGroup home;
    
    public StorageCell(){
        this.topCard = null;
    }
    
    public boolean add(Card newCard){
        if(newCard.getValue() == 1){
            return home.addCard(newCard);//Adds Ace to the homecell instead automatically
        }else if(topCard == null){
            topCard = newCard;
            return true;
        }
        return false;
    }
    
    public Card remove(){// can return null
        if(topCard == null){
            return null;
        }else{
            Card returnCard = topCard;
            topCard = null;
            return returnCard;
        }
    }
    
    public Card peak(){
        return topCard;
    }
    
    public boolean isEmpty(){
        return topCard == null;
    }
}
