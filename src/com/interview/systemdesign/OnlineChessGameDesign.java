package com.interview.systemdesign;

//Reference: https://www.youtube.com/watch?v=yBsWza2039o&list=PLIA-9QRQ0RqHcTCuIusS1G1LikGoD3WxM&index=4&t=34s
public class OnlineChessGameDesign {
    public abstract class Piece {
        private boolean killed = false;
        private boolean white = false;
        public Piece(boolean white) {
            this.white= white;
        }
        public boolean isWhite() {
            return this.white == true;
            
        }
        public void setWhite(boolean white) {
            this.white = white;
            
        }
        
        public boolean isKilled() {
            return this.killed == true;
            
        }
        public void setKilled(boolean killed) {
            this.killed = killed;
            
        }
        
        public abstract boolean canMove(Board board, Box start, Box end);
        
    }
    
    class King extends Piece {
        public King(boolean white) {
            super(white);
        }
        
        @Override
        public boolean canMove(Board board, Box start, Box end) {
            //Code to verify the end position is valid or not
            if (end.getPiece().isWhite()) {
                
            }
            

            return false;
        }
    }
    
    //Similar way implement class and implement canMove
    //Rook means element , knight means horse, bishop means hiran , pawan means police
    //Pawn canMove for pawn and king will be same only differece olny pawn can not not go back
    //For knight differece of x cordinate and y and then take abs of both and mupltiply sud be 2 then return true;
    //For bishap diff of x and y and take abs then x and y sud be same and no boxex oocupiied in between
    
    class Box {
        public Piece getPiece() {
            return piece;
        }
        public void setPiece(Piece piece) {
            this.piece = piece;
        }
        public int getX() {
            return x;
        }
        public void setX(int x) {
            this.x = x;
        }
        public int getY() {
            return y;
        }
        public void setY(int y) {
            this.y = y;
        }
        private Piece piece;
        private int x;
        private int y;
        public Box(int x, int y, Piece piece) {
            
        }
    }
    
    class Board {
        public Box[][] getBoxes() {
            return boxes;
        }

        public void setBoxes(Box[][] boxes) {
            this.boxes = boxes;
        }

        Box[][] boxes;
        public Board() {
            this.initializedBoard();
        }
        void initializedBoard() {
            boxes = new Box[8][8];
            boxes[0][0] = new Box(0, 0 , new King(true));
            //Similar way u can initialized and if no piece on that box then pass null
            
        }
        
    }
    class Person {
        private String name;
        Person(String name) {
            this.name = name;
        }
        
    }
    class Player {
        private Person person;
        private boolean whiteSite = false;
        public Player(Person person, boolean whityeSide) {
            
        }
        
    }
    enum GameStatus {
        NotStarted, Progress, Finished
    }
    
    class Game {
        private Player[] players;
        private Board board;
        private Player currentTurn;
        private GameStatus status;
        void initialized(Player p1, Player p2) {
            players = new Player[2];
            players[0] = p1;
            players[1] = p2;
            board = new Board();
            board.initializedBoard();
            if (p1.whiteSite) {
                this.currentTurn = p1;
                
            } else {
                this.currentTurn = p2;
            }
        }
        
        //
        public boolean canPlayerMove(Player player, int startX, int startY,  int endX, int endY) {
            Box start = board.boxes[startX][startY];
            Box end = board.boxes[endX][endY];
            Piece sourcePiece = start.getPiece();
            if (sourcePiece.canMove(board, start, end)) {
                Piece destPiece = end.getPiece();
                if(destPiece != null) {
                    destPiece.setKilled(true);
                }
                else {
                    end.setPiece(sourcePiece);
                }
                
                if(this.currentTurn == players[0]) {
                    this.currentTurn = players[1];
                    
                } else {
                    this.currentTurn = players[0];
                }
            }
            return false;
        }
    }
    
    void runOnlineChessGame() {
        Game game = new Game();
        Player p1 = new Player(new Person("P1"), true);
        Player p2 = new Player(new Person("P2"), false);
        game.initialized(p1, p2);
        
    }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        OnlineChessGameDesign ocgd = new OnlineChessGameDesign();
        ocgd.runOnlineChessGame();
        
    }
    
}
