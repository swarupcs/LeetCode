<h2><a href="https://leetcode.com/problems/maximal-rectangle">85. Maximal Rectangle</a></h2><h3>Hard</h3><hr><p>Given a <code>rows x cols</code>&nbsp;binary <code>matrix</code> filled with <code>0</code>&#39;s and <code>1</code>&#39;s, find the largest rectangle containing only <code>1</code>&#39;s and return <em>its area</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/09/14/maximal.jpg" style="width: 402px; height: 322px;" />
<pre>
<strong>Input:</strong> matrix = [[&quot;1&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;,&quot;0&quot;],[&quot;1&quot;,&quot;0&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;],[&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;,&quot;1&quot;],[&quot;1&quot;,&quot;0&quot;,&quot;0&quot;,&quot;1&quot;,&quot;0&quot;]]
<strong>Output:</strong> 6
<strong>Explanation:</strong> The maximal rectangle is shown in the above picture.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[&quot;0&quot;]]
<strong>Output:</strong> 0
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> matrix = [[&quot;1&quot;]]
<strong>Output:</strong> 1
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>rows == matrix.length</code></li>
	<li><code>cols == matrix[i].length</code></li>
	<li><code>1 &lt;= row, cols &lt;= 200</code></li>
	<li><code>matrix[i][j]</code> is <code>&#39;0&#39;</code> or <code>&#39;1&#39;</code>.</li>
</ul>



---

## 🔍 Problem Summary

Given a binary matrix filled with `'0'`s and `'1'`s, find the **area of the largest rectangle** containing **only 1s**.

---

## 💡 Intuition

* Every row in the matrix can be treated as the **base** of a histogram.
* For every row, if you build up the histogram of heights of continuous 1s column-wise, then you can apply the **Largest Rectangle in Histogram** technique.
* So, we convert the 2D problem into multiple 1D histogram problems (one for each row).

---

## 🧠 Approach

1. For every row, calculate the **height of 1s** for each column (like stacking up 1s).
2. For each updated height row (a histogram), use **stack-based algorithm** to compute the **largest rectangle in histogram**.
3. Keep track of the **maximum area** found so far.

---

## 🧮 Algorithm

1. Initialize a prefix sum matrix for heights.
2. For each column, compute the vertical height of consecutive 1s up to each row.
3. For each row, pass the histogram (array of heights) to the `largestRectangleArea()` function.
4. In `largestRectangleArea()`, use a stack to calculate the area for every bar using:

   * Previous Smaller Element (PSE)
   * Next Smaller Element (NSE)
5. Update and return the maximum rectangle area found.

---

## ✅ Dry Run (on Example 1)

Matrix:

```
[1, 0, 1, 0, 0]  
[1, 0, 1, 1, 1]  
[1, 1, 1, 1, 1]  
[1, 0, 0, 1, 0]
```

Prefix heights:

```
[1, 0, 1, 0, 0]  
[2, 0, 2, 1, 1]  
[3, 1, 3, 2, 2]  
[4, 0, 0, 3, 0]
```

We apply `largestRectangleArea()` on each row:

* Row 0: area = 1
* Row 1: area = 3
* Row 2: **area = 6 (max)**
* Row 3: area = 3

✅ Final result = **6**

---

## 🧾 Java Code with Full Line-by-Line Comments

```java
import java.util.*;

class Solution {

    // Main function to calculate the area of the largest rectangle of 1s
    public int maximalRectangle(char[][] matrix) {
        // Edge case: if matrix is empty
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length;     // number of rows
        int m = matrix[0].length;  // number of columns

        // Step 1: Build a matrix to represent heights of histograms row by row
        int[][] prefixSum = new int[n][m];

        // Traverse every column to compute height of consecutive '1's from top to bottom
        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                // If matrix[i][j] is '0', reset height
                if (matrix[i][j] == '0') {
                    prefixSum[i][j] = 0;
                    sum = 0;
                } else {
                    // If it's '1', increment the height by 1 (from previous row)
                    sum += 1;
                    prefixSum[i][j] = sum;
                }
            }
        }

        int maxArea = 0;

        // Step 2: For every row, treat it as a histogram and compute the largest rectangle
        for (int i = 0; i < n; i++) {
            int area = largestRectangleArea(prefixSum[i]); // Histogram area of current row
            maxArea = Math.max(maxArea, area);             // Update max area if needed
        }

        return maxArea; // Final answer
    }

    // Helper function to calculate largest rectangle in histogram using stack
    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>(); // Stack to keep track of indices
        int largestArea = 0;               // Result variable to store maximum area

        // Step 1: Traverse through all bars
        for (int i = 0; i < n; i++) {
            // Step 1.1: Maintain a monotonic increasing stack
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                int height = heights[st.pop()]; // Get the height at top of stack
                // Width is either full width from 0 to i if stack is empty,
                // or the distance between current index and index at new top of stack
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                int area = height * width; // Calculate area using popped height
                largestArea = Math.max(largestArea, area); // Update max area
            }
            st.push(i); // Push current index into stack
        }

        // Step 2: Clean up any remaining bars in the stack
        while (!st.isEmpty()) {
            int height = heights[st.pop()];
            int width = st.isEmpty() ? n : n - st.peek() - 1;
            int area = height * width;
            largestArea = Math.max(largestArea, area);
        }

        return largestArea; // Return max histogram area
    }
}

```

---

## ✅ Output

```
The largest rectangle area containing all 1s is: 6
```

---

## ✅ Time and Space Complexity

* **Time Complexity**:

  * `O(n * m)` for building prefix heights
  * `O(n * m)` for histogram processing via stack
  * ✅ Overall: **O(n \* m)**

* **Space Complexity**:

  * `O(m)` for stack in `largestRectangleArea`
  * `O(n * m)` for prefix matrix

---

Let me know if you'd like the same code using `char[][] matrix` input as in the Leetcode version.
