package com.interview.systemdesign;

import java.util.*;

/**
 * Date: 05/08/2020
 * Author: Mukesh Kumar Gupta
 *
 * References:
 * - https://www.youtube.com/watch?v=hsUPfyfPcxE
 * - https://www.geeksforgeeks.org/design-a-hit-counter/?ref=rp
 * - https://leetcode.com/problems/design-hit-counter/
 * Problem Explanation
 * The Hit Counter problem involves designing a system that tracks the number of hits received in the last 5 minutes (300 seconds). The system must provide functionality to log hits at specific timestamps and retrieve the number of hits that occurred within the last 300 seconds from a given timestamp.
 *
 * Key Requirements
 * Hit Tracking: The system must keep track of all the hits that occur within a 300-second window.
 * Time Constraint: For any given timestamp, it should be able to accurately return the number of hits that have occurred in the last 300 seconds.
 * Efficient Data Structure: The implementation should efficiently manage incoming hits and allow for quick retrieval of hits within the specified time frame.
 * Applications
 * Web Analytics: Analyzing user activity on a website, counting page views or clicks in real time.
 * API Rate Limiting: Tracking the number of requests made by users within a certain time window to prevent abuse of resources.
 * Event Logging: Recording events and monitoring activities over a specific period.
 * Code Implementation
 * Below is the implementation of the Hit Counter using a queue. This approach allows us to efficiently manage hits and quickly calculate the number of valid hits in the last 300 seconds.
 *
 * Category: Medium, Must Do
 * Companies: Google, Amazon, Facebook
 *
 * Examples:
 *
 * HitCounter counter = new HitCounter();
 *
 * // hit at timestamp 1.
 * counter.hit(1);
 *
 * // hit at timestamp 2.
 * counter.hit(2);
 *
 * // hit at timestamp 3.
 * counter.hit(3);
 *
 * // get hits at timestamp 4, should return 3.
 * counter.getHits(4);
 *
 * // hit at timestamp 300.
 * counter.hit(300);
 *
 * // get hits at timestamp 300, should return 4.
 * counter.getHits(300);
 *
 * // get hits at timestamp 301, should return 3.
 * counter.getHits(301);
 */
public class HitCounterDesign {
    // Inner class to represent the Hit Counter
    class HitCounter {
        Queue<Integer> queue; // Queue to store timestamps of hits

        // Constructor to initialize the HitCounter
        HitCounter() {
            queue = new LinkedList<>();
        }

        // Method to record a hit at a specific timestamp
        public void hit(int timestamp) {
            queue.offer(timestamp); // Add the timestamp to the queue
        }

        // Method to get the number of hits in the last 300 seconds
        public int getHits(int timestamp) {
            // Remove hits that are older than 300 seconds from the current timestamp
            while (!queue.isEmpty() && timestamp - queue.peek() >= 300) {
                queue.poll(); // Remove the oldest hit
            }

            // Return the number of valid hits in the queue
            return queue.size();
        }
    }

    // Method to run sample tests for the HitCounter
    void runHitCounterDesign() {
        HitCounter hc = new HitCounter(); // Create a new HitCounter instance

        // Simulate hits at various timestamps
        for (int i = 1; i <= 3; i++) {
            hc.hit(i);
        }

        hc.hit(300); // Hit at timestamp 300
        System.out.println(hc.getHits(300)); // Expected output: 4
        System.out.println(hc.getHits(301)); // Expected output: 3
        System.out.println(hc.getHits(302)); // Expected output: 2
        System.out.println(hc.getHits(303)); // Expected output: 1
        System.out.println(hc.getHits(599)); // Expected output: 1
        System.out.println(hc.getHits(600)); // Expected output: 0
        // Calculation
        // At timestamp 300, we have hits at 1, 2, 3, and 300 (total 4 hits).
        // At timestamp 301, hits at 2, 3, and 300 (total 3 hits).
        // At timestamp 302, hits at 3 and 300 (total 2 hits).
        // At timestamp 303, only hit at 300 (total 1 hit).
        // At timestamp 599, still the hit at 300 is valid (total 1 hit).
        // At timestamp 600, no hits are valid (total 0 hits).
    }

    // Main method to run the program
    public static void main(String[] args) {
        HitCounterDesign hcd = new HitCounterDesign();
        hcd.runHitCounterDesign(); // Execute the test runs for the HitCounter
    }
}