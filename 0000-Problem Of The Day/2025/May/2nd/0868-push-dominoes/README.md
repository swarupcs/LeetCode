<h2><a href="https://leetcode.com/problems/push-dominoes">868. Push Dominoes</a></h2><h3>Medium</h3><hr><p>There are <code>n</code> dominoes in a line, and we place each domino vertically upright. In the beginning, we simultaneously push some of the dominoes either to the left or to the right.</p>

<p>After each second, each domino that is falling to the left pushes the adjacent domino on the left. Similarly, the dominoes falling to the right push their adjacent dominoes standing on the right.</p>

<p>When a vertical domino has dominoes falling on it from both sides, it stays still due to the balance of the forces.</p>

<p>For the purposes of this question, we will consider that a falling domino expends no additional force to a falling or already fallen domino.</p>

<p>You are given a string <code>dominoes</code> representing the initial state where:</p>

<ul>
	<li><code>dominoes[i] = &#39;L&#39;</code>, if the <code>i<sup>th</sup></code> domino has been pushed to the left,</li>
	<li><code>dominoes[i] = &#39;R&#39;</code>, if the <code>i<sup>th</sup></code> domino has been pushed to the right, and</li>
	<li><code>dominoes[i] = &#39;.&#39;</code>, if the <code>i<sup>th</sup></code> domino has not been pushed.</li>
</ul>

<p>Return <em>a string representing the final state</em>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dominoes = &quot;RR.L&quot;
<strong>Output:</strong> &quot;RR.L&quot;
<strong>Explanation:</strong> The first domino expends no additional force on the second domino.
</pre>

<p><strong class="example">Example 2:</strong></p>
<img alt="" src="https://s3-lc-upload.s3.amazonaws.com/uploads/2018/05/18/domino.png" style="height: 196px; width: 512px;" />
<pre>
<strong>Input:</strong> dominoes = &quot;.L.R...LR..L..&quot;
<strong>Output:</strong> &quot;LL.RR.LLRRLL..&quot;
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == dominoes.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>5</sup></code></li>
	<li><code>dominoes[i]</code> is either <code>&#39;L&#39;</code>, <code>&#39;R&#39;</code>, or <code>&#39;.&#39;</code>.</li>
</ul>




---

### ✅ **Intuition**

We want to simulate the fall of dominoes over time. However, simulating it second by second is inefficient for large inputs. Instead, we can calculate **how far each domino is from the nearest left-falling (`L`) or right-falling (`R`) domino**, and use that information to decide the final state:

* If a domino is closer to an `R` than to an `L`, it falls to the right.
* If it's closer to an `L`, it falls to the left.
* If it's equidistant from both, or no force reaches it, it stays upright (`.`).

---

### ✅ **Approach**

1. Create two arrays:

   * `rightClosestL[i]`: index of nearest `L` domino to the right of `i` (inclusive).
   * `leftClosestR[i]`: index of nearest `R` domino to the left of `i` (inclusive).

2. Use these arrays to calculate the distance from each domino to the nearest force (`L` or `R`).

3. For each domino:

   * If the distances are equal, it remains `.`.
   * If one side is closer, the domino falls in that direction.

---

### ✅ **Algorithm**

1. Initialize two arrays `rightClosestL` and `leftClosestR` of size `n`, filled with `-1`.
2. Traverse from **right to left** to populate `rightClosestL`.
3. Traverse from **left to right** to populate `leftClosestR`.
4. Traverse each index and:

   * Calculate distances to closest `L` and `R`.
   * Based on comparison, decide the final state of the domino.
5. Build and return the final string.




---

### ✅ Step-by-Step Algorithm (with Ternary Explanation)

#### **Step 1: Initialize**

* Let `n = dominoes.length()`.
* Create two arrays of size `n`:

  * `rightClosestL[]`: will store the index of the nearest `'L'` domino to the right of each index.
  * `leftClosestR[]`: will store the index of the nearest `'R'` domino to the left of each index.

---

#### **Step 2: Fill `rightClosestL[]` (Right to Left Pass)**

