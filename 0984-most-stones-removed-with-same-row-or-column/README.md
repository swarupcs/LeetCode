<h2><a href="https://leetcode.com/problems/most-stones-removed-with-same-row-or-column">984. Most Stones Removed with Same Row or Column</a></h2><h3>Medium</h3><hr><p>On a 2D plane, we place <code>n</code> stones at some integer coordinate points. Each coordinate point may have at most one stone.</p>

<p>A stone can be removed if it shares either <strong>the same row or the same column</strong> as another stone that has not been removed.</p>

<p>Given an array <code>stones</code> of length <code>n</code> where <code>stones[i] = [x<sub>i</sub>, y<sub>i</sub>]</code> represents the location of the <code>i<sup>th</sup></code> stone, return <em>the largest possible number of stones that can be removed</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> stones = [[0,0],[0,1],[1,0],[1,2],[2,1],[2,2]]
<strong>Output:</strong> 5
<strong>Explanation:</strong> One way to remove 5 stones is as follows:
1. Remove stone [2,2] because it shares the same row as [2,1].
2. Remove stone [2,1] because it shares the same column as [0,1].
3. Remove stone [1,2] because it shares the same row as [1,0].
4. Remove stone [1,0] because it shares the same column as [0,0].
5. Remove stone [0,1] because it shares the same row as [0,0].
Stone [0,0] cannot be removed since it does not share a row/column with another stone still on the plane.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> stones = [[0,0],[0,2],[1,1],[2,0],[2,2]]
<strong>Output:</strong> 3
<strong>Explanation:</strong> One way to make 3 moves is as follows:
1. Remove stone [2,2] because it shares the same row as [2,0].
2. Remove stone [2,0] because it shares the same column as [0,0].
3. Remove stone [0,2] because it shares the same row as [0,0].
Stones [0,0] and [1,1] cannot be removed since they do not share a row/column with another stone still on the plane.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> stones = [[0,0]]
<strong>Output:</strong> 0
<strong>Explanation:</strong> [0,0] is the only stone on the plane, so you cannot remove it.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= stones.length &lt;= 1000</code></li>
	<li><code>0 &lt;= x<sub>i</sub>, y<sub>i</sub> &lt;= 10<sup>4</sup></code></li>
	<li>No two stones are at the same coordinate point.</li>
</ul>


Here is the **detailed explanation**, including **approach, intuition, algorithm, dry run, and line-by-line code comments** for better readability and understanding.  

---

## **Approach**
1. **Understanding the Problem**  
   - We can remove a stone if another stone exists in the same row or column.
   - The goal is to maximize the number of removable stones.

2. **Key Observations**  
   - Stones in the same row or column are **connected components** in a graph.
   - The problem can be solved using **Disjoint Set Union (DSU)** to merge connected stones.
   - The number of stones that can be removed is **total stones - number of connected components**.

3. **How DSU Helps?**  
   - Each stone represents a node.
   - If two stones share the same row or column, we connect them in DSU.
   - After processing all stones, the number of **connected components** is counted.
   - The maximum removable stones = **Total stones - Connected components**.

---

## **Algorithm**
1. **Find the maximum row and column indices** from the input stones.
2. **Create a Disjoint Set (DSU)** that supports path compression and union by size.
3. **Map each stone’s row and column as unique nodes** to avoid conflicts.
4. **Union the stones** in the same row or column.
5. **Find the number of unique components** (connected components).
6. **Compute the answer** as `total stones - number of components`.

---

## **Example & Dry Run**
### **Input**
```plaintext
stones = [[0, 0], [0, 1], [1, 0], [1, 2], [2, 1], [2, 2]]
```
### **Step-by-Step Execution**
1. **Find maxRow and maxCol**  
   - maxRow = `2` (from `[2,1]`)  
   - maxCol = `2` (from `[1,2]`)

2. **Convert (row, col) into unique DSU nodes**  
   - `row` remains the same, `col` is mapped as `col + maxRow + 1`  
   - Example: `(0,1) -> (0, 4)`, `(1,2) -> (1, 5)`

3. **Union stones**  
   - Merge `(0,0) - (0,3)`, `(0,1) - (0,4)`, `(1,0) - (1,3)`, etc.

4. **Find unique components**  
   - Connected components = **2**  
   - Maximum removable stones = `6 - 2 = 4`

### **Output**
```plaintext
5
```

---

## **Code with Detailed Line-by-Line Comments**
```java
// Disjoint Set class for Union-Find operations
class DisjointSet {
    int[] rank, parent, size;

    // Constructor to initialize DSU with `n` elements
    DisjointSet(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];

        // Initialize each node as its own parent (self-loop)
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            size[i] = 1;   // Initially, each set has size 1
        }
    }

    // Find function with path compression optimization
    int findUPar(int node) {
        if (node == parent[node])  // If node is its own parent, return it
            return node;
        return parent[node] = findUPar(parent[node]); // Path compression
    }

    // Union by size to merge smaller tree into larger tree
    void unionBySize(int u, int v) {
        int ulp_u = findUPar(u); // Find ultimate parent of u
        int ulp_v = findUPar(v); // Find ultimate parent of v

        if (ulp_u == ulp_v) return; // If already in same set, do nothing

        // Merge smaller tree into the larger tree
        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v;
            size[ulp_v] += size[ulp_u]; // Update size of root
        } else {
            parent[ulp_v] = ulp_u;
            size[ulp_u] += size[ulp_v]; // Update size of root
        }
    }
}

class Solution {
    // Function to compute the maximum number of stones that can be removed
    public int removeStones(int[][] stones) {
        int maxRow = 0; // Track the maximum row index
        int maxCol = 0; // Track the maximum column index

        // Determine the maximum row and column index among all stones
        for (int[] stone : stones) {
            maxRow = Math.max(maxRow, stone[0]); // Update maxRow
            maxCol = Math.max(maxCol, stone[1]); // Update maxCol
        }

        // Create a Disjoint Set with maxRow + maxCol + 1 size
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 1);

        // Map to store unique nodes (row & column indices)
        Map<Integer, Integer> stoneNodes = new HashMap<>();

        // Process each stone to unify its row and column in the DSU
        for (int[] stone : stones) {
            int nodeRow = stone[0]; // Extract row index
            int nodeCol = stone[1] + maxRow + 1; // Convert column to unique index

            // Merge row and column in DSU (same component)
            ds.unionBySize(nodeRow, nodeCol);

            // Store row and column as unique components
            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }

        int numComponents = 0; // Count of unique connected components

        // Iterate over unique nodes to count components
        for (int key : stoneNodes.keySet()) {
            if (ds.findUPar(key) == key) { // If node is its own parent, it's a component
                numComponents++;
            }
        }

        // Maximum stones that can be removed = total stones - unique components
        return stones.length - numComponents;
    }
}
```

---

## **Time & Space Complexity**
- **Time Complexity:**  
  - **Finding maxRow & maxCol:** \( O(n) \)  
  - **Union operations:** \( O(n \cdot \alpha(n)) \) (near constant due to path compression)  
  - **Counting components:** \( O(n) \)  
  - **Total:** \( O(n) \)

- **Space Complexity:**  
  - **DSU storage:** \( O(n) \)  
  - **Map storage:** \( O(n) \)  
  - **Total:** \( O(n) \)

---

## **Final Summary**
✔ **Graph-based approach using Disjoint Set (Union-Find)**  
✔ **Optimized with path compression and union by size**  
✔ **Efficient in \( O(n) \) time complexity**  
✔ **Max removable stones = Total stones - Connected components**  

This approach ensures that we find the **maximum number of stones that can be removed** efficiently! 🚀