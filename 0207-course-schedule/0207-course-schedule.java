class Solution {

    // Main function to check if it's possible to finish all courses
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Build the adjacency list from the prerequisites
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Compute in-degree of each node
        int[] inDegree = new int[numCourses];
        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            adj.get(prerequisiteCourse).add(course);   // Edge: prerequisite → course
            inDegree[course]++;                         // Count this prerequisite
        }

        // Step 3: Use BFS-based topological sort to detect if a cycle exists
        return topologicalSortBFS(numCourses, adj, inDegree);
    }

    // BFS-based topological sort using Kahn's Algorithm
    private boolean topologicalSortBFS(int numCourses, List<List<Integer>> adj, int[] inDegree) {
        // Queue to store all nodes with in-degree 0
        Queue<Integer> queue = new LinkedList<>();

        // Step 1: Enqueue all courses with no prerequisites
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 2: Track how many courses we can process
        int processedCourses = 0;

        // Step 3: Process courses in topological order
        while (!queue.isEmpty()) {
            int current = queue.poll();
            processedCourses++; // This course is now "completed"

            // Visit all neighbors (dependent courses)
            for (int neighbor : adj.get(current)) {
                inDegree[neighbor]--; // Remove one prerequisite

                // If in-degree becomes 0, it's ready to be taken
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Step 4: If we processed all courses, return true
        return processedCourses == numCourses;
    }

}
