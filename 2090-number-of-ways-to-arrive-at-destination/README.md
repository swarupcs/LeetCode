<h2><a href="https://leetcode.com/problems/number-of-ways-to-arrive-at-destination">2090. Number of Ways to Arrive at Destination</a></h2><h3>Medium</h3><hr><p>You are in a city that consists of <code>n</code> intersections numbered from <code>0</code> to <code>n - 1</code> with <strong>bi-directional</strong> roads between some intersections. The inputs are generated such that you can reach any intersection from any other intersection and that there is at most one road between any two intersections.</p>

<p>You are given an integer <code>n</code> and a 2D integer array <code>roads</code> where <code>roads[i] = [u<sub>i</sub>, v<sub>i</sub>, time<sub>i</sub>]</code> means that there is a road between intersections <code>u<sub>i</sub></code> and <code>v<sub>i</sub></code> that takes <code>time<sub>i</sub></code> minutes to travel. You want to know in how many ways you can travel from intersection <code>0</code> to intersection <code>n - 1</code> in the <strong>shortest amount of time</strong>.</p>

<p>Return <em>the <strong>number of ways</strong> you can arrive at your destination in the <strong>shortest amount of time</strong></em>. Since the answer may be large, return it <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2025/02/14/1976_corrected.png" style="width: 255px; height: 400px;" />
<pre>
<strong>Input:</strong> n = 7, roads = [[0,6,7],[0,1,2],[1,2,3],[1,3,3],[6,3,3],[3,5,1],[6,5,1],[2,5,1],[0,4,5],[4,6,2]]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The shortest amount of time it takes to go from intersection 0 to intersection 6 is 7 minutes.
The four ways to get there in 7 minutes are:
- 0 ➝ 6
- 0 ➝ 4 ➝ 6
- 0 ➝ 1 ➝ 2 ➝ 5 ➝ 6
- 0 ➝ 1 ➝ 3 ➝ 5 ➝ 6
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 2, roads = [[1,0,10]]
<strong>Output:</strong> 1
<strong>Explanation:</strong> There is only one way to go from intersection 0 to intersection 1, and it takes 10 minutes.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 200</code></li>
	<li><code>n - 1 &lt;= roads.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>roads[i].length == 3</code></li>
	<li><code>0 &lt;= u<sub>i</sub>, v<sub>i</sub> &lt;= n - 1</code></li>
	<li><code>1 &lt;= time<sub>i</sub> &lt;= 10<sup>9</sup></code></li>
	<li><code>u<sub>i </sub>!= v<sub>i</sub></code></li>
	<li>There is at most one road connecting any two intersections.</li>
	<li>You can reach any intersection from any other intersection.</li>
</ul>

---
# Intuition  
The problem requires finding the number of ways to travel from intersection `0` to intersection `n-1` using the shortest possible time. Since we are dealing with weighted edges, the best approach to finding the shortest path is **Dijkstra's Algorithm**.  
Additionally, to count the number of shortest paths, we need to maintain a **ways array** that keeps track of the number of different ways to reach each node using the shortest time.

# Approach  
1. **Graph Representation:**  
   - Convert the given `roads` array into an **adjacency list** where each node stores a list of `{neighbor, travel_time}` pairs.  

2. **Use Dijkstra’s Algorithm for Shortest Paths:**  
   - Maintain a **min-heap (priority queue)** that always processes the node with the current shortest time.  
   - Use an array `minTime[]` to store the shortest time required to reach each node, initialized to **infinity (`Long.MAX_VALUE`)** except for the starting node (0), which is set to `0`.  
   - Use another array `ways[]` to store the number of ways to reach each node using the shortest time, initialized to `0` except for `ways[0] = 1` (only one way to start at node `0`).  

3. **Processing the Priority Queue:**  
   - Extract the node with the smallest known travel time.  
   - Iterate over all its neighbors and update their shortest known travel time if a shorter path is found.  
   - If an alternate path with the same shortest time is found, increase the count in `ways[]` accordingly.  

4. **Final Answer:**  
   - The value at `ways[n-1]` represents the number of ways to reach the destination `n-1` in the shortest time.  

# Complexity  
- **Time Complexity:** \(O((E + V) \log V)\)  
  - We process each edge and vertex using a priority queue (similar to Dijkstra’s Algorithm).  

- **Space Complexity:** \(O(V + E)\)  
  - We store the graph as an adjacency list and maintain additional arrays for tracking shortest paths and path counts.  

