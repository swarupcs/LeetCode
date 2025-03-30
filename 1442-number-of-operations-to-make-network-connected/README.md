<h2><a href="https://leetcode.com/problems/number-of-operations-to-make-network-connected">1442. Number of Operations to Make Network Connected</a></h2><h3>Medium</h3><hr><p>There are <code>n</code> computers numbered from <code>0</code> to <code>n - 1</code> connected by ethernet cables <code>connections</code> forming a network where <code>connections[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> represents a connection between computers <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>. Any computer can reach any other computer directly or indirectly through the network.</p>

<p>You are given an initial computer network <code>connections</code>. You can extract certain cables between two directly connected computers, and place them between any pair of disconnected computers to make them directly connected.</p>

<p>Return <em>the minimum number of times you need to do this in order to make all the computers connected</em>. If it is not possible, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/01/02/sample_1_1677.png" style="width: 500px; height: 148px;" />
<pre>
<strong>Input:</strong> n = 4, connections = [[0,1],[0,2],[1,2]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> Remove cable between computer 1 and 2 and place between computers 1 and 3.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/01/02/sample_2_1677.png" style="width: 500px; height: 129px;" />
<pre>
<strong>Input:</strong> n = 6, connections = [[0,1],[0,2],[0,3],[1,2],[1,3]]
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> n = 6, connections = [[0,1],[0,2],[0,3],[1,2]]
<strong>Output:</strong> -1
<strong>Explanation:</strong> There are not enough cables.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>1 &lt;= connections.length &lt;= min(n * (n - 1) / 2, 10<sup>5</sup>)</code></li>
	<li><code>connections[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; n</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no repeated connections.</li>
	<li>No two computers are connected by more than one cable.</li>
</ul>

---


### **Intuition**
- The problem is about connecting all computers (nodes) in a network with given cables (edges).
- If there are fewer than `n-1` edges, it's impossible to connect all nodes.
- We use **Disjoint Set Union (DSU)** (Union-Find) to efficiently count connected components and merge them.
- The answer is **(number of components - 1)** because we need to merge all components into one.

---

### **Approach**
1. **Check Edge Condition**: If `edges < n-1`, return `-1` (not enough edges to connect all nodes).
2. **Use Disjoint Set (Union-Find)**: Initialize DSU for `n` nodes.
3. **Process Connections**: Merge the nodes in the given connections using `unionByRank`.
4. **Count Components**: Traverse all nodes and count distinct components using `findUPar`.
5. **Compute Result**: Return `components - 1` as the minimum operations needed.

---

### **Algorithm**
1. **Initialize Disjoint Set** with `n` nodes.
2. **Check if edges are sufficient** (if `edges < n-1`, return `-1`).
3. **Perform Union Operations** for all given connections.
4. **Count the number of connected components**.
5. **Return (number of components - 1)** as the result.

---

### **Code with Line-by-Line Explanation**

```java
class Solution {
    public int makeConnected(int n, int[][] connections) {
        int size = connections.length; // Number of edges in the graph

        // Step 1: If edges are less than (n-1), connection is impossible
        if (size < n - 1) return -1;

        // Step 2: Initialize Disjoint Set for `n` nodes
        DisjointSet ds = new DisjointSet(n);

        // Step 3: Process each edge and merge components
        for (int i = 0; i < size; i++) {
            ds.unionByRank(connections[i][0], connections[i][1]); // Connect edge nodes
        }

        // Step 4: Count number of connected components
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (ds.findUPar(i) == i) // If node is an ultimate parent, it's a component
                count++;
        }

        // Step 5: Return the required operations (components - 1)
        return count - 1;
    }
}

// Disjoint Set (Union-Find) Data Structure
class DisjointSet {
    int[] rank, parent, size;

    // Constructor: Initialize Disjoint Set for `n` nodes
    DisjointSet(int n) {
        rank = new int[n];
        parent = new int[n];
        size = new int[n];

        // Initially, every node is its own parent (self-loop)
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1; // Each node is its own component initially
        }
    }

    // Function to find the ultimate parent of a node (with path compression)
    int findUPar(int node) {
        if (node == parent[node]) // If node is its own parent, return it
            return node;
        return parent[node] = findUPar(parent[node]); // Path compression optimization
    }

    // Union by Rank: Merge two sets based on rank
    void unionByRank(int u, int v) {
        int ulp_u = findUPar(u); // Find ultimate parent of u
        int ulp_v = findUPar(v); // Find ultimate parent of v

        if (ulp_u == ulp_v) return; // Already connected, no need to merge

        // Merge the smaller tree under the larger one
        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v; // u's parent becomes v
        } else if (rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u; // v's parent becomes u
        } else {
            parent[ulp_v] = ulp_u; // If ranks are same, choose one and increase rank
            rank[ulp_u]++;
        }
    }
}
```

---

### **Example & Dry Run**
#### **Input:**
```java
n = 4;
connections = { {0,1}, {0,2}, {1,2} }
```
#### **Step-by-step Execution:**
1. We have 3 edges, which is enough (`3 >= 4-1`).
2. Perform Union operations:
   - `union(0,1)`: Merge `0` and `1`.
   - `union(0,2)`: Merge `2` into `0-1`.
   - `union(1,2)`: Already connected.
3. Find the number of components:
   - `{0,1,2}` is one component.
   - `{3}` is a separate component.
4. **Result**: `1` operation needed to connect `{3}` to `{0,1,2}`.

🔹 **Final Answer:** `1`

---

### **Time Complexity**
- **Union-Find Operations**: `O(E log V)` (Almost constant with path compression)
- **Counting Components**: `O(N)`


