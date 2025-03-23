<h2><a href="https://leetcode.com/problems/path-with-minimum-effort">1753. Path With Minimum Effort</a></h2><h3>Medium</h3><hr><p>You are a hiker preparing for an upcoming hike. You are given <code>heights</code>, a 2D array of size <code>rows x columns</code>, where <code>heights[row][col]</code> represents the height of cell <code>(row, col)</code>. You are situated in the top-left cell, <code>(0, 0)</code>, and you hope to travel to the bottom-right cell, <code>(rows-1, columns-1)</code> (i.e.,&nbsp;<strong>0-indexed</strong>). You can move <strong>up</strong>, <strong>down</strong>, <strong>left</strong>, or <strong>right</strong>, and you wish to find a route that requires the minimum <strong>effort</strong>.</p>

<p>A route&#39;s <strong>effort</strong> is the <strong>maximum absolute difference</strong><strong> </strong>in heights between two consecutive cells of the route.</p>

<p>Return <em>the minimum <strong>effort</strong> required to travel from the top-left cell to the bottom-right cell.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/10/04/ex1.png" style="width: 300px; height: 300px;" /></p>

<pre>
<strong>Input:</strong> heights = [[1,2,2],[3,8,2],[5,3,5]]
<strong>Output:</strong> 2
<strong>Explanation:</strong> The route of [1,3,5,3,5] has a maximum absolute difference of 2 in consecutive cells.
This is better than the route of [1,2,2,2,5], where the maximum absolute difference is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2020/10/04/ex2.png" style="width: 300px; height: 300px;" /></p>

<pre>
<strong>Input:</strong> heights = [[1,2,3],[3,8,4],[5,3,5]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> The route of [1,2,3,4,5] has a maximum absolute difference of 1 in consecutive cells, which is better than route [1,3,5,3,5].
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/04/ex3.png" style="width: 300px; height: 300px;" />
<pre>
<strong>Input:</strong> heights = [[1,2,1,1,1],[1,2,1,2,1],[1,2,1,2,1],[1,2,1,2,1],[1,1,1,2,1]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> This route does not require any effort.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>rows == heights.length</code></li>
	<li><code>columns == heights[i].length</code></li>
	<li><code>1 &lt;= rows, columns &lt;= 100</code></li>
	<li><code>1 &lt;= heights[i][j] &lt;= 10<sup>6</sup></code></li>
</ul>



Here's a detailed breakdown of the problem, approach, intuition, algorithm, code explanation, and a dry run.  

---

## **Approach & Intuition**
The problem requires us to find a path from the top-left cell to the bottom-right cell such that the **maximum absolute difference** in heights between two consecutive cells along the path is minimized.  

This is a shortest-path problem with a weighted graph interpretation:
- Each cell in the grid represents a **node**.
- The absolute height difference between adjacent cells represents the **edge weight**.
- The goal is to reach the bottom-right corner **minimizing the maximum effort required**.

### **Dijkstra’s Algorithm with Min-Heap**
Since we need the path with the **minimum effort**, a **Dijkstra-like approach** is best:
1. Use a **min-heap (priority queue)** to always expand the path with the smallest effort first.
2. Maintain a **2D effort array (`maxDiff[][]`)**, where `maxDiff[row][col]` keeps track of the **minimum maximum effort** needed to reach `(row, col)`.
3. Start at the **top-left corner** with effort `0`.
4. Expand to neighboring cells, updating their effort if a lower maximum effort path is found.
5. Continue until reaching the **bottom-right corner**, then return the effort value.

---

## **Algorithm**
1. **Initialize Variables:**
   - `maxDiff[n][m]`: Stores the minimum maximum effort needed to reach each cell.
   - Fill `maxDiff[][]` with `Integer.MAX_VALUE` (initially unreachable).
   - Priority Queue (`pq`) stores `{effort, row, col}`, sorted by effort.

2. **Push the Starting Point `(0,0)`** into the min-heap with effort `0`.

3. **Dijkstra’s Algorithm:**
   - While the priority queue is not empty:
     - Pop the cell `(row, col)` with the smallest effort.
     - If `(row, col)` is the destination, return its effort.
     - Explore all **4 possible moves** (up, down, left, right).
     - Compute the absolute height difference (`currDiff`).
     - Find the new maximum effort along the path (`newEffort = max(currDiff, prevEffort)`).
     - If `newEffort` is less than `maxDiff[newRow][newCol]`, update it and push to the priority queue.

4. **Return -1** (this case won’t happen since the grid is connected).

---

## **Code with In-Line Comments**
```java
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
```

---

## **Dry Run with Example**
**Input:**
```plaintext
heights = [[1,2,2],
           [3,8,2],
           [5,3,5]]
```

### **Step-by-Step Execution**
1. **Initialization:**
   ```
   maxDiff[][] = 
   [[0, ∞, ∞],
    [∞, ∞, ∞],
    [∞, ∞, ∞]]
   pq = [(0, 0, 0)]  // Start at (0,0) with 0 effort
   ```

2. **Pop (0,0), Explore Neighbors:**
   ```
   maxDiff = 
   [[0, 1, ∞],
    [2, ∞, ∞],
    [∞, ∞, ∞]]
   pq = [(1, 0, 1), (2, 1, 0)]
   ```

3. **Pop (0,1), Explore Neighbors:**
   ```
   maxDiff = 
   [[0, 1, 1],
    [2, 6, ∞],
    [∞, ∞, ∞]]
   pq = [(1, 0, 2), (2, 1, 0), (6, 1, 1)]
   ```

4. **Continue Exploring Until Reaching (2,2)**
   ```
   maxDiff = 
   [[0, 1, 1],
    [2, 6, 2],
    [2, 2, 2]]
   pq = [(2, 2, 2)]  // Destination reached with minimum effort = 2
   ```

**Final Output:** `2`

---

## **Complexity Analysis**
| Step | Complexity |
|------|-----------|
| Initializing `maxDiff[][]` | O(n × m) |
| Dijkstra’s Algorithm | O((n × m) log(n × m)) |
| Total Complexity | **O(n × m log(n × m))** |

This is efficient for large grids.

---

## **Summary**
- Used **Dijkstra’s Algorithm with Min-Heap** to find the path with the **minimum maximum effort**.
- Maintained **min-effort matrix (`maxDiff[][]`)** to track the least effort needed to reach each cell.
- Utilized **PriorityQueue** to always explore the lowest-effort paths first.
- Successfully determined the **optimal hiking route** efficiently.

This approach ensures we always get the **optimal path with the least effort**. 🚀