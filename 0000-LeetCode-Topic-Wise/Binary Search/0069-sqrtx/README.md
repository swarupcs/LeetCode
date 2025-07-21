<h2><a href="https://leetcode.com/problems/sqrtx">69. Sqrt(x)</a></h2><h3>Easy</h3><hr><p>Given a non-negative integer <code>x</code>, return <em>the square root of </em><code>x</code><em> rounded down to the nearest integer</em>. The returned integer should be <strong>non-negative</strong> as well.</p>

<p>You <strong>must not use</strong> any built-in exponent function or operator.</p>

<ul>
	<li>For example, do not use <code>pow(x, 0.5)</code> in c++ or <code>x ** 0.5</code> in python.</li>
</ul>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> x = 4
<strong>Output:</strong> 2
<strong>Explanation:</strong> The square root of 4 is 2, so we return 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> x = 8
<strong>Output:</strong> 2
<strong>Explanation:</strong> The square root of 8 is 2.82842..., and since we round it down to the nearest integer, 2 is returned.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>0 &lt;= x &lt;= 2<sup>31</sup> - 1</code></li>
</ul>

---

Below is a **complete, interview‑ready write‑up** for computing the **integer square root** (`floor(sqrt(x))`) of a non‑negative integer `x` **without using built‑in exponent / sqrt functions**, using **overflow‑safe binary search**.

---

## 1. Problem Restatement

Given a non‑negative integer `x`, return `⌊√x⌋` (square root rounded **down** to nearest integer).
You cannot use built‑in power or square‑root functions.
Input range: `0 ≤ x ≤ 2^31 − 1`.

---

## 2. Key Observations

1. The integer square root of `x` is in the range `[0, x]`, but we can tighten the upper bound:

   * For `x < 2`, answer is `x` (0→0, 1→1).
   * For `x ≥ 2`, `√x ≤ x/2` (true for `x ≥ 4`; for `x = 2` or `3`, `√x = 1 ≤ x/2` using integer division).
     So we can safely search in `[1, x/2]` for `x ≥ 2`.

2. **Overflow hazards** in Java `int`:

   * `(low + high) / 2` may overflow when `x` is near `Integer.MAX_VALUE`.
   * `mid * mid` may overflow (e.g., 50,000² > 2,147,483,647).

   **Fixes:**

   * Use `mid = low + (high - low) / 2`.
   * Compare using division: `mid <= x / mid` instead of `mid * mid <= x`.

---

## 3. High‑Level Approach (Binary Search)

We binary search the range of possible roots:

* Maintain `low`, `high`, and `ans` (best valid root so far).
* At each step:

  * Compute `mid`.
  * If `mid² ≤ x` (checked safely as `mid <= x / mid`), record `mid` in `ans` and move right to seek a larger valid root.
  * Else move left (mid too large).
* When the loop ends, `ans` holds `⌊√x⌋`.

---

## 4. Step‑by‑Step Algorithm

**Input:** integer `x`
**Output:** integer floor square root of `x`

1. **Handle trivial cases:**
   If `x < 2`, return `x` (0→0, 1→1).

2. **Initialize search bounds:**
   `low = 1`, `high = x / 2`, `ans = 1` (minimum possible root for `x ≥ 2`).

3. **Binary search loop while `low <= high`:**

   1. `mid = low + (high - low) / 2` (overflow‑safe midpoint).
   2. If `mid <= x / mid` (safe check for `mid² ≤ x`):

      * `ans = mid` (mid is a valid root candidate).
      * Move right: `low = mid + 1` (try to see if a larger root still squares ≤ x).
   3. Else (`mid² > x`):

      * Move left: `high = mid - 1`.

4. **Return** `ans`.

---

## 5. Fully Commented Java Code (Algorithm in Comments)

```java
class Solution {

    public int mySqrt(int x) {
        // ------------------------------------------
        // STEP 0: Handle small inputs directly.
        // sqrt(0) = 0, sqrt(1) = 1.
        // ------------------------------------------
        if (x < 2) return x;

        // ------------------------------------------
        // STEP 1: Define binary search range.
        // For x >= 2, floor(sqrt(x)) is <= x/2.
        // Example: x=4 -> sqrt=2 <= 2; x=8 -> sqrt=2 <= 4.
        // So searching [1, x/2] is safe and tighter than [1, x].
        // ------------------------------------------
        int low = 1;
        int high = x / 2;  // upper bound for candidate roots
        int ans = 1;       // best valid candidate found so far

        // ------------------------------------------
        // STEP 2: Standard binary search loop.
        // ------------------------------------------
        while (low <= high) {
            // Overflow-safe midpoint: avoids (low + high) overflow.
            int mid = low + (high - low) / 2;

            // ------------------------------------------
            // STEP 3: Compare mid^2 to x WITHOUT overflow.
            // Instead of (mid * mid <= x), use (mid <= x / mid).
            // Because: mid <= x / mid  <=>  mid*mid <= x  (for positive mid).
            // ------------------------------------------
            if (mid <= x / mid) {
                // mid^2 <= x -> mid is a valid sqrt candidate.
                ans = mid;        // record it
                low = mid + 1;    // try to find a larger valid root
            } else {
                // mid^2 > x -> discard mid and larger values
                high = mid - 1;
            }
        }

        // ------------------------------------------
        // STEP 4: ans holds floor(sqrt(x)).
        // ------------------------------------------
        return ans;
    }
}
```

