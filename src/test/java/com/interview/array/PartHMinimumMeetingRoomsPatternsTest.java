package com.interview.array;

import org.junit.Assert;
import org.junit.Test;

public class PartHMinimumMeetingRoomsPatternsTest {

    @Test
    public void testDifferentCases() {
        PartHMinimumMeetingRoomsPatterns partHMinimumMeetingRoomsPatterns = new PartHMinimumMeetingRoomsPatterns();
        PartHMinimumMeetingRoomsPatterns.Interval[] interval = new PartHMinimumMeetingRoomsPatterns.Interval[4];
        interval[0] = new PartHMinimumMeetingRoomsPatterns.Interval(0,3);
        interval[1] = new PartHMinimumMeetingRoomsPatterns.Interval(2,5);
        interval[2] = new PartHMinimumMeetingRoomsPatterns.Interval(6,8);
        interval[3] = new PartHMinimumMeetingRoomsPatterns.Interval(8,10);
        Assert.assertEquals(2, partHMinimumMeetingRoomsPatterns.minMeetingRooms(interval));
        Assert.assertEquals(2, partHMinimumMeetingRoomsPatterns.minMeetingRooms1(interval));
    }
}
