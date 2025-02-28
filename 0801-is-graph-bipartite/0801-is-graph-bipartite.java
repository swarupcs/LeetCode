class Solution {
    public boolean isBipartite(int[][] graph) {
        int[] partition = new int[graph.length];
        Arrays.fill(partition, -1); // Initialize all nodes as unvisited (-1)
        
        // Check each component (important for disconnected graphs)
        for (int i = 0; i < graph.length; i++) {
            if (partition[i] == -1) { // If not visited
                if (!dfs(graph, partition, i, 0)) {
                    return false; // Graph is not bipartite
                }
            }
        }
        return true; // No conflicts found
    }

    private boolean dfs(int[][] graph, int[] partition, int node, int set) {
        partition[node] = set; // Assign current node to a set
        
        for (int neighbor : graph[node]) {
            if (partition[neighbor] == -1) { // If not visited
                if (!dfs(graph, partition, neighbor, 1 - set)) {
                    return false; // Conflict detected
                }
            } else if (partition[neighbor] == set) { // If neighbor is in the same set
                return false;
            }
        }
        return true;
    }
}
