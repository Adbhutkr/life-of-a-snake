
package snakegame;

import javax.swing.*;
//import java.awt.*;

//implements ActionListener  
public class SnakeGame extends JFrame  {
    
    SnakeGame()
    {
        super("SNAKE GAME");
        add(new Board()); 
        pack();
         

        setLocationRelativeTo(null);
        setResizable(false);
        
    }

      
    public static void main(String[] args) {
        new SnakeGame().setVisible(true) ;
    }
    
}
