//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package deckofcards;
import java.util.Iterator;
/**
 *
 * @author Amanda
 */
public class DeckOfCards {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Deck myDeck = new Deck();
        myDeck.fillDeck();
        myDeck.shuffle();
        Iterator <Card> myIterator = myDeck.iterator();
        while(myIterator.hasNext()){
            System.out.println(myIterator.next());
        }
    }
    
}
