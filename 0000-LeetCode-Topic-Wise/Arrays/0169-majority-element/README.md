<h2><a href="https://leetcode.com/problems/majority-element">169. Majority Element</a></h2><h3>Easy</h3><hr><p>Given an array <code>nums</code> of size <code>n</code>, return <em>the majority element</em>.</p>

<p>The majority element is the element that appears more than <code>&lfloor;n / 2&rfloor;</code> times. You may assume that the majority element always exists in the array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> nums = [3,2,3]
<strong>Output:</strong> 3
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> nums = [2,2,1,1,1,2,2]
<strong>Output:</strong> 2
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 5 * 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<strong>Follow-up:</strong> Could you solve the problem in linear time and in <code>O(1)</code> space?


Here’s a detailed explanation of the approach, intuition, and step-by-step breakdown of the code along with a dry run.

---

## **Approach**
The problem requires us to find the majority element, which appears more than ⌊n / 2⌋ times in the array.  
We can solve this problem using **Boyer-Moore's Voting Algorithm**, which runs in **O(n) time complexity** and **O(1) space complexity**.

---

## **Intuition**
1. If a number appears more than `n/2` times, it will always be the majority when counting votes.
2. The idea is to maintain a **candidate element** and a **vote count**.
3. If the count is `0`, we choose the current number as the candidate.
4. If the current number is the same as the candidate, we increase the count.
5. Otherwise, we decrease the count.
6. Since the majority element appears more than `n/2` times, it will always remain as the last candidate when the loop completes.

---

## **Algorithm**
1. Initialize `count = 0` and `element = 0` (temporary majority candidate).
2. Iterate through the array:
   - If `count == 0`, set `element = nums[i]` and `count = 1`.
   - If `nums[i] == element`, increment `count`.
   - Else, decrement `count`.
3. After the loop, `element` stores the majority candidate.
4. Verify the candidate by counting occurrences in the array.
5. If it appears more than `n/2` times, return it.
6. Otherwise, return `-1`.

---

## **Code Implementation with Step-by-Step Explanation**
```java
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length; // Get the size of the array
        int count = 0; // Vote count
        int element = 0; // Majority candidate

        // Step 1: Apply Boyer-Moore Voting Algorithm
        for (int i = 0; i < n; i++) {
            if (count == 0) { // If count is 0, pick a new candidate
                count = 1;
                element = nums[i];
            } else if (element == nums[i]) { // If same as candidate, increase count
                count++;
            } else { // Otherwise, decrease count
                count--;
            }
        }

        // Step 2: Verify if 'element' is actually the majority element
        int count1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == element) {
                count1++; // Count occurrences of the candidate
            }
        }

        // Step 3: Check if candidate appears more than n/2 times
        if (count1 > n / 2) {
            return element; // Return majority element
        }

        return -1; // Return -1 if no majority element found (though problem guarantees one exists)
    }
}
```

---

## **Example with Dry Run**
### **Input:**
```java
nums = [2,2,1,1,1,2,2]
```

### **Step-by-step Execution**
| Index | num[i] | element | count | Action |
|--------|--------|---------|-------|---------|
| 0 | 2 | 2 | 1 | First candidate |
| 1 | 2 | 2 | 2 | Increment count |
| 2 | 1 | 2 | 1 | Decrement count |
| 3 | 1 | 2 | 0 | Decrement count (count reaches 0) |
| 4 | 1 | 1 | 1 | New candidate (1) |
| 5 | 2 | 1 | 0 | Decrement count |
| 6 | 2 | 2 | 1 | New candidate (2) |

After the loop, `element = 2`.

Now, count occurrences of `2`:
- `2` appears **4 times** (more than `7/2 = 3.5`).

So, the majority element is `2`.

### **Output:**
```java
2
```

---

## **Time and Space Complexity**
- **Time Complexity:** **O(n)** (since we iterate through the array twice).
- **Space Complexity:** **O(1)** (we use only two extra variables).

This is the most optimal approach to finding the majority element in **linear time with constant space**.


