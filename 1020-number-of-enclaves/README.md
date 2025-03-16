<h2><a href="https://leetcode.com/problems/number-of-enclaves">1020. Number of Enclaves</a></h2><h3>Medium</h3><hr><p>You are given an <code>m x n</code> binary matrix <code>grid</code>, where <code>0</code> represents a sea cell and <code>1</code> represents a land cell.</p>

<p>A <strong>move</strong> consists of walking from one land cell to another adjacent (<strong>4-directionally</strong>) land cell or walking off the boundary of the <code>grid</code>.</p>

<p>Return <em>the number of land cells in</em> <code>grid</code> <em>for which we cannot walk off the boundary of the grid in any number of <strong>moves</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/enclaves1.jpg" style="width: 333px; height: 333px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,0,0],[1,0,1,0],[0,1,1,0],[0,0,0,0]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> There are three 1s that are enclosed by 0s, and one 1 that is not enclosed because its on the boundary.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/enclaves2.jpg" style="width: 333px; height: 333px;" />
<pre>
<strong>Input:</strong> grid = [[0,1,1,0],[0,0,1,0],[0,0,1,0],[0,0,0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> All 1s are either on the boundary or can reach the boundary.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>



Here is the **commented Java code** for the **Number of Enclaves** problem, explaining each step in detail.

---

### **Approach & Intuition**
- **What is an enclave?**  
  - A land cell (`1`) that **cannot** reach the boundary of the grid (top, bottom, left, or right).
  
- **How to solve the problem?**  
  1. **Identify and mark boundary land cells (`1`s).**  
     - If a land cell is on the boundary, it **cannot** be an enclave.
     - Perform **BFS (Breadth-First Search)** from these boundary `1`s to mark all connected land cells as visited.
  
  2. **Count unvisited land cells (`1`s).**  
     - The remaining unvisited land cells are the enclaves.
     - Count these cells and return the result.

---

### **Code with Comments**
```java
import java.util.*;

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
```

---

### **Algorithm**
1. **Initialize Data Structures**
   - Create a `visited[][]` array to track visited cells.
   - Create a `Queue<int[]>` for **BFS traversal**.

2. **Mark all boundary land cells (`1`s)**
   - Iterate through **first and last row** and **first and last column**.
   - If a boundary cell contains `1`, mark it as visited and push it to the queue.

3. **Perform BFS from boundary land cells**
   - Process all boundary `1`s.
   - Traverse in **4 possible directions** (Up, Right, Down, Left).
   - Mark all reachable land cells as visited.

4. **Count remaining unvisited land cells**
   - Iterate through the grid.
   - If a land cell (`1`) is **not visited**, count it as an **enclave**.

5. **Return the total enclave count**.

---

### **Dry Run Example**
#### **Input Grid**
```
0 0 0 0 0
0 1 1 1 0
0 1 0 1 0
0 1 1 1 0
0 0 0 0 0
```
#### **Step-by-Step Execution**
1. **Mark boundary land cells (`1`s)**
   - There are **no** `1`s on the boundary, so no cells are added to the queue.

2. **BFS Traversal**
   - Since the queue is empty, no BFS occurs.

3. **Count remaining enclaves**
   - All `1`s inside the grid remain unvisited.
   - Count = `6`.

#### **Output**
```java
6
```

---

### **Time & Space Complexity**
- **Time Complexity:** **O(N * M)**  
  - We visit each cell at most once.
  - BFS traversal runs in **O(K)** (where `K` is the number of boundary land cells).
  - Overall: **O(N * M)**.

- **Space Complexity:** **O(N * M)**  
  - **Visited array** takes **O(N * M)** space.
  - **Queue** in the worst case takes **O(N * M)** space.

---

### **Summary**
✅ **Used BFS** to mark all land cells connected to the boundary.  
✅ **Counted remaining land cells** that were not visited.  
✅ **Ensured enclaves are only land cells that can't reach the boundary.** 🚀