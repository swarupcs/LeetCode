<h2><a href="https://leetcode.com/problems/3sum">15. 3Sum</a></h2><h3>Medium</h3><hr><p>Given an integer array nums, return all the triplets <code>[nums[i], nums[j], nums[k]]</code> such that <code>i != j</code>, <code>i != k</code>, and <code>j != k</code>, and <code>nums[i] + nums[j] + nums[k] == 0</code>.</p>

<p>Notice that the solution set must not contain duplicate triplets.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [-1,0,1,2,-1,-4]
<strong>Output:</strong> [[-1,-1,2],[-1,0,1]]
<strong>Explanation:</strong> 
nums[0] + nums[1] + nums[2] = (-1) + 0 + 1 = 0.
nums[1] + nums[2] + nums[4] = 0 + 1 + (-1) = 0.
nums[0] + nums[3] + nums[4] = (-1) + 2 + (-1) = 0.
The distinct triplets are [-1,0,1] and [-1,-1,2].
Notice that the order of the output and the order of the triplets does not matter.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,1,1]
<strong>Output:</strong> []
<strong>Explanation:</strong> The only possible triplet does not sum up to 0.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,0]
<strong>Output:</strong> [[0,0,0]]
<strong>Explanation:</strong> The only possible triplet sums up to 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>3 &lt;= nums.length &lt;= 3000</code></li>
	<li><code>-10<sup>5</sup> &lt;= nums[i] &lt;= 10<sup>5</sup></code></li>
</ul>




---

# ✅ **Problem:**  
Given an integer array `nums`, return **all unique triplets** `[nums[i], nums[j], nums[k]]` such that:

- `i != j`, `i != k`, `j != k`
- `nums[i] + nums[j] + nums[k] == 0`
- **No duplicate triplets allowed**.

---

# ✨ **Intuition:**

- Sorting the array helps easily **skip duplicates**.
- Use a **two-pointer** technique:  
    - Fix one element and find the other two using two pointers (`left` and `right`) by moving them smartly.
    - If the sum is too small ➔ move `left` forward.  
    - If the sum is too big ➔ move `right` backward.

---

# 🛠️ **Algorithm (Step-by-Step):**

1. Sort the array.
2. Loop `i` from `0` to `n-1`:
   - If `i > 0` and `nums[i] == nums[i-1]`, **skip** to avoid duplicate triplet starting from the same number.
3. For each `i`, set two pointers:
   - `j = i+1` (left pointer)
   - `k = n-1` (right pointer)
4. While `j < k`:
   - Calculate `sum = nums[i] + nums[j] + nums[k]`
   - If `sum == 0`, record the triplet.
     - Move both pointers, and skip duplicates for `nums[j]` and `nums[k]`.
   - If `sum < 0`, move `j++`
   - If `sum > 0`, move `k--`
5. Return the list of triplets.

---

# 📚 **Example Dry Run:**

Input: `nums = [2, -2, 0, 3, -3, 5]`

1. Sort → `[-3, -2, 0, 2, 3, 5]`
2. `i = 0` (`-3`):
   - `j = 1` (`-2`), `k = 5` (`5`) → sum = 0 → triplet `[-3, -2, 5]`
   - Move `j++` and `k--`
   - `j=2` (`0`), `k=4` (`3`) → sum = 0 → triplet `[-3, 0, 3]`
3. `i = 1` (`-2`):
   - `j=2` (`0`), `k=5` (`5`) → sum = 3 > 0 → `k--`
   - `j=2` (`0`), `k=4` (`3`) → sum = 1 > 0 → `k--`
   - `j=2` (`0`), `k=3` (`2`) → sum = 0 → triplet `[-2, 0, 2]`
4. Remaining `i` values will not find new triplets.

✅ Final triplets: `[[-3, -2, 5], [-3, 0, 3], [-2, 0, 2]]`

---

# ✍️ **Java Code with Full Step-by-Step Comments:**

