package com.kids.basic;

public class ForLoop {
    
    static void printTable(int m, int n) {
        for (int i = m ; i<= n ;i++) {
            for(int j = 1 ; j <= 10; j++){
                System.out.println(i*j);
            }
        }
    }
    
    public static void main(String[] args) {
       // TODO Auto-generated method stub
        printTable(6, 9 );
        
        
        
    }
    
}
