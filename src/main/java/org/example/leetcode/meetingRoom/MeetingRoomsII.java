package org.example.leetcode.meetingRoom;

import java.util.Arrays;
import java.util.PriorityQueue;

/**
 * LeetCode #253. Meeting Rooms II
 * leetcode.com/problems/meeting-rooms-ii/
 *
 * TC: O(NlogN): per each interval, we do O(logN) to add/remove from pq
 * SC: O(N): pq at most contains N elements
 */
public class MeetingRoomsII {

    /**
     * Approach 1: Sort by start time
     * why sort by start time?
     * - if we sort by end time, we need to check if the current interval's start time is greater than the previous interval's end time
     * keeping the pq sorted by end time
     * only remove the earliest element of pq if it's not overlapped. otherwise, add the current interval's end time
     */
    public int minMeetingRooms(int[][] intervals) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        Arrays.sort(intervals, (a, b) -> a[0] - b[0]);

        for(int[] interval : intervals) {
            if(pq.isEmpty()) pq.add(interval[1]);
            else {
                int earliestEndTime = pq.peek();
                int currStart = interval[0];
                int currEnd = interval[1];
                // remove earliestEndTime cuz it's not overlapped
                if(earliestEndTime <= currStart) {
                    pq.remove();
                }
                // and always put the curr time's end
                pq.add(currEnd);
            }
        }
        return pq.size();
    }

    /**
     * Approach 2: Separate start & end time individually and sort them
     */
    public int minMeetingRooms2(int[][] intervals) {
        if(intervals.length == 0) return 0;

        int len = intervals.length;
        int[] start = new int[len];
        int[] end = new int[len];

        for(int i = 0; i < len; i++) {
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }

        Arrays.sort(end);
        Arrays.sort(start);

        int startPos = 0;
        int endPos = 0;

        int roomOccupied = 0;

        while(startPos < len) {

            // If the last end time is not overlapping with currStart,
            // discard the room occupied and move to next end time
            if(start[startPos] >= end[endPos]) {
                roomOccupied--;
                endPos++;
            }

            roomOccupied++;
            startPos++;
        }

        return roomOccupied;
    }
}
