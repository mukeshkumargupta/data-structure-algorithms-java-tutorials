package com.interview.systemdesign.lowleveldesign;

import java.util.*;

/*
    Reference: https://www.geeksforgeeks.org/design-a-chess-game/?ref=lbp
 * Difficulty: Hard
 * Company: Google, Amazon, Facebook
 * Status: Not Done
 * Here's a complete solution for designing a Chess Game using object-oriented principles in Java.
 * This implementation includes classes for Board, Piece (with subclasses for each type), Game, and Player,
 * along with methods to initialize the board, move pieces, and check game states like checkmate and stalemate.
 *
 * Explanation of the Code:
 * - Piece Class: Represents a generic chess piece with attributes like color and current position.
 *   Subclasses (e.g., King, Queen, Pawn, etc.) handle specific movement rules.
 * - Board Class: Represents the chessboard, which contains an 8x8 grid of squares where each square can hold a Piece.
 * - Game Class: Manages the overall flow of the game, including turn management, check/checkmate detection,
 *   and player interactions.
 * - Player Class: Represents a player (either White or Black) and tracks the player's moves and pieces.
 * - ChessGame Class: Manages game initialization, player turns, and enforces the game rules.
 *
 * Key Considerations:
 * - Handling special moves like castling, pawn promotion, and en passant.
 * - Validating legal moves for each piece.
 * - Detecting check, checkmate, and stalemate conditions.
 *
 * Explanation of Key Components:
 * - Real-Time Gameplay: The ChessGame class manages the state of the game, switching turns between players
 *   and ensuring the game rules are followed.
 * - Valid Moves: Each piece has its own movement rules implemented in the respective subclass (e.g., King, Queen, Pawn),
 *   and only valid moves are allowed.
 * - Checkmate and Stalemate: The game should eventually implement checks for checkmate and stalemate
 *   to declare a winner or a draw.
 * - Special Moves: The current implementation can be extended to support special moves like castling,
 *   pawn promotion, and en passant.
 *
 * Key Considerations:
 * - Piece Movement: Each piece class (King, Queen, Rook, etc.) defines its valid movement.
 * - Game State Management: The ChessGame class manages the game state, such as whose turn it is and
 *   whether the game is over.
 * - Checkmate/Draw: The system should detect checkmate, stalemate, or draw conditions based on the board's state.
 */
// Enum for piece color
enum Color {
    WHITE, BLACK
}

// Position class representing a coordinate on the board
class Position {
    int row, col;

    public Position(int row, int col) {
        this.row = row;
        this.col = col;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Position position = (Position) obj;
        return row == position.row && col == position.col;
    }

    @Override
    public int hashCode() {
        return Objects.hash(row, col);
    }
}

// Abstract Piece class
abstract class Piece {
    protected Color color;
    protected Position position;

    public Piece(Color color, Position position) {
        this.color = color;
        this.position = position;
    }

    public Color getColor() {
        return color;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    public abstract List<Position> getValidMoves(Board board);
}

// King class
class King extends Piece {
    public King(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getValidMoves(Board board) {
        //Don't waster time on this code
        List<Position> moves = new ArrayList<>();
        int[] directions = {-1, 0, 1};
        for (int dx : directions) {
            for (int dy : directions) {
                if (dx != 0 || dy != 0) {
                    Position newPos = new Position(position.row + dx, position.col + dy);
                    if (board.isPositionValid(newPos) && (!board.isPositionOccupied(newPos) || board.getPiece(newPos).getColor() != color)) {
                        moves.add(newPos);
                    }
                }
            }
        }
        return moves;
    }
}

// Queen class
class Queen extends Piece {
    public Queen(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getValidMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        moves.addAll(new Rook(color, position).getValidMoves(board)); // Combining rook-like moves
        moves.addAll(new Bishop(color, position).getValidMoves(board)); // Combining bishop-like moves
        return moves;
    }
}

// Rook class
class Rook extends Piece {
    public Rook(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getValidMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}}; // Up, Down, Right, Left
        for (int[] direction : directions) {
            for (int i = 1; i < 8; i++) {
                Position newPos = new Position(position.row + i * direction[0], position.col + i * direction[1]);
                if (board.isPositionValid(newPos)) {
                    if (board.isPositionOccupied(newPos)) {
                        if (board.getPiece(newPos).getColor() != color) moves.add(newPos);
                        break; // Stop after finding a piece
                    }
                    moves.add(newPos);
                } else break;
            }
        }
        return moves;
    }
}

