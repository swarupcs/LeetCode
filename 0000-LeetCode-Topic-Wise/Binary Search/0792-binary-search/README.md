<h2><a href="https://leetcode.com/problems/binary-search">792. Binary Search</a></h2><h3>Easy</h3><hr><p>Given an array of integers <code>nums</code> which is sorted in ascending order, and an integer <code>target</code>, write a function to search <code>target</code> in <code>nums</code>. If <code>target</code> exists, then return its index. Otherwise, return <code>-1</code>.</p>

<p>You must write an algorithm with <code>O(log n)</code> runtime complexity.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,0,3,5,9,12], target = 9
<strong>Output:</strong> 4
<strong>Explanation:</strong> 9 exists in nums and its index is 4
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,0,3,5,9,12], target = 2
<strong>Output:</strong> -1
<strong>Explanation:</strong> 2 does not exist in nums so return -1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>4</sup> &lt; nums[i], target &lt; 10<sup>4</sup></code></li>
	<li>All the integers in <code>nums</code> are <strong>unique</strong>.</li>
	<li><code>nums</code> is sorted in ascending order.</li>
</ul>

To search for a target in a sorted array using **binary search** with `O(log n)` time, along with:

* ✅ **Approach & Intuition**
* 🧠 **Step-by-step algorithm**
* 💡 **Line-by-line code explanation**
* 🧪 **Example dry run**
* ⏱ **Time and space complexity**

---

### ✅ Problem Summary

Given a **sorted array** `nums[]` in **ascending order**, and a `target`, return its index using **binary search**.
If not found, return `-1`.

---

### ✅ Intuition

Since the array is **sorted**, we can use **Binary Search**, which repeatedly divides the search space in half and checks whether the middle element is equal to the target.
This gives us **O(log n)** time complexity.

---

### 🧠 Binary Search Algorithm (Recursive)

1. Initialize `low = 0`, `high = n - 1`.
2. Recursively:

   * Find `mid = low + (high - low) / 2`.
   * If `nums[mid] == target`, return `mid`.
   * If `nums[mid] > target`, search in the left half.
   * If `nums[mid] < target`, search in the right half.
3. If `low > high`, return `-1`.

---

### ✅ Completed Code with Step-by-Step Comments

```java
class Solution {
    // Recursive helper function to perform binary search
    private int func(int[] nums, int low, int high, int target) {
        // Base case: if low > high, target not found
        if (low > high) return -1;

        // Calculate mid index to prevent overflow
        int mid = low + (high - low) / 2;

        // If middle element is the target, return its index
        if (nums[mid] == target) return mid;

        // If target is smaller, search in the left half
        else if (nums[mid] > target)
            return func(nums, low, mid - 1, target);

        // If target is greater, search in the right half
        else
            return func(nums, mid + 1, high, target);
    }

    // Public method to start binary search on full array
    public int search(int[] nums, int target) {
        int n = nums.length;
        return func(nums, 0, n - 1, target); // Search in range [0, n-1]
    }
}
```

---

### 🧪 Dry Run Example

#### Input:

```java
nums = [-1, 0, 3, 5, 9, 12]
target = 9
```

#### Trace:

* Initial: `low = 0`, `high = 5`, `mid = 2` → nums\[2] = 3 → 3 < 9 → go right
* New: `low = 3`, `high = 5`, `mid = 4` → nums\[4] = 9 → match ✅

🔚 Return index = 4

---

### ⏱ Time & Space Complexity

| Type       | Value    | Why                                     |
| ---------- | -------- | --------------------------------------- |
| **Time**   | O(log n) | Halves the array each time              |
| **Space**  | O(log n) | Due to recursion stack (depth of log n) |
| (Optional) | O(1)     | If implemented iteratively              |

---

### ✅ Summary

* The binary search is efficient for **sorted arrays**.
* This recursive approach uses **divide-and-conquer** to get O(log n) time.
* Can also be implemented iteratively to reduce space complexity to O(1).


