package org.example.onlineAssessments.y2024q3;

import java.util.Arrays;

/**
 * ref: https://leetcode.com/discuss/interview-question/5773642/Amazon-OA-questions
 *
 * # Ensure the server quality by:
 *
 * # • Each of the packets must be sent via a Single Channel.
 * # • Each of the channels must transfer at least one packet.
 *
 * # The quality of the transfer for a channel is defined by the median of the sizes of all the data packets sent through that channel.
 *
 * # Note: The median of an array is the middle element if the array is sorted in non-decreasing order. If the number of elements in the array is even, the median is the average of the two middle elements.
 *
 * # Find the maximum possible sum of the qualities of all channels. If the answer is a floating-point value, round it to the next higher integer.
 *
 * # Example
 * # packets = [1, 2, 3, 4, 5]
 * # channels = 2
 * # At least one packet has to go through each of the 2 channels. One maximal solution is to transfer packets {1, 2, 3, 4} through channel
 * # The quality of transfer for channel 1 is (2 + 3)/2 = 2.5 and that of channel 2 is 5. Their sum is 2.5 + 5 = 7.5 which rounds up to 8.
 *
 * # packets = [2, 2, 1, 5, 3]
 * # channels = 2
 * # output = 7
 *
 * # packets = [89, 48, 14]
 * # channels = 3
 * # output = 151
 */
public class FindMaxQuality {

    public int getMaxQuality(int[] packets, int channels) {
        if(packets.length < channels) return getSum(packets);
        Arrays.sort(packets);
        int answer = 0;
        int oneFillChannel = channels - 1;
        for(int i = packets.length - 1; i >= 0; i--) {
            answer += packets[i];
            oneFillChannel--;
            if(oneFillChannel == 0) break;
        }


        int remainingPackets = packets.length - channels + 1;
        if(remainingPackets == 1) {
            answer += packets[0];
        }
        else if(remainingPackets % 2 == 0) {
            int sum = (int) Math.ceil((packets[remainingPackets/2] + packets[(remainingPackets/2) - 1])/2.0);
            answer += sum;
        }
        else {
            answer += packets[remainingPackets/2];
        }
        return answer;
    }

    private int getSum(int[] packets) {
        int packetSum = 0;
        for(int packet : packets) packetSum += packet;
        return packetSum;
    }

    public static void main(String[] args) {
        int[] p1 = {1, 2, 3, 4, 5};
        int c1 = 2;

        int[] p2 = {2, 2, 1, 5, 3};
        int c2 = 2;

        int[] p3 = {89, 48, 14};
        int c3 = 3;
        FindMaxQuality fmq = new FindMaxQuality();

        int maxQuality = fmq.getMaxQuality(p3, c3);

        System.out.println(maxQuality);

    }
}


