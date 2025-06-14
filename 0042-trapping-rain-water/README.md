<h2><a href="https://leetcode.com/problems/trapping-rain-water">42. Trapping Rain Water</a></h2><h3>Hard</h3><hr><p>Given <code>n</code> non-negative integers representing an elevation map where the width of each bar is <code>1</code>, compute how much water it can trap after raining.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img src="https://assets.leetcode.com/uploads/2018/10/22/rainwatertrap.png" style="width: 412px; height: 161px;" />
<pre>
<strong>Input:</strong> height = [0,1,0,2,1,0,1,3,2,1,2,1]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The above elevation map (black section) is represented by array [0,1,0,2,1,0,1,3,2,1,2,1]. In this case, 6 units of rain water (blue section) are being trapped.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> height = [4,2,0,3,2,5]
<strong>Output:</strong> 9
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == height.length</code></li>
	<li><code>1 &lt;= n &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>0 &lt;= height[i] &lt;= 10<sup>5</sup></code></li>
</ul>



---

## ✅ Problem Statement:

Given an array of non-negative integers `height[]` where each element represents the height of a bar in the elevation map, compute how much water it can trap after raining.

---

## 💡 Intuition:

Think of water being trapped between the bars — water will only accumulate where a **taller bar is to the left and right** of the current bar.
At every index `i`, the water trapped depends on:

```
water_at_i = min(maxLeft[i], maxRight[i]) - height[i]
```

But instead of using extra space to store `maxLeft` and `maxRight`, we can do this using two pointers, and dynamically track leftMax and rightMax.

---

## 🔍 Approach (Two-Pointer Technique):

* Use two pointers: `left` (starts at 0) and `right` (starts at end).
* Maintain two variables: `leftMax` and `rightMax`.
* At each step:

  * If `height[left] <= height[right]`, process left side:

    * If `height[left] < leftMax`, water trapped = `leftMax - height[left]`
    * Else update `leftMax = height[left]`
    * Move `left++`
  * Else process right side:

    * If `height[right] < rightMax`, water trapped = `rightMax - height[right]`
    * Else update `rightMax = height[right]`
    * Move `right--`

---

## ✅ Java Code with Step-by-Step Comments:

```java
import java.util.*;

class Solution {

    // Function to calculate trapped rainwater
    public int trap(int[] height) {

        // Step 1: Get the length of the array
        int n = height.length;

        // Step 2: Edge case - if less than 3 bars, no water can be trapped
        if (n < 3) return 0;

        // Step 3: Initialize pointers and tracking variables
        int left = 0;            // Pointer starting from left
        int right = n - 1;       // Pointer starting from right
        int leftMax = 0;         // Highest bar from the left
        int rightMax = 0;        // Highest bar from the right
        int totalWater = 0;      // Total water trapped

        // Step 4: Traverse until pointers meet
        while (left < right) {
            // Step 4.1: Compare heights at left and right
            if (height[left] <= height[right]) {

                // Step 4.2: Water trapping logic for left pointer
                if (height[left] < leftMax) {
                    // Water can be trapped above current bar
                    totalWater += leftMax - height[left];
                } else {
                    // Update leftMax if current is greater
                    leftMax = height[left];
                }
                // Move left pointer to right
                left++;
            } else {

                // Step 4.3: Water trapping logic for right pointer
                if (height[right] < rightMax) {
                    // Water can be trapped above current bar
                    totalWater += rightMax - height[right];
                } else {
                    // Update rightMax if current is greater
                    rightMax = height[right];
                }
                // Move right pointer to left
                right--;
            }
        }

        // Step 5: Return total water trapped
        return totalWater;
    }

}
```

---

## 📜 Step-by-Step Algorithm:

1. Initialize pointers: `left = 0`, `right = n - 1`
2. Initialize `leftMax = 0`, `rightMax = 0`, `totalWater = 0`
3. While `left < right`:

   * If `height[left] <= height[right]`:

     * If `height[left] < leftMax`: add `leftMax - height[left]` to `totalWater`
     * Else: update `leftMax = height[left]`
     * Increment `left`
   * Else:

     * If `height[right] < rightMax`: add `rightMax - height[right]` to `totalWater`
     * Else: update `rightMax = height[right]`
     * Decrement `right`
4. Return `totalWater`

---

## 🧪 Dry Run Example:

Input: `height = [0,1,0,2,1,0,1,3,2,1,2,1]`
Expected Output: `6`

```
left=0, right=11
leftMax=0, rightMax=0

Step 1: height[left]=0 <= height[right]=1
   leftMax = max(0, 0) = 0
   water = 0
   left++

Step 2: height[left]=1 <= height[right]=1
   leftMax = max(0, 1) = 1
   water = 0
   left++

Step 3: height[left]=0
   water = 1 - 0 = 1
   total = 1
   left++

Step 4: height[left]=2
   leftMax = max(1, 2) = 2
   water = 0
   left++

Step 5: height[left]=1
   water = 2 - 1 = 1
   total = 2
   left++

Step 6: height[left]=0
   water = 2 - 0 = 2
   total = 4
   left++

Step 7: height[left]=1
   water = 2 - 1 = 1
   total = 5
   left++

Step 8: height[left]=3
   leftMax = max(2, 3) = 3
   water = 0
   left++

Step 9: height[left]=2
   water = 3 - 2 = 1
   total = 6
   left++

Now left >= right → Stop

✅ Final total water = 6
```

---
