<h2><a href="https://leetcode.com/problems/minimum-domino-rotations-for-equal-row">1049. Minimum Domino Rotations For Equal Row</a></h2><h3>Medium</h3><hr><p>In a row of dominoes, <code>tops[i]</code> and <code>bottoms[i]</code> represent the top and bottom halves of the <code>i<sup>th</sup></code> domino. (A domino is a tile with two numbers from 1 to 6 - one on each half of the tile.)</p>

<p>We may rotate the <code>i<sup>th</sup></code> domino, so that <code>tops[i]</code> and <code>bottoms[i]</code> swap values.</p>

<p>Return the minimum number of rotations so that all the values in <code>tops</code> are the same, or all the values in <code>bottoms</code> are the same.</p>

<p>If it cannot be done, return <code>-1</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>
<img alt="" src="https://assets.leetcode.com/uploads/2021/05/14/domino.png" style="height: 300px; width: 421px;" />
<pre>
<strong>Input:</strong> tops = [2,1,2,4,2,2], bottoms = [5,2,6,2,3,2]
<strong>Output:</strong> 2
<strong>Explanation:</strong> 
The first figure represents the dominoes as given by tops and bottoms: before we do any rotations.
If we rotate the second and fourth dominoes, we can make every value in the top row equal to 2, as indicated by the second figure.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> tops = [3,5,1,2,3], bottoms = [3,6,3,3,4]
<strong>Output:</strong> -1
<strong>Explanation:</strong> 
In this case, it is not possible to rotate the dominoes to make one row of values equal.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= tops.length &lt;= 2 * 10<sup>4</sup></code></li>
	<li><code>bottoms.length == tops.length</code></li>
	<li><code>1 &lt;= tops[i], bottoms[i] &lt;= 6</code></li>
</ul>



---

## ✅ Intuition

In a row of dominoes, each domino has a top and bottom value. You are allowed to swap the top and bottom of any domino. The goal is to make **either the entire top row or the entire bottom row** consist of the **same number** using the **minimum number of swaps**.

To solve this:

* We need to choose a number from 1 to 6 (as dominoes only contain numbers in this range) and try to make either the top or bottom rows filled with this number.
* We check for each number: how many rotations (swaps) are needed to achieve this.
* If it's not possible for that number (i.e., some domino doesn't contain that number at all), skip it.

---

## 🧠 Approach

1. Try each number from 1 to 6 as a potential candidate.
2. For each number:

   * Traverse the array and check:

     * If that number exists in either top or bottom of each domino.
     * Count the number of rotations required to make either the top or bottom row uniform.
3. Keep track of the minimum rotations needed among all valid candidates.
4. If no number works, return `-1`.

---

## ⚙️ Algorithm

1. Define a helper function `find(tops, bottoms, val)` to compute the minimum swaps to make all tops or bottoms equal to `val`.
2. For each number from 1 to 6:

   * Call `find` for that number.
   * If it's not `-1`, store the minimum among all valid options.
3. Return the minimum found, or `-1` if no valid option exists.

---

## 💻 Java Code with Line-by-Line Explanation

```java
class Solution {

    // Helper method to check the number of swaps needed to make all tops or bottoms equal to 'val'
    private int find(int[] tops, int[] bottoms, int val) {
        int swapTop = 0;     // Counts the swaps needed to make tops equal to 'val'
        int swapBottom = 0;  // Counts the swaps needed to make bottoms equal to 'val'
        int n = tops.length;

        for (int i = 0; i < n; i++) {
            // If neither top nor bottom has 'val', it's impossible to make that row uniform
            if (tops[i] != val && bottoms[i] != val) {
                return -1;
            }
            // If top doesn't have 'val', we need to swap this domino to bring 'val' to top
            else if (tops[i] != val) {
                swapTop++;
            }
            // If bottom doesn't have 'val', we need to swap this domino to bring 'val' to bottom
            else if (bottoms[i] != val) {
                swapBottom++;
            }
        }

        // Return the minimum swaps required (either all tops or all bottoms equal to 'val')
        return Math.min(swapTop, swapBottom);
    }

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int result = Integer.MAX_VALUE; // Initialize result with a large number

        // Try all possible values (1 to 6) as potential candidates for uniform row
        for (int val = 1; val <= 6; val++) {
            int swaps = find(tops, bottoms, val); // Check minimum swaps for current value
            if (swaps != -1) {
                result = Math.min(result, swaps); // Keep track of minimum among valid results
            }
        }

        // If result is still MAX_VALUE, it means no value could make the rows uniform
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
```

---

## 🔍 Example Dry Run

### Input:

```java
tops =    [2, 1, 2, 4, 2, 2]
bottoms = [5, 2, 6, 2, 3, 2]
```

Try each value `val` from 1 to 6:

* `val = 1`: fails at i=2 (neither tops\[2] = 2 nor bottoms\[2] = 6 equals 1) → invalid
* `val = 2`:

  * i=1 → tops\[1] = 1 → needs rotation
  * i=3 → tops\[3] = 4 → needs rotation
  * Total swaps: top=2, bottom=1 → min = **1**
* `val = 3`: fails at i=0 (neither is 3)
* `val = 4`: fails at i=0
* `val = 5`: fails at i=1
* `val = 6`: fails at i=0

So, only `val = 2` is valid → min rotations = **2**

### ✅ Output:

```java
2
```

---

