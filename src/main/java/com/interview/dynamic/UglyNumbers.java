package com.interview.dynamic;

import java.util.PriorityQueue;

/**
 * Date 03/08/2017
 * @author Mukesh Kumar Gupta
 *
 * Find nth ugly number.
 *
 * https://leetcode.com/problems/ugly-number-ii/
 * https://leetcode.com/problems/super-ugly-number/
 * http://www.geeksforgeeks.org/ugly-numbers/
 */
public class UglyNumbers {

    static class TreeNode implements Comparable<TreeNode> {
        int inputIndex;
        int index;
        int val;
        TreeNode (int inputIndex, int index, int val) {
            this.index = index;
            this.val = val;
            this.inputIndex = inputIndex;
        }

        @Override
        public int compareTo(TreeNode other) {
            return this.val - other.val;
        }
    }

    public int nthSuperUglyNumber1(int n, int[] primes) {

        PriorityQueue<TreeNode> pq = new PriorityQueue<>();
        for (int i = 0; i < primes.length; i++) {
            pq.offer(new TreeNode(i, 0, primes[i]));
        }
        int[] val = new int[n];
        val[0] = 1;
        for (int i = 1; i < n; ) {
            TreeNode TreeNode = pq.poll();
            if (val[i-1] != TreeNode.val) {
                val[i] = TreeNode.val;
                i++;
            }
            TreeNode.index = TreeNode.index + 1;
            TreeNode.val = primes[TreeNode.inputIndex]*val[TreeNode.index];
            pq.offer(TreeNode);
        }
        return val[n - 1];
    }

    int ugly(int n){
        int arr[] = new int[n];
        int count = 1;
        arr[0] = 1;
        int i2 = 0;
        int i3 = 0;
        int i5 = 0;
        while(count < n){
            int minNumber = min(arr[i2] * 2, arr[i3] * 3, arr[i5] * 5);
            if(minNumber == arr[i2]*2){
                i2++;
            }
            if(minNumber == arr[i3]*3){
                i3++;
            }
            if(minNumber == arr[i5]*5){
                i5++;
            }
            arr[count++] = minNumber;
        }
        
        return arr[n-1];
    }
    
    private int min(int a,int b, int c){
        int l = Math.min(a, b);
        return Math.min(l, c);
    }
    
    public static void main(String args[]) {
        UglyNumbers ugly = new UglyNumbers();
        int result = ugly.ugly(150);
        System.out.println(result);
        int[] primes = {2, 3, 7, 11};
        System.out.print(ugly.nthSuperUglyNumber1(17, primes));
    }
    
}
