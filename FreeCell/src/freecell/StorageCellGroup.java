//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package freecell;
import deckofcards.Card;
/**
 *
 * @author Amanda
 */
public class StorageCellGroup {
    StorageCell [] storageCells;
    static final int NUM_STORAGE_CELLS = 4;
    int openStorageCells;
    
    public StorageCellGroup(){
        storageCells = new StorageCell[NUM_STORAGE_CELLS];
        for(int i = 0; i < NUM_STORAGE_CELLS; ++i){
            storageCells[i] = new StorageCell();
        }
        openStorageCells = NUM_STORAGE_CELLS;
    }
    
    public Card peakAtCardInSpot(int spot){// 0<=spot<numOpenSpots
        return storageCells[spot].topCard;
    }
    
    public boolean isFull(){
        return openStorageCells == 0;
    }
    
    public boolean placeCard(Card newCard){//uses the StorageCell add function to place a card in the storage cells
        for(int i = 0; i < NUM_STORAGE_CELLS; ++i){//don't need to check if full because add returns boolean. 
            if(storageCells[i].topCard == null){
                storageCells[i].add(newCard);
                --openStorageCells;
                return true;
            }
        }
        System.out.println("Storage cells are full.");
        return false;
    }
    
    public int numOpenStorageCells(){
        return openStorageCells;
    }
    
   
    public Card removeCard(int location){//can return null
        Card desiredCard = storageCells[location].remove();
        ++openStorageCells;
        return desiredCard;
    }
    
    public String getStorageTable(){
        StringBuilder myStorage = new StringBuilder();
        for(int i = 0; i < NUM_STORAGE_CELLS; ++i){
            if(peakAtCardInSpot(i) == null){
                myStorage.append("|_____| ");
            }else{
                myStorage.append("| "+peakAtCardInSpot(i).getShortVersion()).append(" | ");
            }
        }
        return myStorage.toString();
    }
    
    public void clearStorage(){
        for(int i = 0; i < NUM_STORAGE_CELLS; ++i){
            storageCells[i].topCard = null;
        }
        openStorageCells = NUM_STORAGE_CELLS;
    }
}
