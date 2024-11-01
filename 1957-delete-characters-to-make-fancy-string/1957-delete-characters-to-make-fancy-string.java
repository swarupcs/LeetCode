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