```java
import java.util.*;

class Solution {
    // Function to find all unique triplets that sum to zero
    public List<List<Integer>> threeSum(int[] nums) {
        
        // List to store the final triplets
        List<List<Integer>> ans = new ArrayList<>();
        
        // Get the number of elements
        int n = nums.length;
        
        // Step 1: Sort the array
        Arrays.sort(nums);
        
        // Step 2: Traverse each element as the first number of triplet
        for (int i = 0; i < n; i++) {
            
            // Step 2.1: Skip duplicate first elements to avoid duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1]) continue;
            
            // Step 3: Initialize two pointers
            int j = i + 1;     // Left pointer
            int k = n - 1;     // Right pointer
            
            // Step 4: Find pairs (j, k) such that nums[i] + nums[j] + nums[k] == 0
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];
                
                if (sum < 0) {
                    // Step 4.1: Sum too small ➔ Move left pointer to right
                    j++;
                } else if (sum > 0) {
                    // Step 4.2: Sum too big ➔ Move right pointer to left
                    k--;
                } else {
                    // Step 4.3: Found a triplet
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    ans.add(temp);
                    
                    // Step 5: Move both pointers to next unique elements
                    j++;
                    k--;
                    
                    // Skip duplicate numbers at j
                    while (j < k && nums[j] == nums[j - 1]) j++;
                    
                    // Skip duplicate numbers at k
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }
        
        // Step 6: Return the list of triplets
        return ans;
    }
}

```

---

# 🔥 **Output for Different Inputs**

### Example 1:
Input: `[2, -2, 0, 3, -3, 5]`  
Output: `[[-3, -2, 5], [-3, 0, 3], [-2, 0, 2]]`

### Example 2:
Input: `[2, -1, -1, 3, -1]`  
Output: `[[-1, -1, 2]]`

### Example 3:
Input: `[8, -6, 5, 4]`  
Output: `[]`

---

Awesome! 🔥  
Let’s do a **visual dry run** using **pointer movement diagram** for this input:

---

## 🧩 Input:
```
arr = [-1, 0, 1, 2, -1, -4]
```

### Step 1: Sort the array
```
arr = [-4, -1, -1, 0, 1, 2]
```

---

## 🚀 Dry Run with Visual Pointers:

We'll fix `i`, and use two pointers `j` (left) and `k` (right).

---

### 🔵 i = 0 → arr[i] = -4

| i | j  | k  | arr[i] | arr[j] | arr[k] | sum  | Action                  |
|---|----|----|--------|--------|--------|------|-------------------------|
| 0 | 1  | 5  | -4     | -1     | 2      | -3   | sum < 0 ➔ j++            |
| 0 | 2  | 5  | -4     | -1     | 2      | -3   | sum < 0 ➔ j++            |
| 0 | 3  | 5  | -4     | 0      | 2      | -2   | sum < 0 ➔ j++            |
| 0 | 4  | 5  | -4     | 1      | 2      | -1   | sum < 0 ➔ j++            |
| 0 | 5  | 5  |        |        |        |      | j == k (stop inner loop) |

👉 No triplet found for `i=0`

---

### 🔵 i = 1 → arr[i] = -1

| i | j  | k  | arr[i] | arr[j] | arr[k] | sum  | Action                  |
|---|----|----|--------|--------|--------|------|-------------------------|
| 1 | 2  | 5  | -1     | -1     | 2      | 0    | ✅ Found triplet [-1,-1,2] |
| 1 | 3  | 4  | -1     | 0      | 1      | 0    | ✅ Found triplet [-1,0,1]  |
| 1 | 4  | 3  |        |        |        |      | j > k (stop inner loop)   |

👉 Found two triplets:  
`[-1, -1, 2]` and `[-1, 0, 1]`

---

### 🔵 i = 2 → arr[i] = -1

- Skip this `i` because `arr[i] == arr[i-1]` (duplicate)

---

### 🔵 i = 3 → arr[i] = 0

| i | j  | k  | arr[i] | arr[j] | arr[k] | sum  | Action                  |
|---|----|----|--------|--------|--------|------|-------------------------|
| 3 | 4  | 5  | 0      | 1      | 2      | 3    | sum > 0 ➔ k--            |
| 3 | 4  | 4  |        |        |        |      | j == k (stop inner loop) |