// Bishop class
class Bishop extends Piece {
    public Bishop(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getValidMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        int[][] directions = {{1, 1}, {-1, -1}, {1, -1}, {-1, 1}}; // Diagonal moves
        for (int[] direction : directions) {
            for (int i = 1; i < 8; i++) {
                Position newPos = new Position(position.row + i * direction[0], position.col + i * direction[1]);
                if (board.isPositionValid(newPos)) {
                    if (board.isPositionOccupied(newPos)) {
                        if (board.getPiece(newPos).getColor() != color) moves.add(newPos);
                        break;
                    }
                    moves.add(newPos);
                } else break;
            }
        }
        return moves;
    }
}

// Knight class
class Knight extends Piece {
    public Knight(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getValidMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        int[][] movesPattern = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1}, {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};
        for (int[] move : movesPattern) {
            Position newPos = new Position(position.row + move[0], position.col + move[1]);
            if (board.isPositionValid(newPos) && (!board.isPositionOccupied(newPos) || board.getPiece(newPos).getColor() != color)) {
                moves.add(newPos);
            }
        }
        return moves;
    }
}

// Pawn class
class Pawn extends Piece {
    public Pawn(Color color, Position position) {
        super(color, position);
    }

    @Override
    public List<Position> getValidMoves(Board board) {
        List<Position> moves = new ArrayList<>();
        int direction = (color == Color.WHITE) ? -1 : 1;
        Position forwardOne = new Position(position.row + direction, position.col);

        // Move forward by one step if not occupied
        if (board.isPositionValid(forwardOne) && !board.isPositionOccupied(forwardOne)) {
            moves.add(forwardOne);
            // Initial two-step move
            if ((color == Color.WHITE && position.row == 6) || (color == Color.BLACK && position.row == 1)) {
                Position forwardTwo = new Position(position.row + 2 * direction, position.col);
                if (!board.isPositionOccupied(forwardTwo)) moves.add(forwardTwo);
            }
        }

        // Diagonal captures
        Position[] diagonals = {
                new Position(position.row + direction, position.col - 1),
                new Position(position.row + direction, position.col + 1)
        };
        for (Position diag : diagonals) {
            if (board.isPositionValid(diag) && board.isPositionOccupied(diag) && board.getPiece(diag).getColor() != color) {
                moves.add(diag);
            }
        }

        return moves;
    }
}

// Board class to manage the chessboard
class Board {
    private Piece[][] board = new Piece[8][8];

    public Board() {
        initializeBoard();
    }

