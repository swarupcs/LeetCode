class Solution {
    private void dfs(String word, String beginWord, List<String> seq, 
                     Map<String, Integer> mpp, List<List<String>> ans) {

        // Base case: when we've reached the start word, add the sequence
        if (word.equals(beginWord)) {
            Collections.reverse(seq);         // Reverse the sequence since we built it backwards
            ans.add(new ArrayList<>(seq));    // Add the sequence to the result list
            Collections.reverse(seq);         // Reverse back for other backtracking paths
            return;                            // Return to explore other paths
        }

        int val = mpp.get(word);  // Current word's level (distance from start)

        // Try all possible one-letter transformations
        for (int i = 0; i < word.length(); i++) {
            StringBuilder sb = new StringBuilder(word);
            char original = word.charAt(i);  // Store original character to restore later

            for (char ch = 'a'; ch <= 'z'; ch++) {
                sb.setCharAt(i, ch);         // Replace character at position i
                String newWord = sb.toString();

                // Check if this transformed word is valid and one level closer to beginWord
                if (mpp.containsKey(newWord) && mpp.get(newWord) + 1 == val) {
                    seq.add(newWord);        // Add word to the path
                    dfs(newWord, beginWord, seq, mpp, ans);  // Recurse
                    seq.remove(seq.size() - 1);              // Backtrack
                }
            }
        }
    }
    
    public List<List<String>> findLadders(String beginWord, String endWord, List<String> wordList) {
        
        int len = beginWord.length();                 // Length of the words
        Set<String> st = new HashSet<>(wordList);     // Set for quick lookup of wordList

        Map<String, Integer> mpp = new HashMap<>();   // Map to store level (steps from beginWord)
        Queue<String> q = new LinkedList<>();         // Queue for BFS traversal

        q.add(beginWord);                             // Initialize BFS with beginWord
        st.remove(beginWord);                         // Remove from set to avoid revisiting
        mpp.put(beginWord, 1);                        // Start level is 1

        // BFS loop to find shortest paths and build the mpp map
        while (!q.isEmpty()) {
            String word = q.poll();                   // Current word
            int steps = mpp.get(word);                // Level of current word

            // Generate all one-letter transformations of the word
            for (int i = 0; i < len; i++) {
                StringBuilder sb = new StringBuilder(word);
                char original = word.charAt(i);       // Original character

                for (char ch = 'a'; ch <= 'z'; ch++) {
                    sb.setCharAt(i, ch);              // Replace character
                    String newWord = sb.toString();   // Get transformed word

                    // If it's in the word list and not yet visited
                    if (st.contains(newWord)) {
                        q.add(newWord);               // Add to BFS queue
                        mpp.put(newWord, steps + 1);  // Record level (distance from beginWord)
                        st.remove(newWord);           // Remove to avoid revisiting
                    }
                }
            }
        }

        // If endWord is not reachable, return empty result
        if (!mpp.containsKey(endWord))
            return new ArrayList<>();

        // DFS Backtracking to find all shortest sequences using the map
        List<List<String>> ans = new ArrayList<>();   // To store final result sequences
        List<String> seq = new ArrayList<>();         // Temporary path
        seq.add(endWord);                             // Start path from endWord

        dfs(endWord, beginWord, seq, mpp, ans);       // Start backtracking from end to start

        return ans;                                   // Return the final result
    }
}