* Traverse from `i = n - 1` down to `0`:

  * If `dominoes[i] == 'L'`:
    → Set `rightClosestL[i] = i` (this domino is itself falling to the left).
  * Else if `dominoes[i] == '.'`:
    → If `i < n - 1` (not the last element):
    → Set `rightClosestL[i] = rightClosestL[i + 1]`
    (inherit the index of the nearest `'L'` from the next position).
    → Else (at last index):
    → Set `rightClosestL[i] = -1` (no `'L'` exists to the right).
  * Else (i.e., `'R'`):
    → Set `rightClosestL[i] = -1` (a right-falling domino doesn't lead to a left push).

---

#### **Step 3: Fill `leftClosestR[]` (Left to Right Pass)**

* Traverse from `i = 0` to `n - 1`:

  * If `dominoes[i] == 'R'`:
    → Set `leftClosestR[i] = i` (this domino is itself falling to the right).
  * Else if `dominoes[i] == '.'`:
    → If `i > 0` (not the first element):
    → Set `leftClosestR[i] = leftClosestR[i - 1]`
    (inherit the index of the nearest `'R'` from the previous position).
    → Else (at first index):
    → Set `leftClosestR[i] = -1` (no `'R'` exists to the left).
  * Else (i.e., `'L'`):
    → Set `leftClosestR[i] = -1` (a left-falling domino doesn't lead to a right push).

---

#### **Step 4: Build the Result**

* Initialize an empty `StringBuilder` called `result`.

* For every index `i` from `0` to `n - 1`:

  * If `dominoes[i] == 'L'` or `'R'`:
    → Append it directly to the result (no change needed).
  * Else if `dominoes[i] == '.'`:

    1. Compute distance to nearest `'R'` on the left:
       → If `leftClosestR[i] == -1`: set `distLeftR = ∞`
       → Else: `distLeftR = i - leftClosestR[i]`
    2. Compute distance to nearest `'L'` on the right:
       → If `rightClosestL[i] == -1`: set `distRightL = ∞`
       → Else: `distRightL = rightClosestL[i] - i`
    3. Compare distances:

       * If both distances are `∞` or equal → append `'.'` (no force or balanced).
       * If `distLeftR < distRightL` → append `'R'` (right wins).
       * If `distRightL < distLeftR` → append `'L'` (left wins).

---

#### **Step 5: Return Result**

* Return `result.toString()`.

---




### ✅ **Code with Algorithm and Comments**

```java
class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();

        // Arrays to store the closest L to the right and R to the left for each index
        int[] rightClosestL = new int[n];
        int[] leftClosestR = new int[n];

        // Step 1: Fill rightClosestL by scanning from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (dominoes.charAt(i) == 'L') {
                // Current domino is L, so closest L is at i
                rightClosestL[i] = i;
            } else if (dominoes.charAt(i) == '.') {
                // Inherit closest L from the right neighbor
                rightClosestL[i] = i < n - 1 ? rightClosestL[i + 1] : -1;
            } else {
                // Current domino is R, no L to the right of it
                rightClosestL[i] = -1;
            }
        }

        // Step 2: Fill leftClosestR by scanning from left to right
        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) == 'R') {
                // Current domino is R, so closest R is at i
                leftClosestR[i] = i;
            } else if (dominoes.charAt(i) == '.') {
                // Inherit closest R from the left neighbor
                leftClosestR[i] = i > 0 ? leftClosestR[i - 1] : -1;
            } else {
                // Current domino is L, no R to the left of it
                leftClosestR[i] = -1;
            }
        }

        // Step 3: Determine the final state of each domino
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int distRightL = rightClosestL[i] == -1 ? Integer.MAX_VALUE : Math.abs(i - rightClosestL[i]);
            int distLeftR = leftClosestR[i] == -1 ? Integer.MAX_VALUE : Math.abs(i - leftClosestR[i]);

            if (dominoes.charAt(i) != '.') {
                // Already pushed domino remains the same
                result.append(dominoes.charAt(i));
            } else if (distLeftR == distRightL) {
                // Forces are equal or no forces, remains upright
                result.append('.');
            } else if (distRightL < distLeftR) {
                // Closer to an L domino
                result.append('L');
            } else {
                // Closer to an R domino
                result.append('R');
            }
        }

        return result.toString();
    }
}
```

---

### ✅ **Dry Run Example**

Input:

```
dominoes = ".L.R...LR..L.."
```

Step-by-step:

* Initial: `. L . R . . . L R . . L . .`
* Step 1: `L` affects previous dominos: `L L . R . . . L R . . L . .`
* Step 2: `R` affects next dominos, until another L interrupts.

Final:

```
LL.RR.LLRRLL..
```

Each `.` is replaced based on the closest influencing force.

---