    private void initializeBoard() {
        /*
            Note: In interview make dummy code to save time
            Explanation:
            White Pieces:
            The pawns are placed in row 6 for white, which is the second-to-last row from the white side.
            The other white pieces are placed in row 7 with the following order (from left to right): Rook, Knight, Bishop, Queen, King, Bishop, Knight, Rook.
            Black Pieces:
            The pawns are placed in row 1 for black, which is the second row from the black side.
            The other black pieces are placed in row 0 with the same piece order as white.
            Now, the chessboard will be initialized with the proper starting position of all pieces.
         */
        // Initialize White pieces
        for (int col = 0; col < 8; col++) {
            setPiece(new Position(6, col), new Pawn(Color.WHITE, new Position(6, col)));
        }
        setPiece(new Position(7, 0), new Rook(Color.WHITE, new Position(7, 0)));
        setPiece(new Position(7, 1), new Knight(Color.WHITE, new Position(7, 1)));
        setPiece(new Position(7, 2), new Bishop(Color.WHITE, new Position(7, 2)));
        setPiece(new Position(7, 3), new Queen(Color.WHITE, new Position(7, 3)));
        setPiece(new Position(7, 4), new King(Color.WHITE, new Position(7, 4)));
        setPiece(new Position(7, 5), new Bishop(Color.WHITE, new Position(7, 5)));
        setPiece(new Position(7, 6), new Knight(Color.WHITE, new Position(7, 6)));
        setPiece(new Position(7, 7), new Rook(Color.WHITE, new Position(7, 7)));

        // Initialize Black pieces
        for (int col = 0; col < 8; col++) {
            setPiece(new Position(1, col), new Pawn(Color.BLACK, new Position(1, col)));
        }
        setPiece(new Position(0, 0), new Rook(Color.BLACK, new Position(0, 0)));
        setPiece(new Position(0, 1), new Knight(Color.BLACK, new Position(0, 1)));
        setPiece(new Position(0, 2), new Bishop(Color.BLACK, new Position(0, 2)));
        setPiece(new Position(0, 3), new Queen(Color.BLACK, new Position(0, 3)));
        setPiece(new Position(0, 4), new King(Color.BLACK, new Position(0, 4)));
        setPiece(new Position(0, 5), new Bishop(Color.BLACK, new Position(0, 5)));
        setPiece(new Position(0, 6), new Knight(Color.BLACK, new Position(0, 6)));
        setPiece(new Position(0, 7), new Rook(Color.BLACK, new Position(0, 7)));
    }

    public Piece getPiece(Position position) {
        return board[position.row][position.col];
    }

    public void setPiece(Position position, Piece piece) {
        board[position.row][position.col] = piece;
    }

    public void movePiece(Position from, Position to) {
        Piece piece = getPiece(from);
        if (piece != null) {
            setPiece(to, piece);
            setPiece(from, null);
            piece.setPosition(to);
        }
    }

    public boolean isPositionValid(Position position) {
        return position.row >= 0 && position.row < 8 && position.col >= 0 && position.col < 8;
    }

    public boolean isPositionOccupied(Position position) {
        return getPiece(position) != null;
    }
}

// Player class to represent a chess player
class Player {
    private String name;
    private Color color;

    public Player(String name, Color color) {
        this.name = name;
        this.color = color;
    }

    public String getName() {
        return name;
    }

    public Color getColor() {
        return color;
    }
}

// ChessGame class to manage the overall game
class ChessGame {
    private Board board;
    private Player player1, player2;
    private Player currentPlayer;

    public ChessGame(Player player1, Player player2) {
        this.player1 = player1;
        this.player2 = player2;
        this.currentPlayer = player1;
        this.board = new Board();
    }

    public void switchTurns() {
        currentPlayer = (currentPlayer == player1) ? player2 : player1;
    }

    public boolean makeMove(Position from, Position to) {
        Piece piece = board.getPiece(from);
        if (piece != null && piece.getColor() == currentPlayer.getColor()) {
            List<Position> validMoves = piece.getValidMoves(board);
            if (validMoves.contains(to)) {
                board.movePiece(from, to);
                switchTurns();
                return true;
            }
        }
        return false;
    }

    public boolean isCheckmate() {
        // Implement checkmate logic
        return false;
    }

    public boolean isStalemate() {
        // Implement stalemate logic
        return false;
    }
}

// Main class for testing the chess game
public class PartLChessGame {
    public static void main(String[] args) {
        Player player1 = new Player("Alice", Color.WHITE);
        Player player2 = new Player("Bob", Color.BLACK);

        ChessGame game = new ChessGame(player1, player2);

        // Example move: Move a piece from (1, 0) to (2, 0)
        game.makeMove(new Position(1, 0), new Position(2, 0));
    }
}
