package org.example.onlineAssessments;

import java.util.*;

/**
 *
 * ref: https://leetcode.com/discuss/interview-question/4326333/Amazon-OA
 */
public class CreateSpecialTree {

    public int getMinLeavesToRemove(int nodes, int[] arr, int treeFrom[], int treeTo[], int[] treeWeight) {
        Map<Integer, List<int[]>> tree = new HashMap<>();
        for(int i = 0; i < treeFrom.length; i++) {
            int from = treeFrom[i];
            int to = treeTo[i];
            int weight = treeWeight[i];
            if(!tree.containsKey(from)) tree.put(from, new ArrayList<>());

            tree.get(from).add(new int[]{to, weight});
        }

        int[] minDistToLeaf = new int[nodes + 1];

        Stack<Integer> st = new Stack<>();
        st.add(1);
        int visited = 0;
        while(!st.isEmpty()) {
            int currNode = st.pop();
            visited++;
            for(int[] child : tree.getOrDefault(currNode, new ArrayList<>())) {
                int childNode = child[0];
                int weightToChild = child[1];
                minDistToLeaf[childNode] = minDistToLeaf[currNode] + weightToChild;
                if(arr[childNode - 1] >= minDistToLeaf[childNode]) st.add(childNode);
            }
        }

        return nodes - visited;
    }

    public static void main(String[] args) {
        CreateSpecialTree soultion = new CreateSpecialTree();
        int nodes1 = 5;

        int[] arr1 = new int[]{12,2,27,11,1};
        int[] treeFrom1 = new int[]{1,1,3,3};
        int[] treeTo1 = new int[]{2,3,4,5};
        int[] treeWeight1 = new int[]{8,5,2,7};

        int nodes2 = 4;
        int[] arr2 = new int[]{14,66,86,37};
        int[] treeFrom2 = new int[]{3,1,2};
        int[] treeTo2 = new int[]{2,3,4};
        int[] treeWeight2 = new int[]{-9, 93, -57};

//        int minLeavesToRemove = soultion.getMinLeavesToRemove(nodes1, arr1, treeFrom1, treeTo1, treeWeight1);
        int minLeavesToRemove2 = soultion.getMinLeavesToRemove(nodes2, arr2, treeFrom2, treeTo2, treeWeight2);
        System.out.println(minLeavesToRemove2);
    }
}
