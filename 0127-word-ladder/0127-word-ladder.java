class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        Set<String> wordSet=new HashSet<>(wordList);
        if(!wordSet.contains(endWord)) return 0;  // endWord must be in wordList

        Set<String> beginSet = new HashSet<>(), endSet = new HashSet<>();
        beginSet.add(beginWord);
        endSet.add(endWord);
        int count = 1;

        while(!beginSet.isEmpty() || !endSet.isEmpty()){
            // Always expand the smaller set
            if(beginSet.size() > endSet.size()){
                Set<String> temp = beginSet;
                beginSet = endSet;
                endSet=temp;
            }

            Set<String> nextLevel = new HashSet<>();
            for(String word : beginSet){
                char[] wordCh = word.toCharArray();
                for(int i=0; i<wordCh.length; i++){
                    char org = wordCh[i];

                    for(char c = 'a'; c <= 'z'; c++){
                        if(c==org)continue;   // Skip same letter
                        wordCh[i]=c;
                        String nWord = new String(wordCh);

                        if(endSet.contains(nWord))return count + 1;
                        if(wordSet.contains(nWord)){
                            nextLevel.add(nWord);
                            wordSet.remove(nWord);    // Remove immediately to prevent duplicate processing
                        }
                    }

                    wordCh[i] = org;    // Restore character
                }
            }
            if (nextLevel.isEmpty()) return 0;           // No valid transformation
            beginSet = nextLevel;
            count++;
        }

        return 0;

    }
}