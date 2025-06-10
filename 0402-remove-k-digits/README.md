<h2><a href="https://leetcode.com/problems/remove-k-digits">402. Remove K Digits</a></h2><h3>Medium</h3><hr><p>Given string num representing a non-negative integer <code>num</code>, and an integer <code>k</code>, return <em>the smallest possible integer after removing</em> <code>k</code> <em>digits from</em> <code>num</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;1432219&quot;, k = 3
<strong>Output:</strong> &quot;1219&quot;
<strong>Explanation:</strong> Remove the three digits 4, 3, and 2 to form the new number 1219 which is the smallest.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;10200&quot;, k = 1
<strong>Output:</strong> &quot;200&quot;
<strong>Explanation:</strong> Remove the leading 1 and the number is 200. Note that the output must not contain leading zeroes.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> num = &quot;10&quot;, k = 2
<strong>Output:</strong> &quot;0&quot;
<strong>Explanation:</strong> Remove all the digits from the number and it is left with nothing which is 0.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= k &lt;= num.length &lt;= 10<sup>5</sup></code></li>
	<li><code>num</code> consists of only digits.</li>
	<li><code>num</code> does not have any leading zeros except for the zero itself.</li>
</ul>



---

## ✅ **Problem Statement Recap**

Given a string `num` representing a **non-negative integer**, remove `k` digits from it such that the new number is the **smallest possible**.

---

## ✅ **Intuition**

We want to remove digits that **make the number larger**.
So we scan the number **from left to right**, and every time we find a digit that is **greater than the next digit**, we remove it.

We use a **stack** to keep digits in **increasing order** (monotonic stack).
If a smaller digit comes and we still have digits left to remove (`k > 0`), we remove from the top of the stack.

---

## ✅ **Algorithm**

1. Initialize an empty stack.
2. Traverse through the digits of the string:

   * While stack is not empty, and the top of the stack is **greater** than current digit, and we still have digits to remove (`k > 0`):

     * Pop from stack and decrement `k`.
   * Push the current digit to the stack.
3. After traversal, if `k > 0`, pop the remaining digits from the top of the stack.
4. Build the result string from the stack.
5. **Trim leading zeroes**.
6. If the result is empty, return `"0"`, else return the result.

---

## ✅ **Dry Run**

For `num = "1432219", k = 3`

**Initial num**: 1432219
**Goal**: Remove 3 digits to make it smallest.

* Push `1`
* Push `4` → `4 > 3`, so pop `4`
* Push `3` → `3 > 2`, so pop `3`
* Push `2` → `2 > 2`, ok
* Push `2` → `2 > 1`, so pop `2`
* Push `1`
* Push `9`

Stack = `1 2 1 9` ⇒ Output: `"1219"`

---

## ✅ Final Java Code with Detailed Comments

```java
import java.util.Stack;

class Solution {

    // Function to return the smallest number after removing k digits
    public String removeKdigits(String num, int k) {

        // Stack to store the digits of the final number
        Stack<Character> st = new Stack<>();

        // Step 1: Traverse each digit in the given number
        for (int i = 0; i < num.length(); i++) {

            char digit = num.charAt(i);  // Current digit

            // Step 2: Remove digits from stack if they are greater than current digit
            // and we still need to remove k digits
            while (!st.isEmpty() && k > 0 && st.peek() > digit) {
                st.pop();  // Remove the larger digit
                k--;       // Decrease count of digits to remove
            }

            // Step 3: Add the current digit to stack
            st.push(digit);
        }

        // Step 4: If there are still digits to remove, pop from the end (largest remaining)
        while (!st.isEmpty() && k > 0) {
            st.pop();
            k--;
        }

        // Step 5: Build the final number from stack (in reverse order)
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }

        // Step 6: Reverse the number to get the correct order
        sb.reverse();

        // Step 7: Remove leading zeros
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        // Step 8: Handle empty result (when all digits removed)
        if (sb.length() == 0) return "0";

        // Step 9: Return final result
        return sb.toString();
    }

    // Main method to test the function
    public static void main(String[] args) {
        Solution sol = new Solution();

        // Test cases
        String num1 = "1432219";
        int k1 = 3;
        System.out.println("Result: " + sol.removeKdigits(num1, k1));  // Output: 1219

        String num2 = "10200";
        int k2 = 1;
        System.out.println("Result: " + sol.removeKdigits(num2, k2));  // Output: 200

        String num3 = "10";
        int k3 = 2;
        System.out.println("Result: " + sol.removeKdigits(num3, k3));  // Output: 0
    }
}
```

---

## ✅ Time and Space Complexity

* **Time Complexity**: `O(n)`
  Each digit is pushed and popped at most once.

* **Space Complexity**: `O(n)`
  Stack stores at most all digits of the input.

---
