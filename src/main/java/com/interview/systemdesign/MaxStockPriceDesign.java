package com.interview.systemdesign;

import java.util.*;

/**
 * Date 05/08/2020
 * @author Mukesh Kumar Gupta
 *
 * Reference: https://www.youtube.com/watch?v=hsUPfyfPcxE
 * Reference: https://www.geeksforgeeks.org/design-a-price-counter/?ref=rp
 * Difficulty: Hard
 * Company: Google, Amazon, Facebook
 * Category: VImp
 * Status: Done
 * Query: If not price change in last 5 minute then what should we return i think question should be to return last price
 */

public class MaxStockPriceDesign {
    class Stock {
        float price;
        int timestamp;
        Stock (float stockPrice, int stockTimestamp) {
            this.price = stockPrice;
            this.timestamp = stockTimestamp;
        }
    }
    class PriceCounter {
        Queue<Stock> queue;
        
        PriceCounter() {
            queue = new LinkedList<>();
        }
        
        public void price(Stock price) {
            queue.offer(price);
        }
        
        private float findPrice(Queue<Stock> queue) {
            float max = Float.MIN_VALUE;
            for (Stock value : queue) {
                if (value.price > max) {
                    max = value.price;
                }
            }
            if (max <= Float.MIN_VALUE) {
                return (float) 0.0;
            }
            return max;
        }
        
        public float getMaxPrice(int timestamp) {
            while(!queue.isEmpty() && timestamp - queue.peek().timestamp  >= 300) {
                queue.poll();
            }
            
            return findPrice(queue);
        }
        
    }
    
    void runMaxStockPriceDesign() {
        PriceCounter hc = new PriceCounter();

        //Test Case:
        //1(1) 3(2) 2(3) 0(4) 8(300) 6(600) 3(900) 2(1200) 1(1500) 3(1800)    
        hc.price(new Stock(1, 1));
        hc.price(new Stock(9, 2));
        hc.price(new Stock(10, 3));
        hc.price(new Stock(6, 4));
        hc.price(new Stock(8, 300));
        System.out.println(hc.getMaxPrice(300)); //10.0
        System.out.println(hc.getMaxPrice(301)); //10.0
        System.out.println(hc.getMaxPrice(302)); //10.0
        System.out.println(hc.getMaxPrice(303)); //8.0
        System.out.println(hc.getMaxPrice(600)); //0.0
        System.out.println(hc.getMaxPrice(900)); //0.0
        System.out.println(hc.getMaxPrice(1200)); //0.0
        System.out.println(hc.getMaxPrice(1500)); //0.0
 
    }


    
    public static void main(String[] args) {
        // TODO Auto-generated method stub
        MaxStockPriceDesign hcd = new MaxStockPriceDesign();
        hcd.runMaxStockPriceDesign();
        
    }
    
}
