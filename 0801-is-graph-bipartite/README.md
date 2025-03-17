<h2><a href="https://leetcode.com/problems/is-graph-bipartite">801. Is Graph Bipartite?</a></h2><h3>Medium</h3><hr><p>There is an <strong>undirected</strong> graph with <code>n</code> nodes, where each node is numbered between <code>0</code> and <code>n - 1</code>. You are given a 2D array <code>graph</code>, where <code>graph[u]</code> is an array of nodes that node <code>u</code> is adjacent to. More formally, for each <code>v</code> in <code>graph[u]</code>, there is an undirected edge between node <code>u</code> and node <code>v</code>. The graph has the following properties:</p>

<ul>
	<li>There are no self-edges (<code>graph[u]</code> does not contain <code>u</code>).</li>
	<li>There are no parallel edges (<code>graph[u]</code> does not contain duplicate values).</li>
	<li>If <code>v</code> is in <code>graph[u]</code>, then <code>u</code> is in <code>graph[v]</code> (the graph is undirected).</li>
	<li>The graph may not be connected, meaning there may be two nodes <code>u</code> and <code>v</code> such that there is no path between them.</li>
</ul>

<p>A graph is <strong>bipartite</strong> if the nodes can be partitioned into two independent sets <code>A</code> and <code>B</code> such that <strong>every</strong> edge in the graph connects a node in set <code>A</code> and a node in set <code>B</code>.</p>

<p>Return <code>true</code><em> if and only if it is <strong>bipartite</strong></em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/21/bi2.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There is no way to partition the nodes into two independent sets such that every edge connects a node in one and a node in the other.</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/21/bi1.jpg" style="width: 222px; height: 222px;" />
<pre>
<strong>Input:</strong> graph = [[1,3],[0,2],[1,3],[0,2]]
<strong>Output:</strong> true
<strong>Explanation:</strong> We can partition the nodes into two sets: {0, 2} and {1, 3}.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>graph.length == n</code></li>
	<li><code>1 &lt;= n &lt;= 100</code></li>
	<li><code>0 &lt;= graph[u].length &lt; n</code></li>
	<li><code>0 &lt;= graph[u][i] &lt;= n - 1</code></li>
	<li><code>graph[u]</code>&nbsp;does not contain&nbsp;<code>u</code>.</li>
	<li>All the values of <code>graph[u]</code> are <strong>unique</strong>.</li>
	<li>If <code>graph[u]</code> contains <code>v</code>, then <code>graph[v]</code> contains <code>u</code>.</li>
</ul>


Here is the complete implementation of the `isBipartite` function using **BFS (Breadth-First Search)**. The idea is to use a **coloring approach**, where we assign two different colors (0 and 1) to adjacent nodes. If any adjacent nodes end up with the same color, the graph is **not bipartite**.

---

### **Approach & Algorithm**
1. **Initialize a Color Array**  
   - Create a `color[]` array of size `n`, initialized with `-1` (indicating uncolored nodes).
   
2. **Check Each Component**  
   - The graph may have multiple disconnected components, so traverse all nodes.
   - If a node is uncolored, start a **BFS** traversal from it.

3. **BFS Traversal (Coloring Approach)**  
   - Use a queue to process nodes level by level.
   - Assign the current node a color (`0` initially).
   - Traverse its neighbors:
     - If a neighbor is uncolored, assign it the opposite color and push it into the queue.
     - If a neighbor has the **same color** as the current node, return `false`.

4. **If BFS completes without conflicts, return `true`**  

---

### **Code Implementation**
```java
import java.util.*;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n]; // Color array initialized to -1 (uncolored nodes)
        Arrays.fill(color, -1);

        // Traverse all nodes (handle disconnected components)
        for (int i = 0; i < n; i++) {
            if (color[i] == -1) { // If the node is uncolored, start BFS
                if (!bfsCheck(graph, i, color)) {
                    return false; // If any component is not bipartite, return false
                }
            }
        }
        return true; // If all components are bipartite, return true
    }

    private boolean bfsCheck(int[][] graph, int start, int[] color) {
        Queue<Integer> q = new LinkedList<>();
        q.add(start);
        color[start] = 0; // Start coloring with 0

        while (!q.isEmpty()) {
            int node = q.poll(); // Get the front node

            // Traverse all adjacent nodes
            for (int neighbor : graph[node]) {
                if (color[neighbor] == -1) {
                    color[neighbor] = 1 - color[node]; // Assign opposite color
                    q.add(neighbor); // Push neighbor into the queue
                } else if (color[neighbor] == color[node]) {
                    return false; // Same color means not bipartite
                }
            }
        }
        return true;
    }
}
```

