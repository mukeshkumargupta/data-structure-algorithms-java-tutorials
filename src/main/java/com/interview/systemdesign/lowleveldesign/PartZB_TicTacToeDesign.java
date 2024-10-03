package com.interview.systemdesign.lowleveldesign;




//Reference: https://www.youtube.com/watch?v=gktZsX9Z8Kw&list=PLIA-9QRQ0RqHcTCuIusS1G1LikGoD3WxM&index=2
/*
 * Tic Tac Toe Game
 *
 * Overview:
 * This design represents a simple implementation of a Tic Tac Toe game
 * that can be played in a console or a graphical user interface.
 * It includes classes for managing the game board, players, and game logic.
 *
 * Key Components:
 *
 * 1. **Game Class:**
 *    - Responsible for controlling the flow of the game.
 *    - Initializes the game, manages player turns, and checks for win conditions.
 *
 *    Methods:
 *    - startGame(): Initializes the game and starts the main game loop.
 *    - switchPlayer(): Switches between players.
 *    - checkWinner(): Checks the current board state for a winner.
 *    - checkDraw(): Checks if the game has resulted in a draw.
 *
 * 2. **Board Class:**
 *    - Represents the Tic Tac Toe board.
 *    - Stores the current state of the game (X, O, or empty).
 *
 *    Methods:
 *    - displayBoard(): Displays the current state of the board.
 *    - makeMove(int row, int col, char player): Places a player's mark on the board.
 *    - isValidMove(int row, int col): Checks if a move is valid (i.e., the cell is empty).
 *    - resetBoard(): Resets the board for a new game.
 *
 * 3. **Player Class:**
 *    - Represents a player in the game.
 *    - Contains information about the player's name and their mark (X or O).
 *
 *    Methods:
 *    - getName(): Returns the player's name.
 *    - getMark(): Returns the player's mark (X or O).
 *
 * 4. **GameController Class:**
 *    - Orchestrates interactions between the Game, Board, and Player classes.
 *
 *    Methods:
 *    - createPlayers(): Initializes players and their marks.
 *    - start(): Begins the game and handles the game loop.
 *    - updateBoard(int row, int col): Updates the board based on player input.
 *
 * 5. **InputHandler Class:**
 *    - Handles user input for making moves.
 *
 *    Methods:
 *    - getInput(): Captures input from the player (row and column).
 *    - validateInput(int row, int col): Validates player input for board boundaries.
 *
 * Scalability Considerations:
 * - Allow for different board sizes (e.g., 3x3, 4x4).
 * - Implement AI for single-player mode.
 *
 * Security Considerations:
 * - Validate player input to prevent invalid moves.
 * - Implement safeguards against invalid game states.
 *
 * Conclusion:
 * This low-level design provides a foundation for a Tic Tac Toe game.
 * It can be further expanded to include features such as an AI opponent,
 * different board sizes, and multiplayer options.
 *
 * Explanation of the Code
 *
 * Main Class (TicTacToe):
 * - The entry point of the application that starts the game by creating an instance of GameController.
 *
 * Game Class:
 * - Controls the game flow, switching players and checking for win or draw conditions.
 *
 * Board Class:
 * - Manages the game board, including displaying it, validating moves, and checking for wins or draws.
 *
 * Player Class:
 * - Represents each player with their name and mark ('X' or 'O').
 *
 * GameController Class:
 * - Manages user input and the overall game loop, prompting players for their moves and updating the game state accordingly.
 *
 * How to Run the Game:
 * 1. Copy the complete code into a Java file named `TicTacToe.java`.
 * 2. Compile the program using a Java compiler: `javac TicTacToe.java`.
 * 3. Run the program: `java TicTacToe`.
 * 4. Follow the prompts in the console to play the game.
 *
 * Additional Features:
 * - You can expand this implementation by adding features like:
 *   - An AI opponent.
 *   - Different board sizes (e.g., 4x4).
 *   - Ability to save and load game states.
 *   - Implementing a graphical user interface (GUI) using libraries like JavaFX or Swing.
 */
import java.util.Scanner;

