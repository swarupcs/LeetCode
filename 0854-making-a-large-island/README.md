<h2><a href="https://leetcode.com/problems/making-a-large-island">854. Making A Large Island</a></h2><h3>Hard</h3><hr><p>You are given an <code>n x n</code> binary matrix <code>grid</code>. You are allowed to change <strong>at most one</strong> <code>0</code> to be <code>1</code>.</p>

<p>Return <em>the size of the largest <strong>island</strong> in</em> <code>grid</code> <em>after applying this operation</em>.</p>

<p>An <strong>island</strong> is a 4-directionally connected group of <code>1</code>s.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,0],[0,1]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> Change one 0 to 1 and connect two 1s, then we get an island with area = 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1],[1,0]]
<strong>Output:</strong> 4
<strong>Explanation: </strong>Change the 0 to 1 and make the island bigger, only one island with area = 4.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> grid = [[1,1],[1,1]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> Can&#39;t change any 0 to 1, only one island with area = 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == grid.length</code></li>
	<li><code>n == grid[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 500</code></li>
	<li><code>grid[i][j]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>


Here is a detailed explanation of the **"Making a Large Island"** problem, covering the **intuition, approach, algorithm, dry run, and a well-commented Java implementation**.  

---

## **🔹 Intuition**
1. **Understanding the problem**  
   - Given an `n x n` binary grid where `1` represents land and `0` represents water.
   - We are allowed to change at most **one `0` to `1`**.
   - We need to find the **largest island** possible after making the change.
   - **Two `1`s are connected** if they share a side (not diagonally).

2. **Using Disjoint Set Union (DSU)**
   - Each land cell is treated as a **node**.
   - We **merge connected land cells** into **islands** using DSU (also called Union-Find).
   - The **parent array** keeps track of which nodes belong to the same component.
   - The **size array** stores the number of nodes in each connected component.

3. **Handling `0`s (Water cells)**
   - For each `0`, check its **adjacent `1`s**.
   - Find the unique **island parents** of these `1`s.
   - Sum up their sizes + 1 (because we are flipping the `0` to `1`).
   - Keep track of the **maximum island size**.

---

## **🔹 Approach**
1. **Use DSU (Disjoint Set Union) to find all existing islands**  
   - Traverse the grid and apply **Union-Find** on `1`s to **group connected components**.
   - Store the **size of each island**.

2. **Try converting every `0` to `1`**
   - Check **adjacent `1`s** and determine their parent components.
   - Compute the possible new island size.
   - Track the **maximum island size**.

3. **Edge Case: If the grid already contains only `1`s**, return `n * n`.

---

## **🔹 Algorithm**
1. **Create a Disjoint Set (`DisjointSet` class)**
   - Use **path compression** and **union by size** to efficiently find and merge components.

2. **Step 1: Connect all adjacent `1`s using Union-Find**
   - Traverse the grid.
   - If `grid[i][j] == 1`, check its **four neighbors** and perform **union** if they are also `1`.

3. **Step 2: Try converting each `0` to `1` and calculate the new island size**
   - For each `0`, check **adjacent islands**.
   - Sum their sizes and add `1` for the current `0`.
   - Keep track of the **maximum island size**.

4. **Step 3: Return the maximum island size found.**

---

## **🔹 Code Implementation with Line-by-Line Explanation**
```java
import java.util.*;

class DisjointSet {
    int[] parent, size; // Arrays to store parent and size of each set

    // Constructor to initialize the Disjoint Set (Union-Find)
    DisjointSet(int n) {
        parent = new int[n]; // Parent array: stores the parent of each node
        size = new int[n];   // Size array: stores the size of each component
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Initially, each node is its own parent (self-loop)
            size[i] = 1;    // Each node is a component of size 1 initially
        }
    }

    // Find function with path compression to optimize lookup
    int find(int node) {
        if (parent[node] == node) return node; // Base case: if node is its own parent
        return parent[node] = find(parent[node]); // Path compression to speed up future lookups
    }

    // Union by size (merges smaller component into larger one)
    void union(int u, int v) {
        int rootU = find(u); // Find root of node u
        int rootV = find(v); // Find root of node v
        if (rootU != rootV) { // If they belong to different sets, merge them
            if (size[rootU] < size[rootV]) { // Attach smaller tree to larger one
                parent[rootU] = rootV;
                size[rootV] += size[rootU]; // Update size of the new parent
            } else { 
                parent[rootV] = rootU; // Attach rootV to rootU
                size[rootU] += size[rootV]; // Update size of the new parent
            }
        }
    }
}

class Solution {
    // Directions array to explore up, right, down, and left neighbors
    private static final int[] delRow = {-1, 0, 1, 0};
    private static final int[] delCol = {0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        int n = grid.length; // Get the size of the grid (n x n)
        DisjointSet ds = new DisjointSet(n * n); // Create Disjoint Set for all n*n cells

        // Step 1: Connect all adjacent land cells (1s) using DSU
        for (int row = 0; row < n; row++) { // Loop through each row
            for (int col = 0; col < n; col++) { // Loop through each column
                if (grid[row][col] == 1) { // If the current cell is land
                    for (int k = 0; k < 4; k++) { // Check all 4 neighbors
                        int newRow = row + delRow[k]; // Calculate new row index
                        int newCol = col + delCol[k]; // Calculate new column index
                        // Check if the neighbor is within bounds and is also land
                        if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1) {
                            int node = row * n + col; // Convert (row, col) to 1D index
                            int adjNode = newRow * n + newCol; // Convert neighbor to 1D index
                            ds.union(node, adjNode); // Merge the two land components
                        }
                    }
                }
            }
        }

        // Step 2: Try flipping each water cell (0) to land and compute the new island size
        int maxIsland = 0; // Variable to store the maximum island size found
        for (int row = 0; row < n; row++) { // Loop through all rows
            for (int col = 0; col < n; col++) { // Loop through all columns
                if (grid[row][col] == 0) { // If the current cell is water (0)
                    Set<Integer> uniqueIslands = new HashSet<>(); // Set to store unique island roots
                    int totalSize = 1; // Start with size 1 (flipping this 0 to 1)

                    for (int k = 0; k < 4; k++) { // Check all 4 neighbors
                        int newRow = row + delRow[k]; // Calculate neighbor row index
                        int newCol = col + delCol[k]; // Calculate neighbor column index
                        // If neighbor is valid and is land
                        if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1) {
                            int root = ds.find(newRow * n + newCol); // Find root of the neighbor island
                            if (!uniqueIslands.contains(root)) { // If this island has not been counted
                                uniqueIslands.add(root); // Add to unique set
                                totalSize += ds.size[root]; // Add its size to total
                            }
                        }
                    }
                    maxIsland = Math.max(maxIsland, totalSize); // Update max island size
                }
            }
        }

        // Step 3: If there are no water cells, return the largest existing island
        for (int cell = 0; cell < n * n; cell++) { // Check all land cells
            maxIsland = Math.max(maxIsland, ds.size[ds.find(cell)]); // Get max component size
        }

        return maxIsland; // Return the largest island found
    }
}

```

---

## **🔹 Example & Dry Run**
### **Example 1**
#### **Input**  
```java
grid = [[1,0],
        [0,1]]
```
#### **Step 1: DSU Formation**
- `1` at `(0,0)` is isolated.
- `1` at `(1,1)` is isolated.

#### **Step 2: Flipping (0,1) or (1,0)**
- If we flip `(0,1)` or `(1,0)`, it connects both `1`s.
- **Final max island size = 3**.

#### **Output**
```java
3
```

---

### **Example 2**
#### **Input**
```java
grid = [[1,1],
        [1,1]]
```
#### **Step 1: DSU Formation**
- All `1`s are already connected.

#### **Step 2: No flip needed**
- **Largest island remains 4**.

#### **Output**
```java
4
```

---

## **🔹 Time & Space Complexity**
- **Building DSU:** `O(N^2 * α(N))` (`α` is inverse Ackermann function, nearly constant)
- **Checking all `0`s:** `O(N^2)`
- **Total Complexity:** `O(N^2)`

---

## **🔹 Summary**
✔ **DSU helps efficiently merge components**  
✔ **Checking `0`s and merging adjacent islands** increases size  
✔ **Optimized to `O(N^2)`**  

This approach ensures the best **time complexity** while keeping the code structured! 🚀