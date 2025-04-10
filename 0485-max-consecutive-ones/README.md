<h2><a href="https://leetcode.com/problems/max-consecutive-ones">485. Max Consecutive Ones</a></h2><h3>Easy</h3><hr><p>Given a binary array <code>nums</code>, return <em>the maximum number of consecutive </em><code>1</code><em>&#39;s in the array</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,0,1,1,1]
<strong>Output:</strong> 3
<strong>Explanation:</strong> The first two digits or the last three digits are consecutive 1s. The maximum number of consecutive 1s is 3.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,0,1,1,0,1]
<strong>Output:</strong> 2
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>nums[i]</code> is either <code>0</code> or <code>1</code>.</li>
</ul>


### **🔍 Problem Statement**
Given a binary array `nums`, find the **maximum number of consecutive 1s** in the array.

---

## **💡 Intuition**
1. We need to find the longest sequence of consecutive `1`s in the array.
2. We traverse the array while maintaining a **count** of consecutive `1`s.
3. When we encounter a `0`, we reset the count.
4. At each step, we **update the maximum count** of `1`s found so far.
5. The result is the largest streak of `1`s recorded during traversal.

---

## **🛠 Approach**
- We **initialize** two variables:
  - `count` → Keeps track of the **current** streak of `1`s.
  - `maximum` → Stores the **maximum** streak of `1`s found so far.
- Traverse the array:
  - If `nums[i] == 1`, increment `count` and update `maximum`.
  - If `nums[i] == 0`, reset `count` to `0`.
- Return `maximum` at the end.

---

## **📌 Algorithm**
1. **Initialize `count = 0`** and **`maximum = 0`**.
2. **Iterate** through the array `nums`:
   - If `nums[i] == 1`:
     - Increment `count`
     - Update `maximum = max(count, maximum)`
   - Else (`nums[i] == 0`):
     - Reset `count = 0`
3. **Return `maximum`**.

---

## **✅ Java Code with Step-by-Step Comments**
```java
class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        // Step 1: Initialize variables
        int count = 0;     // Tracks the current number of consecutive 1s
        int maximum = 0;   // Stores the maximum count of consecutive 1s found so far

        // Step 2: Iterate through the array
        for(int i = 0; i < nums.length; i++) {
            // Step 3: Check if the current element is 1
            if(nums[i] == 1) {
                count++;  // Increase the count of consecutive 1s
                maximum = Math.max(count, maximum);  // Update maximum if needed
            } else {
                count = 0;  // Step 4: Reset count to 0 when encountering a 0
            }
        }

        // Step 5: Return the maximum consecutive 1s found
        return maximum;
    }
}
```

---

## **🔄 Dry Run**
Let's take **Example 1**:
```java
nums = [1, 1, 0, 1, 1, 1]
```

| Iteration | `i` | `nums[i]` | `count` (before) | Condition (`nums[i] == 1`?) | `count` (after) | `maximum` |
|-----------|----|----------|----------------|--------------------------|----------------|----------|
| 1st       | 0  | 1        | 0              | ✅ Yes                      | 1              | 1        |
| 2nd       | 1  | 1        | 1              | ✅ Yes                      | 2              | 2        |
| 3rd       | 2  | 0        | 2              | ❌ No                       | 0              | 2        |
| 4th       | 3  | 1        | 0              | ✅ Yes                      | 1              | 2        |
| 5th       | 4  | 1        | 1              | ✅ Yes                      | 2              | 2        |
| 6th       | 5  | 1        | 2              | ✅ Yes                      | 3              | 3        |

**Final Output:** `3`

---

## **⏳ Time & Space Complexity**
- **Time Complexity**: **\(O(n)\)** → The loop runs once for all `n` elements.
- **Space Complexity**: **\(O(1)\)** → Uses only two integer variables (`count`, `maximum`).

---

## **🔹 Summary**
✅ **Simple & efficient**  
✅ **Single pass solution**  
✅ **No extra space required**  
✅ **Handles all cases (all 1s, all 0s, mixed cases, single element cases)**  
