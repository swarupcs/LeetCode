<h2><a href="https://leetcode.com/problems/surrounded-regions">130. Surrounded Regions</a></h2><h3>Medium</h3><hr><p>You are given an <code>m x n</code> matrix <code>board</code> containing <strong>letters</strong> <code>&#39;X&#39;</code> and <code>&#39;O&#39;</code>, <strong>capture regions</strong> that are <strong>surrounded</strong>:</p>

<ul>
	<li><strong>Connect</strong>: A cell is connected to adjacent cells horizontally or vertically.</li>
	<li><strong>Region</strong>: To form a region <strong>connect every</strong> <code>&#39;O&#39;</code> cell.</li>
	<li><strong>Surround</strong>: The region is surrounded with <code>&#39;X&#39;</code> cells if you can <strong>connect the region </strong>with <code>&#39;X&#39;</code> cells and none of the region cells are on the edge of the <code>board</code>.</li>
</ul>

<p>To capture a <strong>surrounded region</strong>, replace all <code>&#39;O&#39;</code>s with <code>&#39;X&#39;</code>s <strong>in-place</strong> within the original board. You do not need to return anything.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">board = [[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;O&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;X&quot;,&quot;X&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;X&quot;,&quot;X&quot;,&quot;X&quot;],[&quot;X&quot;,&quot;O&quot;,&quot;X&quot;,&quot;X&quot;]]</span></p>

<p><strong>Explanation:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/19/xogrid.jpg" style="width: 367px; height: 158px;" />
<p>In the above diagram, the bottom region is not captured because it is on the edge of the board and cannot be surrounded.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">board = [[&quot;X&quot;]]</span></p>

<p><strong>Output:</strong> <span class="example-io">[[&quot;X&quot;]]</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n == board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 200</code></li>
	<li><code>board[i][j]</code> is <code>&#39;X&#39;</code> or <code>&#39;O&#39;</code>.</li>
</ul>


## **Approach & Intuition**
The problem requires us to **capture** all regions of 'O's that are **completely surrounded** by 'X's. The key observation is:
- Any **'O' connected to the boundary** (top row, bottom row, leftmost column, rightmost column) **cannot be captured**.
- Any **'O' not connected to the boundary** can be **flipped to 'X'**.

Thus, the solution consists of the following steps:
1. **Identify all 'O's connected to the boundary** using **DFS (Depth First Search)**.
2. **Mark all these boundary-connected 'O's as safe (visited).**
3. **Convert all remaining unmarked 'O's into 'X'.**

---

## **Algorithm**
1. **Mark all boundary 'O's and their connected 'O's using DFS.**
   - Iterate over **first and last row** and perform DFS for any 'O' found.
   - Iterate over **first and last column** and perform DFS for any 'O' found.
   - Mark all visited 'O's in a **visited array (`vis[][]`)**.

2. **Convert all unvisited 'O's into 'X'.**
   - Traverse the entire board.
   - If an 'O' is **not visited**, convert it into 'X'.

3. **Return the modified board.**

---

## **Code with Comments (Fixed Bugs and Readability)**
```java
import java.util.*;

class Solution {
    // Direction arrays for moving Up, Right, Down, Left
    private int[] delRow = {-1, 0, 1, 0};
    private int[] delCol = {0, 1, 0, -1};

    // Function to check if a cell is within matrix boundaries
    private boolean isValid(int i, int j, int n, int m) {
        return i >= 0 && i < n && j >= 0 && j < m;
    }

    // DFS function to mark 'O' cells connected to the boundary
    private void dfs(int row, int col, boolean[][] vis, char[][] board, int n, int m) {
        vis[row][col] = true; // Mark current cell as visited

        // Explore 4 possible directions
        for (int i = 0; i < 4; i++) {
            int nRow = row + delRow[i]; // New row position
            int nCol = col + delCol[i]; // New column position

            // If the new cell is within bounds, contains 'O', and is unvisited, continue DFS
            if (isValid(nRow, nCol, n, m) && board[nRow][nCol] == 'O' && !vis[nRow][nCol]) {
                dfs(nRow, nCol, vis, board, n, m);
            }
        }
    }

    public void solve(char[][] board) {
        int n = board.length;
        int m = board[0].length;

        boolean[][] vis = new boolean[n][m]; // Visited array

        // Step 1: Mark 'O's on first and last row
        for (int j = 0; j < m; j++) {
            if (!vis[0][j] && board[0][j] == 'O') {
                dfs(0, j, vis, board, n, m);
            }
            if (!vis[n - 1][j] && board[n - 1][j] == 'O') {
                dfs(n - 1, j, vis, board, n, m);
            }
        }

        // Step 2: Mark 'O's on first and last column
        for (int i = 0; i < n; i++) {
            if (!vis[i][0] && board[i][0] == 'O') {
                dfs(i, 0, vis, board, n, m);
            }
            if (!vis[i][m - 1] && board[i][m - 1] == 'O') {
                dfs(i, m - 1, vis, board, n, m);
            }
        }

        // Step 3: Convert unvisited 'O's into 'X'
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                if (board[i][j] == 'O' && !vis[i][j]) {
                    board[i][j] = 'X';
                }
            }
        }
    }
}
```

---

## **Line-by-Line Explanation**
### **Step 1: Define Directions for DFS**
```java
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

```
- If an 'O' is **not marked safe**, it means it's completely **surrounded by 'X'**, so we replace it with 'X'.

---

## **Example & Dry Run**
### **Input**
```java
board = {
    {'X', 'X', 'X', 'X'},
    {'X', 'O', 'O', 'X'},
    {'X', 'X', 'O', 'X'},
    {'X', 'O', 'X', 'X'}
}
```

### **Output**
```java
board = {
    {'X', 'X', 'X', 'X'},
    {'X', 'X', 'X', 'X'},
    {'X', 'X', 'X', 'X'},
    {'X', 'O', 'X', 'X'}
}
```
- The **'O' at (1,1) and (1,2) are surrounded**, so they become 'X'.
- The **'O' at (3,1) remains unchanged** as it is connected to the boundary.

---

## **Complexity Analysis**
| Step | Time Complexity |
|------|---------------|
| DFS Traversal | \(O(n \times m)\) |
| Traversing the matrix | \(O(n \times m)\) |
| **Total Complexity** | \(O(n \times m)\) |


