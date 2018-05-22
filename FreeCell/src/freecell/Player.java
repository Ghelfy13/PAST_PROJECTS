//Copyright Amanda V. Harris 2017-present. All Rights Reserved.

package freecell;
import java.util.Scanner;
import java.util.InputMismatchException;

/**
 *
 * @author Amanda
 */
public class Player {
    String name;
    
    public Player(String name){
        this.name = name;
    }
    
    public String getPlayer(Scanner input){
        while(true){
            System.out.println("Please enter the name of the player: ");
            try{
                name = input.nextLine();
                break;
            }catch(InputMismatchException e){
                input.next();
            }
        }
        return name;
    }
    
    public String makeMove(Scanner input){
        String myMove;
        while(true){
            System.out.println("Input the location of where the cards are that you would like to move,"+"\n"
                    + " what column it's in, how many you would like to move, which location you would like to move it to,"+"\n"
                    + " and which column you would like to move it to. (Ex: S1 1 B2 for Storage first column, move one card" + "\n"
                    + " to Board column 2. Also H is for home where the sorted cards are stored)");
            myMove = input.nextLine().toUpperCase();
            if(myMove.length()>=4){
                if(myMove.substring(0,1).equals("S")|| myMove.substring(0,1).equals("B")){
                    break;
                }
            }
            else{
                System.out.println("Input not valid.  Please try again."); 
            }
        }
        return myMove;
    }
    
}
