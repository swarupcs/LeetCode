class Solution {
    static class Pair{
        String word;
        int steps;

        Pair(String word, int steps) {
            this.word = word;
            this.steps = steps;
        }
    }

    private int bfs(String beginWord, String endWord, Set<String> wordSet) {
        Queue<Pair> queue = new LinkedList<>();

        queue.add(new Pair(beginWord, 1));

        wordSet.remove(beginWord);

        while(!queue.isEmpty()) {

            String word = queue.peek().word;
            int steps = queue.peek().steps;

            queue.poll();

            if(word.equals(endWord)) {
                return steps;
            }

            // Iterate through every character in the word
            for (int i = 0; i < word.length(); i++) {

                // Convert word to character array for easy modification
                char[] wordArray = word.toCharArray();

                // Try replacing it with every letter from 'a' to 'z'
                for (char ch = 'a'; ch <= 'z'; ch++) {

                    // Skip if the letter is same as the original
                    if (wordArray[i] == ch) continue;

                    // Create a new word by modifying one character
                    wordArray[i] = ch;
                    String newWord = new String(wordArray);

                    // Check if the modified word exists in the set
                    if (wordSet.contains(newWord)) {

                        // Remove the word from set to avoid revisiting
                        wordSet.remove(newWord);

                        // Push the new word with incremented step count
                        queue.add(new Pair(newWord, steps + 1));
                    }
                }
            }
        }
        return 0;
    }

    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet = new HashSet<>(wordList);

        if(!wordSet.contains(endWord)) {
            return 0;
        }

        return bfs(beginWord, endWord, wordSet);        
    }
}