---


Here's a **complete breakdown** of the solution to the **"Majority Element"** problem using the **Boyer-Moore Voting Algorithm**, with:

* ✅ **Approach & Intuition**
* 🧠 **Algorithm**
* 💡 **Line-by-line code explanation**
* 🧪 **Example with dry run**
* ⏱ **Time & Space Complexity analysis**

---

### ✅ Problem Summary

Given an integer array `nums` of size `n`, return the majority element — the one that appears **more than** ⌊n / 2⌋ times.
It is guaranteed that such an element **always exists** in the array.

---

### ✅ Intuition (Boyer-Moore Voting Algorithm)

The **Boyer-Moore Voting Algorithm** is based on a **voting mechanism**:

* Keep a candidate (`element`) for the majority.
* Increase its `count` when the current number matches the candidate.
* Decrease its `count` when the current number does not match.
* If `count` becomes `0`, choose the current number as a new candidate.
* In the end, the candidate will be the majority element.

> Since the majority element always appears more than `n/2` times, it cannot be fully "canceled out" by the other elements.

---

### 🧠 Step-by-Step Algorithm

1. **Initialize `count = 0` and `element = 0`** (placeholder).
2. **Loop through each number** in the array:

   * If `count == 0`, assign the current number as `element`.
   * If the number is the same as `element`, increase `count`.
   * Else, decrease `count`.
3. **After the loop**, verify if `element` occurs more than `n/2` times.
4. **Return** the `element` if it is majority, else return `-1`.

---

### ✅ Java Code with Comments (Step-by-Step Algorithm)

```java
class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length; // Get the size of the array
        int count = 0;       // Vote count for current candidate
        int element = 0;     // Current majority candidate

        // Step 1: Boyer-Moore Voting Algorithm to find a candidate
        for (int i = 0; i < n; i++) {
            if (count == 0) {
                // If count becomes 0, choose new candidate
                element = nums[i];
                count = 1;
            } else if (nums[i] == element) {
                // Same as candidate → increment count
                count++;
            } else {
                // Different from candidate → decrement count
                count--;
            }
        }

        // Step 2: Verify if the candidate is actually the majority element
        int count1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == element) {
                count1++; // Count the occurrence of the candidate
            }
        }

        // Step 3: Check if candidate occurs more than n/2 times
        if (count1 > n / 2) {
            return element; // Valid majority element
        }

        return -1; // Not possible as per problem, but added for completeness
    }
}
```

---

### 🧪 Dry Run Example

**Input:** `nums = [2, 2, 1, 1, 1, 2, 2]`
**Expected Output:** `2`

#### Step 1: Candidate Selection

| i | nums\[i] | count | element | Action                 |
| - | -------- | ----- | ------- | ---------------------- |
| 0 | 2        | 0     | 0       | count = 1, element = 2 |
| 1 | 2        | 1     | 2       | count++ → 2            |
| 2 | 1        | 2     | 2       | count-- → 1            |
| 3 | 1        | 1     | 2       | count-- → 0            |
| 4 | 1        | 0     | 2       | element = 1, count = 1 |
| 5 | 2        | 1     | 1       | count-- → 0            |
| 6 | 2        | 0     | 1       | element = 2, count = 1 |

🔍 Final candidate: `2`

#### Step 2: Verify

Count of `2` = 4 → ✅ greater than `n/2 = 3.5`

**Result:** `2`

---

### ⏱ Time & Space Complexity

| Part  | Complexity | Explanation                                                            |
| ----- | ---------- | ---------------------------------------------------------------------- |
| Time  | `O(n)`     | Two passes through the array: one to find the candidate, one to verify |
| Space | `O(1)`     | Constant space used — no extra arrays or hash maps                     |

---

### ✅ Summary

* **Boyer-Moore Voting Algorithm** gives an efficient linear time and constant space solution.
* It leverages the guarantee that a majority element **always exists**.
* This algorithm is widely used in interview problems related to "majority" or "dominant" elements.


