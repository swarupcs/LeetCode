<h2><a href="https://leetcode.com/problems/course-schedule-ii">210. Course Schedule II</a></h2><h3>Medium</h3><hr><p>There are a total of <code>numCourses</code> courses you have to take, labeled from <code>0</code> to <code>numCourses - 1</code>. You are given an array <code>prerequisites</code> where <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that you <strong>must</strong> take course <code>b<sub>i</sub></code> first if you want to take course <code>a<sub>i</sub></code>.</p>

<ul>
	<li>For example, the pair <code>[0, 1]</code>, indicates that to take course <code>0</code> you have to first take course <code>1</code>.</li>
</ul>

<p>Return <em>the ordering of courses you should take to finish all courses</em>. If there are many valid answers, return <strong>any</strong> of them. If it is impossible to finish all courses, return <strong>an empty array</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0]]
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong> There are a total of 2 courses to take. To take course 1 you should have finished course 0. So the correct course order is [0,1].
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 4, prerequisites = [[1,0],[2,0],[3,1],[3,2]]
<strong>Output:</strong> [0,2,1,3]
<strong>Explanation:</strong> There are a total of 4 courses to take. To take course 3 you should have finished both courses 1 and 2. Both courses 1 and 2 should be taken after you finished course 0.
So one correct course order is [0,1,2,3]. Another correct ordering is [0,2,1,3].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 1, prerequisites = []
<strong>Output:</strong> [0]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 2000</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= numCourses * (numCourses - 1)</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
	<li><code>a<sub>i</sub> != b<sub>i</sub></code></li>
	<li>All the pairs <code>[a<sub>i</sub>, b<sub>i</sub>]</code> are <strong>distinct</strong>.</li>
</ul>



---

## ✅ **Approach & Intuition:**

This is a classic **Topological Sorting** problem of a **Directed Graph**.

- Each course is a **node** in the graph.
- If to take course `a` you must take course `b` first, we create a **directed edge** from `b → a`.
- The goal is to return a **valid topological order** of all the courses.
- If there is a **cycle**, then it’s **impossible** to finish all courses (i.e., no valid topological ordering exists).

We’ll use **Kahn’s Algorithm (BFS-based topological sort)** to solve this problem.

---

## 🧠 **Algorithm:**

1. **Create an adjacency list** to represent the graph.
2. **Create an array `inDegree[]`** to count incoming edges for each node.
3. **Initialize a queue** with all nodes having `inDegree 0` (they can be taken first).
4. **Process the queue:**
   - Take a node, add it to result.
   - For each neighbor, decrease its in-degree.
   - If in-degree becomes zero, add it to the queue.
5. If the result length is equal to the number of courses, return the result.
6. Else, return empty array (cycle detected).

---

## 🧪 Example:  
### Input:
```java
numCourses = 4  
prerequisites = [[1, 0], [2, 0], [3, 1], [3, 2]]
```

### Explanation:
Edges:  
`0 → 1`, `0 → 2`, `1 → 3`, `2 → 3`

**Valid Orders:**  
- `[0,1,2,3]`
- `[0,2,1,3]`

---

## ✅ Full Java Code with Line-by-Line Comments:
```java
class Solution {

    // Function to perform topological sort using Kahn's Algorithm (BFS-based)
    private int[] topoSort(int N, List<Integer>[] adj) {
        // Step 1: Create an array to store the in-degree of each node (course)
        int[] inDegree = new int[N];

        // Step 2: Fill the in-degree array by iterating over the adjacency list
        for (int i = 0; i < N; i++) {
            for (int it : adj[i]) {
                inDegree[it]++; // Increase in-degree for the destination node
            }
        }

        // Step 3: Initialize a queue to keep track of nodes with in-degree 0 (no prerequisites)
        Queue<Integer> queue = new LinkedList<>();

        // This array will store the final topological order (course order)
        int[] ans = new int[N];
        int idx = 0; // Index for inserting into the ans array

        // Step 4: Add all courses with in-degree 0 to the queue (can be taken first)
        for (int i = 0; i < N; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 5: Process the queue using BFS
        while (!queue.isEmpty()) {
            int node = queue.poll(); // Get the next course with no remaining prerequisites
            ans[idx++] = node;       // Add it to the course order

            // Step 6: For each neighbor of this course, reduce their in-degree by 1
            for (int it : adj[node]) {
                inDegree[it]--;

                // If in-degree becomes 0, it means all prerequisites are satisfied, so add to queue
                if (inDegree[it] == 0) {
                    queue.add(it);
                }
            }
        }

        // Step 7: Return only the valid portion of ans[] (up to idx)
        // If a cycle exists, idx will be less than N
        return Arrays.copyOfRange(ans, 0, idx);
    }

    // Main function to find a valid order to take all courses
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // Step 1: Create the adjacency list for the graph
        List<Integer>[] adj = new ArrayList[numCourses];

        // Initialize each list inside the array
        for (int i = 0; i < numCourses; i++) {
            adj[i] = new ArrayList<>();
        }

        // Step 2: Populate the graph using the prerequisites
        // Each pair [a, b] means an edge from b to a (b → a)
        for (int[] it : prerequisites) {
            int u = it[0]; // course a
            int v = it[1]; // prerequisite b
            adj[v].add(u); // Add edge from b to a
        }

        // Step 3: Call topoSort to get the course order
        int[] topo = topoSort(numCourses, adj);

        // Step 4: If topo.length < numCourses, a cycle exists, return empty array
        if (topo.length < numCourses) return new int[0];

        // Step 5: Return the valid course ordering
        return topo;
    }
}

```

---

## 🔁 Dry Run: (Example 2)
### Input:
```java
numCourses = 4  
prerequisites = [[1,0],[2,0],[3,1],[3,2]]
```

### Adjacency List:
```
0: [1, 2]
1: [3]
2: [3]
3: []
```

### inDegree Array:
```
[0,1,1,2]
```

### Queue Initialization:
```
queue = [0] // only course 0 has inDegree 0
```

### BFS Process:
- Pop 0 → ans = [0] → reduce inDegree[1,2] → queue = [1,2]
- Pop 1 → ans = [0,1] → reduce inDegree[3] = 1
- Pop 2 → ans = [0,1,2] → reduce inDegree[3] = 0 → queue = [3]
- Pop 3 → ans = [0,1,2,3]

✅ Done.

---

