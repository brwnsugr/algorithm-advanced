package org.example.onlineAssessments.y2024q3;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

/**
 * ref: https://leetcode.com/discuss/interview-question/5580409/Amazon-OA-Hackerrank-Questions-or-Aug-2024
 *
 */
class Tuple implements Comparable<Tuple> {
    int weight;
    int index;

    Tuple(int index, int weight) {
        this.index = index;
        this.weight = weight;
    }

    @Override
    public int compareTo(Tuple other) {
        if (this.weight == other.weight) {
            return this.index - other.index;
        }
        return this.weight - other.weight;
    }
}
public class ServerTraffic {

    public List<Integer> allocate(int s, int[] requests) {
        List<Integer> result = new ArrayList<>();
        List<Tuple> servs = new ArrayList<>();
        for (int i = 0; i < s; ++i) {
            servs.add(new Tuple(i, 0));
        }
        for (int i = 0; i < requests.length; ++i) {
            // max heap helps to get the element which satisfies the the criteria of two things.
            // if weights are equal then compare indexes
            // otherwise get the element which has lesser weight.
            PriorityQueue<Tuple> pq = new PriorityQueue<>(servs.subList(0, requests[i]));
            // get the top of the element.
            result.add(pq.peek().index);
            // now for that element increase the weight, as it has served the request.
            servs.get(pq.peek().index).weight++;
        }

        return result;
    }

    public static void main(String... args) {

        ServerTraffic st = new ServerTraffic();
        int s = 5;
        int[] req1 = {3,2,3,2,4};
        System.out.println(st.allocate(5, req1));
    }
}



