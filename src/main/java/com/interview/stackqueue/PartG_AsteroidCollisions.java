package com.interview.stackqueue;

import java.util.Stack;
/*
https://www.youtube.com/watch?v=_eYGqw_VDR4&list=PLgUwDviBIf0pOd5zvVVSzgpo6BaCpHT9c&index=11
https://leetcode.com/problems/asteroid-collision/submissions/1424233342/
Category: Medium, Tricky
https://leetcode.com/problems/can-place-flowers/description/ Easy
https://leetcode.com/problems/destroying-asteroids/description/ Medium
https://leetcode.com/problems/count-collisions-on-a-road/description/ Medium
 */
public class PartG_AsteroidCollisions {
    /*
    Optimized Approach (Using a Stack):
        We can optimize the solution using a stack, which will help keep track of the asteroids that are still "active" after collisions. The stack will allow us to simulate the collisions efficiently.

        Steps:
        Traverse through the asteroid array.
        For each asteroid:
        If the stack is empty or the asteroid is moving right (positive), push it onto the stack.
        If the asteroid is moving left (negative):
        Compare with the top of the stack (which is moving right).
        If they collide (i.e., the top of the stack is positive and the current asteroid is negative), decide which asteroid survives based on size.
        Continue popping from the stack if the negative asteroid keeps destroying the top of the stack.
        If no collision occurs, push the asteroid onto the stack.
        Return the remaining asteroids in the stack.

        Time Complexity:
        O(2n): Each asteroid is pushed and popped from the stack at most once, so the overall time complexity is linear.
        Space Complexity:
        O(n): In the worst case, all asteroids are stored in the stack.
     */
    public static class AsteroidCollisions {

        public int[] asteroidCollision(int[] asteroids) {
            Stack<Integer> stack = new Stack<>();

            for (int asteroid : asteroids) {
                // Process current asteroid
                boolean isAlive = true;  // Indicates whether the asteroid survives after potential collisions

                while (!stack.isEmpty() && asteroid < 0 && stack.peek() > 0) {
                    // Collision occurs: current asteroid is negative, top of stack is positive
                    if (Math.abs(asteroid) > stack.peek()) {
                        // Current asteroid destroys the one on top of the stack
                        stack.pop();
                    } else if (Math.abs(asteroid) == stack.peek()) {
                        // Both asteroids destroy each other
                        stack.pop();
                        isAlive = false;  // Current asteroid also gets destroyed
                        break;
                    } else {
                        // Current asteroid gets destroyed
                        isAlive = false;
                        break;
                    }
                }

                if (isAlive) {
                    // If the asteroid survived the collision, push it onto the stack
                    stack.push(asteroid);
                }
            }

            // Convert stack to array
            int[] result = new int[stack.size()];
            for (int i = result.length - 1; i >= 0; i--) {
                result[i] = stack.pop();
            }
            return result;
        }
    }
}
