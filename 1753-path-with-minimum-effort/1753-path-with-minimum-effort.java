import java.util.*;

class Solution {

    // Directions for moving up, right, down, left
    int[] delRow = {-1, 0, 1, 0};
    int[] delCol = {0, 1, 0, -1};

    // Helper function to check if a position is within grid bounds
    private boolean isValid(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
    }

    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;     // Number of rows
        int m = heights[0].length;  // Number of columns

        // Matrix to store the minimum maximum effort needed to reach each cell
        int[][] maxDiff = new int[n][m];

        // Initialize maxDiff with Integer.MAX_VALUE (unreachable initially)
        for (int[] row : maxDiff) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        // Min-Heap (Priority Queue) storing {effort, row, col}, sorted by effort
        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        // Start from the top-left cell with 0 effort
        maxDiff[0][0] = 0;
        pq.add(new int[]{0, 0, 0}); // {effort, row, col}

        // Dijkstra's Algorithm
        while (!pq.isEmpty()) {
            // Extract the cell with the minimum effort from the queue
            int[] p = pq.poll();
            int diff = p[0]; // Current effort to reach this cell
            int row = p[1];  // Row index
            int col = p[2];  // Column index

            // If we reached the bottom-right cell, return the effort
            if (row == n - 1 && col == m - 1) {
                return diff;
            }

            // Explore all 4 directions (up, right, down, left)
            for (int i = 0; i < 4; i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];

                // Check if the new cell is within bounds
                if (isValid(newRow, newCol, n, m)) {
                    // Calculate the absolute height difference
                    int currDiff = Math.abs(heights[newRow][newCol] - heights[row][col]);

                    // Find the new maximum effort required if we take this path
                    int newEffort = Math.max(currDiff, diff);

                    // If the new effort is smaller than the recorded one, update and push to queue
                    if (newEffort < maxDiff[newRow][newCol]) {
                        maxDiff[newRow][newCol] = newEffort;
                        pq.add(new int[]{newEffort, newRow, newCol});
                    }
                }
            }
        }

        // Return -1 if no path exists (this should never happen)
        return -1;
    }
}
