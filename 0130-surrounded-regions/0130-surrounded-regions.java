import java.util.*;

class Solution {
    // Direction arrays for moving Up, Right, Down, Left
    private int[] delRow = {-1, 0, 1, 0}; // Represents row movement (Up, Right, Down, Left)
    private int[] delCol = {0, 1, 0, -1}; // Represents column movement (Up, Right, Down, Left)

    // Function to check if a cell is within matrix boundaries
    private boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m; // Return true if (i, j) is inside the board
    }

    // DFS function to mark 'O' cells connected to the boundary
    private void dfs(int row, int col, boolean[][] vis, char[][] board, int n, int m) {
        vis[row][col] = true; // Mark current cell as visited

        // Explore all 4 possible directions (Up, Right, Down, Left)
        for (int i = 0; i < 4; i++) {
            int nRow = row + delRow[i]; // Compute new row index
            int nCol = col + delCol[i]; // Compute new column index

            // If the new cell is within bounds, contains 'O', and is unvisited, continue DFS
            if (isValid(nRow, nCol, n, m) && board[nRow][nCol] == 'O' && !vis[nRow][nCol]) {
                dfs(nRow, nCol, vis, board, n, m); // Recursive DFS call
            }
        }
    }

    public void solve(char[][] board) {
        int n = board.length;    // Number of rows
        int m = board[0].length; // Number of columns

        boolean[][] vis = new boolean[n][m]; // Visited array to track safe 'O' cells

        // Step 1: Traverse first and last row to mark boundary-connected 'O's
        for (int j = 0; j < m; j++) {
            if (!vis[0][j] && board[0][j] == 'O') {
                dfs(0, j, vis, board, n, m); // Start DFS from first row
            }
            if (!vis[n - 1][j] && board[n - 1][j] == 'O') {
                dfs(n - 1, j, vis, board, n, m); // Start DFS from last row
            }
        }

        // Step 2: Traverse first and last column to mark boundary-connected 'O's
        for (int i = 0; i < n; i++) {
            if (!vis[i][0] && board[i][0] == 'O') {
                dfs(i, 0, vis, board, n, m); // Start DFS from first column
            }
            if (!vis[i][m - 1] && board[i][m - 1] == 'O') {
                dfs(i, m - 1, vis, board, n, m); // Start DFS from last column
            }
        }

        // Step 3: Convert all unvisited 'O's into 'X'
        for (int i = 0; i < n; i++) { // Iterate over rows
            for (int j = 0; j < m; j++) { // Iterate over columns
                if (board[i][j] == 'O' && !vis[i][j]) { // If 'O' is not marked safe
                    board[i][j] = 'X'; // Convert to 'X' as it's completely surrounded
                }
            }
        }
    }
}
