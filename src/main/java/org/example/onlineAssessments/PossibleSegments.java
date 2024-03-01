package org.example.onlineAssessments;

import java.util.PriorityQueue;

public class PossibleSegments {

    public int countPossibleSegments(int[] weights, int k){
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> a[0] - b[0]);
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a,b) -> b[0] - a[0]);

        int ans = 0;
        int l = 0;
        for(int i = 0; i < weights.length; i++) {
            minHeap.add(new int[]{weights[i], i});
            maxHeap.add(new int[]{weights[i], i});
            while(maxHeap.peek()[0] - minHeap.peek()[0] > k) {
                l = Math.min(maxHeap.peek()[1], minHeap.peek()[1]) + 1;
                while(minHeap.peek()[1] < l) minHeap.remove();
                while(maxHeap.peek()[1] < l) maxHeap.remove();
            }
            ans += (i - l + 1);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] test1 = new int[]{1,3,6};
        PossibleSegments possibleSegments = new PossibleSegments();
        int i = possibleSegments.countPossibleSegments(test1, 3);
        System.out.println(i);
    }
}
