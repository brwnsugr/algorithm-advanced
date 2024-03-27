package org.example.designImpl;

import java.util.*;

public class CurrencyConversion {
    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        Map<String, Map<String, Double>> graph = buildGraph(equations, values);
        double[] answerList = new double[queries.size()];


        for(int i = 0; i < queries.size(); i++) {
            String dividend = queries.get(i).get(0);
            String divisor = queries.get(i).get(1);
            if(!graph.containsKey(dividend) || !graph.containsKey(divisor)) answerList[i] = -1.0;
            else {
                Set<String> visited = new HashSet<>();
                double[] ans = {-1.0};
                double temp = 1.0;
                dfs(dividend, divisor, graph, visited, ans, temp);
                answerList[i] = ans[0];
            }
        }

        return answerList;
    }

    private void dfs(String curr, String dest, Map<String, Map<String, Double>>graph, Set<String> visited, double[] ans, double temp) {
        if(visited.contains(curr)) return;

        visited.add(curr);
        if(curr.equals(dest)) {
            ans[0] = temp;
            return;
        }

        // iterate next nodes
        for(Map.Entry<String, Double> entry : graph.get(curr).entrySet()) {
            String next = entry.getKey();
            double val = entry.getValue();
            dfs(next, dest, graph, visited, ans, temp * val);
        }
    }

    private Map<String, Map<String, Double>> buildGraph(List<List<String>> equations, double[] values) {
        Map<String, Map<String, Double>> graph = new HashMap<>();
        for(int i = 0; i < equations.size(); i++) {
            String dividend = equations.get(i).get(0);
            String divisor = equations.get(i).get(1);

            double value = values[i];
            graph.putIfAbsent(dividend, new HashMap<>());
            graph.putIfAbsent(divisor, new HashMap<>());

            graph.get(dividend).put(divisor, value);
            graph.get(divisor).put(dividend, 1.0/value);
        }

        return graph;
    }
}
