<h2><a href="https://leetcode.com/problems/find-eventual-safe-states">820. Find Eventual Safe States</a></h2><h3>Medium</h3><hr><p>There is a directed graph of <code>n</code> nodes with each node labeled from <code>0</code> to <code>n - 1</code>. The graph is represented by a <strong>0-indexed</strong> 2D integer array <code>graph</code> where <code>graph[i]</code> is an integer array of nodes adjacent to node <code>i</code>, meaning there is an edge from node <code>i</code> to each node in <code>graph[i]</code>.</p>

<p>A node is a <strong>terminal node</strong> if there are no outgoing edges. A node is a <strong>safe node</strong> if every possible path starting from that node leads to a <strong>terminal node</strong> (or another safe node).</p>

<p>Return <em>an array containing all the <strong>safe nodes</strong> of the graph</em>. The answer should be sorted in <strong>ascending</strong> order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="Illustration of graph" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/03/17/picture1.png" style="height: 171px; width: 600px;" />
<pre>
<strong>Input:</strong> graph = [[1,2],[2,3],[5],[0],[5],[],[]]
<strong>Output:</strong> [2,4,5,6]
<strong>Explanation:</strong> The given graph is shown above.
Nodes 5 and 6 are terminal nodes as there are no outgoing edges from either of them.
Every path starting at nodes 2, 4, 5, and 6 all lead to either node 5 or 6.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> graph = [[1,2,3,4],[1,2],[3,4],[0,4],[]]
<strong>Output:</strong> [4]
<strong>Explanation:</strong>
Only node 4 is a terminal node, and every path starting at node 4 leads to node 4.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == graph.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= graph[i].length &lt;= n</code></li>
	<li><code>0 &lt;= graph[i][j] &lt;= n - 1</code></li>
	<li><code>graph[i]</code> is sorted in a strictly increasing order.</li>
	<li>The graph may contain self-loops.</li>
	<li>The number of edges in the graph will be in the range <code>[1, 4 * 10<sup>4</sup>]</code>.</li>
</ul>



# **Approach & Intuition**

The problem requires us to find **eventually safe nodes** in a **directed graph**. A node is **safe** if every path starting from that node leads to a terminal node (a node with no outgoing edges) and does not form a cycle.

### **Key Observations**
1. Nodes that are part of a cycle **are not safe**.
2. Nodes that can reach a cycle **are not safe**.
3. Nodes that only lead to terminal nodes **are safe**.

### **Why Reverse the Graph?**
Instead of finding nodes leading to terminal nodes, we **reverse the edges** and find nodes that are **reachable from terminal nodes**. This ensures:
1. **Terminal nodes (nodes with no outgoing edges) become source nodes (nodes with in-degree 0).**
2. **Nodes leading to cycles will be unreachable in topological sorting.**

---

# **Algorithm**

### **Step 1: Reverse the Graph**
- Create an **adjacency list for the reversed graph**.
- If `A → B` exists in the original graph, then in the reversed graph, `B → A` will exist.
- Compute **in-degree** for each node (the number of incoming edges).

### **Step 2: Perform Topological Sorting (Kahn's Algorithm - BFS)**
- Add nodes with **in-degree = 0** to the queue.
- Process each node:
  - Add it to the result (`safeNodes` list).
  - Reduce the in-degree of its neighbors.
  - If a neighbor's in-degree becomes **0**, add it to the queue.

### **Step 3: Sort and Return the Safe Nodes**
- The nodes left in the result are **safe nodes**.
- Return them in **ascending order**.

---

# **Code with Step-by-Step Explanation**
```java
import java.util.*;

class Solution {
    public List<Integer> eventualSafeNodes(int[][] graph) {
        int V = graph.length; // Number of nodes

        // Step 1: Create a reversed graph
        ArrayList<Integer>[] adjRev = new ArrayList[V];
        for (int i = 0; i < V; i++)
            adjRev[i] = new ArrayList<>();

        // Step 2: Compute in-degrees and reverse edges
        int[] inDegree = new int[V];
        for (int i = 0; i < V; i++) {
            for (int it : graph[i]) {
                adjRev[it].add(i); // Reverse edge
                inDegree[i]++; // Compute in-degree of each node
            }
        }

        // Step 3: BFS using Kahn's algorithm
        Queue<Integer> q = new LinkedList<>();

        // Step 4: Add terminal nodes (nodes with in-degree 0) to the queue
        for (int i = 0; i < V; i++) {
            if (inDegree[i] == 0) q.add(i);
        }

        // Step 5: Process the queue and find safe nodes
        List<Integer> safeNodes = new ArrayList<>();
        while (!q.isEmpty()) {
            int node = q.poll(); // Remove node from queue
            safeNodes.add(node); // Add node to safe list

            // Step 6: Process the reversed edges
            for (int neighbor : adjRev[node]) {
                inDegree[neighbor]--; // Reduce in-degree

                // If in-degree becomes 0, add to queue
                if (inDegree[neighbor] == 0) {
                    q.add(neighbor);
                }
            }
        }

        // Step 7: Sort and return the safe nodes
        Collections.sort(safeNodes);
        return safeNodes;
    }
}
```
---

