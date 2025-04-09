import java.util.*;

class Solution {
    private int timer = 1;  // Global timer to track discovery times

    /* 
       Helper function to perform DFS and detect bridges 
       - node: Current node being visited
       - parent: Parent of the current node
       - vis: Visited array
       - adj: Adjacency list representation of the graph
       - tin: Stores the discovery time of each node
       - low: Stores the lowest discovery time reachable from the node
       - bridges: Stores the critical connections (bridges)
    */
    private void dfs(int node, int parent, int[] vis, List<Integer>[] adj, 
                     int[] tin, int[] low, List<List<Integer>> bridges) {
        
        // Step 1: Mark the node as visited
        vis[node] = 1;  
        
        // Step 2: Set discovery time and low time for this node
        tin[node] = low[node] = timer++;  

        // Step 3: Traverse all adjacent nodes
        for (int neighbor : adj[node]) {

            // Skip the parent node to avoid revisiting the edge leading to it
            if (neighbor == parent) continue;  

            // If the neighbor is not visited, perform DFS
            if (vis[neighbor] == 0) {
                dfs(neighbor, node, vis, adj, tin, low, bridges);

                // Step 4: Update the lowest reachable discovery time
                low[node] = Math.min(low[node], low[neighbor]);

                // Step 5: Check if the edge (node, neighbor) is a bridge
                if (low[neighbor] > tin[node]) {
                    bridges.add(Arrays.asList(node, neighbor));
                }
            } 
            // If the neighbor is already visited and it's not the parent
            // It means it's a back edge (cycle), so update the low value
            else {
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }
    }

    /* 
       Function to find all bridges in the given undirected graph 
       - n: Number of vertices in the graph
       - connections: List of edges representing the graph
       Returns a list of all bridges (critical connections)
    */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Step 1: Create adjacency list representation of the graph
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Step 2: Populate adjacency list with edges
        for (List<Integer> edge : connections) {
            int u = edge.get(0), v = edge.get(1);
            adj[u].add(v);
            adj[v].add(u);
        }

        // Step 3: Initialize arrays to track visited status, discovery time, and lowest time
        int[] vis = new int[n]; // Visited array
        int[] tin = new int[n]; // Discovery time array
        int[] low = new int[n]; // Lowest reachable time array
        List<List<Integer>> bridges = new ArrayList<>(); // List to store bridges

        // Step 4: Start DFS traversal from node 0 (assuming the graph is connected)
        dfs(0, -1, vis, adj, tin, low, bridges);

        // Step 5: Return the list of critical bridges
        return bridges;
    }
}
