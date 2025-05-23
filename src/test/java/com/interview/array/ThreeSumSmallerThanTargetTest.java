package com.interview.array;

import com.interview.twopointer.twopointersumpattern.ThreeSumSmaller;
import junit.framework.Assert;
import org.junit.Test;

public class ThreeSumSmallerThanTargetTest {

    @Test
    public void testDifferentCases() {
        ThreeSumSmaller threeSumSmallerThanTarget = new ThreeSumSmaller();
        int[] input = {-2, 0, 1, 3};
        Assert.assertEquals(2, threeSumSmallerThanTarget.threeSumSmaller(input, 2));
    }
}
