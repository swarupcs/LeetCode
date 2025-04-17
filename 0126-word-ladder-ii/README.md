<h2><a href="https://leetcode.com/problems/word-ladder-ii">126. Word Ladder II</a></h2><h3>Hard</h3><hr><p>A <strong>transformation sequence</strong> from word <code>beginWord</code> to word <code>endWord</code> using a dictionary <code>wordList</code> is a sequence of words <code>beginWord -&gt; s<sub>1</sub> -&gt; s<sub>2</sub> -&gt; ... -&gt; s<sub>k</sub></code> such that:</p>

<ul>
	<li>Every adjacent pair of words differs by a single letter.</li>
	<li>Every <code>s<sub>i</sub></code> for <code>1 &lt;= i &lt;= k</code> is in <code>wordList</code>. Note that <code>beginWord</code> does not need to be in <code>wordList</code>.</li>
	<li><code>s<sub>k</sub> == endWord</code></li>
</ul>

<p>Given two words, <code>beginWord</code> and <code>endWord</code>, and a dictionary <code>wordList</code>, return <em>all the <strong>shortest transformation sequences</strong> from</em> <code>beginWord</code> <em>to</em> <code>endWord</code><em>, or an empty list if no such sequence exists. Each sequence should be returned as a list of the words </em><code>[beginWord, s<sub>1</sub>, s<sub>2</sub>, ..., s<sub>k</sub>]</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]
<strong>Output:</strong> [[&quot;hit&quot;,&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;cog&quot;],[&quot;hit&quot;,&quot;hot&quot;,&quot;lot&quot;,&quot;log&quot;,&quot;cog&quot;]]
<strong>Explanation:</strong>&nbsp;There are 2 shortest transformation sequences:
&quot;hit&quot; -&gt; &quot;hot&quot; -&gt; &quot;dot&quot; -&gt; &quot;dog&quot; -&gt; &quot;cog&quot;
&quot;hit&quot; -&gt; &quot;hot&quot; -&gt; &quot;lot&quot; -&gt; &quot;log&quot; -&gt; &quot;cog&quot;
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> beginWord = &quot;hit&quot;, endWord = &quot;cog&quot;, wordList = [&quot;hot&quot;,&quot;dot&quot;,&quot;dog&quot;,&quot;lot&quot;,&quot;log&quot;]
<strong>Output:</strong> []
<strong>Explanation:</strong> The endWord &quot;cog&quot; is not in wordList, therefore there is no valid transformation sequence.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= beginWord.length &lt;= 5</code></li>
	<li><code>endWord.length == beginWord.length</code></li>
	<li><code>1 &lt;= wordList.length &lt;= 500</code></li>
	<li><code>wordList[i].length == beginWord.length</code></li>
	<li><code>beginWord</code>, <code>endWord</code>, and <code>wordList[i]</code> consist of lowercase English letters.</li>
	<li><code>beginWord != endWord</code></li>
	<li>All the words in <code>wordList</code> are <strong>unique</strong>.</li>
	<li>The <strong>sum</strong> of all shortest transformation sequences does not exceed <code>10<sup>5</sup></code>.</li>
</ul>


Here’s the **detailed solution** to the problem:  
We'll break it down into:

---

### ✅ 1. **Intuition:**
We’re given two words, and a list of words. From the `startWord`, we can change one letter at a time and must land on a valid word from `wordList`. We want to find **all shortest transformation sequences** that lead to the `targetWord`.

To ensure shortest paths, we use **Breadth-First Search (BFS)**. Each level of BFS explores all words that can be formed from the previous words by changing one letter. Once we reach the `targetWord`, we stop and return all paths that reached it in that level.

---

### ✅ 2. **Approach:**
- Use BFS to generate word transformations level-by-level.
- Store the **entire path** during traversal.
- Maintain a `visited` set to avoid revisiting the same words in future levels.
- Use `subVisited` to mark newly visited words in the current level.
- When we find the `targetWord` at a level, stop and collect all such paths.

---

### ✅ 3. **Algorithm:**
1. Add all words from `wordList` into a HashSet for O(1) lookups.
2. Start BFS with the `startWord` in the path.
3. At each level:
   - Explore all possible single-letter changes for the last word in the path.
   - If valid (in `wordList` and not already visited), add to path and queue.
   - Track `subVisited` for this level and update `visited` at the end of the level.
4. If `targetWord` is found, break BFS and return all such paths of same length.

---

