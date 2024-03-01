package org.example.onlineAssessments;

import java.util.Arrays;

/**
 * Amazon Web Services (AWS) is a cloud computing platform with multiple servers. One of the servers is assigned to serve customer requests. There are n customer requests placed sequentially in a queue, where the ith request has a maximum waiting time denoted by wait[i]. That is, if the ith request is not served within waiti] seconds, then the request expires and it is removed from the queue. The server processes the request following the First In First Out (FIFO) principle. The 1st request is processed first, and the nih request is served last. At each second, the first request in the queue is processed. At the next second, the processed request and any expired requests are removed from the queue.Given the maximum waiting time of each request denoted by the array wait, find the number of requests present in the queue at every second until it is empty.
 * Note:If a request is served at some time instant t, it will be counted for that instant and is removed at the next instant.
 * The first request is processed at time = 0. A request expires without being processed when time = wait[i] must be processed while time<wait[i]
 *
 * The number of requests is n = 4, and their
// * maximum wait times are wait = [2, 2, 3, 1].
 *
 * time = 0 seconds, the 1st request is ferved. The
 * number of requests in the queue is 4. queue = [1, 2, 3, 4].
 * ﻿﻿time = 1 second, request 1 is removed because it is
 * processed, request 4 (wait[3] = 1) is removed
 * because time = wait[3] = 1 which exceeds its
 * maximum waiting time. Also, request 2 is served.
 * The number of requests in the queue at time =
 * 1 seconds is 2. queue = [2, 3].
 * ﻿﻿time = 2 seconds, request 2 is removed because it is processed, request 3 is served. The number of
 * requests in the queue is 1. queue = [3].
 * ﻿﻿time = 3 seconds, request 3 is removed because it is processed. The number of requests in the queue is
 * 0. queue = [emptyl.
 * The answer is [4, 2, 1, 0].
 *
 * ref: https://leetcode.com/discuss/interview-question/4692901/Amazon-or-OA-or-2024
 */
public class RequestInQueue {

    public int[] findNumberOfRequest(int[] wait) {
        int n = wait.length;
        int maxWait = Arrays.stream(wait).max().getAsInt();

        int[] expTimes = new int[maxWait + 2];
        int[] reqTime = new int[maxWait+2];

        for(int time : wait) {
            expTimes[time]++;
        }

        int remainReqs = n;
        int i = 0;

        while(remainReqs > 0) {
            remainReqs -= expTimes[i];
            i++;
            reqTime[i] = remainReqs;
        }

        return Arrays.copyOfRange(reqTime, 0, i);
    }

    public static int[] requestsQueueLinear(int[] wait) {
        int N = wait.length;
        int[] removed = new int[N+1];
        int[] answer = new int[N+1];

        // First pass: Count the requests that will be removed at each second
        for (int i = 0; i < N; i++) {
            int time = Math.min(wait[i], i+1);
            removed[time]++;
        }

        // Total requests in the beginning
        int totalRequests = N;

        // Calculate the remaining requests at each second
        for (int i = 1; i <= N; i++) {
            totalRequests -= removed[i-1];
            answer[i] = totalRequests;
        }

        return Arrays.copyOfRange(answer, 1, N + 1);
    }

    public static void main(String[] args) {
        RequestInQueue requestInQueue = new RequestInQueue();
        int[] numberOfRequest = requestInQueue.findNumberOfRequest(new int[]{2, 2, 3, 1});
        int[] numberOfRequest1 = requestInQueue.requestsQueueLinear(new int[]{2, 2, 3, 1});
        int[] numberOfRequest2 = requestInQueue.requestsQueueLinear(new int[]{1,1,1,1});
        int[] numberOfRequest3 = requestInQueue.requestsQueueLinear(new int[]{10,10,10,2});
        int[] numberOfRequest4 = requestInQueue.requestsQueueLinear(new int[]{0,0,0,0});
        int[] numberOfRequest5 = requestInQueue.requestsQueueLinear(new int[]{10,0,6,3});
        System.out.println("dd");
    }
}
