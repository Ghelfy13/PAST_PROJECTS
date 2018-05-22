//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package sudoku;
import java.util.Random;
/**
 *
 * @author Amanda
 */
public class Board {
    int lengthOfBoard = 9;
    int [][] board;
    boolean [] checkArray = new boolean [9];
    
    public Board(){
        board = new int [lengthOfBoard][];
        for(int i = 0; i < lengthOfBoard; ++i){
            board[i] = new int [lengthOfBoard];
            checkArray[i] = false;
        }
        
    }
    
    public boolean isDone(){
        return finalCheckRows() && finalCheckColumns() && finalCheckSquares();
    }
    
    public boolean isCorrect(){
        return checkRows()&&checkColumns()&&checkSquares();
    }
    public void adjustNumOfElements(){
        int numOfIterations = 0;
        int numOfStartElements= 81;
        int numOfElements = 81;
        Random rand = new Random();
        
        while(numOfElements > 40){
            int row = rand.nextInt(9);
            int column = rand.nextInt(9);
            if(board[row][column] != 0){
                board[row][column] = 0;
                ++numOfIterations;
            }
            numOfElements = numOfStartElements - numOfIterations;
        }
    }
    public int get(int row, int column){
        return board[row][column];
    }
    
    public void set(int row, int column, int num){
        board[row][column] = num;
    }
    
    public boolean checkRows(){
        for(int row = 0; row<lengthOfBoard; ++row){
            resetCheckArray();
            for(int column = 0; column<lengthOfBoard; ++column){
                int wantedNum = board[row][column]-1;
                if(wantedNum != -1){
                    if(checkArray[wantedNum]){
                        return false;
                    }
                    checkArray[wantedNum] = true;
                }
            }
                
        }
        resetCheckArray();
        return true;
    }
    
    public boolean finalCheckRows(){
        for(int row = 0; row<lengthOfBoard; ++row){
            resetCheckArray();
            for(int column = 0; column<lengthOfBoard; ++column){
                int wantedNum = board[row][column]-1;
                if(wantedNum == -1){
                    return false;
                }
                else{
                    if(checkArray[wantedNum]){
                        return false;
                    }
                    checkArray[wantedNum] = true;
                }
            }
                
        }
        resetCheckArray();
        return true;
    }
    public boolean checkColumns(){
        for(int column = 0; column < lengthOfBoard; ++ column){
            resetCheckArray();
            for(int row = 0; row < lengthOfBoard; ++ row){
                int wantedNum = board[row][column] -1;
                if(wantedNum != -1){
                    if(checkArray[wantedNum]){
                        return false;
                    }
                    checkArray[wantedNum] = true;
                }
            }
        }
        resetCheckArray();
        return true;
    }
    public boolean finalCheckColumns(){
        for(int column = 0; column < lengthOfBoard; ++ column){
            resetCheckArray();
            for(int row = 0; row < lengthOfBoard; ++ row){
                int wantedNum = board[row][column] -1;
                if(wantedNum == -1){
                    return false;
                }else{
                    if(checkArray[wantedNum]){
                        return false;
                    }
                    checkArray[wantedNum] = true;
                }
            }
        }
        resetCheckArray();
        return true;
    }
    
    private void resetCheckArray(){
        for(int i = 0 ; i < lengthOfBoard; ++i){
            checkArray[i] = false;
        }
    }
    
    public boolean checkSquares(){
        int dimension = (int) Math.round(Math.sqrt(lengthOfBoard));
        for(int column = 2; column < lengthOfBoard; column += dimension){
            for(int row = 2; row < lengthOfBoard; row += dimension){
                int myColumn = column -2;
                int myRow = row - 2;
                int count = 0;
                while(count < lengthOfBoard){
                    if(count != 0 && count%3 ==0){
                        ++myColumn;
                        myRow = row -2;
                    }
                    int wantedNum = board[myRow][myColumn] -1 ;
                    if(wantedNum != -1){
                        if(checkArray[wantedNum]){
                            return false;
                        }
                        checkArray[wantedNum] = true;
                    }
                    ++count;
                    ++myRow;
                }
                resetCheckArray();
            }
        }
        resetCheckArray();
        return true;
    
    }
    
    public boolean finalCheckSquares(){
        int dimension = (int) Math.round(Math.sqrt(lengthOfBoard));
        for(int column = 2; column < lengthOfBoard; column += dimension){
            for(int row = 2; row < lengthOfBoard; row += dimension){
                int myColumn = column -2;
                int myRow = row - 2;
                int count = 0;
                while(count < lengthOfBoard){
                    if(count != 0 && count%3 ==0){
                        ++myColumn;
                        myRow = row -2;
                    }
                    int wantedNum = board[myRow][myColumn] -1 ;
                    if(wantedNum == -1){
                        return false;
                    }else{
                        if(checkArray[wantedNum]){
                            return false;
                        }
                        checkArray[wantedNum] = true;
                    }
                    ++count;
                    ++myRow;
                }
                resetCheckArray();
            }
        }
        resetCheckArray();
        return true;
    }
    
    public boolean repeatsCheck(int[] myArray){//since array is sorted, you don't have to do a random search
        
        for(int i = 0; i < myArray.length-1; ++i){
            if(myArray[i] == myArray[i+1]){
                return false;
            }
        }
        return true;
    }
    
    public String printBoard(){
        StringBuilder myBoard = new StringBuilder();
        for(int i = 0; i < lengthOfBoard; ++i){
            for(int j = 0; j < lengthOfBoard; ++j){
                int myElement = board[i][j];
                if(j == 0){
                    if(myElement == 0){
                        myBoard.append("|   |");
                    }
                    else{
                        myBoard.append("| ").append(myElement).append(" |");
                    }
                }else{
                    if(myElement == 0){
                        myBoard.append("   |");
                    }else{
                        myBoard.append(" " + myElement).append(" |");
                    }
                }
            }
            myBoard.append("\n").append("--------------------------------------").append("\n");
        }
        return myBoard.toString();
    }
}
