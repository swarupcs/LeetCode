<h2><a href="https://leetcode.com/problems/sort-colors">75. Sort Colors</a></h2><h3>Medium</h3><hr><p>Given an array <code>nums</code> with <code>n</code> objects colored red, white, or blue, sort them <strong><a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in-place</a> </strong>so that objects of the same color are adjacent, with the colors in the order red, white, and blue.</p>

<p>We will use the integers <code>0</code>, <code>1</code>, and <code>2</code> to represent the color red, white, and blue, respectively.</p>

<p>You must solve this problem without using the library&#39;s sort function.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,0,2,1,1,0]
<strong>Output:</strong> [0,0,1,1,2,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,0,1]
<strong>Output:</strong> [0,1,2]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 300</code></li>
	<li><code>nums[i]</code> is either <code>0</code>, <code>1</code>, or <code>2</code>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong>&nbsp;Could you come up with a one-pass algorithm using only&nbsp;constant extra space?</p>


---

## ✅ Problem Summary:

You are given an array `nums` containing only `0`s (red), `1`s (white), and `2`s (blue).  
You need to sort the array **in-place** such that all `0`s come first, then all `1`s, and then all `2`s.

---

## ✅ Intuition:

We want to move all:
- 0s to the front
- 2s to the end
- 1s will naturally settle in the middle

We use **three pointers**:
- `low` → the boundary between 0s and 1s
- `mid` → the current element under examination
- `high` → the boundary between 2s and 1s

---

## ✅ Algorithm Steps:

1. Initialize three pointers:
   - `low = 0`, `mid = 0`, and `high = n - 1`
2. Traverse the array with `mid`:
   - If `nums[mid] == 0`:  
     Swap with `nums[low]`, increment both `low` and `mid`
   - If `nums[mid] == 1`:  
     Move `mid` forward
   - If `nums[mid] == 2`:  
     Swap with `nums[high]`, decrement `high`  
     Don't increment `mid` here, because the swapped value at `mid` needs to be processed

---

## ✅ Java Code with Comments (Algorithm Inline):

```java
class Solution {
    public void sortColors(int[] nums) {
        int low = 0;           // pointer for 0's boundary
        int mid = 0;           // pointer to traverse the array
        int high = nums.length - 1; // pointer for 2's boundary

        // Traverse the array till mid passes high
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap nums[mid] and nums[low]
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;  // increment low as 0 is placed at the front
                mid++;  // increment mid to move to next element
            } else if (nums[mid] == 1) {
                // Just move mid ahead, 1 is in correct position
                mid++;
            } else {
                // nums[mid] == 2
                // Swap nums[mid] and nums[high]
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--; // decrement high as 2 is placed at the end
                // don't increment mid here
            }
        }
    }
}
```

---

## ✅ Dry Run Example

### Input: `[2, 0, 2, 1, 1, 0]`  
Initial pointers: `low = 0`, `mid = 0`, `high = 5`

| Step | mid value | Action                             | Array State         | low | mid | high |
|------|------------|------------------------------------|----------------------|-----|-----|------|
| 1    | 2          | Swap nums[0] and nums[5] (2 ↔ 0)   | `[0, 0, 2, 1, 1, 2]` | 0   | 0   | 4    |
| 2    | 0          | Swap nums[0] and nums[0] (0 ↔ 0)   | `[0, 0, 2, 1, 1, 2]` | 1   | 1   | 4    |
| 3    | 0          | Swap nums[1] and nums[1] (0 ↔ 0)   | `[0, 0, 2, 1, 1, 2]` | 2   | 2   | 4    |
| 4    | 2          | Swap nums[2] and nums[4] (2 ↔ 1)   | `[0, 0, 1, 1, 2, 2]` | 2   | 2   | 3    |
| 5    | 1          | Just move mid                      | `[0, 0, 1, 1, 2, 2]` | 2   | 3   | 3    |
| 6    | 1          | Just move mid                      | `[0, 0, 1, 1, 2, 2]` | 2   | 4   | 3    |

End: mid > high, array sorted ✅

---

## ✅ Final Output:

```java
Input:  [2, 0, 2, 1, 1, 0]
Output: [0, 0, 1, 1, 2, 2]
```

---

## ✅ Time & Space Complexity:

- **Time:** O(n) → One pass through the array
- **Space:** O(1) → In-place sort

---
