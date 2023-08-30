package Question_2;

/**
 *
 * @author Rocco Milicic
 */

public class Snake {
    
    private final LinkedList<Character> snakeString;
    private final int[] snakeX;
    private final int[] snakeY;

    public Snake(LinkedList<Character> snakeString, int[] snakeX, int[] snakeY) {
        this.snakeString = snakeString;
        this.snakeX = snakeX;
        this.snakeY = snakeY;
    }      
}
