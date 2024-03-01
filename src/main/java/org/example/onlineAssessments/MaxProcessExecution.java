package org.example.onlineAssessments;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.PriorityQueue;

public class MaxProcessExecution {

    public int[] getPrioritiesAfterExecution(int[] priorities, int n) {


        // 1. find max priority pair
        List<int[]> res = new ArrayList<>();

        PriorityQueue<int[]> pq = new PriorityQueue<>((a, b) -> b[1] - a[1]);

        for(int i = 0; i < n; i++) {
            pq.add(new int[]{i, priorities[i]});
        }


        int[] prev = pq.poll();

        while(!pq.isEmpty()) {
            int[] curr = pq.poll();
            if(prev[1] == curr[1]) {
                int floorVal = (int) Math.floor( curr[1]/2);
                pq.add(new int[]{curr[0], floorVal});
                prev = pq.poll();
            }
            else {
                res.add(new int[]{prev[0], prev[1]});
                prev = curr;
            }
        }

        res.add(new int[]{prev[0], prev[1]});
        Collections.sort(res, (a,b) -> a[0] - b[0]);

        int[] ans = new int[res.size()];
        for(int i = 0; i < res.size(); i++) ans[i] = res.get(i)[1];

        return  ans;
    }

    public static void main(String[] args) {
        MaxProcessExecution maxProcessExecution = new MaxProcessExecution();
        int[] pr1 = new int[]{6,6,6,1,2,2};
        int[] pr2 = new int[]{4,4,2,1};
        maxProcessExecution.getPrioritiesAfterExecution(pr2, pr2.length);
    }
}
