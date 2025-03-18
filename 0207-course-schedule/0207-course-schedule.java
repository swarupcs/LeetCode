import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Create adjacency list representation of the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Compute in-degree (number of prerequisites) for each course
        int[] inDegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            adj.get(prerequisiteCourse).add(course); // Directed edge from prerequisiteCourse -> course
            inDegree[course]++; // Increase in-degree of the course
        }

        // Step 3: Add all courses with zero in-degree to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 4: Process courses using BFS (Topological Sorting)
        int processedCourses = 0;
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            processedCourses++; // Mark course as processed

            // Reduce in-degree of dependent courses
            for (int neighbor : adj.get(currentCourse)) {
                inDegree[neighbor]--; // Reduce in-degree

                // If in-degree becomes zero, add to queue
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Step 5: Check if all courses were processed
        return processedCourses == numCourses;
    }


}
