package com.interview.systemdesign.lowleveldesign;

import java.util.LinkedList;
import java.util.Random;
import java.util.Scanner;

/*
    Category: Hard, Facebook
    Here’s a complete solution for a Snake Game in Java that implements the key components and logic:

    Problem Breakdown:
    1. Snake:
       - The snake moves across the board, grows when it eats food, and dies when it hits the wall or itself.
    2. Food:
       - Randomly generated on the board; the snake needs to eat it.
    3. Board:
       - The grid where the snake moves and food is placed.
    4. Game Over Conditions:
       - The snake dies if it collides with itself or goes outside the board boundaries.

    Components:
    1. Snake Class:
       - Represents the snake, including its body, direction of movement, and logic for growing when it eats food.
    2. Food Class:
       - Represents the food on the board and generates new food positions when the snake eats it.
    3. Board Class:
       - Manages the grid, handles food placement, and detects game over conditions.
    4. SnakeGame Class (Main):
       - The game loop that updates the game state, moves the snake, generates new food, and checks for game over.

    Explanation:
    1. Snake:
       - A LinkedList<Point> is used to represent the body of the snake.
       - The move method handles the snake's movement by adding a new head and removing the tail.
       - If food is eaten, the tail is not removed, allowing the snake to grow.
    2. Food:
       - The Food class generates random positions for the food, ensuring that the food doesn't spawn inside the snake.
    3. Game Over:
       - The snake dies if it hits the wall (isOutOfBounds) or itself (isSelfColliding).

    Features:
    1. Movement:
       - The snake moves in response to user input (W, A, S, D for up, left, down, and right).
    2. Growth:
       - The snake grows when it eats food.
    3. Game Over:
       - The game ends when the snake collides with the wall or itself.
*/
class SnakeGame {

    private final int width, height;
    private final PartOSnake partOSnake;
    private final Food food;
    private boolean gameOver;

    public SnakeGame(int width, int height) {
        this.width = width;
        this.height = height;
        this.partOSnake = new PartOSnake(width / 2, height / 2);
        this.food = new Food(width, height);
        this.gameOver = false;
    }

    // Moves the snake in the given direction and checks game status
    public void moveSnake(char direction) {
        if (gameOver) {
            System.out.println("Game Over! You lost.");
            return;
        }

        partOSnake.move(direction);

        // Check if snake hits the wall
        if (partOSnake.isOutOfBounds(width, height)) {
            gameOver = true;
        }

        // Check if snake hits itself
        if (partOSnake.isSelfColliding()) {
            gameOver = true;
        }

        // Check if the snake eats the food
        if (partOSnake.getHead().equals(food.getPosition())) {
            partOSnake.grow();
            food.generateNewPosition(width, height, partOSnake);
        }
    }

    // Display the current state of the game
    public void displayBoard() {
        char[][] board = new char[height][width];

        // Fill the board with empty spaces
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                board[i][j] = '.';
            }
        }

        // Place the snake on the board
        for (Point point : partOSnake.getBody()) {
            board[point.y][point.x] = 'S';
        }

        // Place the food on the board
        Point foodPos = food.getPosition();
        board[foodPos.y][foodPos.x] = 'F';

        // Print the board
        for (int i = 0; i < height; i++) {
            for (int j = 0; j < width; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        SnakeGame game = new SnakeGame(10, 10);

        while (!game.isGameOver()) {
            game.displayBoard();
            System.out.println("Enter move direction (WASD): ");
            char move = sc.next().charAt(0);
            game.moveSnake(move);
        }

        sc.close();
    }
}

// Snake class represents the snake's body and movement
class PartOSnake {

    private final LinkedList<Point> body;
    private Point direction;

    public PartOSnake(int startX, int startY) {
        body = new LinkedList<>();
        body.addFirst(new Point(startX, startY)); // Snake starts at the center
        direction = new Point(0, 1); // Default direction (right)
    }

    // Moves the snake in the given direction
    public void move(char inputDirection) {
        switch (inputDirection) {
            case 'W': direction = new Point(0, -1); break; // Up
            case 'S': direction = new Point(0, 1); break;  // Down
            case 'A': direction = new Point(-1, 0); break; // Left
            case 'D': direction = new Point(1, 0); break;  // Right
        }

        Point newHead = new Point(getHead().x + direction.x, getHead().y + direction.y);
        body.addFirst(newHead); // Add the new head position
        body.removeLast();      // Remove the tail
    }

    // Makes the snake grow by adding a segment
    public void grow() {
        body.addLast(new Point(body.getLast().x, body.getLast().y));
    }

    // Checks if the snake hits the wall
    public boolean isOutOfBounds(int width, int height) {
        Point head = getHead();
        return head.x < 0 || head.x >= width || head.y < 0 || head.y >= height;
    }

    // Checks if the snake collides with itself
    public boolean isSelfColliding() {
        Point head = getHead();
        for (int i = 1; i < body.size(); i++) {
            if (body.get(i).equals(head)) {
                return true;
            }
        }
        return false;
    }

    public Point getHead() {
        return body.getFirst();
    }

    public LinkedList<Point> getBody() {
        return body;
    }
}

// Food class manages food placement on the board
class Food {

    private Point position;
    private final Random random;

    public Food(int width, int height) {
        random = new Random();
        generateNewPosition(width, height, null);
    }

    // Generates a new position for the food, ensuring it does not overlap with the snake
    public void generateNewPosition(int width, int height, PartOSnake partOSnake) {
        do {
            position = new Point(random.nextInt(width), random.nextInt(height));
        } while (partOSnake != null && partOSnake.getBody().contains(position)); // Avoid snake collision
    }

    public Point getPosition() {
        return position;
    }
}

// Point class represents a point on the board
class Point {
    int x, y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Point point = (Point) o;
        return x == point.x && y == point.y;
    }
}
