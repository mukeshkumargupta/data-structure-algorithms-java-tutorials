package com.interview.stackqueue.C_NGEPatterns;

import java.util.*;
/*
 * Problem: LeetCode 901 - Online Stock Span
 * Reference: https://leetcode.com/problems/online-stock-span/
 * Video Explanation: https://www.youtube.com/watch?v=eay-zoSRkVc&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=15
 *
 * Category: Medium, Tricky
 *
 * Related Problems:
 * - https://leetcode.com/problems/basic-calculator-iv/ (Hard)
 * - https://leetcode.com/problems/final-prices-with-a-special-discount-in-a-shop/ (Easy)
 * - https://leetcode.com/problems/next-greater-node-in-linked-list/ (Medium)
 *
 * Description:
 * ------------
 * Design a class `StockSpanner` that collects daily price quotes for a stock and returns the
 * span of that stock's price for the current day.
 *
 * The span of the stock's price today is defined as the maximum number of consecutive days
 * (starting from today and going backward) for which the price was less than or equal to today's price.
 *
 * Example:
 * --------
 * Input:
 * ["StockSpanner", "next", "next", "next", "next", "next", "next", "next"]
 * [[], [100], [80], [60], [70], [60], [75], [85]]
 *
 * Output:
 * [null, 1, 1, 1, 2, 1, 4, 6]
 *
 * Explanation:
 * StockSpanner S = new StockSpanner();
 * S.next(100) -> returns 1
 * S.next(80)  -> returns 1
 * S.next(60)  -> returns 1
 * S.next(70)  -> returns 2
 * S.next(60)  -> returns 1
 * S.next(75)  -> returns 4
 * S.next(85)  -> returns 6
 *
 * Note:
 * -----
 * - Each call to StockSpanner.next(int price) will have 1 <= price <= 10^5.
 * - At most 10,000 calls to StockSpanner.next per test case.
 * - At most 150,000 calls across all test cases.
 * - Time limit is reduced by 75% for C++ and 50% for other languages.
 */
public class PartF_B_B_OnlineStockSpan {
    /*
     * LeetCode 901: Online Stock Span
     *
     * Problem Description:
     * ---------------------
     * Design a data structure that efficiently computes the span of stock prices.
     * A stock span is the number of consecutive days (including today) where the
     * stock price has been less than or equal to the price on the current day.
     *
     * Problem Statement:
     * -------------------
     * For each day's price, return how many consecutive days (including today) the
     * stock price has been less than or equal to the current day's price.
     *
     * Brute Force Solution:
     * ----------------------
     * - For each new price on day i:
     *     - Start from day i - 1 and check previous prices.
     *     - Continue checking backwards as long as the price is less than or equal
     *       to the current day's price.
     *     - Count the number of such consecutive days.
     *
     * Time Complexity:
     * ----------------
     * O(n^2) in the worst case, where n is the number of days.
     * For each new price, we may need to traverse all previous days.
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
     * Problem: Online Stock Span (LeetCode 901)
     *
     * References:
     * https://leetcode.com/problems/online-stock-span/
     * https://www.youtube.com/watch?v=eay-zoSRkVc
     *
     * ✅ Category: Medium, Tricky
     * ✅ Related Problems:
     *    - Basic Calculator IV (Hard)
     *    - Final Prices With a Special Discount in a Shop (Easy)
     *    - Next Greater Node in Linked List (Medium)
     *
     * ✅ Objective:
     * Implement a class `StockSpanner` which collects daily stock prices and returns
     * the span for each price. The span is defined as the number of consecutive days
     * (including today) the price was less than or equal to today's price.
     *
     * ✅ Approach: Monotonic Stack (Decreasing Stack)
     *
     * We use a stack to store pairs of (price, span). The key idea is:
     * - For each incoming price, pop all previous prices from the stack that are
     *   less than or equal to it.
     * - Accumulate their span values and return the total span for today.
     * - Push the current price and its total span to the stack.
     *
     * ✅ Object-Oriented Design:
     * Define a `PriceSpan` class (or use int[] in Java) to hold both the price and its span.
     * This improves readability and separates concerns.
     *
     * ✅ Efficiency:
     * Each price is pushed and popped from the stack at most once.
     * Hence, the amortized time complexity is O(1) per operation.
     *
     * ✅ Time Complexity:
     * - Amortized O(1) per call to `next()`
     * - Worst case O(n) for n calls overall
     *
     * ✅ Space Complexity:
     * - O(N), where N is the number of calls to `next()`, since stack stores prices that haven't been spanned over yet.
     *
     * ✅ Dry Run Example:
     * For input prices: [100, 80, 60, 70, 60, 75, 85]
     * The spans returned are: [1, 1, 1, 2, 1, 4, 6]
     *
     * Stack evolution:
     * Day  Price  Stack (top to bottom)                   Span
     * 1    100    [100,1]                                 => 1
     * 2    80     [100,1], [80,1]                         => 1
     * 3    60     [100,1], [80,1], [60,1]                 => 1
     * 4    70     [100,1], [80,1], [70,2]                 => 2 (merges with 60)
     * 5    60     [100,1], [80,1], [70,2], [60,1]         => 1
     * 6    75     [100,1], [80,1], [75,4]                 => 4 (merges with 60, 70, 60)
     * 7    85     [100,1], [85,6]                         => 6 (merges with 75, 80)
     *
     * This solution balances efficiency, clarity, and structure, making it suitable
     * for large-scale, real-time stock data processing.
     */
    private static class Optimize {
        static class StockSpanner {

            // Stack stores pairs of (price, span)
            private Stack<int[]> stack;

            public StockSpanner() {
                stack = new Stack<>();
            }

            public int next(int price) {
                int span = 1;

                // Merge spans for prices less than or equal to the current price
                while (!stack.isEmpty() && stack.peek()[0] <= price) {
                    span += stack.pop()[1];
                }

                // Push the current price and its span to the stack
                stack.push(new int[]{price, span});

                return span;
            }
        }
    }

    
}
