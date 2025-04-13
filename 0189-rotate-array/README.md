<h2><a href="https://leetcode.com/problems/rotate-array/description/">189. Rotate Array</a></h2><h3>Medium</h3><hr><p>Given an integer array <code>nums</code>, rotate the array to the right by <code>k</code> steps, where <code>k</code> is non-negative.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,5,6,7], k = 3
<strong>Output:</strong> [5,6,7,1,2,3,4]
<strong>Explanation:</strong>
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,-100,3,99], k = 2
<strong>Output:</strong> [3,99,-1,-100]
<strong>Explanation:</strong> 
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>5</sup></code></li>
	<li><code>-2<sup>31</sup> &lt;= nums[i] &lt;= 2<sup>31</sup> - 1</code></li>
	<li><code>0 &lt;= k &lt;= 10<sup>5</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong></p>

<ul>
	<li>Try to come up with as many solutions as you can. There are at least <strong>three</strong> different ways to solve this problem.</li>
	<li>Could you do it in-place with <code>O(1)</code> extra space?</li>
</ul>



---

## ✅ Problem Statement

You are given an array `nums` and an integer `k`. Rotate the array to the **right** by `k` steps.

---

## 💡 Intuition

To rotate an array to the right by `k` steps, the last `k` elements need to come to the front, and the remaining elements need to shift right.

But shifting elements one by one is **inefficient (O(n × k))**. So instead, we can use a **reversal trick** that does it in **O(n)** time and **O(1)** space.

---

## 🔍 Approach

We use the **reverse algorithm**. Here's the idea:

1. **Reverse the entire array.**
2. **Reverse the first `k` elements.**
3. **Reverse the remaining `n - k` elements.**

This smart trick repositions the elements exactly as needed for a right rotation.

---

## 🧠 Why This Works?

Reversing the whole array brings the last `k` elements to the front (but in reverse order). Then we reverse those `k` elements to get them in correct order. Similarly, the remaining elements are reversed to restore their order.

---

## 📋 Algorithm

1. Let `n = nums.length`
2. Set `k = k % n` to handle cases where `k > n`
3. Reverse the **entire array** from index `0` to `n-1`
4. Reverse the **first `k` elements** from index `0` to `k-1`
5. Reverse the **rest** from index `k` to `n-1`

---

## ✅ Java Code with Comments (Algorithm Steps)

```java
class Solution {
    // Step 0: Helper method to reverse part of the array
    private void reverseArray(int[] nums, int start, int end) {
        while (start < end) {
            // Swap elements at start and end
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }

    public void rotate(int[] nums, int k) {
        int n = nums.length;

        // Step 1: In case k > n, we take mod
        k = k % n;

        // Step 2: Reverse the entire array
        reverseArray(nums, 0, n - 1);

        // Step 3: Reverse the first k elements
        reverseArray(nums, 0, k - 1);

        // Step 4: Reverse the rest of the elements
        reverseArray(nums, k, n - 1);
    }
}
```

---

## 🧪 Dry Run with Example

**Input:** `nums = [1, 2, 3, 4, 5, 6, 7]`, `k = 3`

Let’s go through it step-by-step:

### Step 1: `k = k % n = 3 % 7 = 3`

---

### Step 2: Reverse entire array  
Original:  
```
[1, 2, 3, 4, 5, 6, 7]
```

After reversing from `0` to `6`:
```
[7, 6, 5, 4, 3, 2, 1]
```

---

### Step 3: Reverse first `k=3` elements  
Reverse from `0` to `2`:
```
[5, 6, 7, 4, 3, 2, 1]
```

---

### Step 4: Reverse remaining `n-k=4` elements  
Reverse from `3` to `6`:
```
[5, 6, 7, 1, 2, 3, 4]
```

✅ Final Output: `[5, 6, 7, 1, 2, 3, 4]`

---

## 📌 Explanation Line by Line

### `int n = nums.length;`
- Stores the length of the array.

### `k = k % n;`
- Handles cases where `k` is larger than `n`, e.g., rotating by 10 steps in an array of size 3.

### `reverseArray(nums, 0, n - 1);`
- Reverses the whole array to bring the last `k` elements to the front (in reverse order).

### `reverseArray(nums, 0, k - 1);`
- Fixes the order of the first `k` elements.

### `reverseArray(nums, k, n - 1);`
- Fixes the order of the remaining `n-k` elements.

---

## ✅ Time & Space Complexity

- **Time:** `O(n)` → We reverse the array 3 times.
- **Space:** `O(1)` → No extra space used.

---

