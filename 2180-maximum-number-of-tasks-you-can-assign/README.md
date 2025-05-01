<h2><a href="https://leetcode.com/problems/maximum-number-of-tasks-you-can-assign">2180. Maximum Number of Tasks You Can Assign</a></h2><h3>Hard</h3><hr><p>You have <code>n</code> tasks and <code>m</code> workers. Each task has a strength requirement stored in a <strong>0-indexed</strong> integer array <code>tasks</code>, with the <code>i<sup>th</sup></code> task requiring <code>tasks[i]</code> strength to complete. The strength of each worker is stored in a <strong>0-indexed</strong> integer array <code>workers</code>, with the <code>j<sup>th</sup></code> worker having <code>workers[j]</code> strength. Each worker can only be assigned to a <strong>single</strong> task and must have a strength <strong>greater than or equal</strong> to the task&#39;s strength requirement (i.e., <code>workers[j] &gt;= tasks[i]</code>).</p>

<p>Additionally, you have <code>pills</code> magical pills that will <strong>increase a worker&#39;s strength</strong> by <code>strength</code>. You can decide which workers receive the magical pills, however, you may only give each worker <strong>at most one</strong> magical pill.</p>

<p>Given the <strong>0-indexed </strong>integer arrays <code>tasks</code> and <code>workers</code> and the integers <code>pills</code> and <code>strength</code>, return <em>the <strong>maximum</strong> number of tasks that can be completed.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> tasks = [<u><strong>3</strong></u>,<u><strong>2</strong></u>,<u><strong>1</strong></u>], workers = [<u><strong>0</strong></u>,<u><strong>3</strong></u>,<u><strong>3</strong></u>], pills = 1, strength = 1
<strong>Output:</strong> 3
<strong>Explanation:</strong>
We can assign the magical pill and tasks as follows:
- Give the magical pill to worker 0.
- Assign worker 0 to task 2 (0 + 1 &gt;= 1)
- Assign worker 1 to task 1 (3 &gt;= 2)
- Assign worker 2 to task 0 (3 &gt;= 3)
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tasks = [<u><strong>5</strong></u>,4], workers = [<u><strong>0</strong></u>,0,0], pills = 1, strength = 5
<strong>Output:</strong> 1
<strong>Explanation:</strong>
We can assign the magical pill and tasks as follows:
- Give the magical pill to worker 0.
- Assign worker 0 to task 0 (0 + 5 &gt;= 5)
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> tasks = [<u><strong>10</strong></u>,<u><strong>15</strong></u>,30], workers = [<u><strong>0</strong></u>,<u><strong>10</strong></u>,10,10,10], pills = 3, strength = 10
<strong>Output:</strong> 2
<strong>Explanation:</strong>
We can assign the magical pills and tasks as follows:
- Give the magical pill to worker 0 and worker 1.
- Assign worker 0 to task 0 (0 + 10 &gt;= 10)
- Assign worker 1 to task 1 (10 + 10 &gt;= 15)
The last pill is not given because it will not make any worker strong enough for the last task.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == tasks.length</code></li>
	<li><code>m == workers.length</code></li>
	<li><code>1 &lt;= n, m &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= pills &lt;= m</code></li>
	<li><code>0 &lt;= tasks[i], workers[j], strength &lt;= 10<sup>9</sup></code></li>
</ul>



---

## ✅ **Problem Summary**
You're given:
- `tasks[i]`: Required strength for each task.
- `workers[j]`: Strength of each worker.
- `pills`: Number of pills you can use.
- `strength`: Boost each pill gives.

**Goal**: Assign the **maximum number of tasks** such that:
- Each task is assigned to a unique worker.
- Worker must satisfy strength requirement (with or without a pill).
- Each worker can take **at most one pill**.
- Maintain **max number of task-worker assignments**.

---

## ✅ **Approach / Intuition**
We use **Binary Search** over the number of tasks we can assign (`mid`), and for each candidate value of `mid`, check if we can assign that many tasks using a **greedy** and **efficient multiset** (TreeMap) strategy:
- Sort both tasks and workers.
- Use a `TreeMap` to simulate assigning the **strongest possible workers** to the **hardest tasks** among the current `mid`.
- Give pills only when necessary.

