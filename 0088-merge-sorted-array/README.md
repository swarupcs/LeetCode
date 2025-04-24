<h2><a href="https://leetcode.com/problems/merge-sorted-array">88. Merge Sorted Array</a></h2><h3>Easy</h3><hr><p>You are given two integer arrays <code>nums1</code> and <code>nums2</code>, sorted in <strong>non-decreasing order</strong>, and two integers <code>m</code> and <code>n</code>, representing the number of elements in <code>nums1</code> and <code>nums2</code> respectively.</p>

<p><strong>Merge</strong> <code>nums1</code> and <code>nums2</code> into a single array sorted in <strong>non-decreasing order</strong>.</p>

<p>The final sorted array should not be returned by the function, but instead be <em>stored inside the array </em><code>nums1</code>. To accommodate this, <code>nums1</code> has a length of <code>m + n</code>, where the first <code>m</code> elements denote the elements that should be merged, and the last <code>n</code> elements are set to <code>0</code> and should be ignored. <code>nums2</code> has a length of <code>n</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1,2,3,0,0,0], m = 3, nums2 = [2,5,6], n = 3
<strong>Output:</strong> [1,2,2,3,5,6]
<strong>Explanation:</strong> The arrays we are merging are [1,2,3] and [2,5,6].
The result of the merge is [<u>1</u>,<u>2</u>,2,<u>3</u>,5,6] with the underlined elements coming from nums1.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [1], m = 1, nums2 = [], n = 0
<strong>Output:</strong> [1]
<strong>Explanation:</strong> The arrays we are merging are [1] and [].
The result of the merge is [1].
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums1 = [0], m = 0, nums2 = [1], n = 1
<strong>Output:</strong> [1]
<strong>Explanation:</strong> The arrays we are merging are [] and [1].
The result of the merge is [1].
Note that because m = 0, there are no elements in nums1. The 0 is only there to ensure the merge result can fit in nums1.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>nums1.length == m + n</code></li>
	<li><code>nums2.length == n</code></li>
	<li><code>0 &lt;= m, n &lt;= 200</code></li>
	<li><code>1 &lt;= m + n &lt;= 200</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums1[i], nums2[j] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up: </strong>Can you come up with an algorithm that runs in <code>O(m + n)</code> time?</p>




---

## ✅ Approach & Intuition

We are given two **sorted arrays**:  
- `nums1` with a size of `m + n`, where the first `m` elements are valid, and the last `n` are `0` placeholders.
- `nums2` of size `n`.

Our goal is to merge them into `nums1` **in-place** so that the result is also **sorted**.

### 🔥 Key Idea:
Start **merging from the end** of the arrays to avoid overwriting elements in `nums1`. This gives us an **O(m + n)** time and **O(1)** space solution.

---

## 🧠 Algorithm

1. Set `i = m - 1` (last valid element in nums1).
2. Set `j = n - 1` (last element in nums2).
3. Set `k = m + n - 1` (last index of nums1).
4. Compare `nums1[i]` and `nums2[j]`. Place the **larger one** at `nums1[k]` and move pointers accordingly.
5. Repeat until either `i` or `j` is exhausted.
6. If elements remain in `nums2`, copy them into `nums1`.

---

## 💻 Code with Step-by-Step Algorithm Comments

```java
class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Step 1: Initialize pointers
        int i = m - 1;         // Pointer for last element in nums1's actual data
        int j = n - 1;         // Pointer for last element in nums2
        int k = m + n - 1;     // Pointer for the last index in nums1

        // Step 2: Merge from the end
        while (i >= 0 && j >= 0) {
            // Step 3: Place the larger value at the end of nums1
            if (nums1[i] > nums2[j]) {
                nums1[k] = nums1[i];
                i--;
            } else {
                nums1[k] = nums2[j];
                j--;
            }
            k--; // Move the position backward
        }

        // Step 4: If nums2 has remaining elements, copy them
        // (No need to copy nums1 leftovers, they are already in place)
        while (j >= 0) {
            nums1[k] = nums2[j];
            j--;
            k--;
        }
    }
}
```

---

## 🔍 Example + Dry Run

### Input:
```java
nums1 = [1,2,3,0,0,0], m = 3
nums2 = [2,5,6],       n = 3
```

### Initial pointers:
```
i = 2 (points to 3)
j = 2 (points to 6)
k = 5 (points to end of nums1)
```

### Step-by-step:
```
nums1[5] = max(3, 6) = 6  → nums1 = [1,2,3,0,0,6]   j--, k--
nums1[4] = max(3, 5) = 5  → nums1 = [1,2,3,0,5,6]   j--, k--
nums1[3] = max(3, 2) = 3  → nums1 = [1,2,3,3,5,6]   i--, k--
nums1[2] = max(2, 2) = 2  → nums1 = [1,2,2,3,5,6]   j--, k--
nums1[1] = 1 (leftover)   → Already in place
```

### Final Output:
```
nums1 = [1,2,2,3,5,6]
```

---

