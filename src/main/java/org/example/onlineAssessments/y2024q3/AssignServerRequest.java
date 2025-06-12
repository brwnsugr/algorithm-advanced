package org.example.onlineAssessments.y2024q3;

import java.util.HashMap;
import java.util.Map;

public class AssignServerRequest {

    public int[] allocate(int numServers, int[] requests) {
        int[] servers = new int[numServers];
        Map<Integer, Integer> usageMap = new HashMap<>();
        usageMap.put(0, 0);

        int[] ans = new int[requests.length];
        for (int i = 0; i < requests.length; i++) {
            int request = requests[i];
            int currUsage = servers[request];
            int minIndex = usageMap.get(currUsage);
            ans[i] = minIndex;

            servers[minIndex]++;
            usageMap.put(currUsage, usageMap.get(currUsage) + 1);

            if (!usageMap.containsKey(servers[minIndex])) {
                usageMap.put(servers[minIndex], minIndex);
            }
        }

        return ans;
    }

    public static void main(String[] args) {

        AssignServerRequest sol = new AssignServerRequest();
        int[] allocate = sol.allocate(5, new int[]{0,1,2,3});
        System.out.println(allocate);
    }
}
