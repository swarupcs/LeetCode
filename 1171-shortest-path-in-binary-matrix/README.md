<h2><a href="https://leetcode.com/problems/shortest-path-in-binary-matrix">1171. Shortest Path in Binary Matrix</a></h2><h3>Medium</h3><hr><p>Given an <code>n x n</code> binary matrix <code>grid</code>, return <em>the length of the shortest <strong>clear path</strong> in the matrix</em>. If there is no clear path, return <code>-1</code>.</p>

<p>A <strong>clear path</strong> in a binary matrix is a path from the <strong>top-left</strong> cell (i.e., <code>(0, 0)</code>) to the <strong>bottom-right</strong> cell (i.e., <code>(n - 1, n - 1)</code>) such that:</p>

<ul>
	<li>All the visited cells of the path are <code>0</code>.</li>
	<li>All the adjacent cells of the path are <strong>8-directionally</strong> connected (i.e., they are different and they share an edge or a corner).</li>
</ul>

<p>The <strong>length of a clear path</strong> is the number of visited cells of this path.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/example1_1.png" style="width: 500px; height: 234px;" />
<pre>
<strong>Input:</strong> grid = [[0,1],[1,0]]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/02/18/example2_1.png" style="height: 216px; width: 500px;" />
<pre>
<strong>Input:</strong> grid = [[0,0,0],[1,1,0],[1,1,0]]
<strong>Output:</strong> 4
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0,0],[1,1,0],[1,1,0]]
<strong>Output:</strong> -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>grid[i][j] is 0 or 1</code></li>
</ul>



---

### ✅ **Problem Statement (Recap)**

Given an `n x n` binary matrix `grid`, return the length of the shortest **clear path** from the top-left `(0, 0)` to the bottom-right `(n-1, n-1)`, moving in **8 directions**, where:
- `0` means an open cell
- `1` means a blocked cell

If there's **no path**, return `-1`.

---

### 🧠 **Intuition**

Since all moves cost the same (1 step), we can use **BFS** or **Dijkstra's algorithm**. We’ll use Dijkstra’s here for practice, though it shines more when edge costs vary.

We'll treat each cell like a node in a graph, and try to find the **shortest distance** from the source `(0, 0)` to the target `(n-1, n-1)` using a **min-heap**.

---

### 🧮 **Algorithm (Step-by-step)**

1. If starting or ending cell is blocked (`grid[0][0] == 1 || grid[n-1][n-1] == 1`), return `-1`.
2. Create a `distance` matrix of size `n x n`, initialized to `∞`, except `dist[0][0] = 1`.
3. Use a **min-heap** (PriorityQueue) to process the next shortest cell.
4. Use **8-directional movement**.
5. For each neighbor:
   - If the cell is inside bounds and not blocked:
     - If the new distance is shorter than the previous, update it and push to heap.
6. If you reach the target cell, return the distance.
7. If the heap is exhausted and target is not reached, return `-1`.

---

### ✅ **Code with line-by-line Explanation**

```java
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
```

---

### 🔁 **Example and Dry Run**

#### Input:
```java
grid = [
    [0, 0, 0],
    [1, 1, 0],
    [1, 1, 0]
]
```

#### Dry Run:

| Step | Cell      | Distance | Action                  |
|------|-----------|----------|--------------------------|
| 1    | (0,0)     | 1        | Start cell               |
| 2    | (0,1)     | 2        | Move right               |
| 3    | (1,2)     | 3        | Move diagonal            |
| 4    | (0,2)     | 3        | Move right from (0,1)    |
| 5    | (2,2)     | 4        | Reached destination ✅    |

**Output:** `4`

---

### ✅ Summary:
- **Time Complexity:** `O(n^2 * log n)` because of the priority queue.
- **Space Complexity:** `O(n^2)` for the `dist` matrix and queue.

