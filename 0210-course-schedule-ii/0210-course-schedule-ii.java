class Solution {

    // Function to perform topological sort using Kahn's Algorithm (BFS-based)
    private int[] topoSort(int N, List<Integer>[] adj) {
        // Step 1: Create an array to store the in-degree of each node (course)
        int[] inDegree = new int[N];

        // Step 2: Fill the in-degree array by iterating over the adjacency list
        for (int i = 0; i < N; i++) {
            for (int it : adj[i]) {
                inDegree[it]++; // Increase in-degree for the destination node
            }
        }

        // Step 3: Initialize a queue to keep track of nodes with in-degree 0 (no prerequisites)
        Queue<Integer> queue = new LinkedList<>();

        // This array will store the final topological order (course order)
        int[] ans = new int[N];
        int idx = 0; // Index for inserting into the ans array

        // Step 4: Add all courses with in-degree 0 to the queue (can be taken first)
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 5: Process the queue using BFS
        while (!queue.isEmpty()) {
            int node = queue.poll(); // Get the next course with no remaining prerequisites
            ans[idx++] = node;       // Add it to the course order

            // Step 6: For each neighbor of this course, reduce their in-degree by 1
            for (int it : adj[node]) {
                inDegree[it]--;

                // If in-degree becomes 0, it means all prerequisites are satisfied, so add to queue
                if (inDegree[it] == 0) {
                    queue.add(it);
                }
            }
        }

        // Step 7: Return only the valid portion of ans[] (up to idx)
        // If a cycle exists, idx will be less than N
        return Arrays.copyOfRange(ans, 0, idx);
    }

    // Main function to find a valid order to take all courses
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Step 1: Create the adjacency list for the graph
        List<Integer>[] adj = new ArrayList[numCourses];

        // Initialize each list inside the array
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        // Step 2: Populate the graph using the prerequisites
        // Each pair [a, b] means an edge from b to a (b → a)
        for (int[] it : prerequisites) {
            int u = it[0]; // course a
            int v = it[1]; // prerequisite b
            adj[v].add(u); // Add edge from b to a
        }

        // Step 3: Call topoSort to get the course order
        int[] topo = topoSort(numCourses, adj);

        // Step 4: If topo.length < numCourses, a cycle exists, return empty array
        if (topo.length < numCourses) return new int[0];

        // Step 5: Return the valid course ordering
        return topo;
    }
}
