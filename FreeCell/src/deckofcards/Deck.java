//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package deckofcards;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;

/**
 *
 * @author Amanda
 */
public class Deck {
    Card card;
    ArrayList<Card> deck;
    
    public Deck(){
        deck = new ArrayList(52);
    }
    
     public boolean isEmpty(){
        return (getSize() == 0);
    }
     
    public void fillDeck(){
        for (int i = 0; i < 52; ++i){
            if(i < 13){
                deck.add(new Card(i+1, Suit.CLUB));
            }else if( i < 26){
                deck.add(new Card(i%13 +1, Suit.DIAMOND));
            }else if( i < 39){
                deck.add(new Card(i%13 +1, Suit.HEART));
            }else{
                deck.add(new Card(i%13 +1, Suit.SPADE));
            }
        }
    }
    
    public void shuffle(){
        Random rand = new Random();
        int index = 0;
        Card placeHolder;
        while (index < 52){
            placeHolder = deck.get(index);
            int n = rand.nextInt(52);
            deck.set(index,deck.get(n));//put the new card at index
            deck.set(n, placeHolder);//put original card in new cards old place
            ++index;
        }
    }
    
    public Card drawTopCard(){
        Card topCard = deck.get(0);
        deck.remove(0);
        return topCard;
    }
    
    public int getSize(){
        return deck.size();
    }
    
    public Iterator<Card> iterator(){
        return deck.iterator();
    }
}
