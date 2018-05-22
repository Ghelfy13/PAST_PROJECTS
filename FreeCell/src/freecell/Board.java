//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package freecell;
import deckofcards.Card;

/**
 *
 * @author Amanda
 */
public class Board {
    HomeCellGroup mySortedCards;
    StorageCellGroup myStoredCards;
    Tableaux myPileOfCards;
    
    public Board(){
        mySortedCards = new HomeCellGroup();
        myStoredCards = new StorageCellGroup();
        myPileOfCards = new Tableaux();
    }
    
    private boolean canMoveSet( int setSizeOfCards, int desiredColumnNum){//Only for moving cards from column to column
       
        boolean destinationIsEmpty = myPileOfCards.table[desiredColumnNum].isEmpty();
        int emptyColumns = myPileOfCards.getNumEmptyColumns();
        int emptyStorageCells = myStoredCards.numOpenStorageCells();
        if(destinationIsEmpty){
            return (setSizeOfCards <= ((emptyColumns)*(emptyStorageCells) +1));
        }
        else{
            return(setSizeOfCards <= ((emptyColumns+1)*emptyStorageCells +1));//if no columns empty, multiply by one. if no storage cells open, multiply by 1;
        }
    }
    
    public boolean moveToHome(int initialLocation){
        Card myCard = myPileOfCards.table[initialLocation].popLastCard();
        if(!mySortedCards.addCard(myCard)){
            myPileOfCards.table[initialLocation].addCard(myCard);
            return false;
        }
        return true;
    }
    
    public boolean moveSet(int initialLocation, int setSize, int finalLocation){
        boolean canMove = canMoveSet(setSize, initialLocation);
        if(canMove){
            return myPileOfCards.moveToAnotherColumn(initialLocation, setSize, finalLocation);
        }else{
            System.out.println("Please try a different selection.");
            return false;
        }
        
    }
    public boolean moveToHomeFromStorage(int initialLocation){
        Card myCard = myStoredCards.peakAtCardInSpot(initialLocation);
        boolean successfulMove = mySortedCards.addCard(myCard);
        if(successfulMove){
            myStoredCards.removeCard(initialLocation);
        }
        return successfulMove;
    }
    
    public boolean moveToBoardFromStorage(int initialLocation, int finalLocation){
        boolean cardExists = canGetCardInStorage(initialLocation);
        boolean successfulMove = false;
        Card myCard;
        if(cardExists){
            myCard = myStoredCards.removeCard(initialLocation);
            successfulMove = myPileOfCards.table[finalLocation].addCard(myCard);
        }
        return successfulMove;
    }
    
    public boolean canGetCardInStorage(int column){
        return (myStoredCards.peakAtCardInSpot(column)!= null);
    }
    
    public boolean moveCardToStorageCell(int columnWithDesiredCard){
        if(myStoredCards.isFull()){
            System.out.println("Stored cells are full.");
            return false;
        }
        Card desiredCard = myPileOfCards.table[columnWithDesiredCard].popLastCard();
        boolean successfulAddition = myStoredCards.placeCard(desiredCard);
        if(!successfulAddition){
            myPileOfCards.table[columnWithDesiredCard].addCard(desiredCard);
        }
        //place poped card back onto column
        return successfulAddition;
    }
    
    public String printBoard(){
        return "FREECELL" + '\n' + myStoredCards.getStorageTable()+ "& " + mySortedCards.getHomeCellGroup() +'\n'+ '\n'+ myPileOfCards.getTable();
        
        
    }
    
    public void clearBoard(){
        myStoredCards.clearStorage();
        myPileOfCards.clearTable();
        mySortedCards.clearHome();
    }
    
    public boolean isGameOver(){
        return mySortedCards.isDone();
    }
}
