package Question_2;

/**
 *
 * @author Rocco Milicic
 */
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import javax.swing.JOptionPane;
import javax.swing.Timer;

public class Panel extends JPanel implements KeyListener {

    final private Timer timer; // Timer for continuous movement

    // Playing field parameters
    private final int cellSize = 40;
    private final int rows = 28; // Panel width in cells
    private final int columns = 27; // Panel height in cells
    private final int borderWidth = cellSize;
    final int gridHeight = rows * cellSize; // Panel height in pixels
    final int gridWidth = columns * cellSize; // Panel width in pixels

    // Snake
    private LinkedList<Character> snakeString = new LinkedList<>(); // Linked list containing snakes body contents
    private final int snakeX[] = new int[gridWidth];
    private final int snakeY[] = new int[gridHeight];
    private int snakeLength;
    private boolean snakeAlive = true;
    private char currentDirection = 'r'; // Initial direction (right)
    private final Snake snake; // Snake object

    // Numbers and letters (food)
    private int letterX;
    private int letterY;
    private char randomLetter;
    private final int[] numbersX = new int[10];
    private final int[] numbersY = new int[10];
    private final boolean[] numberDisplayed = new boolean[10]; // Checks if number can be displayed

    public Panel() {
        this.addKeyListener(this);
        this.setFocusable(true);

        // Display game instructions
        String instructions = "Welcome to the game!\n\nInstructions:\n1. Use the WASD keys to move the snake.\n2. You die when you hit the borders, snake head touches the body, or when your snake loses its head!\n3. Have fun!";
        JOptionPane.showMessageDialog(null, instructions, "Game Instructions", JOptionPane.INFORMATION_MESSAGE);

        // Create snake
        snake = new Snake(snakeString, snakeX, snakeY);
        snakeX[0] = 2;
        snakeY[0] = 12;

        // Set timer to refresh snake movements
        timer = new Timer(120, e -> moveSnake());
        timer.start();

        // Generate numbers and letters to put on the playing field
        createNumbers();
        createLetter();

        // Add snake head
        snakeString.add('@');
        snakeLength = 1; // Snake length starts after adding '@'
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        drawPlayingField(g);
        drawSnake(g);
        drawNumbers(g);
        drawLetter(g, letterX, letterY);

        repaint();
    }

    @Override
    public void keyTyped(KeyEvent ke) {
        // Not used
    }

    @Override
    public void keyPressed(KeyEvent ke) {
        /* Updates the current direction based on the key pressed 
        W = Up, S = Down, A = Left, D = Right*/

        char keyPressed = ke.getKeyChar();

        switch (keyPressed) {
            case 'w':
                if (currentDirection != 'd') {
                    currentDirection = 'u'; // Move up
                }
                break;
            case 's':
                if (currentDirection != 'u') {
                    currentDirection = 'd'; // Move down
                }
                break;
            case 'a':
                if (currentDirection != 'r') {
                    currentDirection = 'l'; // Move left
                }
                break;
            case 'd':
                if (currentDirection != 'l') {
                    currentDirection = 'r'; // Move right
                }
                break;
            default:
                break;
        }
    }

    private void moveSnake() {
        /* Allows snake to move across the playing field with the body following the head */

        for (int i = snakeString.getSize(); i > 0; --i) {
            snakeX[i] = snakeX[i - 1];
            snakeY[i] = snakeY[i - 1];
        }

        switch (currentDirection) {
            case 'u':
                snakeY[0] -= 1; // Move up
                break;
            case 'd':
                snakeY[0] += 1; // Move down
                break;
            case 'l':
                snakeX[0] -= 1; // Move left
                break;
            case 'r':
                snakeX[0] += 1; // Move right
                break;
            default:
                break;
        }

        // Check if snake collides with anything
        checkBorderCollisions();
        checkFoodCollisions();

        // Check if snake is still able to play
        if (!snakeAlive || snakeLength <= 0) {
            gameOver();
        }

        repaint();

    }

    @Override
    public void keyReleased(KeyEvent ke) {
        // Not used
    }

    public void drawSnake(Graphics g) {
        /* Draws the snake head and all its letter contents at the specified location */

        int[] centeredCoordinates;

        // Snake styling
        g.setColor(Color.WHITE);
        g.setFont(new Font("Arial", Font.BOLD, 24));

        for (int i = 0; i < snakeString.getSize(); ++i) {
            centeredCoordinates = centreSnake(snakeX[i], snakeY[i]); // Fetches centered snake coordinates
            g.drawString(String.valueOf(snakeString.getData(i)), centeredCoordinates[0], centeredCoordinates[1]);
        }
    }

    public int[] centreSnake(int x, int y) {
        /* Centers the snake into the middle of the grid */
        int[] snakeCentered = {(x * cellSize) - 30, (y * cellSize) + 25};
        return snakeCentered;
    }