/**
 * Tic Tac Toe Game
 *
 * Overview:
 * This implementation represents a simple console-based Tic Tac Toe game
 * that can be played between two players.
 */
public class TicTacToeDesign {

    public static void main(String[] args) {
        GameController gameController = new GameController();
        gameController.start();
    }
}

/**
 * Class representing the Game logic and flow
 */
class Game {
    private Board board;
    private Player currentPlayer;

    public Game() {
        board = new Board();
        currentPlayer = new Player("Player 1", 'X');
    }

    public void switchPlayer() {
        if (currentPlayer.getMark() == 'X') {
            currentPlayer = new Player("Player 2", 'O');
        } else {
            currentPlayer = new Player("Player 1", 'X');
        }
    }

    public boolean makeMove(int row, int col) {
        if (board.isValidMove(row, col)) {
            board.makeMove(row, col, currentPlayer.getMark());
            return true;
        }
        return false;
    }

    public boolean checkWinner() {
        return board.checkWin(currentPlayer.getMark());
    }

    public boolean checkDraw() {
        return board.isDraw();
    }

    public void displayBoard() {
        board.displayBoard();
    }

    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void resetGame() {
        board.resetBoard();
        currentPlayer = new Player("Player 1", 'X');
    }
}

/**
 * Class representing the game board
 */
class Board {
    private char[][] board;
    private final int SIZE = 3;

    public Board() {
        board = new char[SIZE][SIZE];
        resetBoard();
    }

    public void resetBoard() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void displayBoard() {
        for (int i = 0; i < SIZE; i++) {
            System.out.println("-------------");
            for (int j = 0; j < SIZE; j++) {
                System.out.print("| " + board[i][j] + " ");
            }
            System.out.println("|");
        }
        System.out.println("-------------");
    }

    public boolean isValidMove(int row, int col) {
        return row >= 0 && row < SIZE && col >= 0 && col < SIZE && board[row][col] == ' ';
    }

    public void makeMove(int row, int col, char mark) {
        board[row][col] = mark;
    }

    public boolean checkWin(char mark) {
        // Check rows and columns
        for (int i = 0; i < SIZE; i++) {
            if ((board[i][0] == mark && board[i][1] == mark && board[i][2] == mark) ||
                    (board[0][i] == mark && board[1][i] == mark && board[2][i] == mark)) {
                return true;
            }
        }
        // Check diagonals
        if ((board[0][0] == mark && board[1][1] == mark && board[2][2] == mark) ||
                (board[0][2] == mark && board[1][1] == mark && board[2][0] == mark)) {
            return true;
        }
        return false;
    }

    public boolean isDraw() {
        for (int i = 0; i < SIZE; i++) {
            for (int j = 0; j < SIZE; j++) {
                if (board[i][j] == ' ') {
                    return false;
                }
            }
        }
        return true;
    }
}

/**
 * Class representing a Player
 */
class Player {
    private String name;
    private char mark;

    public Player(String name, char mark) {
        this.name = name;
        this.mark = mark;
    }

    public String getName() {
        return name;
    }

    public char getMark() {
        return mark;
    }
}

/**
 * Class to handle the game controller and user input
 */
class GameController {
    private Game game;
    private Scanner scanner;

    public GameController() {
        game = new Game();
        scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            game.displayBoard();
            Player currentPlayer = game.getCurrentPlayer();
            System.out.println(currentPlayer.getName() + "'s turn (mark: " + currentPlayer.getMark() + ")");
            int row, col;
            while (true) {
                System.out.print("Enter row (0-2): ");
                row = scanner.nextInt();
                System.out.print("Enter column (0-2): ");
                col = scanner.nextInt();
                if (game.makeMove(row, col)) {
                    break;
                }
                System.out.println("Invalid move. Try again.");
            }
            if (game.checkWinner()) {
                game.displayBoard();
                System.out.println(currentPlayer.getName() + " wins!");
                break;
            }
            if (game.checkDraw()) {
                game.displayBoard();
                System.out.println("It's a draw!");
                break;
            }
            game.switchPlayer();
        }
        scanner.close();
    }
}

