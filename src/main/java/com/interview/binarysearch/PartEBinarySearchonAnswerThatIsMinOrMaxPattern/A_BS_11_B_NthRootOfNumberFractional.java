package com.interview.binarysearch.PartEBinarySearchonAnswerThatIsMinOrMaxPattern;
/*
 * Category: Easy, Must Do
 * https://www.youtube.com/watch?v=rjEJeYCasHs&list=PLgUwDviBIf0pMFMWuuvDNMAkoQFi-h0ZF&index=12
 *
 * https://www.youtube.com/watch?v=WjpswYrS2nY,
 * This solution looks better solution because +1 and -1 we did in square root which might increase more iteration
 * https://www.geeksforgeeks.org/n-th-root-number/
 * Given two numbers N and A, find N-th root of A. In mathematics, Nth root of a number A is a real number that gives A, when we raise it to integer power N. These roots are used in Number Theory and other advanced branches of mathematics.
Refer Wiki page for more information.
Examples:


Input : A = 81
        N = 4
Output : 3
3^4 = 81
 */
public class A_BS_11_B_NthRootOfNumberFractional {
    private static class Optimized {
        double multiply(int powerRoot, double number) {
            double ans = 1.0;
            for(int i = 1;i<=powerRoot;i++) {
                ans = ans * number;
            }
            return ans;
        }

        double getNthRoot(int powerRoot, int number) {
            //TC: powerRoot*Log2(10^decimalPlaces)*number)
            double low = 1;
            double high = number;
            double eps = 1e-6; //Question is asked for five digit then take ten to power -6

            while((high - low) > eps) {
                double mid = (low + high) / 2.0;
                if(multiply(powerRoot, mid) < number) {
                    low = mid; //Tricky, Note: mid +1  will not work
                }
                else {
                    high = mid;  //Tricky, mid -1 will not work
                }
            }

            System.out.println(low + "->" + high);
            return low;

        }
        public static void main(String[] args) {
            // TODO Auto-generated method stub
            Optimized nrn = new Optimized();
            //nrn.getNthRoot( 4, 81);
            nrn.getNthRoot( 2, 9);

        }
    }
}
