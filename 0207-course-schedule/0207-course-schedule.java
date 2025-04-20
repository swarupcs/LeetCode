import java.util.*;

class Solution {

    // Helper function to perform Topological Sort using Kahn's Algorithm
    private int[] topoSort(int N, ArrayList<Integer>[] adj) {

        // Step 1: Create inDegree array to store count of incoming edges for each node
        int[] inDegree = new int[N];

        // Step 2: Calculate in-degree for each node
        for (int i = 0; i < N; i++) {
            for (int neighbor : adj[i]) {
                inDegree[neighbor]++;  // Increment in-degree of the neighbor
            }
        }

        // Step 3: Add all nodes with in-degree 0 to the queue (no prerequisites)
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i); // Course with no prerequisites
            }
        }

        // Step 4: Prepare a result array to store topological order
        int[] result = new int[N];
        int index = 0; // Index for result array

        // Step 5: Process nodes with in-degree 0
        while (!queue.isEmpty()) {
            int node = queue.poll();  // Remove the front node from the queue

            result[index++] = node;  // Add node to topological order

            // Decrease the in-degree of neighboring nodes
            for (int neighbor : adj[node]) {
                inDegree[neighbor]--;

                // If in-degree becomes 0, add to queue
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Step 6: Return only the filled portion of result (length may be < N if cycle exists)
        return Arrays.copyOfRange(result, 0, index);
    }

    // Main function to determine if all courses can be finished
    public boolean canFinish(int numCourses, int[][] prerequisites) {

        // Step 1: Build adjacency list for directed graph
        ArrayList<Integer>[] adj = new ArrayList[numCourses];

        // Initialize list for each course
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        // Step 2: Fill adjacency list
        // Edge: prerequisite -> course
        for (int[] pair : prerequisites) {
            int course = pair[0];
            int prerequisite = pair[1];

            adj[prerequisite].add(course);
        }

        // Step 3: Perform Topological Sort
        int[] topoOrder = topoSort(numCourses, adj);

        // Step 4: If the topo order contains all courses, return true (no cycle)
        return topoOrder.length == numCourses;
    }
}
