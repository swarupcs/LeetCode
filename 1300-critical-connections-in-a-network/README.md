<h2><a href="https://leetcode.com/problems/critical-connections-in-a-network">1300. Critical Connections in a Network</a></h2><h3>Hard</h3><hr><p>There are <code>n</code> servers numbered from <code>0</code> to <code>n - 1</code> connected by undirected server-to-server <code>connections</code> forming a network where <code>connections[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> represents a connection between servers <code>a<sub>i</sub></code> and <code>b<sub>i</sub></code>. Any server can reach other servers directly or indirectly through the network.</p>

<p>A <em>critical connection</em> is a connection that, if removed, will make some servers unable to reach some other server.</p>

<p>Return all critical connections in the network in any order.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2019/09/03/1537_ex1_2.png" style="width: 198px; height: 248px;" />
<pre>
<strong>Input:</strong> n = 4, connections = [[0,1],[1,2],[2,0],[1,3]]
<strong>Output:</strong> [[1,3]]
<strong>Explanation:</strong> [[3,1]] is also accepted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, connections = [[0,1]]
<strong>Output:</strong> [[0,1]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>n - 1 &lt;= connections.length &lt;= 10<sup>5</sup></code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>There are no repeated connections.</li>
</ul>



Here's a detailed breakdown of the **Bridges in Graph** problem, including the **approach, intuition, code explanation, algorithm, and a dry run example.**  

---

## **Approach & Intuition**
A **bridge** (also known as a **critical connection**) in an undirected graph is an edge whose removal increases the number of connected components.  
To find such edges, we use **Tarjan's Algorithm**, which utilizes **DFS traversal** to track:  
1. **Discovery time** (`tin[]`) – When a node was first visited.
2. **Lowest discovery time** (`low[]`) – The earliest visited node that can be reached via any alternative path.

- If **low[neighbor] > tin[current]**, then there is no way to reach the `neighbor` without the `current` edge, meaning it's a **bridge**.

---

## **Algorithm**
1. **Initialize data structures**  
   - Create an adjacency list for the graph.
   - Define arrays for **visited nodes (`vis[]`)**, **discovery time (`tin[]`)**, and **lowest time (`low[]`)**.
   - Create a list to store the **bridges**.
   
2. **Run a DFS Traversal**  
   - Start DFS from any node (e.g., `0`), with `parent = -1` (since it's the root).  
   - During DFS:  
     - Mark the node as **visited**.  
     - Assign its discovery and lowest time (`tin[]` and `low[]`).  
     - Explore all **unvisited** neighbors and recurse.  
     - If the **back edge** exists, update `low[]` to indicate an alternative route.  
     - If no alternative route exists (`low[neighbor] > tin[node]`), mark it as a **bridge**.  

3. **Return the list of bridges.**

---

## **Code with Inline Explanation**
```java
import java.util.*;

class Solution {
    private int timer = 1;  // Global timer to track discovery times

    /* 
       Helper function to perform DFS and detect bridges 
       - node: Current node being visited
       - parent: Parent of the current node
       - vis: Visited array
       - adj: Adjacency list representation of the graph
       - tin: Stores the discovery time of each node
       - low: Stores the lowest discovery time reachable from the node
       - bridges: Stores the critical connections (bridges)
    */
    private void dfs(int node, int parent, int[] vis, List<Integer>[] adj, 
                     int[] tin, int[] low, List<List<Integer>> bridges) {
        
        // Step 1: Mark the node as visited
        vis[node] = 1;  
        
        // Step 2: Set discovery time and low time for this node
        tin[node] = low[node] = timer++;  

        // Step 3: Traverse all adjacent nodes
        for (int neighbor : adj[node]) {

            // Skip the parent node to avoid revisiting the edge leading to it
            if (neighbor == parent) continue;  

            // If the neighbor is not visited, perform DFS
            if (vis[neighbor] == 0) {
                dfs(neighbor, node, vis, adj, tin, low, bridges);

                // Step 4: Update the lowest reachable discovery time
                low[node] = Math.min(low[node], low[neighbor]);

                // Step 5: Check if the edge (node, neighbor) is a bridge
                if (low[neighbor] > tin[node]) {
                    bridges.add(Arrays.asList(node, neighbor));
                }
            } 
            // If the neighbor is already visited and it's not the parent
            // It means it's a back edge (cycle), so update the low value
            else {
                low[node] = Math.min(low[node], tin[neighbor]);
            }
        }
    }

    /* 
       Function to find all bridges in the given undirected graph 
       - n: Number of vertices in the graph
       - connections: List of edges representing the graph
       Returns a list of all bridges (critical connections)
    */
    public List<List<Integer>> criticalConnections(int n, List<List<Integer>> connections) {
        // Step 1: Create adjacency list representation of the graph
        List<Integer>[] adj = new ArrayList[n];
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        // Step 2: Populate adjacency list with edges
        for (List<Integer> edge : connections) {
            int u = edge.get(0), v = edge.get(1);
            adj[u].add(v);
            adj[v].add(u);
        }

        // Step 3: Initialize arrays to track visited status, discovery time, and lowest time
        int[] vis = new int[n]; // Visited array
        int[] tin = new int[n]; // Discovery time array
        int[] low = new int[n]; // Lowest reachable time array
        List<List<Integer>> bridges = new ArrayList<>(); // List to store bridges

        // Step 4: Start DFS traversal from node 0 (assuming the graph is connected)
        dfs(0, -1, vis, adj, tin, low, bridges);

        // Step 5: Return the list of critical bridges
        return bridges;
    }
}

```

---

## **Example & Dry Run**
### **Input:**
```java
V = 4, E = [ [0,1],[1,2],[2,0],[1,3] ]
```

### **Graph Representation:**
```
    0 --- 1 --- 3
     \   /
       2
```

### **Step-by-Step DFS Traversal**
| Step | Node | Parent | `tin[]` | `low[]` | Bridges |
|------|------|--------|---------|---------|---------|
| 1    | 0    | -1     | `[1, 0, 0, 0]` | `[1, 0, 0, 0]` | `[]` |
| 2    | 1    | 0      | `[1, 2, 0, 0]` | `[1, 2, 0, 0]` | `[]` |
| 3    | 2    | 1      | `[1, 2, 3, 0]` | `[1, 2, 3, 0]` | `[]` |
| 4    | Back to 1 | Updates | `[1, 2, 3, 0]` | `[1, 2, 1, 0]` | `[]` |
| 5    | 3    | 1      | `[1, 2, 3, 4]` | `[1, 2, 1, 4]` | `[]` |
| 6    | Back to 1 | Edge (1,3) | `[1, 2, 3, 4]` | `[1, 2, 1, 4]` | `[(1,3)]` |

### **Output:**
```java
[[1, 3]]
```
Since removing edge `(1,3)` disconnects the graph, it is a **bridge**.

---

## **Time & Space Complexity**
- **Time Complexity**: **O(V + E)**  
  - DFS runs once for each vertex and processes each edge once.
- **Space Complexity**: **O(V + E)**  
  - For adjacency list and recursion stack.

---

## **Key Takeaways**
1. **Tarjan’s Algorithm** efficiently finds bridges using DFS and timestamps.
2. **low[] and tin[]** arrays help determine if a node can reach ancestors via other paths.
3. **If `low[neighbor] > tin[node]`, the edge `(node, neighbor)` is a bridge**.

