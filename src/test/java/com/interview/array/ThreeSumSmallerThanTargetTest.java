package com.interview.array;

import com.interview.twopointer.twopointersumpattern.E_ThreeSumSmaller;
import junit.framework.Assert;
import org.junit.Test;

public class ThreeSumSmallerThanTargetTest {

    @Test
    public void testDifferentCases() {
        E_ThreeSumSmaller threeSumSmallerThanTarget = new E_ThreeSumSmaller();
        int[] input = {-2, 0, 1, 3};
        Assert.assertEquals(2, threeSumSmallerThanTarget.threeSumSmaller(input, 2));
    }
}
