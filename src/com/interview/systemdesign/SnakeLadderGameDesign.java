package com.interview.systemdesign;

import java.util.*;

import com.interview.graph.KnightTour.cell;

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 *
 * Reference: https://www.youtube.com/watch?v=zWS2fCJGxmU
 * Reference: https://www.geeksforgeeks.org/design-snake-game/?ref=lbp
 * Leet code: https://leetcode.com/problems/snakes-and-ladders/
 * Difficulty: Hard
 * Company: Google, Amazon, Facebook
 * Status: Not Done
 */
public class SnakeLadderGameDesign {
    
    // Utility method returns true if (x, y) lies 
    // inside Board 
    static boolean isInside(int x, int y, int N) 
    { 
        if (x >= 0 && x < N && y >= 0 && y < N) 
            return true; 
        return false; 
    } 
    //Return minimum step to reach end or minimum dice throw to reach end
    public int snakesAndLadders(int[][] board) {
     int n = board.length;
       int steps = 0;
     Queue<Integer> q = new LinkedList<Integer>();
     boolean visited[][] = new boolean[n][n];
      q.add(1);
      visited[n-1][0] = true;
      while(!q.isEmpty()){
        int size = q.size();
       
        //For each element in queue we need to try for all six dice
         for(int i =0; i <size; i++){
             int x = q.poll();
             //Base case
             if(x == n*n) return steps;
             
             for(int k=1; k <=6; k++){
                 //Need to correct in own way
                 if(k+x > n*n) break;
                 
                 int pos[] = findCoordinates(k+x, n);
                 int r = pos[0];
                 int c = pos[1];
                 if(visited[r][c] == true) continue;
                 visited[r][c] = true;
                 if(board[r][c] == -1){
                     q.add(k+x);
                 }else{
                     q.add(board[r][c]);
                 }
             }
         }
         
       steps++;
      
     }    
       return -1;
   }
    
    public int snakesAndLaddersOrig(int[][] board) {
        int n = board.length;
          int steps = 0;
        Queue<Integer> q = new LinkedList<Integer>();
        boolean visited[][] = new boolean[n][n];
         q.add(1);
         visited[n-1][0] = true;
         while(!q.isEmpty()){
           int size = q.size();
          
            for(int i =0; i <size; i++){
                int x = q.poll();
                if(x == n*n) return steps;
                for(int k=1; k <=6; k++){
                    if(k+x > n*n) break;
                    int pos[] = findCoordinates(k+x, n);
                    int r = pos[0];
                    int c = pos[1];
                    if(visited[r][c] == true) continue;
                    visited[r][c] = true;
                    if(board[r][c] == -1){
                        q.add(k+x);
                    }else{
                        q.add(board[r][c]);
                    }
                }
            }
            
          steps++;
         
        }    
          return -1;
      }
   
  //Tricky 
  public int[] findCoordinates(int curr, int n) {
       int r = n - (curr - 1) / n  -1;
       int c = (curr - 1) % n;
       if (r % 2 == n % 2) {
           return new int[]{r, n - 1 - c};
       } else {
           return new int[]{r, c};
       }
   }
    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        // Let us construct the board given in above diagram 
        int N = 6; 
        int board[][] = new int[N][N]; 
        for (int i = 0; i < N; i++) {
            for (int j = 0; j < N; j++) {
                board[i][j] = -1; 
            }
        }

  
        // Ladders 
        board[3][2] = 35;
        board[5][1] = 15;
  
        // Snakes 
        board[3][4] = 13;

        SnakeLadderGameDesign sl = new SnakeLadderGameDesign();
  
        System.out.println("Min Dice throws required is " +  
                sl.snakesAndLadders(board)); 
        
    }
    
}
