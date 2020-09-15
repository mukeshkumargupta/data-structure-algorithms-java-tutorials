package com.interview.graph;

import java.util.LinkedList;
import java.util.Queue;
//This problem is derived of island problem so solve in similar way and using both dfs and bfs. find minimum distance to reach target if some places is already occupied
//Good example: https://www.youtube.com/watch?v=9-rOWC6-S08
//DFS Approach https://www.youtube.com/watch?v=tVHB7HC7L5w
//Reference: https://leetcode.com/problems/minimum-knight-moves/



public class MinimumStepsToReachTargetByAKnight {
    static int[] rowOffsets = {-2,-2,2,2,1,1,-1,-1};
    static int[] columnOffsets = {-1, 1, -1, 1,-2, 2,-2,2 };
    public int minimumStepsToReachTargetByAKnightDfs(int[] knightPos, int[] targetPos, int N) {
        boolean[][] visitedGrid = new boolean[N][N];
        int[] minDistance = new int[1];
        minDistance[0] = Integer.MAX_VALUE;
        
        if(!visitedGrid[knightPos[0]][knightPos[1]]) {
            DFS(knightPos,targetPos, N, visitedGrid, 0, minDistance);
        }
        if (minDistance[0] != Integer.MAX_VALUE) {
            return minDistance[0];
        } else {
            return -1;
        }
    }
    
    
    
    public void DFS(int[] knightPos, int[] targetPos, int N, boolean[][] visitedGrid, int distance, int[] minDistance) {
        
        if (visitedGrid[knightPos[0]][knightPos[1]]) return;
        
        //Check base case
        if (knightPos[0] == targetPos[0] && knightPos[1] == targetPos[1]) {
            minDistance[0] = distance;
            return;
        }
        
        
        visitedGrid[knightPos[0]][knightPos[1]] = true;
        for(int i = 0; i < 8; i++) {
            int row = rowOffsets[i];
            int column = columnOffsets[i];
            if(isNotVisited(knightPos[0]+row, knightPos[1]+column, N, visitedGrid)) {
                knightPos[0] = knightPos[0]+row;
                knightPos[1] = knightPos[1]+column;
                DFS(knightPos, targetPos, N, visitedGrid, distance+1, minDistance);
            }
        }
    }
    
    public int minimumStepsToReachTargetByAKnightBfs(int[] knightPos, int[] targetPos, int N) {
        boolean[][] visitedGrid = new boolean[N][N];
        int[] minDistance = new int[1];
        minDistance[0] = Integer.MAX_VALUE;
        
        if(!visitedGrid[knightPos[0]][knightPos[1]]) {
            BFS(knightPos,targetPos, N, visitedGrid, minDistance);
        }
        if (minDistance[0] != Integer.MAX_VALUE) {
            return minDistance[0];
        } else {
            return -1;
        }
    }
    

    
    public void BFS(int[] knightPos, int[] targetPos, int N, boolean[][] visitedGrid, int[] minDistance) {

        Queue<Point> queue = new LinkedList<>();
        
        visitedGrid[knightPos[0]][knightPos[1]] = true;
        queue.offer(new Point(knightPos[0], knightPos[1], 0));//with distance 0 update
        while(!queue.isEmpty()) {
            Point point = queue.poll();
            //Check base case
            if (point.x == targetPos[0] && point.y == targetPos[1]) {
                minDistance[0] = point.distance;
                return;
            }
            
            for(int i = 0; i < 8; i++) {
                int row = rowOffsets[i];
                int column = columnOffsets[i];
                if(isNotVisited(point.x+row, point.y+column, N, visitedGrid)) {
                    knightPos[0] = point.x+row;
                    knightPos[1] = point.y+column;
                    visitedGrid[knightPos[0]][knightPos[1]] = true;
                    queue.offer(new Point(knightPos[0], knightPos[1], point.distance +1));
                }
            }
        }

    }
    
    public boolean isNotVisited(int i, int j, int N, boolean[][] visitedGrid) {
        if( (i >= 0 && i < N) && (j>=0 && j < N) && !visitedGrid[i][j]) {
            return true;
        }
        
        return false;
    }

    
    public static void main(String args[]){
        MinimumStepsToReachTargetByAKnight mstr = new MinimumStepsToReachTargetByAKnight();
        int knightPos[] = { 0, 0 }; 
        int targetPos[] = { 29, 29 };
        
        int distance = mstr.minimumStepsToReachTargetByAKnightDfs(knightPos, targetPos, 30);
        System.out.println(distance);
        mstr = new MinimumStepsToReachTargetByAKnight();//Not required to create new memory
        //distance = mstr.minimumStepsToReachTargetByAKnightBfs(knightPos, targetPos, 30);
        //System.out.println(distance);
    }

    
}
