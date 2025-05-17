<h2><a href="https://leetcode.com/problems/valid-parentheses">20. Valid Parentheses</a></h2><h3>Easy</h3><hr><p>Given a string <code>s</code> containing just the characters <code>&#39;(&#39;</code>, <code>&#39;)&#39;</code>, <code>&#39;{&#39;</code>, <code>&#39;}&#39;</code>, <code>&#39;[&#39;</code> and <code>&#39;]&#39;</code>, determine if the input string is valid.</p>

<p>An input string is valid if:</p>

<ol>
	<li>Open brackets must be closed by the same type of brackets.</li>
	<li>Open brackets must be closed in the correct order.</li>
	<li>Every close bracket has a corresponding open bracket of the same type.</li>
</ol>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;()&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;()[]{}&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;(]&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">false</span></p>
</div>

<p><strong class="example">Example 4:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">s = &quot;([])&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">true</span></p>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>4</sup></code></li>
	<li><code>s</code> consists of parentheses only <code>&#39;()[]{}&#39;</code>.</li>
</ul>



---

## ✅ 1. Intuition

The string is valid if:

* Every **opening bracket** has a **corresponding closing bracket** of the same type.
* Brackets are **closed in the correct order**.

This is a classic **stack problem**:

* Push opening brackets to a **stack**.
* On encountering a closing bracket, **pop** the top and **match** it.
* If there's a mismatch or stack is not empty at the end, it's invalid.

---

## ⚙️ 2. Approach

* Use a `Stack` to store **opening brackets**.
* Traverse the string character by character.
* Push if it's an opening bracket.
* If it's a closing bracket:

  * Return false if stack is empty.
  * Pop the top and check if it matches using a helper method `isMatched`.
* At the end, return `true` only if the stack is empty.

---

## 🧠 3. Algorithm (Step-by-Step)

1. Initialize an empty stack.
2. For each character in the string:

   * If it's `(`, `{`, or `[`, push to stack.
   * If it's `)`, `}`, or `]`:

     * If the stack is empty → return `false`.
     * Pop the top item and check if it matches.
     * If not matched → return `false`.
3. After loop ends:

   * If the stack is empty → return `true`.
   * Else → return `false`.

---

## 💡 4. Java Code with Line-by-Line Explanation

```java
import java.util.Stack;

class Solution {
    // Main method to check if the input string has valid brackets
    public boolean isValid(String s) {
        // Step 1: Initialize a stack to store opening brackets
        Stack<Character> stack = new Stack<>();

        // Step 2: Loop through each character in the input string
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);  // Get the current character

            // Step 3: If it's an opening bracket, push it to the stack
            if (currentChar == '(' || currentChar == '{' || currentChar == '[') {
                stack.push(currentChar); // Push to stack
            } 
            // Step 4: If it's a closing bracket
            else {
                // Step 4a: If stack is empty, there is no opening bracket to match
                if (stack.isEmpty()) {
                    return false;
                }

                // Step 4b: Pop the top opening bracket
                char topChar = stack.pop();

                // Step 4c: Check if the popped opening matches current closing
                if (!isMatched(topChar, currentChar)) {
                    return false;  // Mismatch found
                }
            }
        }

        // Step 5: If stack is empty, all brackets matched correctly
        return stack.isEmpty();
    }

    // Helper function to check if an opening and closing bracket match
    private boolean isMatched(char open, char close) {
        // Return true if they form a valid pair
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
```

---

## 🔍 5. Dry Run with Example

**Input**: `s = "({[]})"`

### Step-by-step:

| Char | Stack State      | Operation             | Match Check |
| ---- | ---------------- | --------------------- | ----------- |
| `(`  | `[`(`]`]         | push `(`              |             |
| `{`  | \[`(`, `{`]      | push `{`              |             |
| `[`  | \[`(`, `{`, `[`] | push `[`              |             |
| `]`  | \[`(`, `{`]      | pop `[` → matched `]` | ✅           |
| `}`  | \[`(`]           | pop `{` → matched `}` | ✅           |
| `)`  | `[]`             | pop `(` → matched `)` | ✅           |

✔️ Final stack is empty → **Valid**

---

## ✅ Final Thoughts

* **Time Complexity**: `O(n)` where `n` is length of string
* **Space Complexity**: `O(n)` in the worst case (all openings)

---


