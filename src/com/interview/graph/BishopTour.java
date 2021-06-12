package com.interview.graph;

import java.util.*;

public class BishopTour {
    
    // Class for storing a cell's data 
    static class cell { 
        int x, y; 
        int dis;
        cell previousCell;
  
        public cell(int x, int y, int dis) 
        { 
            this.x = x; 
            this.y = y; 
            this.dis = dis;
            this.previousCell = null;
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
    static cell minStepToReachTarget( 
        int bishopPos[], int targetPos[], 
        int N) 
    { 
        // x and y direction, where a bishop can move 
        int dx[] = { -1, 1, -1, 1 }; 
        int dy[] = { -1, 1, 1, -1}; 
  
        // queue for storing states of bishop in board 
        Queue<cell> q = new LinkedList<>(); 
  
        // push starting position of bishop with 0 distance 
        cell c = new cell(bishopPos[0], bishopPos[1], 0);
        c.previousCell = null;
        q.add(c); 
  
        cell t; 
        int x, y; 
        boolean visited[][] = new boolean[N ][N]; 
  
  
        // visited starting state 
        visited[bishopPos[0]][bishopPos[1]] = true; 
  
        // loop until we have one element in queue 
        while (!q.isEmpty()) { 
            t = q.poll(); 
  
            // if current cell is equal to target cell, 
            // return its distance 
            if (t.x == targetPos[0] && t.y == targetPos[1]) 
                //return t.dis; 
                return t;
  
            // loop for all reachable states 
            for (int i = 0; i < 4; i++) { 
                x = t.x + dx[i]; 
                y = t.y + dy[i]; 
  
                // If reachable state is not yet visited and 
                // inside board, push that state into queue 
                if (isInside(x, y, N) && !visited[x][y]) { 
                    visited[x][y] = true;
                    cell newCell = new cell(x, y, t.dis + 1);
                    newCell.previousCell = t;
                    q.offer(newCell);
                } 
            } 
        } 
        return new cell(-1, -1, Integer.MAX_VALUE);
    } 
    static String minStepForBishopToReachTarget( char x1, int y1, char x2, int y2) {
        Map<Character, Integer> lookup = new HashMap<>();
        Map<Integer, Character> reverseLookup = new HashMap<>();
        lookup.put('A', 0);
        lookup.put('B', 1);
        lookup.put('C', 2);
        lookup.put('D', 3);
        lookup.put('E', 4);
        lookup.put('F', 5);
        lookup.put('G', 6);
        lookup.put('H', 7);
        reverseLookup.put(0, 'A');
        reverseLookup.put(1, 'B');
        reverseLookup.put(2, 'C');
        reverseLookup.put(3, 'D');
        reverseLookup.put(4, 'E');
        reverseLookup.put(5, 'F');
        reverseLookup.put(6, 'G');
        reverseLookup.put(7, 'H');
        int[] bishopPos = new int[2];
        int[] targetPos = new int[2];
        int x1Value = lookup.get(x1);
        int x2Value = lookup.get(x2);
        bishopPos[0] = x1Value;
        bishopPos[1] = y1 - 1;
        targetPos[0] = x2Value;
        targetPos[1] = y2 -1;
        int N = 8;
        cell target = minStepToReachTarget( bishopPos, targetPos, N);
        //System.out.println(target.x + " " + target.y);
        //System.out.println(target.previousCell.x + " " + target.previousCell.y);

        if (target.dis == Integer.MAX_VALUE) {
            return "Impossible";
            //System.out.println(result);
        } else if (target.x == bishopPos[0] && target.y == bishopPos[1]) { //Both are same
            int yCord = target.y + 1;
            return "0 " + reverseLookup.get(target.x) + " " + yCord;
        } else { //Trace path and if slop change then print that point and keep trace it
            Stack<String> s = new Stack<>();
            int yCord = target.y + 1;
            String point = ""+ reverseLookup.get(target.x) + " " + yCord;
            s.push(point);
            int distance = 1;
            while (target.previousCell != null) {
                if (target.y - target.x == target.previousCell.y - target.previousCell.x) {//If slop not change the continue
                    target = target.previousCell;
                    continue;
                } else {
                    distance++;
                    yCord = target.previousCell.y + 1;
                    point = ""+ reverseLookup.get(target.previousCell.x) + " " + yCord;
                    s.push(point);
                    target = target.previousCell;
                }
            }
            yCord = target.y + 1;
            point = ""+ reverseLookup.get(target.x) + " " + yCord;
            s.push(point);
            StringBuffer bf = new StringBuffer();
            bf.append(distance);//put total move
            while (s.size() > 0) {
                bf.append(" ");
                bf.append(s.pop());
            }
            return bf.toString();
            
        }
    }
    
  
    // Driver code 
    public static void main(String[] args) 
    { 
        //System.out.println(minStepForBishopToReachTarget('F', 1, 'E', 8));
        System.out.println(minStepForBishopToReachTarget('F', 1, 'E', 8));
    } 
} 

