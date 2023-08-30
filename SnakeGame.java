package Question_2;

/**
 *
 * @author Rocco Milicic
 */

import javax.swing.JFrame;

public class SnakeGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame frame = new JFrame("Snake");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        Panel panel = new Panel();
        
        frame.getContentPane().add(panel);
        frame.setSize(panel.gridWidth, panel.gridHeight);
        frame.setVisible(true);
        frame.setResizable(false);
        
    }
    
}
