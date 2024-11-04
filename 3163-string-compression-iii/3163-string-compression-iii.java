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
