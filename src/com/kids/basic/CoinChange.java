package com.kids.basic;

public class CoinChange {
    
    /*
     * int getAllSum(int input[], int sum) { int result = 0;
     * 
     * int T[][] = if (input[i] < j) { T[i][j] = T[i-1][j];
     * 
     * } else { T[i][j] = 1 + max(T[i][j-input[i]], T[i-1][j]) } }
     * 
     * 
     * 
     * 
     * 
     * return result; }
     */
    /*
     * /// j 0 1 2 3 4 5 6 7 8
     * 
     * i1 0 1 1 1 1 1 1 1 1
     * 
     * 2 0 1 2 2 3 3 4 4 5
     * 
     * 5 0 1 2 3 4 4 5 5 5
     * 
     * 
     * /// j 0 1 2 3 4 5 6 7 8
     * 
     * i1 0 1 1 1 1 1 1 1 1
     * 
     * 2 0 1 1
     * 
     * 5 0
     * 
     * if (input[i] < j) { T[i][j] = T[i-1][j];
     * 
     * } else { //T[i][j] = 1 + max(T[i][j-input[i]], T[i-1][j])
     * 
     * T[i][j] = (1 + (T[i][j-input[i]] + T[i-1][j])) } }
     */
    
    void printStart(int N) {
        for (int i = 0; i < N; i++) {
            StringBuffer bf = new StringBuffer();
            for (int j = i; j < N; j++) {
                bf.append("*");
                
            }
            System.out.println("*");
        }
    }
    
    // ****
    // ***
    
    static void printStartRecur(int N, int i, StringBuffer bf) {
        // Base case
        if (i == N) {
            System.out.println(bf.toString());
            return;
        }
        bf.append("*");
        printStartRecur(N, i + 1, bf);

    }
    
    static void printRecur(int N, int m) {
        if (m <= 0) {
            return;
            
        }
        StringBuffer bf = new StringBuffer();
        printStartRecur(m, 0, bf);
        printRecur(N, m - 1);
    }
    
    public static void main(String[] args) {
        int N = 5;
        printRecur(N, N);
        
    }
    
}
