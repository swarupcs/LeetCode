class Solution {
    // Direction vectors for movement in 4 directions (Up, Right, Down, Left)
    private int[] delRow = {-1, 0, 1, 0}; 
    private int[] delCol = {0, 1, 0, -1}; 

    // Function to check if a cell is within grid boundaries
    private boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    // BFS function to traverse land cells starting from the boundary
    private void bfs(int[][] grid, Queue<int[]> q, boolean[][] vis) {
        int n = grid.length;
        int m = grid[0].length;

        // Process all cells in the queue (boundary land cells)
        while(!q.isEmpty()) {
            int[] cell = q.poll(); // Get the first element in the queue
            int row = cell[0];
            int col = cell[1];

            // Explore 4 possible directions
            for(int i = 0; i < 4; i++) {
                int nRow = row + delRow[i];
                int nCol = col + delCol[i];

                // If the new cell is valid, unvisited, and is land (1)
                if(isValid(nRow, nCol, n, m) && grid[nRow][nCol] == 1 && !vis[nRow][nCol]) {
                    vis[nRow][nCol] = true; // Mark as visited
                    q.add(new int[]{nRow, nCol}); // Add to queue for further traversal
                }
            }
        }
    }

    // Function to count the number of enclaves
    public int numEnclaves(int[][] grid) {
        int n = grid.length;
        int m = grid[0].length;

        Queue<int[]> q = new LinkedList<>(); // Queue for BFS traversal
        boolean[][] vis = new boolean[n][m]; // Visited array to track visited land cells

        // Step 1: Identify and mark boundary land cells
        for(int i = 0; i < n; i++) { // Iterate through rows
            for(int j = 0; j < m; j++) { // Iterate through columns
                // If it's a boundary land cell (top, bottom, left, right)
                if((i == 0 || i == n - 1 || j == 0 || j == m - 1) && grid[i][j] == 1) {
                    vis[i][j] = true; // Mark as visited
                    q.add(new int[]{i, j}); // Add to queue for BFS traversal
                }
            }
        }

        // Step 2: Perform BFS from boundary land cells to mark reachable land
        bfs(grid, q, vis);

        // Step 3: Count enclaves (unvisited land cells)
        int count = 0;
        for(int i = 0; i < n; i++) { // Iterate through grid
            for(int j = 0; j < m; j++) {
                if(grid[i][j] == 1 && !vis[i][j]) { // If land cell is not visited
                    count++; // Increment enclave count
                }
            }
        }

        return count; // Return total enclave count
    }
}
