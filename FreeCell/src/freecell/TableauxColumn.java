//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package freecell;
import deckofcards.Card;
import java.util.ArrayList;

/**
 *
 * @author Amanda
 */
public class TableauxColumn {
    ArrayList<Card> column;
    
    public TableauxColumn(){
        column = new ArrayList<Card>(10);
    }
    
    public boolean addCard(Card newCard){//used to place single cards during the dealing of the deck
        return column.add(newCard);
    }
    
    public boolean add(ArrayList<Card> setOfCards){//used to move cards during the game
        if(column.size() == 0){
            return column.addAll(setOfCards);
        }
        Card lastInColumn = column.get(column.size()-1);
        Card firstInSet = setOfCards.get(0);
        if(firstInSet.getColor() != lastInColumn.getColor()){
            if(firstInSet.getValue() == lastInColumn.getValue()-1){
                return column.addAll(setOfCards);
            }
        }
        return false;
    }
    public Card getCard(int i){
        return column.get(i);
    }
    
    public int getSize(){
        return this.column.size();
    }
    
    public Card popLastCard(){
        if(!isEmpty()){
            int size = this.column.size();
            Card myCard = this.column.get(size -1);
            this.column.remove(size-1);
            return myCard;
        }
        return null;
    }
    
    public boolean isEmpty(){
        return (column.size() == 0);
    }
    
    public String returnColumnContents(){
        ArrayList<Card> myColumn = this.column;
        
        for(int i = 0; i < myColumn.size(); ++i){
                return myColumn.get(i).getShortVersion();
            }
            return " ";
        }
    
    public String printOutColumn(){
        int length = this.getSize();
        int i = 0;
        StringBuilder myString = new StringBuilder();
        while(i < length){
            myString.append(this.getCard(i).getShortVersion() + " ");
            ++i;
        }
        return myString.toString() + "\n";
    }
    
    public void clearColumn(){
        column.clear();
    }
}
