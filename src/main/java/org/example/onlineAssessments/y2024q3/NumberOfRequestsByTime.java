package org.example.onlineAssessments.y2024q3;

import java.util.*;

public class NumberOfRequestsByTime {

    public List<Integer> getRemainingRequestsByTimeSeries(int requests, int[] wait) {
        PriorityQueue<Integer> pq = new PriorityQueue<>();

        for(int expTime : wait) {
            pq.add(expTime);
        }

        int timer = 0;

        List<Integer> res = new ArrayList<>();
        while(!pq.isEmpty()) {
            res.add(pq.size());
            pq.remove(wait[timer]);
            timer++;
            while(!pq.isEmpty() && pq.peek() <= timer) {
                pq.poll();
            }
        }

        while(timer < requests) {
            res.add(0);
            timer++;
        }

        return res;
    }

    public static String sortPermutation(List<Integer> p, List<Integer> moves) {
        int n = p.size();
        boolean[] visited = new boolean[n];
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                int cycle_length = 0;
                int x = i;
                while (!visited[x]) {
                    visited[x] = true;
                    x = p.get(x) - 1;
                    cycle_length++;
                }
                if (cycle_length > 1) {
                    count += (cycle_length - 1);
                }
            }
        }

        int[] res = new int[moves.size()];
        for (int idx = 0; idx < moves.size(); idx++) {
            int move = moves.get(idx);
            if (move >= count && (move - count) % 2 == 0) {
                res[idx] = 1;
            }
        }

        StringBuilder rs = new StringBuilder();
        for (int r : res) {
            rs.append(r);
        }

        return rs.toString();
    }

    public static void main(String[] args) {
        NumberOfRequestsByTime numberTime = new NumberOfRequestsByTime();
        List<Integer> p = Arrays.asList(3,1,2);
        List<Integer> moves = Arrays.asList(2,4);
        String s = NumberOfRequestsByTime.sortPermutation(p, moves);
        List<Integer> remainingRequestsByTimeSeries = numberTime.getRemainingRequestsByTimeSeries(4, new int[]{2, 2, 3, 1});
    }
}
