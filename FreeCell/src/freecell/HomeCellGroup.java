//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package freecell;
import deckofcards.Card;
/**
 *
 * @author Amanda
 */
public class HomeCellGroup {
        
    private static final int numHomeCells = 4;
    HomeCell[] homeCells;
    
    public HomeCellGroup(){
        homeCells = new HomeCell[numHomeCells];
        for( int i = 0; i < numHomeCells; ++i){
            homeCells[i] = new HomeCell();
        }
    }
    
    public Card peakAtTop(int spot){
        return homeCells[spot].getTopCard();
    }
    
    public boolean isDone(){
        for(int i = 0; i < numHomeCells; ++i){
            if(!homeCells[i].isDone()){
                return false;
            }
        }
        return true;
    }
    
    public boolean addCard(Card newCard){
        for( int i = 0; i < numHomeCells; ++i){
            if(homeCells[i].addCard(newCard)){
                return true;
            }
        }
        return false;
    }
    
    public String getHomeCellGroup(){
        StringBuilder myHomeCellGroup = new StringBuilder();
        for(int i = 0; i < homeCells.length; ++i){
            if(peakAtTop(i)== null){
                myHomeCellGroup.append("|_____| ");
            }else{
                myHomeCellGroup.append("| "+peakAtTop(i).getShortVersion()).append(" | ");
            }
        }
        return myHomeCellGroup.toString();
    }
    
    public void clearHome(){
        for(int i = 0; i < numHomeCells; ++i){
            homeCells[i].clearHomeCell();
        }
    }
}
