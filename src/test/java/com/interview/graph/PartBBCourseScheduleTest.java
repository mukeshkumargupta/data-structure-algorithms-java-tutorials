package com.interview.graph;

import com.interview.graph.PartBTopologicalSorting.PartB_B_CourseSchedule;
import org.junit.Assert;
import org.junit.Test;

public class PartBBCourseScheduleTest {

    @Test
    public void testDifferentCases() {
        PartB_B_CourseSchedule cs = new PartB_B_CourseSchedule();
        int[][] courses = {{1,0},{2,0},{3,1},{3,2}};
        int[] output = cs.findOrder(4, courses);
        int[] expected = {0, 2, 1, 3};
        Assert.assertArrayEquals(expected, output);


        int[][] courses1 = {{1,0},{2,0},{3,1},{3,2}, {0, 3}};
        int[] output1 = cs.findOrder(4, courses1);
        int[] expected1 = {};
        Assert.assertArrayEquals(expected1, output1);
    }
}
