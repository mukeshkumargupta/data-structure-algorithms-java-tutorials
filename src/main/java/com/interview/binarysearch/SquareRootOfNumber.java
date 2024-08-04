package com.interview.binarysearch;

/**
 *
 * https://leetcode.com/problems/sqrtx/
 * Very good explanation is given
 * https://www.youtube.com/watch?v=fItuKa_tIpY
 * Reference Video: https://www.youtube.com/watch?v=H7WymG3gab0
 * This is same as getNthRoot here it is asked square root
 * Category: Easy, Top150, Must Do
 * Related:
 * https://leetcode.com/problems/valid-perfect-square/ Easy
 * Tricky
 */
//Working code for https://leetcode.com/problems/sqrtx/ but difivult to understand
public class SquareRootOfNumber {
    public int mySqrt(int x) {
        /*
         * Runtime: 2 ms, faster than 79.01% of Java online submissions for Sqrt(x).
Memory Usage: 40.9 MB, less than 75.17% of Java online submissions for Sqrt(x).
TC: Log(N)
         */
        if (x == 0) {
            return 0;
        }
        //if x is not 0 then probable ans is 1 so start from x
        //so range is 1 to x
        int start = 1;
        int end = x;
        int result = 1;
        while (start <= end) {
            int mid = start + (end - start)/2; //to avoud overflow no (start + end)/2
            if (mid <= x/mid) {//no mid*mid to avoid over flow
                result = mid; //probable ans
                start = mid+1;
            } else {
                end = mid -1;
            }
        }
        return result;


    }
    
    //Real code for square root, Mine trying and working with real example
    private static String findSquareRoot_realbinarySearch(int number) {
        
        Boolean isNegative = false;
        if(number < 0) {
            number = -number;
            isNegative = true;
        }
 
        if(number == 1) {
            return number + (isNegative ? "i" : "");
        }
 
        double start = 0;
        double end = number;
        double precision = 0.0005;
        double mid = (start + end )/2;
        
        //while(start <= end) {//here this condition will not work, so we need to keep trying until we get result
          while(true) {
            mid = (start + end )/2;
            if(Math.abs(mid*mid - number) <= precision) {
                break; 
            } else if (mid*mid > number) {
                end = mid -1;
            } else {
                start = mid +1;
            }
        }
         
        return mid + (isNegative ? "i" : "");//either start or end
    }
    
    //Real code for square root
    //https://www.youtube.com/watch?v=H7WymG3gab0
    private static String findSquareRoot(int number) {
        
        Boolean isNegative = false;
        if(number < 0) {
            number = -number;
            isNegative = true;
        }
 
        if(number == 1) {
            return number + (isNegative ? "i" : "");
        }
 
        double start = 0;
        double end = number;
        double mid = (start+end)/2;
        double prevMid = 0;
        double diff = Math.abs(mid - prevMid);
        double precision = 0.0005;
 
        while((mid*mid != number) && (diff > precision)) {
            if(mid*mid > number) {
                end = mid -1; //Earlier it was mid, normal mid -1 is also wroking that is as usual binary search
            } else {
                start = mid +1;//Earlier it was mid, normal mid -1 is also wroking that is as usual binary search
            }
            prevMid = mid;
            mid = (start+end)/2;
            diff = Math.abs(mid - prevMid);
        }
         
        return mid + (isNegative ? "i" : "");
    }
    

    
    //Note this code will not work for some cases
    //https://leetcode.com/problems/sqrtx/
    public static int mySqrt_v1(int x) {
        double start = 0;
        double end = x;
        double mid = (start+end)/2;
        double prevMid = 0;
        double diff = Math.abs(mid - prevMid);
        double precision = 0.0005;
 
        while((mid*mid != x) && (diff > precision)) {
            if(mid*mid > x) {
                end = mid;
            } else {
                start = mid;
            }
            prevMid = mid;
            mid = (start+end)/2;
            diff = Math.abs(mid - prevMid);
        }
        int result = (int) mid;
        if (result + 1 - mid < .001)  {
            return result + 1;
        } else return result;
    }
    
    public static void main(String[] args) {
        int number = 9;
        //int number = 1;
        System.out.println("Square root of " + number + " = " + findSquareRoot(number));
        System.out.println("Square root of " + number + " = " + findSquareRoot_realbinarySearch(number));
        //System.out.println("Square root of " + number + " = " + mySqrt_v1(number));
    }
    
}