### ✅ 4. **Code with Step-by-Step Comments:**
```java

class Solution {

    public ArrayList<ArrayList<String>> findSequences(String startWord, String targetWord, String[] wordListArr) {

        // Step 1: Convert wordList array to a HashSet for O(1) lookups
        Set<String> wordList = new HashSet<>(Arrays.asList(wordListArr));
        ArrayList<ArrayList<String>> ans = new ArrayList<>(); // Final result list

        // If targetWord doesn't exist in wordList, return empty list immediately
        if (!wordList.contains(targetWord)) {
            return ans;
        }

        // Step 2: Prepare for BFS - build a graph (adjacency list) of shortest connections
        Map<String, List<String>> adj = new HashMap<>(); // Maps each word to next possible words
        Set<String> visited = new HashSet<>();           // Keeps track of visited words
        Queue<String> queue = new LinkedList<>();        // BFS queue
        queue.add(startWord);                            // Start BFS with startWord

        Set<String> currentLevel = new HashSet<>();      // Tracks words at current BFS level
        boolean found = false;                           // Flag to stop BFS after finding shortest path

        // Step 3: BFS to construct the graph of shortest paths
        while (!queue.isEmpty()) {
            int size = queue.size();     // Number of elements at current BFS level
            currentLevel.clear();        // Clear the currentLevel tracker

            // Process all nodes (words) in the current level
            for (int i = 0; i < size; i++) {
                String word = queue.poll();           // Get word from queue
                char[] chArr = word.toCharArray();    // Convert to char array for easy mutation

                // Try changing every character in the word
                for (int j = 0; j < chArr.length; j++) {
                    char original = chArr[j];         // Save the original character

                    // Replace character with 'a' to 'z'
                    for (char c = 'a'; c <= 'z'; c++) {
                        chArr[j] = c;
                        String nextWord = new String(chArr);  // Form new word

                        // Check if new word is in the wordList
                        if (wordList.contains(nextWord)) {
                            // If not visited before, add to queue and track in current level
                            if (!visited.contains(nextWord)) {
                                currentLevel.add(nextWord);
                                queue.add(nextWord);
                            }

                            // Add nextWord as a neighbor of current word in the adjacency list
                            adj.computeIfAbsent(word, k -> new ArrayList<>()).add(nextWord);

                            // If nextWord is the target, we’ve found shortest path level
                            if (nextWord.equals(targetWord)) {
                                found = true;
                            }
                        }
                    }

                    chArr[j] = original; // Restore original character for next iteration
                }
            }

            // Add all words from this level to the visited set to avoid cycles
            visited.addAll(currentLevel);

            // Break out of BFS if shortest path is found
            if (found) break;
        }

        // Step 4: Perform DFS to find all shortest paths using the adjacency list
        ArrayList<String> path = new ArrayList<>();
        path.add(startWord); // Start DFS with startWord
        dfs(startWord, targetWord, adj, path, ans);

        // Step 5: Return the list of all shortest transformation sequences
        return ans;
    }

    // DFS function to generate all paths from startWord to targetWord using the graph built in BFS
    private void dfs(String current, String target, Map<String, List<String>> adj,
                     ArrayList<String> path, ArrayList<ArrayList<String>> ans) {

        // Base Case: If current word is the target, add the current path to result
        if (current.equals(target)) {
            ans.add(new ArrayList<>(path)); // Deep copy of path to store in final answer
            return;
        }

        // If current word has no neighbors, stop
        if (!adj.containsKey(current)) return;

        // Recur for all neighbors (next valid transformations)
        for (String next : adj.get(current)) {
            path.add(next);                   // Choose
            dfs(next, target, adj, path, ans); // Explore
            path.remove(path.size() - 1);     // Un-choose (Backtrack)
        }
    }
}

```

---

### ✅ 5. Example & Dry Run:

#### **Input:**
```java
startWord = "der";
targetWord = "dfs";
wordList = {"des", "der", "dfr", "dgt", "dfs"};
```

---

#### **Dry Run:**
```
Level 0:
Queue = [["der"]]
Visited = {"der"}

Level 1:
Try changing one letter in "der":
→ "des" → valid → ["der", "des"]
→ "dfr" → valid → ["der", "dfr"]
(subVisited = {"des", "dfr"})

Queue = [["der", "des"], ["der", "dfr"]]
Visited = {"der", "des", "dfr"}

Level 2:
From path ["der", "des"]:
→ "dfs" → valid → ["der", "des", "dfs"] → target found!

From path ["der", "dfr"]:
→ "dfs" → valid → ["der", "dfr", "dfs"] → target found!

Both paths are same length = 3 → shortest
Stop BFS here
```

#### **Output:**
```java
[["der", "des", "dfs"], ["der", "dfr", "dfs"]]
```

---

### ✅ Summary:

| Step               | Description                             |
|--------------------|-----------------------------------------|
| Data Structure     | BFS queue with paths                    |
| Early Stopping     | Break on first targetWord discovery     |
| Level-wise Visited | Ensures only shortest paths considered  |
| Time Complexity    | `O(N * M * 26)`                         |
| Space Complexity   | `O(N * M)` for storing paths and visited|

Let me know if you'd like a visual version, or the Python version of this solution too!