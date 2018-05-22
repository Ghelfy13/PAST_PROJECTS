//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package sudoku;
/**
 *
 * @author Amanda
 */
public class Sudoku {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Board myBoard = SudokuLoader.loadSudoku("res/SudokuSolution.txt");
        Scrambler justScramble = new Scrambler();
        Board desiredBoard = justScramble.scrambleBoard(myBoard);
        desiredBoard.adjustNumOfElements();
        BoardViewController controller = new BoardViewController(desiredBoard);
        int [] preSetInts = controller.preSetLocations(desiredBoard);
        BoardView myView = new BoardView(controller);
        myView.setLayout(null);
        myView.setVisible(true);
        
    }
    
}
