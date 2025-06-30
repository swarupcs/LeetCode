<h2><a href="https://leetcode.com/problems/intersection-of-two-arrays">349. Intersection of Two Arrays</a></h2><h3>Easy</h3><hr><p>Given two integer arrays <code>nums1</code> and <code>nums2</code>, return <em>an array of their <span data-keyword="array-intersection">intersection</span></em>. Each element in the result must be <strong>unique</strong> and you may return the result in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,2,1], nums2 = [2,2]
<strong>Output:</strong> [2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [4,9,5], nums2 = [9,4,9,8,4]
<strong>Output:</strong> [9,4]
<strong>Explanation:</strong> [4,9] is also accepted.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums1.length, nums2.length &lt;= 1000</code></li>
	<li><code>0 &lt;= nums1[i], nums2[i] &lt;= 1000</code></li>
</ul>



---

## ✅ Problem Statement

Given two integer arrays `nums1` and `nums2`, return **an array of their intersection**.
Each element in the result must appear **only once**, and the order of output **does not matter**.

---

## ✅ Approach & Intuition

We can use **HashSet** data structures to efficiently:

1. Track the unique elements from `nums1`
2. Check which elements from `nums2` also exist in `nums1`
3. Collect only unique elements in the result using another `HashSet`

---

## ✅ Algorithm (Step-by-step)

1. Create a HashSet `set1` and insert all elements from `nums1` → this stores unique elements.
2. Traverse `nums2` and for each element:

   * If it exists in `set1`, add it to `resultSet`.
3. Convert `resultSet` to an array and return it.

---

## ✅ Dry Run with Example

### Input:

```java
nums1 = [1, 2, 2, 1]
nums2 = [2, 2]
```

### Step-by-step:

* Step 1: Add `nums1` elements to `set1` → `set1 = {1, 2}`
* Step 2: Check elements in `nums2`:

  * `2` is in `set1` → add to `resultSet` → `{2}`
  * `2` is already in `resultSet`, so no change
* Step 3: Convert `resultSet = {2}` to array → `[2]`

✅ Final Output: `[2]`

---

## ✅ Fully Commented Code with Line-by-Line Explanation

```java
import java.util.*;

class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        // Step 1: Create a HashSet to store unique elements of nums1
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num); // Adds element from nums1 to set1 (duplicates ignored)
        }

        // Step 2: Create another HashSet to store the result (intersection)
        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2) {
            // If num exists in set1, it's part of the intersection
            if (set1.contains(num)) {
                resultSet.add(num); // Only unique elements will be added
            }
        }

        // Step 3: Convert resultSet (HashSet) to int[] array
        int[] result = new int[resultSet.size()];
        int i = 0; // Index for result array
        for (int num : resultSet) {
            result[i++] = num; // Copy each element from set to array
        }

        // Step 4: Return the result array
        return result;
    }
}
```

---

## ✅ Time and Space Complexity Analysis

| Operation             | Complexity | Explanation                           |
| --------------------- | ---------- | ------------------------------------- |
| Time Complexity       | O(n + m)   | One pass through nums1 and nums2 each |
| Space Complexity      | O(n + m)   | Space for `set1` and `resultSet`      |
| `n` = length of nums1 |            |                                       |
| `m` = length of nums2 |            |                                       |

---

## ✅ Summary

| Feature                      | Description                              |
| ---------------------------- | ---------------------------------------- |
| Handles duplicates           | ✅ Yes, uses `HashSet`                    |
| Output uniqueness enforced   | ✅ Yes, through `resultSet`               |
| Input arrays can be unsorted | ✅ Yes                                    |
| Order of output              | ❌ Not preserved (HashSet is unordered)   |
| Efficient for large inputs   | ✅ Yes (linear time + constant space ops) |

---


