<h2><a href="https://leetcode.com/problems/find-a-peak-element-ii">2047. Find a Peak Element II</a></h2><h3>Medium</h3><hr><p>A <strong>peak</strong> element in a 2D grid is an element that is <strong>strictly greater</strong> than all of its <strong>adjacent </strong>neighbors to the left, right, top, and bottom.</p>

<p>Given a <strong>0-indexed</strong> <code>m x n</code> matrix <code>mat</code> where <strong>no two adjacent cells are equal</strong>, find <strong>any</strong> peak element <code>mat[i][j]</code> and return <em>the length 2 array </em><code>[i,j]</code>.</p>

<p>You may assume that the entire matrix is surrounded by an <strong>outer perimeter</strong> with the value <code>-1</code> in each cell.</p>

<p>You must write an algorithm that runs in <code>O(m log(n))</code> or <code>O(n log(m))</code> time.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<p><img alt="" src="https://assets.leetcode.com/uploads/2021/06/08/1.png" style="width: 206px; height: 209px;" /></p>

<pre>
<strong>Input:</strong> mat = [[1,4],[3,2]]
<strong>Output:</strong> [0,1]
<strong>Explanation:</strong>&nbsp;Both 3 and 4 are peak elements so [1,0] and [0,1] are both acceptable answers.
</pre>

<p><strong class="example">Example 2:</strong></p>

<p><strong><img alt="" src="https://assets.leetcode.com/uploads/2021/06/07/3.png" style="width: 254px; height: 257px;" /></strong></p>

<pre>
<strong>Input:</strong> mat = [[10,20,15],[21,30,14],[7,16,32]]
<strong>Output:</strong> [1,1]
<strong>Explanation:</strong>&nbsp;Both 30 and 32 are peak elements so [1,1] and [2,2] are both acceptable answers.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == mat.length</code></li>
	<li><code>n == mat[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 500</code></li>
	<li><code>1 &lt;= mat[i][j] &lt;= 10<sup>5</sup></code></li>
	<li>No two adjacent cells are equal.</li>
</ul>


---

## 1. **Intuition & Approach**

- **Goal**: In a matrix (`mat`), find *any* element strictly greater than its four neighbors (up, down, left, right).
- **Constraints**: No two adjacent matrix elements are equal. Problem size can reach 500x500, so brute force O(mn) is too slow.
- **Efficient Solution**: **Binary Search on Columns or Rows**
    - For each chosen column/row, find the global maximum in that column/row.
    - Compare this max element with its adjacent columns/rows.
    - Use binary search to move towards the direction of a larger neighbor (i.e., climb the "hill").
    - Repeat until a peak is found.
- **Complexity**: Binary search on n columns, for each column O(m) scan ⇒ O(m log n) time.

## 2. **Algorithm Steps**

Suppose we use **binary search on columns**:

1. Let `low = 0` and `high = n-1` (for columns).
2. While `low  both neighbors, it's a peak — return `[row, mid]`.
     - If left neighbor > mat[row][mid], move `high = mid - 1` (search left columns).
     - If right neighbor > mat[row][mid], move `low = mid + 1` (search right columns).
   - Repeat until found.
3. If no peak found, return `[-1, -1]` (should not happen given problem constraints).

## 3. **Code: With Line by Line Comments**

```java
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;        // number of rows
        int m = mat[0].length;     // number of columns

        int low = 0;               // leftmost column index
        int high = m - 1;          // rightmost column index

        // Binary search on columns
        while (low = 0 ? mat[row][mid - 1] : Integer.MIN_VALUE;
            // Get value of the right neighbor, or -inf if out of bounds
            int right = (mid + 1)  left && mat[row][mid] > right) {
                // Found a peak
                return new int[]{row, mid};
            } else if (mat[row][mid]  maxVal) {
                maxVal = mat[i][col];
                index = i;
            }
        }
        return index;
    }
}
```

## 4. **Code Explanation (Step by Step / Line by Line)**

### Main Function: `findPeakGrid`

1. `int n = mat.length; int m = mat.length;`
   - Fetch the number of rows and columns from the matrix.
2. `int low = 0, high = m - 1;`
   - Initialize binary search bounds for columns.
3. `while (low  left && mat[row][mid] > right`, return as a peak.
8. If the left neighbor is bigger, move to left columns.
9. If the right neighbor is bigger, move to right columns.
10. If none are found, return `[-1, -1]` (should not occur).

### Helper Function: `maxElement`

- Iterates all rows for a column, keeps track of the maximum value and its row index.
- Returns the row index with the max value.

## 5. **Dry Run Example**

### **Input:**  
`mat = [, , ]`

```
i/j   0   1   2
   -------------
0 | 10  20  15
1 | 21  30  14
2 |  7  16  32
```
- `n = 3`, `m = 3`
- `low = 0`, `high = 2`
- First iteration: `mid = 1`

#### 1. `maxElement(mat, 1)` (col 1)
- Values: 20, 30, 16 → max at row=1, value=30.

- left neighbor: mat=21
- right neighbor: mat=14

Check:  
- 30 > 21 ✔️  
- 30 > 14 ✔️  

Therefore, ** is a peak**.

**Returns:** ``

## 6. **Time and Space Complexity**

### Time Complexity

- Each binary search step splits columns in half (`log n` steps).
- In each step, we scan all rows in the chosen column (`O(m)`).
- **Total:** `O(m * log n)` (for binary search over columns), or reverse for binary search over rows.

### Space Complexity

- `O(1)` if ignoring recursion stack/use (everything is in-place except simple variables).

## 7. **Summary Table**

| Step             | Time      | Notes                    |
|------------------|-----------|--------------------------|
| maxElement call  | O(m)      | Per binary search step   |
| Binary search    | O(log n)  | Over columns             |
| **Total**        | O(m log n)| For the whole algorithm  |
| Space            | O(1)      | In-place, constant extra |

## 8. **Full Annotated Code (Algorithm as Comments)**

```java
class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;        // Number of rows
        int m = mat[0].length;     // Number of columns

        int low = 0;               // Set initial binary search lower bound
        int high = m - 1;          // Set initial binary search upper bound

        // Binary search on columns to find a peak
        while (low = 0 ? mat[row][mid - 1] : Integer.MIN_VALUE;     // left neighbor
            int right = (mid + 1)  left && mat[row][mid] > right) {
                return new int[]{row, mid};  // Return row, column as answer
            } else if (mat[row][mid]  maxVal) {      // Update if larger value found
                maxVal = mat[i][col];
                index = i;
            }
        }
        return index;
    }
}
```

## 9. **In summary**

- The solution finds a peak using binary search over columns (or rows).
- It achieves O(m log n) time; can swap roles to get O(n log m) depending on which is smaller.
- Space is O(1).
- Each binary search step is guided by the biggest neighbor, always moving towards higher values.

