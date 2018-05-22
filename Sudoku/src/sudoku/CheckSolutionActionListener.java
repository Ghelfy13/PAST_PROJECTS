//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package sudoku;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
/**
 *
 * @author Amanda
 */

public class CheckSolutionActionListener implements ActionListener{
    private BoardViewController controller;
    private JLabel congratsBanner;
    public CheckSolutionActionListener(BoardViewController controller, JLabel congratsBanner){
        this.controller = controller;
        this.congratsBanner = congratsBanner;
    }
    
    public void actionPerformed(ActionEvent e){
        if(controller.isCompleteAndCorrect()){
            congratsBanner.setVisible(true);
        }else{
            System.out.println("Keep Playing!");
        }
    }
    
    
    
}
