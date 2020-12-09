package com.interview.graph;

import java.util.*;

//Status: done
public class KnightTour {
    // Class for storing a cell's data 
    static class cell { 
        int x, y; 
        int dis; 
  
        public cell(int x, int y, int dis) 
        { 
            this.x = x; 
            this.y = y; 
            //this.dis = dis; 
        } 
    } 
  
    // Utility method returns true if (x, y) lies 
    // inside Board 
    static boolean isInside(int x, int y, int N) 
    { 
        if (x >= 0 && x < N && y >= 0 && y < N) 
            return true; 
        return false; 
    } 
  
    // Method returns minimum step 
    // to reach target position 
    static int minStepToReachTarget( 
        int knightPos[], int targetPos[], 
        int N, int [] distance) 
    { 
        // x and y direction, where a knight can move 
        int dx[] = { -2,-2,2,2,1,1,-1,-1 }; 
        int dy[] = { -1, 1, -1, 1,-2, 2,-2,2}; 
  
        // queue for storing states of knight in board 
        Queue<cell> q = new LinkedList<>(); 
  
        // push starting position of knight with 0 distance 
        q.add(new cell(knightPos[0], knightPos[1], 0)); 
  
        cell t; 
        int x, y; 
        boolean visited[][] = new boolean[N ][N]; 
  
  
        // visited starting state 
        visited[knightPos[0]][knightPos[1]] = true; 
  
        // loop until we have one element in queue 
        while (!q.isEmpty()) { 
            t = q.poll(); 
  
            // if current cell is equal to target cell, 
            // return its distance 
            if (t.x == targetPos[0] && t.y == targetPos[1]) 
                return t.dis; 
  
            // loop for all reachable states 
            for (int i = 0; i < 8; i++) { 
                x = t.x + dx[i]; 
                y = t.y + dy[i]; 
  
                // If reachable state is not yet visited and 
                // inside board, push that state into queue 
                if (isInside(x, y, N) && !visited[x][y]) { 
                    visited[x][y] = true; 
                    distance[0] += distance[0];
                    //q.offer(new cell(x, y, t.dis + 1)); 
                    q.offer(new cell(x, y, 0)); 
                } 
            } 
        } 
        return Integer.MAX_VALUE; 
    } 
  
    // Driver code 
    public static void main(String[] args) 
    { 
        int N = 30; 
        int knightPos[] = { 0, 0 }; 
        int targetPos[] = { 29, 29 };
        int [] distance = new int[1];
        System.out.println( 
            minStepToReachTarget( 
                knightPos, targetPos, N, distance)); 
    } 
} 

