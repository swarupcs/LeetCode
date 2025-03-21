<h2><a href="https://leetcode.com/problems/word-ladder">127. Word Ladder</a></h2><h3>Hard</h3><hr><p>A <strong>transformation sequence</strong> from word <code>beginWord</code> to word <code>endWord</code> using a dictionary <code>wordList</code> is a sequence of words <code>beginWord -&gt; s<sub>1</sub> -&gt; s<sub>2</sub> -&gt; ... -&gt; s<sub>k</sub></code> such that:</p>

<ul>
	<li>Every adjacent pair of words differs by a single letter.</li>
	<li>Every <code>s<sub>i</sub></code> for <code>1 &lt;= i &lt;= k</code> is in <code>wordList</code>. Note that <code>beginWord</code> does not need to be in <code>wordList</code>.</li>
	<li><code>s<sub>k</sub> == endWord</code></li>
</ul>

<p>Given two words, <code>beginWord</code> and <code>endWord</code>, and a dictionary <code>wordList</code>, return <em>the <strong>number of words</strong> in the <strong>shortest transformation sequence</strong> from</em> <code>beginWord</code> <em>to</em> <code>endWord</code><em>, or </em><code>0</code><em> if no such sequence exists.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]
<strong>Output:</strong> 5
<strong>Explanation:</strong> One shortest transformation sequence is &quot;hit&quot; -&gt; &quot;hot&quot; -&gt; &quot;dot&quot; -&gt; &quot;dog&quot; -&gt; cog&quot;, which is 5 words long.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;]
<strong>Output:</strong> 0
<strong>Explanation:</strong> The endWord &quot;cog&quot; is not in wordList, therefore there is no valid transformation sequence.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= beginWord.length &lt;= 10</code></li>
	<li><code>endWord.length == beginWord.length</code></li>
	<li><code>1 &lt;= wordList.length &lt;= 5000</code></li>
	<li><code>wordList[i].length == beginWord.length</code></li>
	<li><code>beginWord</code>, <code>endWord</code>, and <code>wordList[i]</code> consist of lowercase English letters.</li>
	<li><code>beginWord != endWord</code></li>
	<li>All the words in <code>wordList</code> are <strong>unique</strong>.</li>
</ul>


Here's a detailed explanation of the **Word Ladder I** problem, including **approach, intuition, code explanation, algorithm, dry run, and fully commented code**.  

---

## **🔍 Approach & Intuition**  
1. **Graph Representation:**  
   - Consider words as nodes and edges exist if two words differ by **only one letter**.
   - We need to find the **shortest path** from `beginWord` to `endWord`.

2. **Breadth-First Search (BFS):**  
   - BFS is ideal for finding the shortest path in an **unweighted graph**.
   - We explore all possible transformations at each level before moving to the next.

3. **Processing Words Efficiently:**  
   - Store words in a **set (HashSet)** for **quick lookup and removal**.
   - Modify one letter at a time and check if the new word exists in the `wordSet`.
   - If a word is valid, enqueue it with an incremented step count.

4. **Early Stopping Condition:**  
   - If `endWord` is reached, return the transformation length.
   - If the queue becomes empty, return `0` (indicating no valid path).

---

## **📜 Algorithm**  
1. **Convert the word list into a HashSet** for quick lookups.
2. **Edge case:** If `endWord` is not in `wordList`, return `0`.
3. **Initialize a queue** with `{beginWord, 1}` (word, steps).
4. **Perform BFS:**
   - Pop a word and its step count from the queue.
   - If it matches `endWord`, return steps.
   - Modify each character from `'a'` to `'z'` and generate new words.
   - If the new word exists in `wordSet`, add it to the queue and remove it from the set.
5. **Return `0`** if `endWord` is unreachable.

---

## **🚀 Code with Step-by-Step Comments**  

