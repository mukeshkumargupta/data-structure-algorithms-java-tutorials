package com.interview.recursionBacktracking.G_SudukoMazesNQueneProblemsPatterns;

import java.util.*;
/*
 * https://leetcode.com/problems/the-maze/
 * Category: Hard, Must Do
 * https://www.youtube.com/watch?v=bLGZhJlt4y0&list=PLIA-9QRQ0RqHYFNJc6zVT1_sJz0qCU9b0&index=39
 */
public class B_RatInAMaze {
    
    private static boolean isValid(int i, int j, int a[][], boolean vis[][], int n){
        if(i >= 0 && j >= 0 && i < n && j < n && vis[i][j] == false && a[i][j] == 1) {
            return true;
        }
        return false;
    }
    
    private static void findPathUtil(int i, int j, int a[][], int n, ArrayList<String> ans, String move, 
    boolean vis[][]) {
        if(i==n-1 && j==n-1) {//base case
            ans.add(move);
            return; 
        }
        // String dir = "DLRU"; //Asked lexografically so we need to go in this order
        // for(int ind = 0; ind<4;ind++) {
        //     int nexti = i + di[ind]; 
        //     int nextj = j + dj[ind]; 
        //     if(nexti >= 0 && nextj >= 0 && nexti < n && nextj < n 
        //      && vis[nexti][nextj] == 0 && a[nexti][nextj] == 1) {

        //         vis[i][j] = 1; 
        //         solve(nexti, nextj, a, n, ans, move + dir.charAt(ind), vis, di, dj);
        //         vis[i][j] = 0; 
                
        //     }
        // }
        
        //int di[] = {+1, 0, 0, -1}; 
        //int dj[] = {0, -1, 1, 0};
        /*//I used to do like this but many check here in isValidAPI so taking time
         * int[][] dirs = {{1, 0}, {0, -1}, {0, 1}, {-1, 0}}; //"DLRU"; //Asked lexographically so we need to go in this order
        for (int[] dir : dirs) {
            int nexti = i + dir[0]; 
            int nextj = j + dir[0]; 
            if (isValid(nexti, nextj, a, vis, n)) {
                vis[i][j] = 1; 
                findPathUtil(nexti, nextj, a, n, ans, move + 'D', vis);
                vis[i][j] = 0; //backtrack
            }
        }*/
        // downward
        if(i+1<n && !vis[i+1][j] && a[i+1][j] == 1) {
            vis[i][j] = true; 
            findPathUtil(i+1, j, a, n, ans, move + 'D', vis);
            vis[i][j] = false; 
        }
        
        // left
        if(j-1>=0 && !vis[i][j-1] && a[i][j-1] == 1) {
            vis[i][j] = true; 
            findPathUtil(i, j-1, a, n, ans, move + 'L', vis);
            vis[i][j] = false; 
        }
        
        // right 
        if(j+1<n && !vis[i][j+1] && a[i][j+1] == 1) {
            vis[i][j] = true; 
            findPathUtil(i, j+1, a, n, ans, move + 'R', vis);
            vis[i][j] = false; 
        }
        
        // upward
        if(i-1>=0 && !vis[i-1][j] && a[i-1][j] == 1) {
            vis[i][j] = true; 
            findPathUtil(i-1, j, a, n, ans, move + 'U', vis);
            vis[i][j] = false; 
        }
    }
    public static ArrayList<String> findPath(int[][] m, int n) {
        boolean vis[][] = new boolean[n][n];
         
        ArrayList<String> result = new ArrayList<>(); 
        if(m[0][0] == 1) findPathUtil(0, 0, m, n, result, "", vis); 
        return result; 
    }
}
