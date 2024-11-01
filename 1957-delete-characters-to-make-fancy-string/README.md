<h2><a href="https://leetcode.com/problems/delete-characters-to-make-fancy-string">1957. Delete Characters to Make Fancy String</a></h2><h3>Easy</h3><hr><p>A <strong>fancy string</strong> is a string where no <strong>three</strong> <strong>consecutive</strong> characters are equal.</p>

<p>Given a string <code>s</code>, delete the <strong>minimum</strong> possible number of characters from <code>s</code> to make it <strong>fancy</strong>.</p>

<p>Return <em>the final string after the deletion</em>. It can be shown that the answer will always be <strong>unique</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;le<u>e</u>etcode&quot;
<strong>Output:</strong> &quot;leetcode&quot;
<strong>Explanation:</strong>
Remove an &#39;e&#39; from the first group of &#39;e&#39;s to create &quot;leetcode&quot;.
No three consecutive characters are equal, so return &quot;leetcode&quot;.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;<u>a</u>aab<u>aa</u>aa&quot;
<strong>Output:</strong> &quot;aabaa&quot;
<strong>Explanation:</strong>
Remove an &#39;a&#39; from the first group of &#39;a&#39;s to create &quot;aabaaaa&quot;.
Remove two &#39;a&#39;s from the second group of &#39;a&#39;s to create &quot;aabaa&quot;.
No three consecutive characters are equal, so return &quot;aabaa&quot;.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> s = &quot;aab&quot;
<strong>Output:</strong> &quot;aab&quot;
<strong>Explanation:</strong> No three consecutive characters are equal, so return &quot;aab&quot;.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= s.length &lt;= 10<sup>5</sup></code></li>
	<li><code>s</code> consists only of lowercase English letters.</li>
</ul>

### Java Solution

```java
class Solution {
    public String makeFancyString(String s) {
        // Step 1: If the string is too short to have three consecutive identical characters, return it as-is.
        if(s.length() <= 2) {
            return s;
        }

        // Step 2: Initialize a StringBuilder to efficiently build the result string.
        StringBuilder sb = new StringBuilder();

        // Step 3: Append the first two characters, as they can't form a "three consecutive" pattern by themselves.
        sb.append(s.charAt(0));
        sb.append(s.charAt(1));

        // Step 4: Loop through the string starting from the third character.
        for(int i = 2; i < s.length(); i++) {
            // Step 5: Check if the current character is the same as the previous two characters.
            // If it is, skip it to avoid forming three consecutive identical characters.
            if(s.charAt(i) == s.charAt(i-1) && s.charAt(i) == s.charAt(i-2)) {
                continue; // Skip the current character
            } else {
                // Step 6: Otherwise, append the current character to the result string.
                sb.append(s.charAt(i));
            }
        }

        // Step 7: Convert the StringBuilder to a string and return the final "fancy" string.
        return sb.toString();
    }
}
```

### Step-by-Step Algorithm Recap:

1. **Check Short Strings**: If `s` has 2 or fewer characters, return `s` directly (since it can't have three consecutive identical characters).
2. **Initialize `StringBuilder`**: Use `StringBuilder` to build the result, starting with the first two characters of `s`.
3. **Iterate Through Remaining Characters**:
   - For each character starting from the third position (`i = 2`), check if it’s the same as the previous two characters.
   - If so, skip this character to avoid three consecutive identical characters.
   - Otherwise, add it to `StringBuilder`.
4. **Return the Result**: Convert `StringBuilder` to a string and return the "fancy" string.

This structured approach efficiently prevents three consecutive identical characters in the resulting string.

### Code Analysis

Your Java code effectively constructs a "fancy" string by iterating through each character in the input `s` and appending characters to a `StringBuilder` only if adding them would not result in three consecutive identical characters. The code checks the last two characters added before deciding whether to include the current character.

### Algorithm

1. **Handle Short Strings**: If the length of `s` is 2 or less, return `s` directly, as no three consecutive characters can exist.
2. **Initialize `StringBuilder`**: Start building the result string by appending the first two characters of `s`, as these cannot form a "three consecutive" pattern on their own.
3. **Iterate Through String**: From the third character onward:
   - For each character `s[i]`, check if it's equal to both of the preceding two characters (`s[i-1]` and `s[i-2]`).
   - If it is, skip this character, as adding it would create three consecutive identical characters.
   - Otherwise, append it to `StringBuilder`.
4. **Return Result**: Convert `StringBuilder` to a string and return it.

### Intuition

The goal is to eliminate "triple repeats" with minimal deletions. By examining each character relative to the two before it, you ensure that no three consecutive characters are identical while maintaining as much of the original string as possible.

### Approach

1. **Check Conditions**: Begin with a simple check for strings that don’t need modification (length <= 2).
2. **Build Fancy String**: As you iterate over `s`, conditionally append characters to avoid creating a trio of identical characters.
3. **Efficiency**: By using a `StringBuilder`, appending characters is efficient, and the linear scan through the string ensures an optimal runtime.

### Dry Run

Consider `s = "aaabaaaa"`.

1. **Initialize**:
   - `sb = "aa"`
2. **Iterate**:
   - `i = 2`: `s[i] = a`, `s[i-1] = a`, `s[i-2] = a` → skip.
   - `i = 3`: `s[i] = b`, does not match `s[i-1]` and `s[i-2]` → append `b`.
   - `i = 4`: `s[i] = a`, does not match `s[i-1]` and `s[i-2]` → append `a`.
   - `i = 5`: `s[i] = a`, matches `s[i-1]`, but not `s[i-2]` → append `a`.
   - `i = 6`: `s[i] = a`, matches `s[i-1]` and `s[i-2]` → skip.
3. **Result**: Final string is `"aabaa"`.

Your code efficiently prevents three consecutive identical characters while preserving as much of the original string as possible.