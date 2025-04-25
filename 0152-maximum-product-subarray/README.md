<h2><a href="https://leetcode.com/problems/maximum-product-subarray">152. Maximum Product Subarray</a></h2><h3>Medium</h3><hr><p>Given an integer array <code>nums</code>, find a <span data-keyword="subarray-nonempty">subarray</span> that has the largest product, and return <em>the product</em>.</p>

<p>The test cases are generated so that the answer will fit in a <strong>32-bit</strong> integer.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [2,3,-2,4]
<strong>Output:</strong> 6
<strong>Explanation:</strong> [2,3] has the largest product 6.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [-2,0,-1]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The result cannot be 2, because [-2,-1] is not a subarray.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>-10 &lt;= nums[i] &lt;= 10</code></li>
	<li>The product of any subarray of <code>nums</code> is <strong>guaranteed</strong> to fit in a <strong>32-bit</strong> integer.</li>
</ul>


---

### ✅ Intuition

The product of subarrays can drastically change due to:
- **Negative numbers**: Can flip the sign of the product.
- **Zeros**: Reset the product to 0.
- A **negative × negative = positive** → so we must track both **max and min products**.

We track two running products from:
- **Left to right (prefix)**
- **Right to left (suffix)**

This helps us:
- Avoid issues caused by 0s splitting the array.
- Handle subarrays ending at different positions.

---

### ⚙️ Algorithm

1. Initialize:
   - `ans = Integer.MIN_VALUE` → Stores maximum product found.
   - `prefix = 1`, `suffix = 1` → Running products from left & right.

2. Traverse the array from both ends:
   - Multiply current element from left to `prefix`
   - Multiply current element from right to `suffix`
   - If any becomes 0 → reset to 1 (start fresh after zero).
   - Update `ans` as the maximum of: `ans`, `prefix`, and `suffix`.

3. Return `ans`.

---

### ✅ Code with Comments (Step-by-Step)

```java
class Solution {
    // Function to find maximum product subarray
    int maxProduct(int[] arr) {
        int n = arr.length;
        
        // Step 1: Initialize the answer with the smallest possible value
        int ans = Integer.MIN_VALUE;
        
        // Step 2: Initialize prefix and suffix products
        int prefix = 1;
        int suffix = 1;
        
        // Step 3: Traverse from both directions
        for (int i = 0; i < n; i++) {
            
            // Step 4: If prefix or suffix becomes 0, reset it to 1
            // Because product with 0 restarts the subarray
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;
            
            // Step 5: Update prefix (left-to-right product)
            prefix *= arr[i];
            
            // Step 6: Update suffix (right-to-left product)
            suffix *= arr[n - i - 1];
            
            // Step 7: Update the answer with the max of current products
            ans = Math.max(ans, Math.max(prefix, suffix));
        }
        
        // Step 8: Return the maximum product found
        return ans;
    }
}
```

---

### 🧪 Example Dry Run

Input: `[-2, 6, -3, -10, 0, 2]`

| i | arr[i] | arr[n-i-1] | prefix | suffix | ans |
|--|--------|-------------|--------|--------|-----|
| 0 | -2     | 2           | -2     | 2      | 2   |
| 1 | 6      | 0           | -12    | 0      | 2   |
| 2 | -3     | -10         | 36     | -10    | 36  |
| 3 | -10    | -3          | -360   | 30     | 36  |
| 4 | 0      | 6           | 0 → 1  | 180    | 180 |
| 5 | 2      | -2          | 2      | -360   | 180 |

Final `ans = 180`

✅ Subarray: `[6, -3, -10]`  
✅ Product: `6 * -3 * -10 = 180`

---