---

### Code with Comments and Explanation  

```java
import java.util.*;

class Solution {
    public int countPaths(int n, int[][] roads) {
        int mod = 1000000007; // Modulo value to prevent overflow in large numbers

        // Step 1: Create an adjacency list representation of the graph
        List<int[]>[] adj = new ArrayList[n]; // Array of lists, each storing {neighbor, travelTime}
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>(); // Initialize each list in the adjacency array
        }

        // Step 2: Construct the graph from the given roads
        for (int[] road : roads) {
            int u = road[0]; // Start node of the road
            int v = road[1]; // End node of the road
            int time = road[2]; // Time to travel between u and v
            
            // Since it's an undirected graph, add the road in both directions
            adj[u].add(new int[]{v, time});
            adj[v].add(new int[]{u, time});
        }

        // Step 3: Create an array to store the minimum time required to reach each node
        long[] minTime = new long[n];
        Arrays.fill(minTime, Long.MAX_VALUE); // Initialize with a large value (infinity)

        // Step 4: Create an array to count the number of ways to reach each node in the shortest time
        int[] ways = new int[n]; // Ways to reach each node using the shortest time

        // Step 5: Use a Min-Heap (Priority Queue) to process nodes based on the shortest time first
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        // Step 6: Initialize for the starting node (node 0)
        minTime[0] = 0; // Time to reach node 0 is 0
        ways[0] = 1; // There's only one way to start from node 0
        pq.add(new long[]{0, 0}); // {time, node} is added to the priority queue

        // Step 7: Process nodes in order of their shortest known time
        while (!pq.isEmpty()) {
            long[] curr = pq.poll(); // Extract the node with the smallest recorded time
            long time = curr[0]; // Time taken to reach the current node
            int node = (int) curr[1]; // Current node

            // Step 8: Traverse all the adjacent nodes (neighbors)
            for (int[] neighbor : adj[node]) {
                int adjNode = neighbor[0]; // Adjacent node
                int travelTime = neighbor[1]; // Travel time to the adjacent node

                // Step 9: If we find a shorter path to the adjacent node
                if (minTime[adjNode] > time + travelTime) {
                    minTime[adjNode] = time + travelTime; // Update the shortest time
                    ways[adjNode] = ways[node]; // Reset the count of ways to reach this node
                    pq.add(new long[]{minTime[adjNode], adjNode}); // Push the new shortest path to PQ
                }
                // Step 10: If we find another shortest path of the same time
                else if (minTime[adjNode] == time + travelTime) {
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod; // Add the ways from the previous node
                }
            }
        }

        // Step 11: Return the number of ways to reach the destination node (n - 1) modulo 10^9 + 7
        return ways[n - 1] % mod;
    }
}
```

---

### Example with Dry Run  

#### **Input:**  
```java
n = 7;
roads = {
    {0, 6, 7}, {0, 1, 2}, {1, 2, 3}, {1, 3, 3}, 
    {6, 3, 3}, {3, 5, 1}, {6, 5, 1}, {2, 5, 1}, 
    {0, 4, 5}, {4, 6, 2}
};
```

#### **Graph Representation:**  
```
     (0) --2-- (1) --3-- (2)
      |         |       /
      5         3      1
      |         |     /
     (4) --2-- (6) --1-- (5)
             \   |      /
              3 3     1
              (3)
```

#### **Priority Queue Processing:**  
| Iteration | Node Processed | MinTime Update | Ways Update | PQ Status |
|-----------|---------------|---------------|------------|-----------|
| 1         | 0             | `{0, ∞, ∞, ∞, ∞, ∞, ∞}` → `{0, 2, ∞, ∞, 5, ∞, 7}` | `{1, 1, 0, 0, 1, 0, 1}` | `{(2,1), (5,4), (7,6)}` |
| 2         | 1             | `{0, 2, 5, 5, 5, ∞, 7}` | `{1, 1, 1, 1, 1, 0, 1}` | `{(5,2), (5,3), (7,6), (5,4)}` |
| 3         | 2             | `{0, 2, 5, 5, 5, 6, 7}` | `{1, 1, 1, 1, 1, 1, 1}` | `{(5,3), (5,4), (6,5), (7,6)}` |
| ...       | ...           | ...           | ...        | ...       |

#### **Final Output:**  
```java
// The number of ways to reach node 6 in the shortest time
System.out.println(countPaths(n, roads)); // Output: 4
```

