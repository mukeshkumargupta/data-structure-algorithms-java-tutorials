package com.interview.stackqueue;

import java.util.*;
/*
 * 
 * Reference: https://leetcode.com/problems/online-stock-span/
 * https://www.youtube.com/watch?v=eay-zoSRkVc&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=15
 * Category: Medium, Tricky
 * Related:
 * https://leetcode.com/problems/basic-calculator-iv/description/ Hard
 * https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/description/ Easy
 * https://leetcode.com/problems/next-greater-node-in-linked-list/description/ Medium
 * Write a class StockSpanner which collects daily price quotes for some stock, and returns the span of that stock's price for the current day.

The span of the stock's price today is defined as the maximum number of consecutive days (starting from today and going backwards) for which the price of the stock was less than or equal to today's price.

For example, if the price of a stock over the next 7 days were [100, 80, 60, 70, 60, 75, 85], then the stock spans would be [1, 1, 1, 2, 1, 4, 6].

 

Example 1:

Input: ["StockSpanner","next","next","next","next","next","next","next"], [[],[100],[80],[60],[70],[60],[75],[85]]
Output: [null,1,1,1,2,1,4,6]
Explanation: 
First, S = StockSpanner() is initialized.  Then:
S.next(100) is called and returns 1,
S.next(80) is called and returns 1,
S.next(60) is called and returns 1,
S.next(70) is called and returns 2,
S.next(60) is called and returns 1,
S.next(75) is called and returns 4,
S.next(85) is called and returns 6.

Note that (for example) S.next(75) returned 4, because the last 4 prices
(including today's price of 75) were less than or equal to today's price.
 

Note:

Calls to StockSpanner.next(int price) will have 1 <= price <= 10^5.
There will be at most 10000 calls to StockSpanner.next per test case.
There will be at most 150000 calls to StockSpanner.next across all test cases.
The total time limit for this problem has been reduced by 75% for C++, and 50% for all other languages.
 */
public class OnlineStockSpan {
    /*
    The Online Stock Span problem on LeetCode (Problem 901) requires you to design a data structure that efficiently computes the span of stock prices. A stock span is the number of consecutive days the price of the stock has been less than or equal to the price on the current day.

Problem:
For each day's price, you need to return how many consecutive days (including today) the stock price has been less than or equal to the current day's price.

Brute Force Solution:
For each new price, you check previous prices to determine how many consecutive days have stock prices less than or equal to the current price.

Brute Force Approach:
For each new price on day i, start from day i - 1 and check previous prices.
Keep going back as long as the previous day's price is less than or equal to the current day's price.
Return the count of such consecutive days.
Time Complexity:
O(n^2) in the worst case, where n is the number of days, since for each new price, you may need to traverse all the previous days.
     */
        public static class StockSpannerBruteForce {
            private List<Integer> prices;

            public StockSpannerBruteForce() {
                prices = new ArrayList<>();
            }

            public int next(int price) {
                prices.add(price);
                int span = 1;
                int i = prices.size() - 2; // Previous index

                // Check how many consecutive days have price <= current price
                while (i >= 0 && prices.get(i) <= price) {
                    span++;
                    i--;
                }

                return span;
            }

            public static void main(String[] args) {
                StockSpannerBruteForce spanner = new StockSpannerBruteForce();
                System.out.println(spanner.next(100)); // Output: 1
                System.out.println(spanner.next(80));  // Output: 1
                System.out.println(spanner.next(60));  // Output: 1
                System.out.println(spanner.next(70));  // Output: 2
                System.out.println(spanner.next(60));  // Output: 1
                System.out.println(spanner.next(75));  // Output: 4
                System.out.println(spanner.next(85));  // Output: 6
            }
        }

        /*
        Explanation:
        PriceSpan Class:

        We define a PriceSpan class that holds the price and the corresponding span (the number of consecutive days with prices less than or equal to this price).
        This improves readability and provides a clean way to manage both price and span together.
        Stack:

        The stack now stores PriceSpan objects. Each PriceSpan object contains the price and the computed span for that price.
        Span Calculation:

        For each new price, we pop all elements from the stack where the price is less than or equal to the current price.
        The span for the current price is incremented by the span of each popped element (as those prices are part of the span for the current day).
        Efficiency:

        This approach ensures that each price is pushed and popped from the stack at most once, leading to an overall time complexity of O(n) for n calls to the next() method.
        Example Walkthrough:
        For the input prices [100, 80, 60, 70, 60, 75, 85], the span for each price is calculated as follows:
        For 100, span is 1.
        For 80, span is 1.
        For 60, span is 1.
        For 70, span is 2 (because 70 >= 60).
        For 60, span is 1.
        For 75, span is 4 (it covers 60, 70, and 60).
        For 85, span is 6 (it covers all previous prices).
        This solution maintains the same logic as before but uses an object-oriented approach for better structure and readability.
         */

    public static class StockSpanner {

        // Define a class to represent (price, span) pair
        private static class PriceSpan {
            int price;
            int span;

            PriceSpan(int price, int span) {
                this.price = price;
                this.span = span;
            }
        }

        private Stack<PriceSpan> stack; // Stack to store PriceSpan objects

        public StockSpanner() {
            stack = new Stack<>();
        }

        public int next(int price) {
            int span = 1;

            // Pop elements from the stack while the price at the top is less than or equal to the current price
            while (!stack.isEmpty() && stack.peek().price <= price) {
                span += stack.pop().span; // Accumulate the span
            }

            // Push the current price and its span onto the stack
            stack.push(new PriceSpan(price, span));

            return span;
        }

        public static void main(String[] args) {
            StockSpanner spanner = new StockSpanner();
            System.out.println(spanner.next(100)); // Output: 1
            System.out.println(spanner.next(80));  // Output: 1
            System.out.println(spanner.next(60));  // Output: 1
            System.out.println(spanner.next(70));  // Output: 2
            System.out.println(spanner.next(60));  // Output: 1
            System.out.println(spanner.next(75));  // Output: 4
            System.out.println(spanner.next(85));  // Output: 6
        }
    }
    
}
