package com.interview.array;

/*
 * https://leetcode.com/problems/gas-station/ 100% runtime
 * Category: Medium, Must Do, Google
 * Related;
 * https://leetcode.com/problems/minimum-moves-to-equal-array-elements-ii/ Medium
 * https://leetcode.com/problems/lonely-pixel-ii/ Medium
 * https://leetcode.com/problems/find-the-shortest-superstring/ Hard
 */
public class GasStation {
    public int canCompleteCircuit(int[] gas, int[] cost) {//runtime 100%
        int l = gas.length;
        int startingIndex = 0;
        int gasLeft = 0;
        int lastCalculatedDiff = 0;
        for (int i = 0; i < l ; i++) {
            gasLeft += gas[i] - cost[i];
            if (gasLeft < 0) {
                lastCalculatedDiff += gasLeft;
                startingIndex = i+1;
                if (startingIndex == l) {//if crossed
                  return -1;  
                }
                gasLeft = 0;
            }
        }
        //check circular condition
        if ((lastCalculatedDiff +  gasLeft) >=0) {
            return startingIndex;
        } else {
            return -1;
        }
        
    }
}
