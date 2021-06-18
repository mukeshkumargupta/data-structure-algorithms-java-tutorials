package com.interview.graph;

/**
 * Reference: https://leetcode.com/problems/flood-fill
 * Video: https://www.youtube.com/watch?v=RwozX--B_Xs
 * Category: Easy
 * Tricky
 * 
 */
public class FloodFillAlgorithm {

    public void dfs(int[][] image, int sr, int sc, int newColor, int srs, int scs, int src) {
        //Base case
        if (sr < 0 || sr >= srs || sc < 0 || sc >= scs) {
            return;
        } else if (image[sr][sc] != src) {
            return;
        }
        
        image[sr][sc] = newColor;
        
        dfs(image, sr, sc+1, newColor, srs, scs, src);//right
        dfs(image, sr+1, sc, newColor, srs, scs, src);//bottom
        dfs(image, sr, sc-1, newColor, srs, scs, src);//left
        dfs(image, sr-1, sc, newColor, srs, scs, src);//top
    }
    
    public int[][] floodFill(int[][] image, int sr, int sc, int newColor) {
        if (image[sr][sc] == newColor) {
            return image;
        }
        int srs = image.length;
        int scs = image[0].length;
        dfs(image, sr, sc, newColor, srs, scs, image[sr][sc]);
        return image;
    }
}
