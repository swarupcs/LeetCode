class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length; // Number of nodes in the graph

        // Step 1: Create a color array initialized to -1 (uncolored nodes)
        int[] color = new int[n];
        Arrays.fill(color, -1);

        // Step 2: Traverse all nodes (to handle disconnected components)
        for (int i = 0; i < n; i++) {
            // If node is uncolored, start BFS traversal
            if (color[i] == -1) {
                if (!bfsCheck(graph, i, color)) {
                    return false; // If any component is not bipartite, return false
                }
            }
        }
        // If all components are bipartite, return true
        return true;
    }

    private boolean bfsCheck(int[][] graph, int start, int[] color) {
        Queue<Integer> q = new LinkedList<>();

        // Step 3: Start BFS traversal from the given node
        q.add(start);
        color[start] = 0; // Assign the first color (0) to the start node

        // Step 4: BFS traversal
        while (!q.isEmpty()) {
            int node = q.poll(); // Remove the front node from the queue

            // Step 5: Traverse all adjacent nodes of 'node'
            for (int neighbor : graph[node]) {

                // Step 6: If neighbor is not colored, color it with opposite color
                if (color[neighbor] == -1) {
                    color[neighbor] = 1 - color[node]; // Assign opposite color (flip color)
                    q.add(neighbor); // Add neighbor to queue for further BFS traversal
                } 
                // Step 7: If neighbor has the same color as current node, graph is not bipartite
                else if (color[neighbor] == color[node]) {
                    return false; // Conflict in coloring, return false
                }
            }
        }
        // Step 8: If BFS completes without conflicts, the graph is bipartite
        return true;
    }
}
