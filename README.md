# Snake Game with Linked Lists

## Description

This is a simple Snake game implementation in Java that incorporates a linked list data structure to manage the snake's body and movement. The game is built using Java's Swing library and provides a graphical interface for the user to control the snake's movements.

## Features

- Snake movement using WASD keys.
- Collect letters and numbers to grow the snake.
- Game over when the snake hits the borders or collides with its own body.

## Linked List Integration

In this Snake game, a linked list data structure is used to manage the snake's body. Each segment of the snake is represented by a node in the linked list. When the snake collects a letter or a number, a new node is added to the linked list, extending the snake's length.

The linked list enables efficient addition and removal of nodes, making it a suitable choice for representing the snake's body as it grows and shrinks during the game. The snake's movement is achieved by updating the positions of the linked list nodes based on the user's input.

## How to Play

1. Use the WASD keys to control the snake's movement:
   - W: Move Up
   - A: Move Left
   - S: Move Down
   - D: Move Right

2. Collect letters (A-Z) and numbers (0-9) to grow the snake's length.

3. Avoid hitting the borders of the playing field or colliding with the snake's own body.

4. The game ends when the snake hits the borders or collides with its own body.

## How to Run

1. Clone the repository to your local machine.

2. Open the project in your preferred Java IDE.

3. Run the `SnakeGame.java` file to start the game.

## Acknowledgments

- This project is inspired by classic Snake games and demonstrates the use of linked lists in game mechanics.

## License

This project is licensed under the MIT License - see the [LICENSE](LICENSE) file for details.
