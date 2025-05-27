package com.interview.linklist.PartABasicLinkedListOperations.D_RotationPattern;

/**
 * Date 12/30/2017
 * @author Mukesh Kumar Gupta
 *
 * Given an input array find which rotation will give max sum of i * arr[i]
 *
 * Time complexity - O(n)
 * Space complexity - O(1)
 *
 * http://www.geeksforgeeks.org/find-maximum-value-of-sum-iarri-with-only-rotations-on-given-array-allowed/
 */
public class C_RotationWithMaxSum {
    int maxSum(int input[]) {
        int arrSum = 0;
        int rotationSum = 0;
        for (int i =0; i < input.length; i++) {
            arrSum += input[i];
            rotationSum += i*input[i];
        }

        int maxRotationSum = rotationSum;

        for (int i = 1; i < input.length; i++) {
            rotationSum += input.length*input[i - 1] - arrSum;
            maxRotationSum = Math.max(maxRotationSum, rotationSum);
        }
        return maxRotationSum;
    }

    public static void main(String args[]) {
        int input[] = {10, 1, 2, 3, 4, 5, 6, 7, 8, 9};
        C_RotationWithMaxSum rms = new C_RotationWithMaxSum();
        System.out.print(rms.maxSum(input));
    }
}
