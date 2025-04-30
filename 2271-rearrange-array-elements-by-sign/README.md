<h2><a href="https://leetcode.com/problems/rearrange-array-elements-by-sign">2271. Rearrange Array Elements by Sign</a></h2><h3>Medium</h3><hr><p>You are given a <strong>0-indexed</strong> integer array <code>nums</code> of <strong>even</strong> length consisting of an <strong>equal</strong> number of positive and negative integers.</p>

<p>You should return the array of nums such that the the array follows the given conditions:</p>

<ol>
	<li>Every <strong>consecutive pair</strong> of integers have <strong>opposite signs</strong>.</li>
	<li>For all integers with the same sign, the <strong>order</strong> in which they were present in <code>nums</code> is <strong>preserved</strong>.</li>
	<li>The rearranged array begins with a positive integer.</li>
</ol>

<p>Return <em>the modified array after rearranging the elements to satisfy the aforementioned conditions</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,1,-2,-5,2,-4]
<strong>Output:</strong> [3,-2,1,-5,2,-4]
<strong>Explanation:</strong>
The positive integers in nums are [3,1,2]. The negative integers are [-2,-5,-4].
The only possible way to rearrange them such that they satisfy all conditions is [3,-2,1,-5,2,-4].
Other ways such as [1,-2,2,-5,3,-4], [3,1,2,-2,-5,-4], [-2,3,-5,1,-4,2] are incorrect because they do not satisfy one or more conditions.  
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,1]
<strong>Output:</strong> [1,-1]
<strong>Explanation:</strong>
1 is the only positive integer and -1 the only negative integer in nums.
So nums is rearranged to [1,-1].
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= nums.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>nums.length</code> is <strong>even</strong></li>
	<li><code>1 &lt;= |nums[i]| &lt;= 10<sup>5</sup></code></li>
	<li><code>nums</code> consists of <strong>equal</strong> number of positive and negative integers.</li>
</ul>

<p>&nbsp;</p>

---

### ✅ **Approach & Intuition**

We are given an array with equal numbers of positive and negative integers. We must return a new array such that:

- Every **pair of consecutive elements** has **opposite signs**.
- The **order of positives** and **order of negatives** must be **preserved**.
- The result should **start with a positive number**.

### 🧠 **Algorithm**
1. Create a new array `ans` of same size as `nums`.
2. Maintain two pointers:
   - `posIndex = 0` → next index to insert positive numbers.
   - `negIndex = 1` → next index to insert negative numbers.
3. Traverse the original array `nums`:
   - If the element is **positive**, insert it at `posIndex` and increment `posIndex` by 2.
   - If the element is **negative**, insert it at `negIndex` and increment `negIndex` by 2.
4. Return the final rearranged array `ans`.

---

### 🔍 **Dry Run with Example**

**Input:** `nums = [3, 1, -2, -5, 2, -4]`  
**Positives:** `[3, 1, 2]`  
**Negatives:** `[-2, -5, -4]`  

**Steps:**
- `3` → positive → `ans[0] = 3`
- `1` → positive → `ans[2] = 1`
- `-2` → negative → `ans[1] = -2`
- `-5` → negative → `ans[3] = -5`
- `2` → positive → `ans[4] = 2`
- `-4` → negative → `ans[5] = -4`

✅ Final Output: `[3, -2, 1, -5, 2, -4]`

---

### 💡 **Code with Step-by-Step Comments**

```java
class Solution {
    public int[] rearrangeArray(int[] nums) {
        // Step 1: Get the size of the input array
        int n = nums.length;

        // Step 2: Create a new result array of the same size
        int[] ans = new int[n];

        // Step 3: Initialize pointers
        // posIndex starts at 0 (even index) to store positives
        // negIndex starts at 1 (odd index) to store negatives
        int posIndex = 0, negIndex = 1;

        // Step 4: Traverse each number in the input array
        for (int i = 0; i < n; i++) {
            // Step 5a: If number is negative
            if (nums[i] < 0) {
                // Place it at the next available odd index
                ans[negIndex] = nums[i];
                // Move to the next odd index for future negatives
                negIndex += 2;
            } else {
                // Step 5b: If number is positive
                // Place it at the next available even index
                ans[posIndex] = nums[i];
                // Move to the next even index for future positives
                posIndex += 2;
            }
        }

        // Step 6: Return the rearranged result array
        return ans;
    }
}
```

---
