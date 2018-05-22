//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package sudoku;

import java.util.Random;
import static sudoku.SudokuLoader.myBoard;

/**
 *
 * @author Amanda
 */
public class Scrambler {
    Board desiredBoard;
    
    public void Scrambler(){
        desiredBoard = new Board();
    }
    
    public Board scrambleBoard(Board originalBoard){
        int [] scrambledOrder = scrambleValues();
        desiredBoard = writeToBoard(scrambledOrder);
        return desiredBoard;
    }
    
    
    public int[] scrambleValues(){
        Random rand = new Random();
        int numOfSwaps = rand.nextInt(9);
        int [] replacementInts = new int [9];
        Board newSolutionBoard = myBoard;
        
        for(int i = 0; i < 9; ++i){
            replacementInts[i] = i + 1;
        }
        int counter = 0;
        while(counter < numOfSwaps){
            int randIndex = rand.nextInt(9);
            int valueHolder = replacementInts[counter];
            replacementInts[counter] = replacementInts[randIndex];
            replacementInts[randIndex] = valueHolder;
            ++counter;
        }
        return replacementInts;
    }
    
    public Board writeToBoard(int [] values){
        Board scrambledBoard = new Board();
        for(int rows = 0; rows < 9; ++rows){
            for(int columns = 0; columns < 9; ++columns){
                scrambledBoard.board[rows][columns] = values[myBoard.board[rows][columns]-1] ;
            }
        }
        return scrambledBoard;
    }
    
   
}