    public void drawPlayingField(Graphics g) {
        /* Draws a border and background for the snake to play in */

        // Dark gray and black checkered grid
        for (int x = borderWidth; x < gridWidth; x += cellSize) {
            for (int y = borderWidth; y < gridHeight; y += cellSize) {
                g.setColor((x / cellSize + y / cellSize) % 2 == 0 ? Color.DARK_GRAY : Color.BLACK);
                g.fillRect(x, y, cellSize, cellSize);
            }
        }

        // Magenta border
        g.setColor(Color.MAGENTA);
        g.fillRect(0, 0, gridWidth, borderWidth); // Top border
        g.fillRect(0, gridHeight - borderWidth * 2, gridWidth, borderWidth); // Bottom border
        g.fillRect(0, 0, borderWidth, gridHeight); // Left border
        g.fillRect(gridWidth - borderWidth, 0, borderWidth, gridHeight); // Right border
    }

    public void drawNumbers(Graphics g) {
        /* Draws 10 numbers at given locations across the playing field */

        // Number styling
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Arial", Font.BOLD, 24));

        for (int i = 0; i < 10; i++) {
            if (numberDisplayed[i]) { // If number can be displayed
                int numberX = numbersX[i]; // Grid X coordinate
                int numberY = numbersY[i]; // Grid Y coordinate

                g.drawString(String.valueOf(i), numberX, numberY);
            }
        }
    }

    public void drawLetter(Graphics g, int x, int y) {
        /* Draws the 1 letter at a given location in the playing field */
        
        g.setColor(Color.ORANGE);
        g.setFont(new Font("Arial", Font.BOLD, 24));
        g.drawString(String.valueOf(randomLetter), x, y);
    }

    private void createNumbers() {
        /* Creates numbers 0-9 and sets locations for them to spawn  */
        
        for (int i = 0; i < 10; i++) {
            numbersX[i] = setLocationX();
            numbersY[i] = setLocationY();
            numberDisplayed[i] = true;
        }
    }

    private void createLetter() {
        /* Randomly generates a letter and gives it a location for it to spawn */
        
        randomLetter = (char) ('A' + (int) (Math.random() * 26));
        letterX = setLocationX();
        letterY = setLocationY();
    }

    public void respawnNumber(int num) {
        /* Respawns a number after being eaten */
        
        int respawnX = setLocationX();
        int respawnY = setLocationY();
        numbersX[num] = respawnX;
        numbersY[num] = respawnY;
        numberDisplayed[num] = true;
    }

    public int setLocationX() {
        /* Set the X location for numbers and letters */
        
        int min = cellSize * 2;
        int max = rows * cellSize - cellSize;
        int increment = cellSize;

        int numberOfValues = (max - min) / increment;
        int randomIndex = (int) (Math.random() * numberOfValues);

        return min + randomIndex * increment - 25; // -25 just to center it into grid and make it look "pretty"
    }

    public int setLocationY() {
        /* Set the Y location for numbers and letters */
        
        int min = cellSize * 2;
        int max = rows * cellSize - cellSize;
        int increment = cellSize;

        int numberOfValues = (max - min) / increment;
        int randomIndex = (int) (Math.random() * numberOfValues);

        return min + randomIndex * increment - 10; // -10 just to center it into grid and make it look "pretty"
    }

    public void checkBorderCollisions() {
        /* Check for snake and border collisions */
        
        if (snakeX[0] <= 1 || snakeX[0] >= (gridWidth / cellSize)
                || snakeY[0] < 1 || snakeY[0] >= (gridHeight / cellSize) - 2) {
            timer.stop(); // Snake hit the border, stop moving
            snakeAlive = false; // Snake died
        }
    }

    public void checkFoodCollisions() {
        /* Checks if the snake has eaten food (a letter or a number) */

        // Calculate the grid coordinates for the snake head
        int snakeGridX = snakeX[0] * cellSize;
        int snakeGridY = snakeY[0] * cellSize;

        // Check collision with the letter
        if (snakeGridX >= letterX && snakeGridX <= letterX + cellSize
                && snakeY[0] >= (letterY / cellSize) && snakeY[0] <= (letterY / cellSize)) {
            snakeLength++;
            snakeString.addInOrder(randomLetter);
            createLetter();
            System.out.println("Letter hit!");
        }

        // Check collisions with numbers
        for (int i = 0; i < 10; i++) {
            if (numberDisplayed[i]) {
                if (snakeGridX >= numbersX[i] && snakeGridX <= numbersX[i] + cellSize
                        && snakeY[0] >= (numbersY[i] / cellSize) && snakeY[0] <= (numbersY[i] / cellSize)) {
                    snakeLength--;
                    numberDisplayed[i] = false; // Hide the number
                    snakeString.remove(i);
                    respawnNumber(i); // Respawn a new number with int i
                    System.out.println("Number hit!");
                }
            }
        }
    }

    public void gameOver() {
        /* Ends the game */
        
        JOptionPane.showMessageDialog(null, "Game Over! Your snake has died :(");
        System.exit(0);
    }
}
