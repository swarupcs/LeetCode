<h2><a href="https://leetcode.com/problems/search-insert-position">35. Search Insert Position</a></h2><h3>Easy</h3><hr><p>Given a sorted array of distinct integers and a target value, return the index if the target is found. If not, return the index where it would be if it were inserted in order.</p>

<p>You must&nbsp;write an algorithm with&nbsp;<code>O(log n)</code> runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,6], target = 5
<strong>Output:</strong> 2
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,6], target = 2
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,5,6], target = 7
<strong>Output:</strong> 4
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt;= nums[i] &lt;= 10<sup>4</sup></code></li>
	<li><code>nums</code> contains <strong>distinct</strong> values sorted in <strong>ascending</strong> order.</li>
	<li><code>-10<sup>4</sup> &lt;= target &lt;= 10<sup>4</sup></code></li>
</ul>

---

✅ **Approach & Intuition**
🧠 **Step-by-step algorithm**
💡 **Line-by-line code explanation**
🧪 **Example dry run**
⏱ **Time & space complexity**

---

### ✅ Problem Summary

You're given a **sorted array of distinct integers** `nums` and a `target` value.
You must return the **index if the target is found**, else return the **index where it would be inserted** to maintain sorted order.

---

### ✅ Intuition

Since the array is **sorted**, we can use **binary search** to find:

* If `target` exists → return its index.
* Else → return the **position where it should be inserted**.

This behaves like `lower_bound()` in C++ STL.

---

### 🧠 Algorithm Steps

1. Initialize `low = 0`, `high = n - 1`, `ans = n`.
2. While `low <= high`:

   * Compute `mid = (low + high) / 2`.
   * If `nums[mid] >= target`:

     * Update `ans = mid` (possible position).
     * Search **left half**.
   * Else:

     * Search **right half**.
3. Return `ans`.

---

### ✅ Java Code with Step-by-Step Comments

```java
import java.util.*;

class Solution {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;

        int ans = n; // Default insert at end if not found

        // Binary search loop
        while (low <= high) {
            int mid = (low + high) / 2; // Calculate mid index

            // If mid element is >= target, it could be the answer
            if (nums[mid] >= target) {
                ans = mid;        // Update answer with current mid
                high = mid - 1;   // Move to left half
            } else {
                // If mid element < target, move to right half
                low = mid + 1;
            }
        }

        // Return final answer (insert position or found index)
        return ans;
    }

    public static void main(String[] args) {
        int[] nums = {1, 3, 5, 6};  // Sorted input array
        int target = 5;             // Target to search

        Solution sol = new Solution(); // Create solution instance
        int index = sol.searchInsert(nums, target); // Call method

        System.out.println("The index is: " + index); // Output result
    }
}
```

---

### 🧪 Dry Run Example

#### Input:

`nums = [1, 3, 5, 6]`, `target = 5`

**Initial state:**
`low = 0`, `high = 3`, `ans = 4`

| Iter | low | high | mid | nums\[mid] | action                      | ans |
| ---- | --- | ---- | --- | ---------- | --------------------------- | --- |
| 1    | 0   | 3    | 1   | 3          | nums\[mid] < target → right | 4   |
| 2    | 2   | 3    | 2   | 5          | nums\[mid] == target → left | 2   |
| 3    | 2   | 1    |     |            | loop ends                   | 2   |

✅ Final result: `index = 2`

---

### ⏱ Time & Space Complexity

| Complexity | Value    | Explanation                   |
| ---------- | -------- | ----------------------------- |
| **Time**   | O(log n) | Binary search on sorted array |
| **Space**  | O(1)     | No extra space used           |

---

### ✅ Summary

* Uses **binary search** to achieve `O(log n)` performance.
* Handles both:

  * **Target present** → return index.
  * **Target absent** → return insert position.
* Similar to **`Arrays.binarySearch()`** behavior in Java with modification.


