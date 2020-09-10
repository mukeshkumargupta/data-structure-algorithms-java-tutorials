package com.interview.misc;

import java.util.*;

/**
 * Find the Celebrity
 * https://leetcode.com/problems/find-the-celebrity/
 * Category: Medium
 * Status: done
 */
class Relation {
    boolean knows(int a, int b) {
        return false;
    }
}

public class FindingCelebrity extends Relation {

    public int findCelebrity(int n) {
        int celebrity = 0;
        for (int i = 1; i < n; i++) {
            if (knows(celebrity, i)) {
                celebrity = i;
            }
        }
        for (int i = 0; i < n; i++) {
            if (i == celebrity) {
                continue;
            }
            if (knows(celebrity, i) || !knows(i, celebrity)) {
                return -1;
            }
        }
        return celebrity;
    }
}

//Reference: https://www.youtube.com/watch?v=5rkKCrPzD8c&list=PLIA-9QRQ0RqGV42TbXFWWrwfsPI_W6u21&index=20&t=0s
class Celebrity
{
    // The task is to complete this function
    int getId(int arr[][], int n)
    {
        // Your code here
       Stack<Integer> s = new Stack<Integer>();
       for(int i =0; i <n; i++)
        s.push(i);
       while(s.size() >1){
           int a = s.pop();
           int b = s.pop();
           if(arr[a][b] == 0)
            s.push(a);
           else 
             s.push(b);
       }
       int x = s.pop();
       boolean flag =true;
       
       for(int i =0; i<n; i++){
         if(i == x) continue;
         if(arr[x][i] == 1)    flag = false;
         if(arr[i][x] == 0)    flag = false;
       }
       
        if(!flag) return -1;
        return x;
    }
}