---

## 6. Line‑By‑Line Explanation (Plain English)

**`if (x < 2) return x;`**
When `x` is 0 or 1, the answer is `x` itself; no search needed.

**`int low = 1;`**
Lowest possible non‑zero root candidate for `x ≥ 2`.

**`int high = x / 2;`**
Tight upper bound for integer root—never need to check beyond half of `x`.

**`int ans = 1;`**
Tracks the **largest** `mid` value seen so far whose square does not exceed `x`.
Initialized to 1 because for any `x ≥ 2`, `√x ≥ 1`.

**`while (low <= high) { ... }`**
Binary search the candidate root range.

**`int mid = low + (high - low) / 2;`**
Overflow‑safe midpoint.

**`if (mid <= x / mid) { ... }`**
Safe check for `mid * mid <= x`. We divide once rather than multiply and overflow.

**Inside true branch:**

* `ans = mid;` keep `mid` as current best root.
* `low = mid + 1;` look for bigger candidate (maybe closer to actual sqrt).

**Inside false branch:**

* `high = mid - 1;` `mid` is too large (mid² > x), shrink right boundary.

**After loop:**
Search exhausted; `ans` is the largest value whose square fits in `x`, i.e., `⌊√x⌋`.

---

## 7. Dry Runs

### Example A: x = 4

Search range: `low=1`, `high=2`

| low | high | mid | x/mid | mid <= x/mid? | ans | next low | next high |
| --- | ---- | --- | ----- | ------------- | --- | -------- | --------- |
| 1   | 2    | 1   | 4     | yes           | 1   | 2        | 2         |
| 2   | 2    | 2   | 2     | yes           | 2   | 3        | 2         |

Loop ends (`low > high`). Return `ans = 2`.

---

### Example B: x = 8

Search range: `low=1`, `high=4`

| low | high | mid | x/mid | mid <= x/mid? | ans | next low | next high |
| --- | ---- | --- | ----- | ------------- | --- | -------- | --------- |
| 1   | 4    | 2   | 4     | yes           | 2   | 3        | 4         |
| 3   | 4    | 3   | 2     | no            | 2   | 3        | 2         |

Loop ends. Return `ans = 2` (⌊√8⌋ = 2).

---

### Example C: x = 2147483647 (Integer.MAX\_VALUE)

You never compute `mid * mid`, so no overflow. The loop converges to `46340`, since:

* `46340^2 = 2,147,395,600 ≤ INT_MAX`
* `46341^2 = 2,147,488,281 > INT_MAX`
  Return `46340`.

---

## 8. Common Pitfalls (Why Naive Code Fails / TLE / Wrong Answer)

| Pitfall                  | What Happens                                                        | Fix                                                                 |
| ------------------------ | ------------------------------------------------------------------- | ------------------------------------------------------------------- |
| `mid = (low + high) / 2` | Can overflow to negative when `low + high` > INT\_MAX               | Use `low + (high - low) / 2`                                        |
| `if (mid * mid <= x)`    | `mid * mid` overflows → wraps negative → condition becomes nonsense | Use `mid <= x / mid` OR cast to `long` before multiply              |
| Searching `[1, x]`       | Works but slightly more iterations                                  | Use `[1, x/2]` for `x ≥ 2` to tighten range (optional optimization) |
| Not handling `x=0 or 1`  | May incorrectly search with `high=0` and produce wrong answer       | Early return for `x < 2`                                            |

---

## 9. Alternative Safe Version (Using `long`)

If you prefer to reason with explicit squares:

```java
class Solution {
    public int mySqrt(int x) {
        if (x < 2) return x;
        int low = 1, high = x / 2;
        int ans = 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            long sq = (long) mid * mid; // promote to long to avoid overflow

            if (sq == x) return mid;
            if (sq < x) {
                ans = mid;
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return ans;
    }
}
```

Both are correct; the division form avoids the extra `long`.

---

## 10. Time & Space Complexity

| Measure | Complexity   | Explanation                                                                    |
| ------- | ------------ | ------------------------------------------------------------------------------ |
| Time    | **O(log x)** | Each iteration halves the search interval. For 32‑bit ints, ≤ \~31 iterations. |
| Space   | **O(1)**     | Only a few scalar variables used; no extra data structures or recursion.       |

---

### Final Takeaway

Use overflow‑safe binary search and a safe square comparison (`mid <= x / mid` or cast to `long`). This yields a fast, precise integer square root even for the largest 32‑bit values.

---