# **Line-by-Line Explanation**
### **Step 1: Reverse the Graph**
```java
ArrayList<Integer>[] adjRev = new ArrayList[V];
for (int i = 0; i < V; i++)
    adjRev[i] = new ArrayList<>();
```
- We create an adjacency list for the **reversed graph**.
- Each node has an empty list initially.

### **Step 2: Compute In-Degrees and Reverse Edges**
```java
int[] inDegree = new int[V];
for (int i = 0; i < V; i++) {
    for (int it : graph[i]) {
        adjRev[it].add(i); // Reverse edge
        inDegree[i]++; // Compute in-degree of each node
    }
}
```
- We traverse the original graph and **reverse all edges**.
- We also calculate the **in-degree** of each node (number of incoming edges).

### **Step 3: BFS using Kahn’s Algorithm**
```java
Queue<Integer> q = new LinkedList<>();
```
- We initialize a **queue** for BFS traversal.

### **Step 4: Add Terminal Nodes (in-degree = 0) to the Queue**
```java
for (int i = 0; i < V; i++) {
    if (inDegree[i] == 0) q.add(i);
}
```
- We add all nodes with `in-degree = 0` (nodes that have no outgoing edges in the original graph).

### **Step 5: Process the Queue and Find Safe Nodes**
```java
List<Integer> safeNodes = new ArrayList<>();
while (!q.isEmpty()) {
    int node = q.poll(); // Remove node from queue
    safeNodes.add(node); // Add node to safe list

    // Step 6: Process the reversed edges
    for (int neighbor : adjRev[node]) {
        inDegree[neighbor]--; // Reduce in-degree

        // If in-degree becomes 0, add to queue
        if (inDegree[neighbor] == 0) {
            q.add(neighbor);
        }
    }
}
```
- We process each node in the queue.
- Reduce the **in-degree** of its neighbors.
- If any neighbor’s **in-degree becomes 0**, it is added to the queue.

### **Step 7: Sort and Return Safe Nodes**
```java
Collections.sort(safeNodes);
return safeNodes;
```
- The list of safe nodes is sorted before returning.

---

# **Example with Dry Run**
### **Input**
```java
graph = {
    {1, 2},  // 0 → 1, 0 → 2
    {2, 3},  // 1 → 2, 1 → 3
    {5},     // 2 → 5
    {0},     // 3 → 0 (cycle)
    {5},     // 4 → 5
    {},      // 5 (terminal node)
}
```

### **Step 1: Reverse the Graph**
```java
0 ← 3
1 ← 0
2 ← 0, 1
3 ← 1
5 ← 2, 4
```
### **Step 2: Compute In-Degree**
```
inDegree = [1, 1, 2, 1, 1, 2]
```

### **Step 3: Add Terminal Nodes to Queue**
```
Queue: [5] (because in-degree[5] = 0)
```

### **Step 4: Process Queue**
1. **Pop `5`** → Reduce in-degree of {2, 4}  
   `inDegree = [1, 1, 1, 1, 0, 2]`
   **Add `4` to queue**  
   `Queue = [4]`
2. **Pop `4`** → Reduce in-degree of {5}  
   `inDegree = [1, 1, 1, 1, 0, 1]`
3. **No more nodes to process. Stop.**

### **Step 5: Output Safe Nodes**
```
Safe Nodes = [4, 5]
```

---

# **Complexity Analysis**
| Step | Time Complexity |
|------|---------------|
| Reversing the graph | \(O(V + E)\) |
| Computing in-degrees | \(O(V + E)\) |
| BFS (Topological Sorting) | \(O(V + E)\) |
| Sorting the result | \(O(V \log V)\) |
| **Total Complexity** | \(O(V + E + V \log V)\) |

This ensures an **efficient solution** to find eventually safe nodes in a **directed graph**. 🚀