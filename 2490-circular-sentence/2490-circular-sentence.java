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
