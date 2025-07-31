<h2><a href="https://leetcode.com/problems/n-queens">51. N-Queens</a></h2><h3>Hard</h3><hr><p>The <strong>n-queens</strong> puzzle is the problem of placing <code>n</code> queens on an <code>n x n</code> chessboard such that no two queens attack each other.</p>

<p>Given an integer <code>n</code>, return <em>all distinct solutions to the <strong>n-queens puzzle</strong></em>. You may return the answer in <strong>any order</strong>.</p>

<p>Each solution contains a distinct board configuration of the n-queens&#39; placement, where <code>&#39;Q&#39;</code> and <code>&#39;.&#39;</code> both indicate a queen and an empty space, respectively.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2020/11/13/queens.jpg" style="width: 600px; height: 268px;" />
<pre>
<strong>Input:</strong> n = 4
<strong>Output:</strong> [[&quot;.Q..&quot;,&quot;...Q&quot;,&quot;Q...&quot;,&quot;..Q.&quot;],[&quot;..Q.&quot;,&quot;Q...&quot;,&quot;...Q&quot;,&quot;.Q..&quot;]]
<strong>Explanation:</strong> There exist two distinct solutions to the 4-queens puzzle as shown above
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> n = 1
<strong>Output:</strong> [[&quot;Q&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= n &lt;= 9</code></li>
</ul>

---

* ✅ Intuition
* ✅ Approach
* ✅ Algorithm
* ✅ Line-by-line explanation
* ✅ Code comments
* ✅ Dry run with example (n = 4)
* ✅ Time and Space Complexity analysis

---

## 🧠 Intuition

The N-Queens problem is a classic **backtracking** problem where we aim to place queens on an `n x n` chessboard such that:

* No two queens share the same row.
* No two queens share the same column.
* No two queens share the same diagonal (both ↘️ and ↙️).

We place queens column by column. For each column, we try all possible rows and choose a valid (safe) position. If we reach column `n`, we’ve placed all queens successfully.

---

## 🪜 Approach

1. Start with column 0.
2. For every row in this column, check if it's safe to place a queen.
3. If yes:

   * Place the queen.
   * Recurse for the next column.
   * After recursion, backtrack (remove the queen).
4. Continue this until all columns are processed.

---

## 📜 Algorithm

```java
class Solution {
    // Main function to solve the N-Queens problem
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>(); // To store all valid board configurations
        List<String> board = new ArrayList<>(); // To represent current board state

        // Initialize an empty board with all '.'
        String s = ".".repeat(n); // Creates a string like "...."
        for(int i = 0; i < n; i++) {
            board.add(s); // Add n rows to the board
        }

        // Start the recursive backtracking from the first column
        func(0, ans, board);

        // Return the list of all possible valid configurations
        return ans;
    }

    // Recursive backtracking function to place queens column by column
    private void func(int col, List<List<String>> ans, List<String> board){
        // Base Case: All queens placed successfully
        if(col == board.size()) {
            ans.add(new ArrayList<>(board)); // Add a deep copy of current board to answer
            return;
        }

        // Try placing a queen in each row of the current column
        for(int row = 0; row < board.size(); row++) {
            if(safe(board, row, col)) { // Check if it's safe to place a queen
                // Place the queen
                char[] charArray = board.get(row).toCharArray(); // Convert row string to char array
                charArray[col] = 'Q'; // Place queen at (row, col)
                board.set(row, new String(charArray)); // Update the board with the placed queen

                // Recurse to place queen in the next column
                func(col + 1, ans, board);

                // Backtrack: remove the queen and try the next row
                charArray[col] = '.'; // Remove the queen
                board.set(row, new String(charArray)); // Update board back to original state
            }
        }
    }

    // Utility function to check if placing a queen at (row, col) is safe
    private boolean safe(List<String> board, int row, int col) {
        int r = row, c = col;

        // Check upper-left diagonal
        while (r >= 0 && c >= 0) {
            if (board.get(r).charAt(c) == 'Q') return false;
            r--;
            c--;
        }

        // Check left side of the current row
        r = row; c = col;
        while (c >= 0) {
            if (board.get(r).charAt(c) == 'Q') return false;
            c--;
        }

        // Check lower-left diagonal
        r = row; c = col;
        while (r < board.size() && c >= 0) {
            if (board.get(r).charAt(c) == 'Q') return false;
            r++;
            c--;
        }

        // If no queens are found attacking, it's safe to place
        return true;
    }
}

```

---

## 🪵 Dry Run (n = 4)

Start with empty board:

```
. . . .
. . . .
. . . .
. . . .
```

### Step-by-step:

1. Try placing a queen in column 0:

   * Row 0: Safe ➝ place Q at (0,0)
   * Board:

     ```
     Q . . .
     . . . .
     . . . .
     . . . .
     ```

2. Recurse to column 1:

   * Row 1: (1,1) attacked diagonally.
   * Row 2: Safe ➝ place Q at (2,1)
   * Board:

     ```
     Q . . .
     . . . .
     . Q . .
     . . . .
     ```

3. Column 2:

   * Row 0: Attacked.
   * Row 1: Attacked.
   * Row 3: Safe ➝ place Q at (3,2)
   * Board:

     ```
     Q . . .
     . . . .
     . Q . .
     . . Q .
     ```

4. Column 3:

   * Row 1: Safe ➝ place Q at (1,3)
   * Board:

     ```
     Q . . .
     . . . Q
     . Q . .
     . . Q .
     ```

5. Column 4: Success! Add this board to results.

6. Backtrack and try other possibilities.

This continues until all possibilities are exhausted.

---

## ⏱️ Time and Space Complexity

### Time Complexity: `O(N!)`

* We try to place a queen in N rows for each column, reducing by 1 row each time.
* For n = 4 → 4 \* 3 \* 2 \* 1 = 24 possibilities
* Worst-case: `O(N!)` for checking all permutations.

### Space Complexity: `O(N^2)`

* For each board configuration: `N` strings of length `N` → `O(N^2)`
* Call stack recursion depth = N → `O(N)`
* Storing all solutions: worst case `N!` boards → `O(N! * N^2)`

---

## ✅ Summary

* 🔁 **Backtracking** is used to explore all placements column by column.
* 🛑 Prune invalid placements using `safe()` check.
* 🔄 **Backtrack** by removing queens after recursion.
* ✅ Return all valid board configurations in `List<List<String>>`.


