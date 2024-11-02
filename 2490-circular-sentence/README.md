<h2><a href="https://leetcode.com/problems/circular-sentence">2490. Circular Sentence</a></h2><h3>Easy</h3><hr><p>A <strong>sentence</strong> is a list of words that are separated by a<strong> single</strong> space with no leading or trailing spaces.</p>

<ul>
	<li>For example, <code>&quot;Hello World&quot;</code>, <code>&quot;HELLO&quot;</code>, <code>&quot;hello world hello world&quot;</code> are all sentences.</li>
</ul>

<p>Words consist of <strong>only</strong> uppercase and lowercase English letters. Uppercase and lowercase English letters are considered different.</p>

<p>A sentence is <strong>circular </strong>if:</p>

<ul>
	<li>The last character of a word is equal to the first character of the next word.</li>
	<li>The last character of the last word is equal to the first character of the first word.</li>
</ul>

<p>For example, <code>&quot;leetcode exercises sound delightful&quot;</code>, <code>&quot;eetcode&quot;</code>, <code>&quot;leetcode eats soul&quot; </code>are all circular sentences. However, <code>&quot;Leetcode is cool&quot;</code>, <code>&quot;happy Leetcode&quot;</code>, <code>&quot;Leetcode&quot;</code> and <code>&quot;I like Leetcode&quot;</code> are <strong>not</strong> circular sentences.</p>

<p>Given a string <code>sentence</code>, return <code>true</code><em> if it is circular</em>. Otherwise, return <code>false</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;leetcode exercises sound delightful&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The words in sentence are [&quot;leetcode&quot;, &quot;exercises&quot;, &quot;sound&quot;, &quot;delightful&quot;].
- leetcod<u>e</u>&#39;s&nbsp;last character is equal to <u>e</u>xercises&#39;s first character.
- exercise<u>s</u>&#39;s&nbsp;last character is equal to <u>s</u>ound&#39;s first character.
- soun<u>d</u>&#39;s&nbsp;last character is equal to <u>d</u>elightful&#39;s first character.
- delightfu<u>l</u>&#39;s&nbsp;last character is equal to <u>l</u>eetcode&#39;s first character.
The sentence is circular.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;eetcode&quot;
<strong>Output:</strong> true
<strong>Explanation:</strong> The words in sentence are [&quot;eetcode&quot;].
- eetcod<u>e</u>&#39;s&nbsp;last character is equal to <u>e</u>etcode&#39;s first character.
The sentence is circular.</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> sentence = &quot;Leetcode is cool&quot;
<strong>Output:</strong> false
<strong>Explanation:</strong> The words in sentence are [&quot;Leetcode&quot;, &quot;is&quot;, &quot;cool&quot;].
- Leetcod<u>e</u>&#39;s&nbsp;last character is <strong>not</strong> equal to <u>i</u>s&#39;s first character.
The sentence is <strong>not</strong> circular.</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= sentence.length &lt;= 500</code></li>
	<li><code>sentence</code> consist of only lowercase and uppercase English letters and spaces.</li>
	<li>The words in <code>sentence</code> are separated by a single space.</li>
	<li>There are no leading or trailing spaces.</li>
</ul>


### Intuition
The problem requires us to check if a sentence is "circular," which means:
1. For each consecutive pair of words, the last character of one word should match the first character of the next word.
2. The sentence should "wrap around" so that the last character of the final word matches the first character of the first word.

This approach uses a simple loop to traverse through each character of the sentence to:
- Identify word boundaries (indicated by spaces),
- Verify if consecutive words meet the required conditions,
- Check the circular condition by comparing the first and last characters of the entire sentence.

### Approach
1. **Loop Through Characters**: Traverse through the sentence to identify spaces, which indicate word boundaries.
2. **Check Adjacent Words**: For each space found, compare the last character of the current word with the first character of the next word.
3. **Check Circular Condition**: After checking all consecutive word pairs, ensure the last character of the sentence matches the first character, confirming the circular condition.

### Step-by-Step Algorithm
1. **Initialize Loop**: Start iterating from the second character (index `1`) to avoid out-of-bounds errors when checking characters before and after a space.
  
2. **Identify Word Boundaries**:
   - For each character in the sentence, if it is a space (`' '`), it signifies a boundary between two words.
   
3. **Compare Last and First Characters of Consecutive Words**:
   - If a space is found at `sentence.charAt(i) == ' '`, check if:
     - The character before the space (`sentence.charAt(i - 1)`) matches the character immediately after the space (`sentence.charAt(i + 1)`).
   - If they don’t match, return `false` since this fails the circular condition for consecutive words.

4. **Final Circular Check**:
   - After the loop, check if the first character of the sentence (`sentence.charAt(0)`) matches the last character (`sentence.charAt(sentence.length() - 1)`).
   - If they match, return `true`; otherwise, return `false`.

### Code with Comments
```java
class Solution {
    public boolean isCircularSentence(String sentence) {
        // Step 1: Iterate through each character in the sentence, starting from the second character
        for (int i = 1; i < sentence.length(); i++) {
            // Step 2: Check if the current character is a space
            if (sentence.charAt(i) == ' ') {
                // Step 3: If it's a space, compare the last character of the previous word (i - 1)
                // with the first character of the next word (i + 1)
                if (sentence.charAt(i - 1) != sentence.charAt(i + 1)) {
                    return false; // If they don't match, the sentence is not circular
                }
            }
        }
        
        // Step 4: Finally, check if the last character of the last word matches
        // the first character of the first word to ensure circular condition
        return sentence.charAt(0) == sentence.charAt(sentence.length() - 1);
    }
}
```

### Example Walkthrough

#### Example 1
- Input: `"leetcode exercises sound delightful"`
- Steps:
  - `sentence.charAt(0) == sentence.charAt(sentence.length() - 1)` checks if the last and first characters match.
  - Iterates over each space found:
    - `sentence.charAt(7) == ' '`: check `sentence.charAt(6) == sentence.charAt(8)`.
    - Repeat for each space boundary.
- Output: `true`

#### Example 2
- Input: `"Leetcode is cool"`
- Steps:
  - `sentence.charAt(0) != sentence.charAt(sentence.length() - 1)`: immediately fails the circular check.
- Output: `false`

This approach ensures that the sentence meets all conditions for being circular in an efficient manner.

### Complexity Analysis
Let n be the length of the string sentence.

#### Time complexity: O(n)

The algorithm iterates through the list of words exactly once. During each iteration, it performs constant-time operations. Therefore, the overall time complexity is linear.

#### Space complexity: O(1)

The algorithm uses few variables, which do not depend on the length of the string. No additional data structures are created to store the results, so the overall space complexity is constant.