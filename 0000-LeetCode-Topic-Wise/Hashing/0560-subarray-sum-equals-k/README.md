<h2><a href="https://leetcode.com/problems/subarray-sum-equals-k">560. Subarray Sum Equals K</a></h2><h3>Medium</h3><hr><p>Given an array of integers <code>nums</code> and an integer <code>k</code>, return <em>the total number of subarrays whose sum equals to</em> <code>k</code>.</p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [1,1,1], k = 2
<strong>Output:</strong> 2
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [1,2,3], k = 3
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= nums[i] &lt;= 1000</code></li>
	<li><code>-10<sup>7</sup> &lt;= k &lt;= 10<sup>7</sup></code></li>
</ul>
Here's the **complete Java solution** to find the number of subarrays with sum equal to `k`, including:

* ✅ **Approach & Intuition**
* 🧠 **Step-by-step algorithm**
* 💡 **Line-by-line commented code**
* 🧪 **Example dry run**
* ⏱ **Time & space complexity analysis**

---

### ✅ Problem Summary

You are given an array `nums[]` and an integer `k`.
Return the number of **contiguous subarrays** whose sum equals to `k`.

---

### ✅ Intuition

We use **prefix sum** and a **HashMap** to store the frequency of cumulative sums.
For each current prefix sum, we check if `(currentSum - k)` exists in the map. If yes, we found such subarrays.

This approach avoids the O(n²) brute-force and reduces it to **O(n)** using a HashMap.

---

### 🧠 Algorithm

1. Initialize:

   * `count = 0` → total subarrays found.
   * `sum = 0` → current prefix sum.
   * `Map<Integer, Integer>` to store prefix sum frequencies. Put `(0, 1)` to handle subarrays starting at index 0.
2. Traverse each element `num` in `nums`:

   * Add `num` to `sum` (prefix sum).
   * Check if `(sum - k)` exists in map → add its frequency to `count`.
   * Update the frequency of `sum` in map.
3. Return `count`.

---

### ✅ Complete Java Code with Comments

```java
import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        // Initialize a map to store prefix sum frequencies
        HashMap<Integer, Integer> prefixSumFreq = new HashMap<>();
        
        prefixSumFreq.put(0, 1); // Base case: sum 0 occurs once (empty prefix)
        
        int sum = 0;    // Cumulative/prefix sum
        int count = 0;  // Number of subarrays found

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // Add current element to prefix sum

            // If (sum - k) exists in map, there exists a subarray ending at i with sum k
            if (prefixSumFreq.containsKey(sum - k)) {
                count += prefixSumFreq.get(sum - k);
            }

            // Update the frequency of current prefix sum in the map
            prefixSumFreq.put(sum, prefixSumFreq.getOrDefault(sum, 0) + 1);
        }

        // Return total count of subarrays with sum == k
        return count;
    }
}
```

---

### 🧪 Dry Run

#### Input: `nums = [1, 1, 1], k = 2`

**Initial State**:
`sum = 0`, `count = 0`, `map = {0: 1}`

| i | nums\[i] | sum | sum-k | map.contains(sum-k)? | count | map after            |
| - | -------- | --- | ----- | -------------------- | ----- | -------------------- |
| 0 | 1        | 1   | -1    | ❌                    | 0     | {0:1, 1:1}           |
| 1 | 1        | 2   | 0     | ✅                    | 1     | {0:1, 1:1, 2:1}      |
| 2 | 1        | 3   | 1     | ✅                    | 2     | {0:1, 1:1, 2:1, 3:1} |

🔚 Final answer: `count = 2`

---

### ⏱ Time & Space Complexity

| Complexity | Value | Reason                                  |
| ---------- | ----- | --------------------------------------- |
| **Time**   | O(n)  | Single pass through the array           |
| **Space**  | O(n)  | HashMap may store up to `n` prefix sums |

---

### ✅ Summary

* The prefix sum + HashMap strategy lets us find subarrays in **linear time**.
* Handles negative numbers and varying values of `k`.
* Works even when subarrays overlap.


