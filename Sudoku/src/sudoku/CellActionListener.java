//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package sudoku;
import javax.swing.SwingUtilities;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.text.BadLocationException;
import javax.swing.text.Document;
/**
 *
 * @author Amanda
 */
public class CellActionListener implements DocumentListener{
    
    private ResizeRunnable resize;// variable that is actually a method used to resize the cells. 
    private BoardViewController remoteController;
    private int row;
    private int column;
    
    public CellActionListener(BoardViewController remoteController, int row, int column) {
        resize = new ResizeRunnable();// used to change size of cell
        this.remoteController = remoteController;
        this.row = row;
        this.column = column;
    }

    public class ResizeRunnable implements Runnable {//class that was called ResizeRunnable that uses Runnable

        public ResizeRunnable() {
            this.document = null;
        }
        
        private Document document;
        
        public void setDocument(Document document) {
            this.document = document;
        }
        
        @Override
        public void run() {
            try {
                if (document.getLength() >= 1) {
                    char first = document.getText(0, document.getLength()).charAt(0);
                    if( first >= '1' && first <= '9'){
                        document.remove(1, document.getLength() -1);
                        int value = first - '0';
                        remoteController.set(row, column, value);
                    }else{
                        document.remove(0, document.getLength());
                    }
                }
            } catch (BadLocationException ex) {
                // purposely left blank
            }
        }
        
    }
    
    @Override
    public void insertUpdate(DocumentEvent de) {
        resize.setDocument(de.getDocument());
        SwingUtilities.invokeLater(resize);
    }

    @Override
    public void removeUpdate(DocumentEvent de) {
        if (de.getDocument().getLength() == 0 ){
            remoteController.unSet(row, column);
        }
        
    }

    @Override
    public void changedUpdate(DocumentEvent de) {
    }

    
}