---

### **Explanation of Code (Step-by-Step)**
1. **Initialize `color[]` array**:  
   - `color[i] == -1` → Uncolored
   - `color[i] == 0` or `color[i] == 1` → Colored nodes

2. **Check all nodes using BFS**  
   - If a node is not colored (`-1`), call `bfsCheck()`.
   - If `bfsCheck()` returns `false`, the graph is **not bipartite**.

3. **BFS Traversal (`bfsCheck()`)**  
   - Assign the **starting node** `0` color.
   - Traverse its **neighbors**:
     - If uncolored (`-1`), color with **opposite** of the current node (`1 - color[node]`).
     - If already **colored the same as the current node**, return `false`.

---

### **Dry Run**
#### **Input:**
```java
graph = [[1,2,3],[0,2],[0,1,3],[0,2]]
```
#### **Adjacency List Representation:**
```
   0
  /|\
 1 2 3
  \|/
```

#### **Step-by-Step Execution**
| Step | Node Processed | Queue (`q`) | Color Array (`color[]`) | Notes |
|------|--------------|------------|-----------------|-------|
| 1    | Start at `0` | `[0]`      | `[0, -1, -1, -1]` | Color `0` |
| 2    | Dequeue `0`  | `[1,2,3]`  | `[0, 1, 1, 1]`  | Assign opposite colors |
| 3    | Dequeue `1`  | `[2,3]`    | `[0, 1, 1, 1]`  | Check `2` (already colored) |
| 4    | Dequeue `2`  | `[3]`      | `[0, 1, 1, 1]`  | **Conflict! `2` is already `1`** |
| 5    | **Return `false`** |  |  | The graph is **not bipartite** |

---

### **Complexity Analysis**
- **BFS Traversal:** `O(V + E)`
- **Checking All Nodes:** `O(V)`
- **Overall Complexity:** **`O(V + E)`**, where `V` is the number of nodes and `E` is the number of edges.

---

### **Example Cases**
#### **Example 1:**
✅ **Input:**
```java
graph = [[1,3],[0,2],[1,3],[0,2]];
```
✅ **Output:**
```java
true
```
✅ **Explanation:**
- The graph can be colored like:
  ```
    0---1
    |   |
    3---2
  ```
- **Valid Bipartition:** `{0,2}` and `{1,3}`.

#### **Example 2:**
❌ **Input:**
```java
graph = [[1,2,3],[0,2],[0,1,3],[0,2]];
```
❌ **Output:**
```java
false
```
❌ **Explanation:**  
- There is a **conflict** in coloring → The graph is **not bipartite**.

---

### **Conclusion**
✅ **Why BFS?** Ensures level-by-level coloring.  
✅ **Why Use a Color Array?** To track node assignments.  
✅ **What If the Graph is Disconnected?** We check all components.  
✅ **Time Complexity:** `O(V + E)`, efficient for large graphs.  

🚀 **Would you like a DFS-based solution as well?**

```java
import java.util.*;

class Solution {
    public boolean isBipartite(int[][] graph) {
        int n = graph.length; // Number of nodes in the graph

        // Step 1: Create a color array initialized to -1 (uncolored nodes)
        int[] color = new int[n];
        Arrays.fill(color, -1);

        // Step 2: Traverse all nodes (to handle disconnected components)
        for (int i = 0; i < n; i++) {
            // If node is uncolored, start BFS traversal
            if (color[i] == -1) {
                if (!bfsCheck(graph, i, color)) {
                    return false; // If any component is not bipartite, return false
                }
            }
        }
        // If all components are bipartite, return true
        return true;
    }

    private boolean bfsCheck(int[][] graph, int start, int[] color) {
        Queue<Integer> q = new LinkedList<>();

        // Step 3: Start BFS traversal from the given node
        q.add(start);
        color[start] = 0; // Assign the first color (0) to the start node

        // Step 4: BFS traversal
        while (!q.isEmpty()) {
            int node = q.poll(); // Remove the front node from the queue

            // Step 5: Traverse all adjacent nodes of 'node'
            for (int neighbor : graph[node]) {

                // Step 6: If neighbor is not colored, color it with opposite color
                if (color[neighbor] == -1) {
                    color[neighbor] = 1 - color[node]; // Assign opposite color (flip color)
                    q.add(neighbor); // Add neighbor to queue for further BFS traversal
                } 
                // Step 7: If neighbor has the same color as current node, graph is not bipartite
                else if (color[neighbor] == color[node]) {
                    return false; // Conflict in coloring, return false
                }
            }
        }
        // Step 8: If BFS completes without conflicts, the graph is bipartite
        return true;
    }
}
```

