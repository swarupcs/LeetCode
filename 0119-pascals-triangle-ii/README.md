<h2><a href="https://leetcode.com/problems/pascals-triangle-ii">119. Pascal's Triangle II</a></h2><h3>Easy</h3><hr><p>Given an integer <code>rowIndex</code>, return the <code>rowIndex<sup>th</sup></code> (<strong>0-indexed</strong>) row of the <strong>Pascal&#39;s triangle</strong>.</p>

<p>In <strong>Pascal&#39;s triangle</strong>, each number is the sum of the two numbers directly above it as shown:</p>
<img alt="" src="https://upload.wikimedia.org/wikipedia/commons/0/0d/PascalTriangleAnimated2.gif" style="height:240px; width:260px" />
<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<pre><strong>Input:</strong> rowIndex = 3
<strong>Output:</strong> [1,3,3,1]
</pre><p><strong class="example">Example 2:</strong></p>
<pre><strong>Input:</strong> rowIndex = 0
<strong>Output:</strong> [1]
</pre><p><strong class="example">Example 3:</strong></p>
<pre><strong>Input:</strong> rowIndex = 1
<strong>Output:</strong> [1,1]
</pre>
<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= rowIndex &lt;= 33</code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you optimize your algorithm to use only <code>O(rowIndex)</code> extra space?</p>



---

### ✅ **Intuition**
Pascal's Triangle is formed such that:
- The first and last elements of each row are `1`.
- Every interior element is the sum of the two elements directly above it.

Instead of building the entire triangle, we only compute the desired row directly, which makes the algorithm more efficient.

---

### ✅ **Approach**
We can generate the Pascal's Triangle row using iterative computation:
- Start with `[1]`.
- For each new row, update values from end to start to avoid overwriting needed values.

---

### ✅ **Algorithm**
1. Initialize a list with `1` (row 0).
2. Loop from `1` to `rowIndex`.
   - For each index from the end to `1`, update `row[i] = row[i] + row[i - 1]`.
   - Append `1` to the end after each row build.
3. Return the final list.

---

### ✅ **Code with Step-by-Step Comments**

```java
class Solution {
    public List<Integer> getRow(int rowIndex) {
        // Step 1: Create an ArrayList to store the result row
        List<Integer> row = new ArrayList<>();
        
        // Step 2: Add the first element which is always 1
        row.add(1);  // row 0: [1]

        // Step 3: Loop from 1 to rowIndex to build each row
        for (int i = 1; i <= rowIndex; i++) {
            // Traverse backwards to avoid overwriting elements
            // Start from the end and move towards the beginning
            for (int j = i - 1; j >= 1; j--) {
                // Update the element using the sum of two numbers above it
                row.set(j, row.get(j) + row.get(j - 1));
            }

            // Step 4: Add 1 at the end of the current row
            row.add(1);
        }

        // Step 5: Return the computed row
        return row;
    }
}
```

---

### ✅ **Example: rowIndex = 3**

**Dry Run:**

1. Start with: `[1]`
2. i = 1 → add 1 → `[1, 1]`
3. i = 2 →  
   - j = 1: `row[1] = 1 + 1 = 2` → `[1, 2]`  
   → add 1 → `[1, 2, 1]`
4. i = 3 →  
   - j = 2: `row[2] = 1 + 2 = 3`  
   - j = 1: `row[1] = 2 + 1 = 3`  
   → `[1, 3, 3]` → add 1 → `[1, 3, 3, 1]`

✅ Final Output: `[1, 3, 3, 1]`

---

### ✅ **Time & Space Complexity**

- **Time:** O(n²) — Due to nested loop (each row has O(i) work)
- **Space:** O(n) — We only store one row

---