```java
import java.util.*;

class Solution {
    
    // Custom Pair class to store word and its transformation steps
    static class Pair {
        String word;
        int steps;

        Pair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }

    // Function to perform BFS and find the shortest transformation sequence
    private int bfs(String beginWord, String endWord, Set<String> wordSet) {
        // Queue to store words and their transformation steps
        Queue<Pair> queue = new LinkedList<>();

        // Start BFS with the initial word
        queue.add(new Pair(beginWord, 1));

        // Remove the start word from the set (if present)
        wordSet.remove(beginWord);

        // BFS Traversal
        while (!queue.isEmpty()) {

            // Get the front element from the queue
            String word = queue.peek().word;
            int steps = queue.peek().steps;

            // Remove the processed element from the queue
            queue.poll();

            // If we reached the endWord, return the transformation steps
            if (word.equals(endWord)) {
                return steps;
            }

            // Iterate through each character in the word
            for (int i = 0; i < word.length(); i++) {

                // Convert word to character array to modify letters
                char[] wordArray = word.toCharArray();

                // Try replacing the current character with 'a' to 'z'
                for (char ch = 'a'; ch <= 'z'; ch++) {

                    // Skip if the character is the same as original
                    if (wordArray[i] == ch) continue;

                    // Create a new word by modifying one character
                    wordArray[i] = ch;
                    String newWord = new String(wordArray);

                    // If the new word exists in the word set, process it
                    if (wordSet.contains(newWord)) {
                        
                        // Remove the word to prevent revisiting
                        wordSet.remove(newWord);

                        // Push the new word with incremented step count
                        queue.add(new Pair(newWord, steps + 1));
                    }
                }
            }
        }

        // If no transformation is possible, return 0
        return 0;
    }

    // Main function to find the shortest transformation sequence length
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // Convert wordList to HashSet for quick lookup
        Set<String> wordSet = new HashSet<>(wordList);

        // If endWord is not in the set, return 0 (impossible transformation)
        if (!wordSet.contains(endWord)) {
            return 0;
        }

        // Call BFS function to find the shortest transformation sequence
        return bfs(beginWord, endWord, wordSet);
    }
}
```

---

## **📌 Dry Run Example**  
### **Example 1:**
#### **Input:**
```java
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log","cog"]
```

#### **Step-by-Step Execution:**
| Queue State  | Word  | Steps | Transformation |
|-------------|-------|-------|---------------|
| `["hit", 1]` | "hit" | 1 | Start BFS |
| `["hot", 2]` | "hot" | 2 | "hit" → "hot" |
| `["dot", 3]` | "dot" | 3 | "hot" → "dot" |
| `["lot", 3]` | "lot" | 3 | "hot" → "lot" |
| `["dog", 4]` | "dog" | 4 | "dot" → "dog" |
| `["log", 4]` | "log" | 4 | "lot" → "log" |
| `["cog", 5]` | "cog" | 5 | "dog" → "cog" |

**✅ Output:** `5`

---

### **Example 2:**
#### **Input:**
```java
beginWord = "hit"
endWord = "cog"
wordList = ["hot","dot","dog","lot","log"]
```

- `cog` is **not present** in the `wordList`.
- Since we cannot reach `endWord`, we return **0**.

**✅ Output:** `0`

---

## **🔑 Time & Space Complexity Analysis**
### **Time Complexity:**
- **O(M × 26 × N)**  
  - **M** = length of words  
  - **N** = number of words in `wordList`  
  - Each word has **M** positions, and we try **26** possible letters.  
  - The worst case iterates over **all N words**, leading to **O(M × 26 × N)**.

### **Space Complexity:**
- **O(N)**  
  - **Queue:** Stores at most **N words**.  
  - **HashSet:** Stores **N words**.  
  - **Total:** **O(N)**.

---

## **🎯 Summary**
✅ **BFS is used** to find the shortest transformation sequence.  
✅ **Queue stores word and step count** for level-wise traversal.  
✅ **HashSet is used** for quick lookup and avoiding revisits.  
✅ **Each character is replaced** from `'a'` to `'z'` to generate new words.  
✅ **Returns shortest path length** if `endWord` is found, otherwise `0`.  

---

