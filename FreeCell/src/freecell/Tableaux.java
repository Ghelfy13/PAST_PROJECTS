//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package freecell;
import deckofcards.Card;
import deckofcards.Deck;
import java.util.ArrayList;
/**
 *
 * @author Amanda
 */
public class Tableaux {//this is the table part of the board
    TableauxColumn [] table;
    Deck deckOfCards;
    
    public Tableaux(){
        int numColumns = 8;
        table = new TableauxColumn[numColumns];
        for(int i = 0; i < numColumns; ++i){
            table[i] = new TableauxColumn();
        }
        deckOfCards = new Deck();
        deckOfCards.fillDeck();
    }
    
    public boolean dealCards(){
        deckOfCards.shuffle();
        while(!deckOfCards.isEmpty()){
            for(int i = 0; i < table.length; ++i){
                if(deckOfCards.isEmpty()){
                    break;
                }
                Card topCard = deckOfCards.drawTopCard();
                if(table[i] == null){
                    System.out.println("MayDay");
                }
                boolean worked = table[i].addCard(topCard);//There isn't a table yet?
                if(!worked){
                    return false;
                }
            }
        }
        return true;
    }
    
    public int getNumEmptyColumns(){
        int numEmpty = 0;
        for( int i = 0; i < table.length; ++i){
            if(table[i].isEmpty()){
                ++numEmpty;
            }
        }
        return numEmpty;
    }
    
    public boolean moveToAnotherColumn(int initialLocation, int sizeOfSet, int finalLocation){
        TableauxColumn startLocation = table[initialLocation];
        TableauxColumn endLocation = table[finalLocation];
        ArrayList<Card> mySet = new ArrayList<>(sizeOfSet);
        int startSpot = startLocation.getSize() - (sizeOfSet);
        if(sizeOfSet == 1){
            mySet.add(startLocation.getCard(startSpot));
        }else{
            for(int i = startSpot; i < startLocation.getSize(); ++i){
                mySet.add(startLocation.getCard(i));
            }
        }
        boolean successful = endLocation.add(mySet);
        if(!successful){
               return false;
           }
        remove(sizeOfSet, initialLocation);
        return true;
    }
    
    public boolean remove(int sizeOfRemovedSet, int columnWithCards){//seperate from moving
        TableauxColumn myColumn = this.table[columnWithCards];
        int columnSize = myColumn.getSize();
        if(columnSize >= sizeOfRemovedSet){
            for(int i = 1; i <= sizeOfRemovedSet; ++i){
                myColumn.column.remove(columnSize - i);
            }
            return true;
        }
        return false;
    }
    
    public int longestColumnLength(){
        int longest = 0;
        for(int i = 0; i<table.length; ++i){
            if(table[i].getSize()>longest){
                longest = table[i].getSize();
            }
        }
        return longest;
    }
    
    public String getTable(){
        StringBuilder myTable = new StringBuilder();
        int rowLength = table.length;
        int longestColumn = longestColumnLength();
        for(int i = 0; i < longestColumn; ++i){
            for(int j = 0; j < rowLength; ++j){
                if(table[j].getSize()< i+1){
                    myTable.append("|     | ");
                }else{
                    myTable.append("| " + table[j].getCard(i).getShortVersion() + " | ");
                }
            }
            myTable.append("\n");
        }
        return myTable.toString();
    }
    
    public void clearTable(){
        for(int i = 0; i < table.length; ++i){
            table[i].clearColumn();
        }
    }
}
