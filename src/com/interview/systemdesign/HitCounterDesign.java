package com.interview.systemdesign;

import java.util.*;

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 *
 * Reference: https://www.youtube.com/watch?v=hsUPfyfPcxE
 * Reference: https://www.geeksforgeeks.org/design-a-hit-counter/?ref=rp
 * Difficulty: Hard
 * Company: Google, Amazon, Facebook
 * Status: Not Done
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
        
    }


    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        HitCounterDesign hcd = new HitCounterDesign();
        hcd.runHitCounterDesign();
        
    }
    
}
