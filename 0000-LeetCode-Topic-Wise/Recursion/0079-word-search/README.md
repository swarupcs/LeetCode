<h2><a href="https://leetcode.com/problems/word-search">79. Word Search</a></h2><h3>Medium</h3><hr><p>Given an <code>m x n</code> grid of characters <code>board</code> and a string <code>word</code>, return <code>true</code> <em>if</em> <code>word</code> <em>exists in the grid</em>.</p>

<p>The word can be constructed from letters of sequentially adjacent cells, where adjacent cells are horizontally or vertically neighboring. The same letter cell may not be used more than once.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word2.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;ABCCED&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/04/word-1.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;SEE&quot;
<strong>Output:</strong> true
</pre>

<p><strong class="example">Example 3:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/10/15/word3.jpg" style="width: 322px; height: 242px;" />
<pre>
<strong>Input:</strong> board = [[&quot;A&quot;,&quot;B&quot;,&quot;C&quot;,&quot;E&quot;],[&quot;S&quot;,&quot;F&quot;,&quot;C&quot;,&quot;S&quot;],[&quot;A&quot;,&quot;D&quot;,&quot;E&quot;,&quot;E&quot;]], word = &quot;ABCB&quot;
<strong>Output:</strong> false
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>m == board.length</code></li>
	<li><code>n = board[i].length</code></li>
	<li><code>1 &lt;= m, n &lt;= 6</code></li>
	<li><code>1 &lt;= word.length &lt;= 15</code></li>
	<li><code>board</code> and <code>word</code> consists of only lowercase and uppercase English letters.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you use search pruning to make your solution faster with a larger <code>board</code>?</p>



---

## 🔍 Problem Summary:

Given a 2D grid of characters and a word, return `true` if the word exists in the grid. The word can be constructed from **adjacent** cells (horizontal or vertical), and each cell **can be used only once** in a path.

---

## 🧠 Intuition:

This is a classic **backtracking problem**. For each cell that matches the **first character** of the word, we try to explore all four directions recursively to see if we can form the entire word.

We must make sure not to revisit a cell in the current path, so we **temporarily mark the cell** (e.g., with a space `' '`) and restore it afterward (backtrack).

---

## 🧭 Approach:

1. Iterate through each cell in the grid.
2. If the character matches the first character of the word, start a DFS (backtracking).
3. In the DFS:

   * If all characters of the word are matched, return `true`.
   * Check boundary conditions and whether the current character matches.
   * Mark the cell as visited.
   * Explore all 4 directions.
   * Backtrack (unmark the cell).
4. If no path returns `true`, return `false`.

---

## ✅ Algorithm (Step-by-step):

```
1. For each cell (i, j) in board:
   a. If board[i][j] == word[0], call recursive function with k = 0.
2. In recursive function (func):
   a. If k == word.length(), return true.
   b. If i or j is out of bounds or board[i][j] != word[k], return false.
   c. Mark cell as visited (e.g., board[i][j] = ' ').
   d. Recursively check all four directions (up, down, left, right).
   e. Restore the original character (backtrack).
   f. Return true if any recursive call returns true.
3. If all cells are tried and no match, return false.
```

---

## ✅ Java Code with Line-by-Line Comments:

```java
class Solution {

    // Recursive function to check if the word can be formed starting from (i, j)
    private boolean func(char[][] board, int i, int j, String word, int k) {
        // Base case: if all characters are matched
        if (k == word.length()) return true;

        // Check boundaries and character mismatch
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(k)) {
            return false;
        }

        // Store current character and mark cell as visited
        char temp = board[i][j];
        board[i][j] = ' '; // mark as visited

        // Recursive DFS: check all 4 directions
        boolean found = func(board, i + 1, j, word, k + 1) ||  // down
                        func(board, i - 1, j, word, k + 1) ||  // up
                        func(board, i, j + 1, word, k + 1) ||  // right
                        func(board, i, j - 1, word, k + 1);    // left

        // Backtrack: restore original character
        board[i][j] = temp;

        return found;
    }

    // Main function to check if the word exists in the grid
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // Try every cell as starting point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If first character matches, start DFS
                if (board[i][j] == word.charAt(0)) {
                    if (func(board, i, j, word, 0)) return true;
                }
            }
        }

        // If not found
        return false;
    }
}
```

---

## 🧪 Dry Run:

### Example:

**Input:**

```java
board = {
  {'A','B','C','E'},
  {'S','F','C','S'},
  {'A','D','E','E'}
}
word = "ABCCED"
```

### Steps:

1. Start at (0,0) = 'A' → matches word\[0]
2. Move right → (0,1) = 'B' → matches word\[1]
3. Move right → (0,2) = 'C' → matches word\[2]
4. Move down → (1,2) = 'C' → matches word\[3]
5. Move down → (2,2) = 'E' → matches word\[4]
6. Move left → (2,1) = 'D' → matches word\[5]
   ✅ All characters matched → return `true`.

---

## ⏱ Time & Space Complexity:

### Time Complexity:

* **O(m × n × 4^L)**
  Where:
* `m` = number of rows
* `n` = number of columns
* `L` = length of the word
* For each cell, we explore up to 4 directions recursively up to length `L`.

### Space Complexity:

* **O(L)** for recursion stack (maximum depth is length of the word).
* **O(1)** extra space as we modify the board in place (no separate `visited` array).

---

## ✅ Summary:

| Aspect           | Value                                       |
| ---------------- | ------------------------------------------- |
| Technique        | Backtracking / DFS                          |
| Key Tricks       | Temporarily mark visited cells              |
| Direction Order  | Up, Down, Left, Right (can be changed)      |
| Visited Cells    | Handled in-place with character replacement |
| Time Complexity  | `O(m × n × 4^L)`                            |
| Space Complexity | `O(L)` (recursion stack)                    |


