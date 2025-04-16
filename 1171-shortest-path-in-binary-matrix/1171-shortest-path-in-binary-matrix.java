import java.util.*;

class Solution {
    public int shortestPathBinaryMatrix(int[][] grid) {
        int n = grid.length;

        // Step 1: Check if the start or end cells are blocked
        if (grid[0][0] != 0 || grid[n - 1][n - 1] != 0) {
            return -1; // No valid path if blocked
        }

        // Step 2: Priority Queue for Dijkstra - stores [distance, row, col]
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));
        pq.offer(new int[]{1, 0, 0}); // Initial cell with distance 1

        // Step 3: 8 possible directions (up, down, left, right + 4 diagonals)
        int[][] directions = {
            {-1, -1}, {-1, 0}, {-1, 1},
            {0, -1},           {0, 1},
            {1, -1},  {1, 0},  {1, 1}
        };

        // Step 4: Distance matrix to track shortest path to each cell
        int[][] dist = new int[n][n];
        for (int[] row : dist) Arrays.fill(row, Integer.MAX_VALUE); // Fill with "infinity"
        dist[0][0] = 1; // Starting point has distance 1

        // Step 5: Dijkstra's main loop
        while (!pq.isEmpty()) {
            int[] current = pq.poll(); // Get cell with current shortest distance
            int d = current[0]; // Current distance
            int r = current[1]; // Row
            int c = current[2]; // Column

            // Step 6: If we reached destination, return distance
            if (r == n - 1 && c == n - 1) {
                return d;
            }

            // Step 7: Explore all 8 neighbors
            for (int[] dir : directions) {
                int nr = r + dir[0];
                int nc = c + dir[1];

                // Step 8: Check bounds and if the cell is not blocked
                if (nr >= 0 && nc >= 0 && nr < n && nc < n && grid[nr][nc] == 0) {
                    // Step 9: If found shorter path, update distance and add to heap
                    if (d + 1 < dist[nr][nc]) {
                        dist[nr][nc] = d + 1;
                        pq.offer(new int[]{dist[nr][nc], nr, nc});
                    }
                }
            }
        }

        // Step 10: No path found
        return -1;
    }
}
