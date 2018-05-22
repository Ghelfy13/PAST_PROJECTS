//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package sudoku;

/**
 *
 * @author Amanda
 */
public class BoardViewController {//this allows the program to interact with the actual board
    Board myBoard;
    
    public BoardViewController(Board jankyBoard){
        myBoard = jankyBoard;
    }
    
    public boolean isCompleteAndCorrect(){
        return myBoard.isDone();
    }
    
    public Board getBoard(){
        return myBoard;
    }
    
    public int[] preSetLocations(Board jankyBoard){
        int [] positions = new int[9];
        for(int row = 0; row < 9; ++row){
            for(int column = 0; column < 9; ++column){
                if(jankyBoard.board[row][column] != 0){
                    positions[row] = column;
                }
            }
        }
        return positions;
    }
    
    public void set(int row, int column, int value){
        System.out.println("Setting value @ " + row + " , " + column +" to " + value);
        myBoard.set(row, column, value);
    }
    
    public void unSet(int row, int column){
        System.out.println("Deleting value @ " + row + " , " + column);
        myBoard.set(row, column, 0);
    }
}
