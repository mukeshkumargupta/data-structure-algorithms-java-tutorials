package com.interview.systemdesign;

import java.util.*;

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 *
 * Reference: https://www.youtube.com/watch?v=hsUPfyfPcxE
 * Reference: https://www.geeksforgeeks.org/design-a-hit-counter/?ref=rp
 * https://leetcode.com/problems/design-hit-counter/
 * Category: Medium
 * Company: Google, Amazon, Facebook
 * Examples:


HitCounter counter = new HitCounter();

// hit at timestamp 1.
counter.hit(1);

// hit at timestamp 2.
counter.hit(2);

// hit at timestamp 3.
counter.hit(3);

// get hits at timestamp 4, should return 3.
counter.getHits(4);

// hit at timestamp 300.
counter.hit(300);

// get hits at timestamp 300, should return 4.
counter.getHits(300);

// get hits at timestamp 301, should return 3.
counter.getHits(301);
 */

public class HitCounterDesign {
    class HitCounter {
        Queue<Integer> queue;
        
        HitCounter() {
            queue = new LinkedList<>();
        }
        
        public void hit(int timestanp) {
            queue.offer(timestanp);
        }
        
        public int getHits(int timestamp) {
            while(!queue.isEmpty() && timestamp - queue.peek()  >= 300) {
                queue.poll();
            }
            
            return queue.size();
        }
        
    }
    
    void runHitCounterDesign() {
        HitCounter hc = new HitCounter();
        for (int i=1 ; i <=  3 ; i++) {
            hc.hit(i);
        }

        hc.hit(300);
        System.out.println(hc.getHits(300)); //4
        System.out.println(hc.getHits(301)); //3
        System.out.println(hc.getHits(302)); //2
        System.out.println(hc.getHits(303)); //1
        System.out.println(hc.getHits(599)); //1
        System.out.println(hc.getHits(600)); //0
        //Calculation
        //Just write queue then do calculation u will find it out easily
        //300-1 =299
        //301-2=300
    }


    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HitCounterDesign hcd = new HitCounterDesign();
        hcd.runHitCounterDesign();
        
    }
    
}