---

## **Algorithm with Explanation (Step-by-Step)**
### **Step 1: Initialize a Color Array**
- The `color[]` array stores colors of nodes.
- Initially, all nodes are uncolored (`-1`).

### **Step 2: Check All Nodes (Handling Disconnected Graphs)**
- Some graphs have multiple components.
- If a node is uncolored, we start BFS from it.

### **Step 3: Start BFS Traversal**
- Start BFS with a given node.
- Assign it **color `0`**.

### **Step 4: BFS Process**
- Use a **queue** to process nodes level by level.

### **Step 5: Traverse Adjacent Nodes**
- For each adjacent node (`neighbor`):
  - If it is **not colored**, color it with the **opposite** of the current node.

### **Step 6: Detect Conflict**
- If any adjacent node is **already colored the same**, return `false` (not bipartite).

### **Step 7: Continue BFS Until Queue is Empty**
- If BFS completes without conflict, the component is bipartite.

### **Step 8: Check All Components**
- If all components pass the bipartite test, return `true`.

---

## **Example Walkthrough (Dry Run)**

### **Example 1**
#### **Input:**
```java
graph = [[1,3],[0,2],[1,3],[0,2]]
```
#### **Graph Representation:**
```
   0---1
   |   |
   3---2
```
#### **BFS Coloring Process**
| Step | Node Processed | Queue (`q`) | Color Array (`color[]`) | Notes |
|------|--------------|------------|-----------------|-------|
| 1    | Start at `0` | `[0]`      | `[0, -1, -1, -1]` | Assign `0` |
| 2    | Dequeue `0`  | `[1,3]`    | `[0, 1, -1, 1]`  | Assign `1` to `1` and `3` |
| 3    | Dequeue `1`  | `[3,2]`    | `[0, 1, 0, 1]`  | Assign `0` to `2` |
| 4    | Dequeue `3`  | `[2]`      | `[0, 1, 0, 1]`  | All valid colors |
| 5    | Dequeue `2`  | `[]`       | `[0, 1, 0, 1]`  | All valid colors |
| 6    | **Return `true`** | | | The graph is **bipartite** ✅ |

#### **Output:**
```java
true
```

---

### **Example 2**
#### **Input:**
```java
graph = [[1,2,3],[0,2],[0,1,3],[0,2]];
```
#### **Graph Representation:**
```
   0
  /|\
 1 2 3
  \|/
```
#### **BFS Coloring Process**
| Step | Node Processed | Queue (`q`) | Color Array (`color[]`) | Notes |
|------|--------------|------------|-----------------|-------|
| 1    | Start at `0` | `[0]`      | `[0, -1, -1, -1]` | Assign `0` |
| 2    | Dequeue `0`  | `[1,2,3]`  | `[0, 1, 1, 1]`  | Assign `1` to all neighbors |
| 3    | Dequeue `1`  | `[2,3]`    | `[0, 1, 1, 1]`  | `2` already colored |
| 4    | Dequeue `2`  | `[3]`      | `[0, 1, 1, 1]`  | **Conflict! `2` is already `1`** |
| 5    | **Return `false`** |  |  | The graph is **not bipartite** ❌ |

#### **Output:**
```java
false
```

---

## **Time Complexity Analysis**
- **BFS Traversal:** `O(V + E)`
- **Checking All Nodes:** `O(V)`
- **Overall Complexity:** **`O(V + E)`**, where `V` is the number of nodes and `E` is the number of edges.

---

## **Key Takeaways**
✔ **BFS ensures proper level-wise coloring**  
✔ **Color array prevents reprocessing nodes**  
✔ **Handling disconnected components is crucial**  
✔ **Efficient time complexity of `O(V + E)`**  
✔ **Conflict detection ensures correctness**  

