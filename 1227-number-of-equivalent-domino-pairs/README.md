<h2><a href="https://leetcode.com/problems/number-of-equivalent-domino-pairs">1227. Number of Equivalent Domino Pairs</a></h2><h3>Easy</h3><hr><p>Given a list of <code>dominoes</code>, <code>dominoes[i] = [a, b]</code> is <strong>equivalent to</strong> <code>dominoes[j] = [c, d]</code> if and only if either (<code>a == c</code> and <code>b == d</code>), or (<code>a == d</code> and <code>b == c</code>) - that is, one domino can be rotated to be equal to another domino.</p>

<p>Return <em>the number of pairs </em><code>(i, j)</code><em> for which </em><code>0 &lt;= i &lt; j &lt; dominoes.length</code><em>, and </em><code>dominoes[i]</code><em> is <strong>equivalent to</strong> </em><code>dominoes[j]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> dominoes = [[1,2],[2,1],[3,4],[5,6]]
<strong>Output:</strong> 1
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> dominoes = [[1,2],[1,2],[1,1],[1,2],[2,2]]
<strong>Output:</strong> 3
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= dominoes.length &lt;= 4 * 10<sup>4</sup></code></li>
	<li><code>dominoes[i].length == 2</code></li>
	<li><code>1 &lt;= dominoes[i][j] &lt;= 9</code></li>
</ul>



---

## 🔍 **Problem Understanding**

You are given a list of dominoes, and each domino can be represented as `[a, b]`. Two dominoes are **equivalent** if:

* Either they are the same directly: `[a, b] == [a, b]`
* Or one is a rotation of the other: `[a, b] == [b, a]`

Your goal is to **count the number of unique pairs `(i, j)` such that `i < j` and `dominoes[i]` is equivalent to `dominoes[j]`**.

---

## 💡 **Intuition**

Instead of comparing each domino with every other (which is O(n²)), we can normalize each domino such that:

* The smaller number comes first (i.e., `[a, b]` becomes `[min(a, b), max(a, b)]`)
* Convert each normalized pair into a unique integer key (e.g., `1 * 10 + 2 = 12`)
* Count how many times each key has appeared
* For every new occurrence of a key, the number of new equivalent pairs is equal to the count so far

---

## 🧠 **Approach / Algorithm**

1. Create an integer array `count[100]` to store the frequency of each normalized domino key.
2. Initialize `result = 0`
3. Loop through each domino `[a, b]`:

   * Normalize: swap if `a > b`
   * Create key: `key = a * 10 + b`
   * Add `count[key]` to result (because each previous domino with the same key can pair with this one)
   * Increment `count[key]` by 1
4. Return `result`

---

## ✅ **Code with Step-by-Step Comments**

```java
public class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        // Step 1: Array to store frequency of each unique domino after normalization
        int[] count = new int[100];

        // Step 2: Result variable to store total number of equivalent pairs
        int result = 0;

        // Step 3: Iterate through each domino
        for (int[] d : dominoes) {
            int a = d[0];
            int b = d[1];

            // Step 3.1: Normalize the domino (put smaller number first)
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            // Step 3.2: Create a unique key using normalized domino
            int key = a * 10 + b;

            // Step 3.3: Add count[key] to result
            // Because current domino can pair with all previous ones with same key
            result += count[key];

            // Step 3.4: Increment count of this key
            count[key]++;
        }

        // Step 4: Return total pairs
        return result;
    }
}
```

---

## 🧪 **Dry Run**

Input: `[[1,2],[2,1],[3,4],[5,6]]`

**Step-by-step:**

| Domino | Normalized | Key | count\[key] before | result | count\[key] after |
| ------ | ---------- | --- | ------------------ | ------ | ----------------- |
| \[1,2] | \[1,2]     | 12  | 0                  | 0      | 1                 |
| \[2,1] | \[1,2]     | 12  | 1                  | 1      | 2                 |
| \[3,4] | \[3,4]     | 34  | 0                  | 1      | 1                 |
| \[5,6] | \[5,6]     | 56  | 0                  | 1      | 1                 |

✅ **Output: 1**

---

## 🧪 Example 2 Dry Run

Input: `[[1,2],[1,2],[1,1],[1,2],[2,2]]`

| Domino | Normalized | Key | count\[key] before | result | count\[key] after |
| ------ | ---------- | --- | ------------------ | ------ | ----------------- |
| \[1,2] | \[1,2]     | 12  | 0                  | 0      | 1                 |
| \[1,2] | \[1,2]     | 12  | 1                  | 1      | 2                 |
| \[1,1] | \[1,1]     | 11  | 0                  | 1      | 1                 |
| \[1,2] | \[1,2]     | 12  | 2                  | 3      | 3                 |
| \[2,2] | \[2,2]     | 22  | 0                  | 3      | 1                 |

✅ **Output: 3**

---

## 🧾 Summary

* **Time Complexity:** O(n)
* **Space Complexity:** O(1), since `count[100]` is a constant size array

This solution is optimal for the input size (up to 40,000 dominoes).


