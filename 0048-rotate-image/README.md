<h2><a href="https://leetcode.com/problems/rotate-image">48. Rotate Image</a></h2><h3>Medium</h3><hr><p>You are given an <code>n x n</code> 2D <code>matrix</code> representing an image, rotate the image by <strong>90</strong> degrees (clockwise).</p>

<p>You have to rotate the image <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a>, which means you have to modify the input 2D matrix directly. <strong>DO NOT</strong> allocate another 2D matrix and do the rotation.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat1.jpg" style="width: 500px; height: 188px;" />
<pre>
<strong>Input:</strong> matrix = [[1,2,3],[4,5,6],[7,8,9]]
<strong>Output:</strong> [[7,4,1],[8,5,2],[9,6,3]]
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/08/28/mat2.jpg" style="width: 500px; height: 201px;" />
<pre>
<strong>Input:</strong> matrix = [[5,1,9,11],[2,4,8,10],[13,3,6,7],[15,14,12,16]]
<strong>Output:</strong> [[15,13,2,5],[14,3,4,1],[12,6,8,9],[16,7,10,11]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == matrix.length == matrix[i].length</code></li>
	<li><code>1 &lt;= n &lt;= 20</code></li>
	<li><code>-1000 &lt;= matrix[i][j] &lt;= 1000</code></li>
</ul>

---

# ✅ **Approach**
To rotate a matrix by 90 degrees clockwise **in-place** (without using extra space), we can **follow two steps**:
1. **Transpose** the matrix (convert rows into columns).
2. **Reverse** every row individually.

---

# ✅ **Intuition**
- In a **transpose**, we swap `matrix[i][j]` with `matrix[j][i]`, meaning rows become columns.
- After transposing, if you **reverse each row**, it aligns all elements to their correct rotated positions.

**That's why:**  
**Transpose + Reverse Row = 90° Clockwise Rotation**

---

# ✅ **Algorithm**
1. Find the size `n` of the matrix (since it's an N×N square matrix).
2. **Transpose** the matrix:
   - Iterate `i` from 0 to n-1
   - For each `i`, iterate `j` from 0 to i-1 (i.e., `j < i`)
   - Swap `matrix[i][j]` with `matrix[j][i]`
3. **Reverse each row**:
   - For each row:
     - Swap the first element with the last, second with second-last, and so on (till middle).

---

# ✅ **Code (with Step-by-Step Comments)**

```java
class Solution {
    // Rotate the given matrix by 90 degrees clockwise.
    public void rotateMatrix(int[][] matrix) {
        int n = matrix.length; // Step 1: Get the size of the matrix (n x n)

        // Step 2: Transpose the matrix
        for (int i = 0; i < n; i++) { // Iterate over each row
            for (int j = 0; j < i; j++) { // Iterate only over elements before the diagonal
                // Swap elements across the main diagonal (i, j) ↔ (j, i)
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 3: Reverse each row
        for (int i = 0; i < n; i++) { // Iterate over each row
            for (int j = 0; j < n / 2; j++) { // Iterate till half the columns
                // Swap the elements at the start and end of the row
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}
```

---

# ✅ **Dry Run Example**

Input:

```
matrix = [
  [1, 2, 3],
  [4, 5, 6],
  [7, 8, 9]
]
```

### Step 1: **Transpose**
Swap elements across the diagonal:

```
Transpose swaps:
(1,2) → (2,1)
(1,3) → (3,1)
(2,6) → (6,2)
(3,6) → (6,3)

After transpose:
[
  [1, 4, 7],
  [2, 5, 8],
  [3, 6, 9]
]
```

### Step 2: **Reverse each row**

- Reverse row 0: `[1, 4, 7]` → `[7, 4, 1]`
- Reverse row 1: `[2, 5, 8]` → `[8, 5, 2]`
- Reverse row 2: `[3, 6, 9]` → `[9, 6, 3]`

Final Output:

```
[
  [7, 4, 1],
  [8, 5, 2],
  [9, 6, 3]
]
```

✅ Correct!

---

# ✅ **Summary**
- **Transpose** makes rows into columns.
- **Reverse each row** adjusts the order to get a 90° clockwise rotation.
- **No extra space** used (in-place modification).

---
