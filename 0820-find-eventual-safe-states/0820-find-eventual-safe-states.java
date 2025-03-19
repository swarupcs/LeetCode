import java.util.*;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int V = graph.length;
        
        // Step 1: Create the reversed graph
        List<List<Integer>> adjRev = new ArrayList<>();
        for (int i = 0; i < V; i++) {
            adjRev.add(new ArrayList<>());
        }
        
        // Step 2: Compute in-degrees and reverse edges
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int neighbor : graph[i]) {
                adjRev.get(neighbor).add(i); // Reverse the edge
                inDegree[i]++; // Count in-degree for original graph
            }
        }
        
        // Step 3: Add all nodes with in-degree 0 to the queue
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) {
                q.add(i);
            }
        }
        
        // Step 4: Perform Topological Sort
        List<Integer> safeNodes = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll();
            safeNodes.add(node);
            
            // Reduce in-degree of neighbors
            for (int neighbor : adjRev.get(node)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }
        
        // Step 5: Sort safe nodes before returning
        Collections.sort(safeNodes);
        return safeNodes;
    }
}