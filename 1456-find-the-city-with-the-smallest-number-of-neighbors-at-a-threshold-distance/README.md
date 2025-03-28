<h2><a href="https://leetcode.com/problems/find-the-city-with-the-smallest-number-of-neighbors-at-a-threshold-distance">1456. Find the City With the Smallest Number of Neighbors at a Threshold Distance</a></h2><h3>Medium</h3><hr><p>There are <code>n</code> cities numbered from <code>0</code> to <code>n-1</code>. Given the array <code>edges</code> where <code>edges[i] = [from<sub>i</sub>, to<sub>i</sub>, weight<sub>i</sub>]</code> represents a bidirectional and weighted edge between cities <code>from<sub>i</sub></code> and <code>to<sub>i</sub></code>, and given the integer <code>distanceThreshold</code>.</p>

<p>Return the city with the smallest number of cities that are reachable through some path and whose distance is <strong>at most</strong> <code>distanceThreshold</code>, If there are multiple such cities, return the city with the greatest number.</p>

<p>Notice that the distance of a path connecting cities <em><strong>i</strong></em> and <em><strong>j</strong></em> is equal to the sum of the edges&#39; weights along that path.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/08/23/problem1334example1.png" style="width: 300px; height: 224px;" /></p>

<pre>
<strong>Input:</strong> n = 4, edges = [[0,1,3],[1,2,1],[1,3,4],[2,3,1]], distanceThreshold = 4
<strong>Output:</strong> 3
<strong>Explanation: </strong>The figure above describes the graph.&nbsp;
The neighboring cities at a distanceThreshold = 4 for each city are:
City 0 -&gt; [City 1, City 2]&nbsp;
City 1 -&gt; [City 0, City 2, City 3]&nbsp;
City 2 -&gt; [City 0, City 1, City 3]&nbsp;
City 3 -&gt; [City 1, City 2]&nbsp;
Cities 0 and 3 have 2 neighboring cities at a distanceThreshold = 4, but we have to return city 3 since it has the greatest number.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2024/08/23/problem1334example0.png" style="width: 300px; height: 224px;" /></p>

<pre>
<strong>Input:</strong> n = 5, edges = [[0,1,2],[0,4,8],[1,2,3],[1,4,2],[2,3,1],[3,4,1]], distanceThreshold = 2
<strong>Output:</strong> 0
<strong>Explanation: </strong>The figure above describes the graph.&nbsp;
The neighboring cities at a distanceThreshold = 2 for each city are:
City 0 -&gt; [City 1]&nbsp;
City 1 -&gt; [City 0, City 4]&nbsp;
City 2 -&gt; [City 3, City 4]&nbsp;
City 3 -&gt; [City 2, City 4]
City 4 -&gt; [City 1, City 2, City 3]&nbsp;
The city 0 has 1 neighboring city at a distanceThreshold = 2.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= n &lt;= 100</code></li>
	<li><code>1 &lt;= edges.length &lt;= n * (n - 1) / 2</code></li>
	<li><code>edges[i].length == 3</code></li>
	<li><code>0 &lt;= from<sub>i</sub> &lt; to<sub>i</sub> &lt; n</code></li>
	<li><code>1 &lt;= weight<sub>i</sub>,&nbsp;distanceThreshold &lt;= 10^4</code></li>
	<li>All pairs <code>(from<sub>i</sub>, to<sub>i</sub>)</code> are distinct.</li>
</ul>

---


# Solution

```java
import java.util.*;

class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        // Step 1: Initialize the adjacency matrix with a large value (representing infinity)
        int[][] adjMat = new int[n][n];
        for(int[] row : adjMat) {
            Arrays.fill(row, (int)1e9); // Fill each cell with a large value (infinity)
        }

        // Step 2: Fill adjacency matrix with given edge weights
        for(int[] it : edges) {
            adjMat[it[0]][it[1]] = it[2]; // Set the weight for the direct edge
            adjMat[it[1]][it[0]] = it[2]; // Since the graph is bidirectional, set the reverse edge
        }

        // Step 3: Apply Floyd-Warshall Algorithm to compute the shortest distances between all pairs of cities
        for(int k = 0; k < n; k++) { // Intermediate city
            for(int i = 0; i < n; i++) { // Start city
                for(int j = 0; j < n; j++) { // End city
                    // Update the shortest distance between city i and city j using intermediate city k
                    adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
                }
            }
        }

        // Step 4: Find the city with the minimum number of reachable neighbors
        int minCount = (int)1e9; // Store the minimum count of reachable cities
        int ans = -1; // Store the city with the least number of reachable neighbors

        for(int i = 0; i < n; i++) { // Iterate through each city
            int count = 0; // Counter for cities reachable within the threshold
            for(int j = 0; j < n; j++) { // Check each other city
                if(i != j && adjMat[i][j] <= distanceThreshold) { // If reachable within the threshold
                    count++;
                }
            }

            // Step 5: Update answer if a city with fewer reachable neighbors is found
            if(count < minCount) {
                minCount = count; // Update minimum count
                ans = i; // Update answer to current city
            } else if(count == minCount) { 
                ans = i; // Prefer the city with a higher index in case of a tie
            }
        }

        // Step 6: Return the resulting city
        return ans;
    }
}
```

---

### 🔍 **Step-by-Step Algorithm**
1. **Initialize adjacency matrix** (`adjMat[][]`):  
   - Set all distances to **a large value** (`1e9`) to represent **infinity** (unreachable paths).
  
2. **Fill the adjacency matrix** using the given `edges` list:  
   - Since the graph is **undirected**, update both `adjMat[it[0]][it[1]]` and `adjMat[it[1]][it[0]]`.

3. **Apply Floyd-Warshall Algorithm** to compute **shortest paths** between all city pairs:  
   - Iterate through all **intermediate cities** (`k`).
   - Update the shortest path from **city `i` to `j`** using `k` as an intermediate node.

4. **Find the city with the minimum number of neighbors** within the given `distanceThreshold`:  
   - Iterate through each city and count the number of reachable cities.
   - Keep track of the city with the **minimum** count.
   - If there's a tie, **return the city with the larger index**.

5. **Return the city index** that has the **minimum reachable neighbors**.

---

### 🛠 **Complexity Analysis**
- **Floyd-Warshall Algorithm**: **O(N³)**  
- **Counting neighbors**: **O(N²)**  
- **Total Complexity**: **O(N³) + O(N²) ≈ O(N³)**  

This solution is efficient for **small to medium-sized graphs** (N ≤ 200).  

