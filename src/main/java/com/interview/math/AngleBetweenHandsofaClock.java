package com.interview.math;

/*
https://leetcode.com/problems/angle-between-hands-of-a-clock/description/
Category: Medium, Facebook, Must Do
https://www.youtube.com/watch?v=FK_wnA0ERBQ
Given two numbers, hour and minutes, return the smaller angle (in degrees) formed between the hour and the minute hand.

Answers within 10-5 of the actual value will be accepted as correct.



Example 1:


Input: hour = 12, minutes = 30
Output: 165
Example 2:


Input: hour = 3, minutes = 30
Output: 75
Example 3:


Input: hour = 3, minutes = 15
Output: 7.5


Constraints:

1 <= hour <= 12
0 <= minutes <= 59
 */
public class AngleBetweenHandsofaClock {
    public double angleClock(int hour, int minutes) {
        float h = (hour%12 + (float)minutes/60)*30;
        float m = minutes*6;
        float angle = Math.abs(h-m);
        if(angle > 180) angle = 360-angle;
        return angle;
    }
}
