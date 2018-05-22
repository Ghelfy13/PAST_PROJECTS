//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package freecell;

import deckofcards.Card;
import java.util.ArrayList;

/**
 *
 * @author Amanda
 */
//This is what the deck sorts into at the end; Can not access a card once placed on HomeCells;
public class HomeCell {
    ArrayList<Card> myHomeCell;
    
    public HomeCell(){
        myHomeCell = new ArrayList<>();
    }

    public boolean isDone() {
        if(myHomeCell.isEmpty()){
            return false;
        }
        int topCardValue = getTopCard().getValue();
        return topCardValue == 13;
    }
    
    public Card getTopCard(){
        if(myHomeCell.isEmpty()){
            return null;
        }
        return myHomeCell.get(myHomeCell.size()-1);
    }
    
    public boolean addCard(Card newCard){
        if(myHomeCell.isEmpty()){
            if(newCard.getValue() == 1){
                myHomeCell.add(newCard);
                return true;
            }else{
                return false;
            }
        }else{
            Card topCard = getTopCard();
            if(topCard.getSuit() == newCard.getSuit() && topCard.getValue() == newCard.getValue()-1){
                myHomeCell.add(newCard);
                return true;
            }else{
                return false;
            }
        }
    }
    
    public void clearHomeCell(){
        myHomeCell.clear();
    }
}
