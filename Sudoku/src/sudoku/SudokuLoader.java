//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package sudoku;

import java.io.BufferedReader; //makes reading the file faster
import java.io.FileReader; //reads, opens, accesses a file
import java.io.IOException;
import java.util.Random;
/**
 *
 * @author Amanda
 * average of 31 elements in a medium puzzle
 */
public class SudokuLoader {
    static Board myBoard = new Board();
    public static Board loadSudoku(String sudokuSolution){
        
        try{
            FileReader myFile = new FileReader(sudokuSolution);
            BufferedReader reader = new BufferedReader(myFile);
            for(int row = 0; row < myBoard.lengthOfBoard; ++ row){
                String line = reader.readLine();
                String[] myNums = line.split(" ");
                for(int column = 0; column < myBoard.lengthOfBoard; ++column){
                    int myNumInt = Integer.parseInt(myNums[column]);
                    myBoard.board[row][column] = myNumInt;
                }
            }
            return myBoard;
            
        }catch(IOException e){
            System.out.println("File not found.");
            e.printStackTrace();
            return null;
        }
        
    }
    
    public static void scrambler(String sudokuSolution){//TODO: scramble elements in array and use that to replace nums on board.  Use find() to find elements. 
        Random rand = new Random();
        int numOfSwaps = rand.nextInt(9);
        int [] originalOrder = new int [9];
        int [] replacementInts = new int [9];
        
        for(int i = 0; i < 9; ++i){
            originalOrder[i] = i;
        }
        int counter = 0;
        while(counter < numOfSwaps){
            int randIndex = rand.nextInt(9);
            if(originalOrder[randIndex] != 0){
                replacementInts[counter] = originalOrder[randIndex];
                originalOrder[randIndex] = 0;
            }
        }
        
    }
    
    public int[] findOriginalLocations(int desiredNum){
        int [] locationsOfDesiredNum = new int[9];
        for(int i = 0; i < 9; ++i){
            for(int j = 0; j < 9; ++j){
                if(myBoard.board[i][j] == desiredNum){
                    locationsOfDesiredNum[i] = j;
                }
            }
        }
        return locationsOfDesiredNum;
        
    }
    
    public void findAndSwap(int desiredNum1, int desiredNum2, int [] locationsOfDesiredNum1, int [] locationsOfDesiredNum2){
        for(int i = 0; i < 9; ++i){
            myBoard.board[i][locationsOfDesiredNum1[i]] = desiredNum2;
            myBoard.board[i][locationsOfDesiredNum2[i]] = desiredNum1;
        }
    }
    
}
