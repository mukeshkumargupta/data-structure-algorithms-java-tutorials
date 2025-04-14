package com.interview.matrix;

/*
 * Date 10/20/2017
 * @author Mukesh Kumar Gupta
 * https://leetcode.com/problems/game-of-life/submissions/
 * Reference: https://www.youtube.com/watch?v=x1kRrOdcOAg
 * Category: Medium, Tricky
 * Related:
 * https://leetcode.com/problems/image-smoother/ Easy
 * https://leetcode.com/problems/falling-squares/ Hard
 * https://leetcode.com/problems/check-if-move-is-legal/ Medium
 * 
 * Given a board with m by n cells, each cell has an initial state live (1) or dead (0).
 * Each cell interacts with its eight neighbors (horizontal, vertical, diagonal) using the following
 * four rules (taken from the above Wikipedia article):
 * Read full qs on leetcode.
 *
 * Solution - Keep two array prev and current. Fill the values in current array. As soon as current row is done
 * replace elemments of board with prev array.
 *
 * Time complexity O(n * m)
 *
 * https://leetcode.com/problems/game-of-life/
 */
public class GameOfLife {

    private static class BruitForce {
        boolean isSafe(int i, int j, int R, int C) {
            if (i < R && i >= 0 && j < C && j >= 0) {
                return true;
            }
            return false;

        }
        public void gameOfLife1(int[][] board) {//TODO: Memory can be enhanced: Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life.
            int R = board.length;
            int C = board[0].length;
            int[][] copyBoard = new int[R][C];


            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    copyBoard[i][j] = board[i][j];
                }
            }
            int[][] dir = { {1, 0}, {1,1}, {0, 1},  {-1, 1}, {-1,0}, {-1, -1}, {0, -1}, {1, -1} };

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    //8 dir
                    int countLives = 0;
                    for (int[] pos: dir) {
                        if (isSafe(i+ pos[1] , j + pos[0], R, C ) && copyBoard[i+ pos[1]][j + pos[0]] == 1) {
                            countLives++;
                        }
                    }

                    if (copyBoard[i][j] == 0 && countLives == 3) {
                        board[i][j] = 1;
                    } else if (copyBoard[i][j] == 1 && (countLives < 2 || countLives > 3)) {
                        board[i][j] = 0;
                    }

                }
            }


        }
    }

    private static class Optimized {
        boolean isSafe(int i, int j, int R, int C) {
            if (i < R && i >= 0 && j < C && j >= 0) {
                return true;
            }
            return false;

        }
        public void gameOfLife(int[][] board) {//Runtime: 0 ms, faster than 100.00% of Java online submissions for Game of Life., Memory Usage: 37 MB, less than 94.45% of Java online submissions for Game of Life.Space optimized, but refer second method and top of it optimized the code, it is way to write code
            int R = board.length;
            int C = board[0].length;

            int[][] dir = { {1, 0}, {1,1}, {0, 1},  {-1, 1}, {-1,0}, {-1, -1}, {0, -1}, {1, -1} };

            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    //8 dir
                    int countLives = 0;
                    for (int[] pos: dir) {
                        if (isSafe(i+ pos[1] , j + pos[0], R, C ) && Math.abs(board[i+ pos[1]][j + pos[0]]) == 1) {////It was already live cell so abs is used
                            countLives++;
                        }
                    }

                    if (board[i][j] == 0 && countLives == 3) {
                        board[i][j] = 2;
                    } else if (board[i][j] == 1 && (countLives < 2 || countLives > 3)) {
                        board[i][j] = -1;
                    }

                }
            }
            for (int i = 0; i < R; i++) {
                for (int j = 0; j < C; j++) {
                    board[i][j] = board[i][j] > 0 ? 1 : 0;
                }
            }


        }
    }



}
