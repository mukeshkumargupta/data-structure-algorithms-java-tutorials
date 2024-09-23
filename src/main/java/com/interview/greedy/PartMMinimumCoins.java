package com.interview.greedy;

import java.util.*;

/*
 * https://www.youtube.com/watch?v=mVg9CfJvayM&list=PLIA-9QRQ0RqHnG0S2GPaNhJEIL3RYqpGR&index=6
 *
 * If previous two is smaller than next then greedy work and same way all currency design. If this is not case then dp will work
 */
public class PartMMinimumCoins {
    
    void findMin(int V) {
        int[] denom = {1, 2, 5, 10, 20, 50, 100, 500, 1000};
        List<Integer> ans = new ArrayList<>();
        int l = denom.length;
        for (int i = l-1; i >=0; i--) {
            while(V >= denom[i]) {
                ans.add(denom[i]);
                V -= denom[i];
            }
        }
        for (int coin : ans) {
            System.out.println(coin);
            
        }
        
    }
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        PartMMinimumCoins mc = new PartMMinimumCoins();
        mc.findMin(49);
    }
    
}