---

## ✅ **High-Level Algorithm**
1. Sort `tasks` and `workers` in ascending order.
2. Use **binary search** on the answer space: `1` to `min(tasks.length, workers.length)`.
3. For each `mid`, check if we can assign `mid` tasks.
    - Take the `mid` hardest tasks (last `mid` from tasks).
    - Take the `mid` strongest workers (last `mid` from workers).
    - Try to assign each task starting from the hardest to weakest (greedily assign strongest available worker).
    - If the worker isn’t strong enough, check if we can boost a weaker worker with a pill.
    - Use `TreeMap` to store worker strengths (multiset behavior).
4. Return the **maximum valid value** of `mid`.

---

## ✅ **Dry Run Example**

**Input**:
```java
tasks = [3,2,1], workers = [0,3,3], pills = 1, strength = 1
```

Sorted:  
tasks = `[1,2,3]`, workers = `[0,3,3]`

Binary Search space: 1 to 3  
Try mid = 3 → check feasibility:

- Take 3 tasks: `[1,2,3]`
- Take 3 strongest workers: `[0,3,3]` → TreeMap: {0:1, 3:2}
- Assign task 3 → match worker 3 (strength = 3) ✅  
- Task 2 → match other worker 3 ✅  
- Task 1 → no strong enough worker, but use pill on worker 0 → 0+1 = 1 ✅  

→ ✅ All 3 tasks can be assigned

---

## ✅ **Java Code with Line-by-Line Explanation**

```java
class Solution {

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        // Sort both arrays to allow greedy + binary search
        Arrays.sort(tasks);
        Arrays.sort(workers);

        int n = tasks.length, m = workers.length;
        int left = 1, right = Math.min(n, m), ans = 0;

        // Binary search to find max number of tasks we can assign
        while (left <= right) {
            int mid = (left + right) / 2;

            // Check if mid tasks can be assigned with given pills and strength
            if (check(tasks, workers, pills, strength, mid)) {
                ans = mid;         // mid is feasible, try to find more
                left = mid + 1;    // look in the higher half
            } else {
                right = mid - 1;   // mid not feasible, reduce search space
            }
        }
        return ans; // return max number of tasks that can be assigned
    }

    private boolean check(int[] tasks, int[] workers, int pills, int strength, int mid) {
        int p = pills;

        // TreeMap simulates a multiset of workers' strengths for fast search/remove
        TreeMap<Integer, Integer> ws = new TreeMap<>();

        // Add the strongest 'mid' workers into TreeMap
        for (int i = workers.length - mid; i < workers.length; ++i) {
            ws.put(workers[i], ws.getOrDefault(workers[i], 0) + 1);
        }

        // Try to assign the hardest 'mid' tasks to available workers
        for (int i = mid - 1; i >= 0; --i) {
            int task = tasks[i];

            // Try to find the strongest worker available
            Integer key = ws.lastKey();

            if (key >= task) {
                // Directly assign this worker to the task
                decrement(ws, key);
            } else {
                // Worker too weak, try to find a worker that can do it with a pill
                if (p == 0) return false; // no pills left
                key = ws.ceilingKey(task - strength); // min worker that with pill can do the task
                if (key == null) return false;        // no such worker exists
                decrement(ws, key); // assign this worker (using pill)
                --p; // consume one pill
            }
        }

        // All mid tasks successfully assigned
        return true;
    }

    // Helper function to remove a worker from TreeMap (simulate multiset)
    private void decrement(TreeMap<Integer, Integer> map, int key) {
        map.put(key, map.get(key) - 1);
        if (map.get(key) == 0) {
            map.remove(key);
        }
    }
}
```

---

## ✅ Time and Space Complexity

- **Time Complexity**:
  - Sorting: `O(n log n + m log m)`
  - Binary Search: `O(log min(n, m))`
  - For each `mid`, check takes `O(mid log mid)` (due to TreeMap operations)
  - Total: `O((n + m) log(n + m) + log(min(n, m)) * mid log mid)`

- **Space Complexity**:  
  `O(mid)` — for TreeMap used during checking

---
