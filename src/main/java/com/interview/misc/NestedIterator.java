package com.interview.misc;

import java.util.Iterator;
import java.util.List;
import java.util.Stack;

/*
 * https://leetcode.com/problems/flatten-nested-list-iterator/ 
 * https://www.youtube.com/watch?v=V-gPWPOyq8I
 * Category: Medium, Tricky, Top150, Facebook
 * Related: https://leetcode.com/problems/flatten-2d-vector/ Medium
 * https://leetcode.com/problems/zigzag-iterator/ Medium
 * https://leetcode.com/problems/mini-parser/ Medium
 * https://leetcode.com/problems/array-nesting/ Medium
 * You are given a nested list of integers nestedList. Each element is either an integer or a list whose elements may also be integers or other lists. Implement an iterator to flatten it.

Implement the NestedIterator class:

NestedIterator(List<NestedInteger> nestedList) Initializes the iterator with the nested list nestedList.
int next() Returns the next integer in the nested list.
boolean hasNext() Returns true if there are still some integers in the nested list and false otherwise.
Your code will be tested with the following pseudocode:

initialize iterator with nestedList
res = []
while iterator.hasNext()
    append iterator.next() to the end of res
return res
If res matches the expected flattened list, then your code will be judged as correct.

 

Example 1:

Input: nestedList = [[1,1],2,[1,1]]
Output: [1,1,2,1,1]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,1,2,1,1].
Example 2:

Input: nestedList = [1,[4,[6]]]
Output: [1,4,6]
Explanation: By calling next repeatedly until hasNext returns false, the order of elements returned by next should be: [1,4,6].
 

Constraints:

1 <= nestedList.length <= 500
The values of the integers in the nested list is in the range [-106, 106].
 */
public class NestedIterator implements Iterator<Integer> {
    /*
     * Runtime: 1 ms, faster than 100.00% of Java online submissions for Flatten Nested List Iterator.
Memory Usage: 41.5 MB, less than 57.73% of Java online submissions for Flatten Nested List Iterator.
TC: O(N) iterating only one
hasNext and next has O(1)
     */
    Stack<Iterator<NestedInteger>> stack = new Stack<>();
    Integer nextVal = null;
    public NestedIterator(List<NestedInteger> nestedList) {
        stack.push(nestedList.iterator());
    }

    @Override
    public Integer next() {
        if (!hasNext()) {
            throw new IllegalArgumentException();
        }
        Integer val = nextVal;
        nextVal = null;
        return val;
    }

    @Override
    public boolean hasNext() {
        if (nextVal != null) {
            return true;
        }
        while (!stack.isEmpty()) {
            boolean pushedIntoStack = false;
            Iterator<NestedInteger> itr = stack.peek();
            if (itr.hasNext()) {
                NestedInteger ni = itr.next();
                if (ni.isInteger()) {
                    nextVal = ni.getInteger();
                    return true;
                } else {
                    pushedIntoStack = true;
                    stack.push(ni.getList().iterator());
                }
            }
            if (!pushedIntoStack) {
                stack.pop();
            }
        }
        return false;
    }
}

interface NestedInteger {
    boolean isInteger();
    Integer getInteger();
    List<NestedInteger> getList();
}