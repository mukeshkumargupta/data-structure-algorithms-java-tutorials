package com.interview.multithreaded;

/*
 * Reference: https://www.youtube.com/watch?v=qQjSPkC6_gU
 * https://leetcode.com/problems/print-foobar-alternately/description/?envType=problem-list-v2&envId=concurrency
 * Reference: https://leetcode.com/problems/print-foobar-alternately/
 * Category: Medium, Concurrency
 *
 * Explanation:
 * - Two synchronized methods: `printFoo()` and `printBar()`
 * - `fooTurn` flag ensures "foo" prints first.
 * - Threads wait until it's their turn and then notifyAll().
 *
 * Example Output:
 * For n = 2, the output will be:
 *
 *  "foobarfoobar"
 *
 * Why is this Clean?
 * ✅ Simple and readable
 * ✅ Uses wait() and notifyAll() for synchronization
 * ✅ Avoids busy-waiting or unnecessary complexity
 * ✅ Works efficiently for any n
 */

public class PartA_D_A_PrintFooBarAlternately {

    private static class FooBar {
        private int n;
        private boolean fooTurn = true;

        public FooBar(int n) {
            this.n = n;
        }

        public synchronized void foo(Runnable printFoo) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (!fooTurn) {
                    wait();
                }
                printFoo.run();  // Prints "foo"
                fooTurn = false;
                notifyAll();
            }
        }

        public synchronized void bar(Runnable printBar) throws InterruptedException {
            for (int i = 0; i < n; i++) {
                while (fooTurn) {
                    wait();
                }
                printBar.run();  // Prints "bar"
                fooTurn = true;
                notifyAll();
            }
        }

    }

}
