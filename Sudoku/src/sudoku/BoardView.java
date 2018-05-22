//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package sudoku;
import java.awt.Color;
import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.WindowConstants;
import javax.swing.border.Border;
import javax.swing.BorderFactory;
import javax.swing.JButton;

/**
 *
 * @author Amanda
 */
public class BoardView extends JFrame{
//this is the puzzle users will see.  
    
    
    
    static private final Font cellFont = new Font(Font.SERIF, Font.ITALIC, 40);
    
    public BoardView(BoardViewController controller){
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        setSize(725, 800);
        Board initialBoard = controller.myBoard;
        
        for(int row = 0; row < initialBoard.lengthOfBoard; ++row){
            for(int column = 0; column < initialBoard.lengthOfBoard; ++column){
                if(initialBoard.get(row, column) != 0){
                    CellPreSetView preSetCell = new CellPreSetView(initialBoard.get(row, column), row, column);
                    add(preSetCell);
                }else{
                    CellView myCell = new CellView(row, column, controller);
                    add(myCell);
                }
            }
        }
        JLabel congratsBanner = new JLabel("Congratulations! You solved it!");
        congratsBanner.setVisible(false);
        congratsBanner.setBounds(230, 650, 400, 100);
        Font buttonFont = new Font(Font.SERIF, Font.ITALIC, 20);
        congratsBanner.setFont(buttonFont);
        add(congratsBanner);
        JButton checkSolution = new JButton("Check my solution");
        checkSolution.setBounds(250, 600, 200, 75);
        CheckSolutionActionListener aL = new CheckSolutionActionListener(controller, congratsBanner);
        checkSolution.setFont(buttonFont);
        checkSolution.addActionListener(aL);
        add(checkSolution);
    }
    
    
    public static class CellView extends JTextField{
        static final int WIDTH = 65;
        static final int LENGTH = 50;
        public CellView(int row, int column, BoardViewController controller){
            getDocument().addDocumentListener(new CellActionListener(controller, row, column));
            setHorizontalAlignment(JTextField.CENTER);
            setFont(cellFont);
            setBounds(50 + (column)*65 + column/3*10, 100 + (row)*50 + row/3*10, WIDTH, LENGTH);
            
        }
    }
    
    public static class CellPreSetView extends JLabel{
        static final int WIDTH = 64;
        static final int LENGTH = 49;
              
        public CellPreSetView(int myNum, int row, int column){
            super(Integer.toString(myNum), JLabel.CENTER);
            setBounds(50 + (column)*65 + column/3*10, 100 + (row)*50 + row/3*10, 64, 49);
            setFont(cellFont);
            setForeground(Color.BLUE);
            setBackground(Color.WHITE);
            Border border = BorderFactory.createLineBorder(Color.GRAY, 1);
            setBorder(border);
            setOpaque(true);
        }
        
        
    }
    
    
}


