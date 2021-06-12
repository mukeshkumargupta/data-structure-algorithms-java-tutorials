package com.kids.basic;


public class TestClass {
    
    public double calculateDistance(int x1, int y1, int x2, int y2, int p) {
        double distance = 0;
        int xDiff = x1 - x2;
        int yDiff = y1 - y2;
        double ecDist = Math.pow(xDiff, p) + Math.pow(yDiff, p);
        distance = Math.pow(ecDist, 1/p);
        return distance;
    }

    
    static void main(String[] args) {
        TestClass tc = new TestClass();
        System.out.println(tc.calculateDistance(1, 1, 2, 2, 2));
        
        
        
    }
    
    //
        
}

