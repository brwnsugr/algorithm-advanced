package org.example.leetcode.meetingRoom;

import java.util.Arrays;

/**
 * LeetCode #2402. Meeting Rooms III
 * leetcode.com/problems/meeting-rooms-iii/
 *
 * TC: (MlogM + M*N)
 */
public class MeetingRoomsIII {

    public int mostBooked(int n, int[][] meetings) {
        long[] roomAvialableTime = new long[n];
        int[] usedCounts = new int[n];

        Arrays.sort(meetings, (a, b) -> a[0] - b[0]);

        for(int[] meeting : meetings) {
            int currStart = meeting[0];
            int currEnd = meeting[1];
            long minRoomAvailabilityTime = Long.MAX_VALUE;
            int minTimeRoom = 0;
            boolean isRoomAvailable = false;

            // per meeting, iterate each room
            for(int i = 0; i < n; i++) {

                // if available room now, put meeting
                if(roomAvialableTime[i] <= currStart) {
                    // enque meeting into this room
                    isRoomAvailable = true;
                    usedCounts[i]++;
                    roomAvialableTime[i] = currEnd;
                    break;
                }

                // otherwiser, iterate all over and update the min
                if(minRoomAvailabilityTime > roomAvialableTime[i]) {
                    minRoomAvailabilityTime = roomAvialableTime[i];
                    minTimeRoom = i;
                }
            }

            if(!isRoomAvailable) {
                roomAvialableTime[minTimeRoom] += (currEnd - currStart);
                usedCounts[minTimeRoom]++;
            }
        }

        int maxMeetingCount = 0;
        int maxMeetingRoom = 0;

        for(int i = 0; i < n; i++) {
            if(usedCounts[i] > maxMeetingCount) {
                maxMeetingCount = usedCounts[i];
                maxMeetingRoom = i;
            }
        }

        return maxMeetingRoom;
    }
}