👉 No triplet found for `i=3`

---

### 🔵 i = 4 → arr[i] = 1
- Only 2 elements left ➔ Not enough for a triplet ➔ Skip

### 🔵 i = 5 → arr[i] = 2
- Only 1 element left ➔ Not enough for a triplet ➔ Skip

---

# ✨ Final Triplets:
```
[[-1, -1, 2], [-1, 0, 1]]
```

---

# 📈 Summary Diagram (Flow)

```
Fix i
 |
Sort the array
 |
For each i -> two pointers (j, k)
 |
Move j and k based on sum
 |
Avoid duplicates
 |
Collect triplets
```

---

# ⚡ Visualization Animation Idea (if we animated):

- Pointers j ➡️ and k ⬅️ move towards each other.
- When sum == 0 ➔ 🎯 Lock a triplet and move both.
- If sum < 0 ➔ move j ➡️
- If sum > 0 ➔ move k ⬅️


---

# 📽️ GIF-like Example for:  
Input → `arr = [-1, 0, 1, 2, -1, -4]`

---

## 🎬 **Frame 1: Sorting**
```
Sorted array:
[-4, -1, -1, 0, 1, 2]
```

---

## 🎬 **Frame 2: i = 0 (arr[i] = -4)**

Pointers:
```
 i   j               k
[-4, -1, -1, 0, 1, 2]

sum = -4 + (-1) + 2 = -3
```
Action:  
**sum < 0 ➔ Move j to right**

---

## 🎬 **Frame 3: i = 0, j = 2, k = 5**

Pointers:
```
 i       j           k
[-4, -1, -1, 0, 1, 2]

sum = -4 + (-1) + 2 = -3
```
Action:  
**sum < 0 ➔ Move j to right**

---

## 🎬 **Frame 4: i = 0, j = 3, k = 5**

Pointers:
```
 i           j       k
[-4, -1, -1, 0, 1, 2]

sum = -4 + 0 + 2 = -2
```
Action:  
**sum < 0 ➔ Move j to right**

---

## 🎬 **Frame 5: i = 0, j = 4, k = 5**

Pointers:
```
 i             j     k
[-4, -1, -1, 0, 1, 2]

sum = -4 + 1 + 2 = -1
```
Action:  
**sum < 0 ➔ Move j to right**

---

## 🎬 **Frame 6: j == k (loop ends for i=0)**

---

## 🎬 **Frame 7: i = 1 (arr[i] = -1)**

Pointers:
```
     i   j         k
[-4, -1, -1, 0, 1, 2]

sum = -1 + (-1) + 2 = 0 ✅
```
Action:  
🎯 **Triplet found** → `[-1, -1, 2]`

Move **j++ and k--**

---

## 🎬 **Frame 8: i = 1, j = 3, k = 4**

Pointers:
```
     i       j     k
[-4, -1, -1, 0, 1, 2]

sum = -1 + 0 + 1 = 0 ✅
```
Action:  
🎯 **Triplet found** → `[-1, 0, 1]`

Move **j++ and k--**

---

## 🎬 **Frame 9: j >= k (loop ends for i=1)**

---

## 🎬 **Frame 10: i = 2 (arr[i] = -1)**

Duplicate of previous `-1`, so **skip** to next.

---

## 🎬 **Frame 11: i = 3 (arr[i] = 0)**

Pointers:
```
         i   j   k
[-4, -1, -1, 0, 1, 2]

sum = 0 + 1 + 2 = 3
```
Action:  
**sum > 0 ➔ Move k to left**

---

## 🎬 **Frame 12: j == k (loop ends for i=3)**

---

# ✨ Final Result:
```
[[-1, -1, 2], [-1, 0, 1]]
```

---

# 🎥 Visual Flow Summary:
```
Start with sorted array ➔ Fix i ➔ move j ➡️ and k ⬅️
Whenever sum == 0 ➔ 🎯 Save triplet
Skip duplicates
Done!
```

---

