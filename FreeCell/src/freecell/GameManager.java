//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package freecell;

import java.util.Scanner;
import java.util.InputMismatchException;
import deckofcards.Deck;

/**
 *
 * @author Amanda
 */
public class GameManager {
    Player player; 
    Board board;
    Scanner screen;
    Deck myDeck;
    
    public GameManager(){
        this.player = new Player("Player1");
        board = new Board();
        screen = new Scanner(System.in);
        myDeck = new Deck();
        myDeck.fillDeck();
    }
    
    public void setUp(){
        player = new Player(player.getPlayer(screen));
        board.clearBoard();
        myDeck.shuffle();
        board.myPileOfCards.dealCards();
    }
    
    public void setUpForAnotherGame(){
        board.clearBoard();
        myDeck.fillDeck();
        myDeck.shuffle();
        board.myPileOfCards.dealCards();
    }
    
    public void tearDown(){
        player = null;
        board.clearBoard();
    }
    
    public void run(){
        setUp();
        boolean stillPlaying = true;
        while(stillPlaying){
            boolean endOfGame = false;
            while(!endOfGame){
                System.out.println(board.printBoard());
                String myMove = player.makeMove(screen);
                String delims = " ";
                int quantityOfCards = -1;
                char endLocation;
                int endColumn = 0;
                String[] instructions = myMove.split(delims);
                int startColumn = Integer.parseInt(instructions[0].substring(1,2)) -1;
                char beginLocation = instructions[0].charAt(0);
                
                if(instructions.length == 2){
                    quantityOfCards = 1;
                    if(instructions[1].length() ==1){
                        endLocation = instructions[1].charAt(0);
                    }else{
                        endColumn = Character.getNumericValue(instructions[1].charAt(1)) -1;
                        endLocation = instructions[1].charAt(0);
                    }
                }else{
                    if(isInteger(instructions[1])){
                        quantityOfCards = Integer.valueOf(instructions[1]);
                    }
                    endLocation = instructions[2].charAt(0);
                    endColumn = Character.getNumericValue(instructions[2].charAt(1)) -1;                            ;
                }
                switch(beginLocation){
                    case('B'):
                        switch(endLocation){
                            case('B'):
                                moveFromBoardToBoard(startColumn, quantityOfCards, endColumn);
                                break;
                            case('S')://can throw exception
                                moveFromBoardToStorage(startColumn, quantityOfCards);
                                break;
                            case('H')://can throw exception if quantity != 1
                                moveFromBoardToHome(startColumn, quantityOfCards);
                                break;
                        }
                        break;
                    case('S'):
                        switch(endLocation){
                            case('B'):
                                moveFromStorageToBoard(startColumn, endColumn);
                                break;
                            case('H'):
                                moveFromStorageToHome(startColumn, quantityOfCards);
                                break;
                        }
                        break;
                    
                }
                endOfGame = board.isGameOver();
            }
            stillPlaying = stillPlaying(screen);
            if(stillPlaying){
                setUpForAnotherGame();
            }
        }
        tearDown();
    }
    private boolean isInteger(String myNumOfCards){
        try{
            Integer.valueOf(myNumOfCards);
            return true;
        }catch(NumberFormatException e){
            return false;
        }
    }
    private void moveFromStorageToHome(int startColumn, int quantity) throws InputMismatchException{
        if(quantity != 1){
            System.out.println("Can't move more than one card to home.");
            throw new InputMismatchException();
        }
        boolean successfulMove = board.moveToHomeFromStorage(startColumn);
        if(!successfulMove){
            System.out.println("Move is invalid");
            throw new InputMismatchException();
        }
    }
    private void moveFromStorageToBoard(int startColumn, int endColumn)throws InputMismatchException{
        boolean successfulMove = board.moveToBoardFromStorage(startColumn, endColumn);
        if(!successfulMove){
            System.out.println("Invalid Move.");
            throw new InputMismatchException();
        }
    }
    private boolean moveFromBoardToHome(int startColumn, int quantityOfCards) throws InputMismatchException{
        if(quantityOfCards != 1){
            System.out.println("Can't move more than one card to home at a time.");
            throw new InputMismatchException();
        }
        boolean successfulMove = board.moveToHome(startColumn);
        return successfulMove;
    }
    
    private boolean moveFromBoardToStorage(int startColumn, int quantityOfCards) throws InputMismatchException{
        //if enough open spaces
        if(board.myStoredCards.numOpenStorageCells() >= quantityOfCards){
            while(quantityOfCards > 0){
                board.moveCardToStorageCell(startColumn);
                --quantityOfCards;
            }
            return true;
        }
        System.out.println("There isn't enough space in storage for all the designated cards.");
        throw new InputMismatchException();
        
    }
    
    private boolean moveFromBoardToBoard(int startColumn, int quantityOfCards, int endColumn){
       
        boolean moveSuccessful = board.moveSet(startColumn, quantityOfCards, endColumn);
        return moveSuccessful;
    }
    
    private boolean stillPlaying(Scanner input){
        String response;
        while(true){
            System.out.println("Would you like to start a new game? Type Yes or No.");
            try{
                response = input.nextLine();
                if(response.equals("Yes") || response.equals("No")){
                    break;
                }else{
                    System.out.println("Invalid input.  Please type Yes or No to continue.");
                }
            }catch(InputMismatchException e){
                input.next();
            }
            
        }
        if(response.equals("Yes")){
            return true;
        }
        return false;
    }
}
