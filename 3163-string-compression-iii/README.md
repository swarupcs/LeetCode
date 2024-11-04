<h2><a href="https://leetcode.com/problems/string-compression-iii/description/?envType=daily-question&envId=2024-11-04">3163. String Compression III</a></h2><h3>Medium</h3><hr><p>Given a string <code>word</code>, compress it using the following algorithm:</p>

<ul>
	<li>Begin with an empty string <code>comp</code>. While <code>word</code> is <strong>not</strong> empty, use the following operation:

	<ul>
		<li>Remove a maximum length prefix of <code>word</code> made of a <em>single character</em> <code>c</code> repeating <strong>at most</strong> 9 times.</li>
		<li>Append the length of the prefix followed by <code>c</code> to <code>comp</code>.</li>
	</ul>
	</li>
</ul>

<p>Return the string <code>comp</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;abcde&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;1a1b1c1d1e&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, <code>comp = &quot;&quot;</code>. Apply the operation 5 times, choosing <code>&quot;a&quot;</code>, <code>&quot;b&quot;</code>, <code>&quot;c&quot;</code>, <code>&quot;d&quot;</code>, and <code>&quot;e&quot;</code> as the prefix in each operation.</p>

<p>For each prefix, append <code>&quot;1&quot;</code> followed by the character to <code>comp</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">word = &quot;aaaaaaaaaaaaaabb&quot;</span></p>

<p><strong>Output:</strong> <span class="example-io">&quot;9a5a2b&quot;</span></p>

<p><strong>Explanation:</strong></p>

<p>Initially, <code>comp = &quot;&quot;</code>. Apply the operation 3 times, choosing <code>&quot;aaaaaaaaa&quot;</code>, <code>&quot;aaaaa&quot;</code>, and <code>&quot;bb&quot;</code> as the prefix in each operation.</p>

<ul>
	<li>For prefix <code>&quot;aaaaaaaaa&quot;</code>, append <code>&quot;9&quot;</code> followed by <code>&quot;a&quot;</code> to <code>comp</code>.</li>
	<li>For prefix <code>&quot;aaaaa&quot;</code>, append <code>&quot;5&quot;</code> followed by <code>&quot;a&quot;</code> to <code>comp</code>.</li>
	<li>For prefix <code>&quot;bb&quot;</code>, append <code>&quot;2&quot;</code> followed by <code>&quot;b&quot;</code> to <code>comp</code>.</li>
</ul>
</div>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= word.length &lt;= 2 * 10<sup>5</sup></code></li>
	<li><code>word</code> consists only of lowercase English letters.</li>
</ul>

Certainly! Here’s a breakdown of your code, step-by-step algorithm, intuition, dry run, and the time and space complexity. I'll also add comments in your code following the steps.

### Step-by-Step Algorithm

1. **Initialize Variables**:
   - Create a `StringBuilder` `comp` to store the compressed string efficiently.
   - Get the length of `word` in `n`.
   - Set an index variable `i` to 0, to serve as the pointer for traversing `word`.

2. **Loop Through the String**:
   - While `i` is less than `n` (the length of the string):
     1. **Set Up for Current Character**:
        - Initialize `count` to 0 to track the number of consecutive occurrences of the current character.
        - Set `ch` to the character at index `i` in `word`.
     2. **Count Consecutive Characters up to 9**:
        - While `i` is within bounds, `count` is less than 9, and the character at `i` is the same as `ch`:
          - Increment `count` and move `i` to the next position.
     3. **Append Count and Character**:
        - Append `count` and `ch` to `comp`.

3. **Return Result**:
   - Convert `comp` to a string and return it as the compressed result.

### Dry Run

Let's dry run this on each example to understand how the code works.

#### Example 1
**Input**: `word = "abcde"`

1. `comp = ""`, `n = 5`, `i = 0`
2. First iteration:
   - `ch = 'a'`, `count = 0`
   - Count increments to 1 (for `a`), so `comp = "1a"`
   - Move `i = 1`
3. Repeat similarly for `b`, `c`, `d`, and `e`, adding `"1b"`, `"1c"`, `"1d"`, and `"1e"` respectively to `comp`.
4. **Output**: `"1a1b1c1d1e"`

#### Example 2
**Input**: `word = "aaaaaaaaaaaaaabb"`

1. `comp = ""`, `n = 16`, `i = 0`
2. First iteration:
   - `ch = 'a'`, `count = 0`
   - Count increments to 9 (for `a`), so `comp = "9a"`
   - Move `i = 9`
3. Second iteration:
   - `ch = 'a'`, `count = 0`
   - Count increments to 5 (remaining `'a'` characters), so `comp = "9a5a"`
   - Move `i = 14`
4. Third iteration:
   - `ch = 'b'`, `count = 0`
   - Count increments to 2, so `comp = "9a5a2b"`
   - Move `i = 16` (end of string)
5. **Output**: `"9a5a2b"`

### Time Complexity

- **Time Complexity**: \( O(n) \), where \( n \) is the length of `word`. The algorithm goes through each character once.

- **Space Complexity**: \( O(n) \) for the `StringBuilder` `comp`, which stores the compressed result. In the worst case, `comp` could be nearly as long as `word`.

### Code with Comments

Here’s your code with comments added, based on the step-by-step algorithm:

```java
class Solution {
    public String compressedString(String word) {
        // Initialize StringBuilder for storing the compressed result
        StringBuilder comp = new StringBuilder();
        int n = word.length(); // Get the length of the input word
        int i = 0; // Initialize the index to 0
        
        // Loop through the entire string
        while (i < n) {
            int count = 0; // Initialize count for consecutive characters
            char ch = word.charAt(i); // Get the current character
            
            // Count up to 9 consecutive characters for the current character
            while (i < n && count < 9 && word.charAt(i) == ch) {
                count++; // Increment count for the current character
                i++; // Move to the next character
            }
            
            // Append the count and character to the compressed result
            comp.append(count).append(ch);
        }
        
        // Convert StringBuilder to String and return as compressed result
        return comp.toString();
    }
}
```

### Explanation and Intuition

- **Intuition**: The algorithm takes advantage of the problem’s rule that allows compressing a single character up to 9 times. By counting consecutive characters and resetting the count every time a new character or the count limit (9) is reached, we ensure an efficient traversal and compression of `word`.
- **Design Choice**: Using `StringBuilder` ensures efficient appending, and the loop structure allows managing different consecutive character groups without extra space or operations.