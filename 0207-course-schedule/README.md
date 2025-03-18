<h2><a href="https://leetcode.com/problems/course-schedule">207. Course Schedule</a></h2><h3>Medium</h3><hr><p>There are a total of <code>numCourses</code> courses you have to take, labeled from <code>0</code> to <code>numCourses - 1</code>. You are given an array <code>prerequisites</code> where <code>prerequisites[i] = [a<sub>i</sub>, b<sub>i</sub>]</code> indicates that you <strong>must</strong> take course <code>b<sub>i</sub></code> first if you want to take course <code>a<sub>i</sub></code>.</p>

<ul>
	<li>For example, the pair <code>[0, 1]</code>, indicates that to take course <code>0</code> you have to first take course <code>1</code>.</li>
</ul>

<p>Return <code>true</code> if you can finish all courses. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0]]
<strong>Output:</strong> true
<strong>Explanation:</strong> There are a total of 2 courses to take. 
To take course 1 you should have finished course 0. So it is possible.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> numCourses = 2, prerequisites = [[1,0],[0,1]]
<strong>Output:</strong> false
<strong>Explanation:</strong> There are a total of 2 courses to take. 
To take course 1 you should have finished course 0, and to take course 0 you should also have finished course 1. So it is impossible.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= numCourses &lt;= 2000</code></li>
	<li><code>0 &lt;= prerequisites.length &lt;= 5000</code></li>
	<li><code>prerequisites[i].length == 2</code></li>
	<li><code>0 &lt;= a<sub>i</sub>, b<sub>i</sub> &lt; numCourses</code></li>
	<li>All the pairs prerequisites[i] are <strong>unique</strong>.</li>
</ul>


## **Approach & Intuition**
The problem requires checking whether all `numCourses` courses can be completed given certain prerequisites. This is a classic **cycle detection problem in a Directed Graph**, where:
- **Nodes** represent courses.
- **Directed edges** represent prerequisite relationships.

### **Key Idea:**
1. **Cycle Detection**: If there is a cycle in the prerequisite graph, it is impossible to complete all courses.
2. **Topological Sorting**: If a valid **topological order** exists, all courses can be taken.

We will use **Kahn’s Algorithm (BFS-based Topological Sorting)** to check for cycles.

---

## **Algorithm**
1. **Build the Graph**: Create an adjacency list representation of the graph.
2. **Compute In-degrees**: Count incoming edges for each node.
3. **Queue Initialization**: Add nodes with **zero in-degree** to a queue.
4. **BFS Traversal (Kahn’s Algorithm)**:
   - Remove a node from the queue.
   - Reduce the in-degree of its neighbors.
   - If a neighbor’s in-degree becomes zero, add it to the queue.
5. **Check Completion**:
   - If all nodes are processed, return **true** (possible to complete all courses).
   - If some nodes remain unprocessed, return **false** (cycle exists).

---

## **Code with Step-by-Step Explanation**
```java
import java.util.*;

class Solution {
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // Step 1: Create adjacency list representation of the graph
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }

        // Step 2: Compute in-degree (number of prerequisites) for each course
        int[] inDegree = new int[numCourses];

        for (int[] prerequisite : prerequisites) {
            int course = prerequisite[0];
            int prerequisiteCourse = prerequisite[1];
            adj.get(prerequisiteCourse).add(course); // Directed edge from prerequisiteCourse -> course
            inDegree[course]++; // Increase in-degree of the course
        }

        // Step 3: Add all courses with zero in-degree to the queue
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (inDegree[i] == 0) {
                queue.add(i);
            }
        }

        // Step 4: Process courses using BFS (Topological Sorting)
        int processedCourses = 0;
        while (!queue.isEmpty()) {
            int currentCourse = queue.poll();
            processedCourses++; // Mark course as processed
            
            // Reduce in-degree of dependent courses
            for (int neighbor : adj.get(currentCourse)) {
                inDegree[neighbor]--; // Reduce in-degree
                
                // If in-degree becomes zero, add to queue
                if (inDegree[neighbor] == 0) {
                    queue.add(neighbor);
                }
            }
        }

        // Step 5: Check if all courses were processed
        return processedCourses == numCourses;
    }

    // Driver code to test the function
    public static void main(String[] args) {
        Solution sol = new Solution();

        int numCourses1 = 2;
        int[][] prerequisites1 = {{1, 0}};
        System.out.println(sol.canFinish(numCourses1, prerequisites1)); // Output: true

        int numCourses2 = 2;
        int[][] prerequisites2 = {{1, 0}, {0, 1}};
        System.out.println(sol.canFinish(numCourses2, prerequisites2)); // Output: false
    }
}
```

---

## **Step-by-Step Explanation with Dry Run**
### **Example 1:**
#### **Input:**
```plaintext
numCourses = 2
prerequisites = [[1,0]]
```
#### **Graph Representation:**
```plaintext
0 → 1
```
#### **Execution:**
1. **Compute In-degrees:**
   - `0`: `0` (No prerequisites)
   - `1`: `1` (Needs `0` first)

2. **Queue Initialization:** `{0}`
3. **Processing BFS:**
   - Remove `0`, process it → `{0}`
   - Decrease in-degree of `1` → `0`
   - Add `1` to queue → `{1}`
   - Remove `1`, process it → `{0,1}`

4. **All courses processed → Return `true`**

---

### **Example 2 (Cycle Case):**
#### **Input:**
```plaintext
numCourses = 2
prerequisites = [[1,0],[0,1]]
```
#### **Graph Representation:**
```plaintext
0 → 1
↑    ↓
1 ← 0
```
#### **Execution:**
1. **Compute In-degrees:**
   - `0`: `1`
   - `1`: `1`

2. **Queue Initialization:** `{}` (empty!)
3. **No nodes to process → Cycle Detected**
4. **Return:** `false`

---

## **Complexity Analysis**
- **Building Graph:** `O(N + E)`
- **Computing In-degree:** `O(N + E)`
- **BFS Traversal:** `O(N + E)`
- **Overall Complexity:** `O(N + E)`, where:
  - `N` = Number of courses
  - `E` = Number of prerequisite pairs

---

## **Conclusion**
- If a **topological sort includes all courses**, it is possible to complete them → **Return `true`**.
- If a **cycle exists**, it is impossible to complete all courses → **Return `false`**.
- This approach efficiently detects cycles using **Kahn’s Algorithm (BFS-based Topological Sorting)**.

