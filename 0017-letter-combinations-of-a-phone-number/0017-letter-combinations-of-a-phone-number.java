

class Solution {
    // Mapping from digits to letters, just like a telephone keypad
    private final String[] map;

    public Solution() {
        // Index 0 and 1 are empty because digits '0' and '1' do not map to any letters
        map = new String[]{"", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"};
    }

    // Helper function using backtracking
    private void helper(String digits, List<String> ans, int index, String current) {
        // Step 1: Base Case – when index reaches the end of digits
        if (index == digits.length()) {
            ans.add(current); // Add current combination to result
            return;
        }

        // Step 2: Get the letters corresponding to the current digit
        String letters = map[digits.charAt(index) - '0'];

        // Step 3: Loop through each letter and recurse
        for (int i = 0; i < letters.length(); i++) {
            // Step 4: Add current letter to the string and recurse to next digit
            helper(digits, ans, index + 1, current + letters.charAt(i));
        }
    }

    // Main function to return all possible letter combinations
    public List<String> letterCombinations(String digits) {
        List<String> ans = new ArrayList<>(); // Result list

        // Edge case: return empty list if digits string is empty
        if (digits.length() == 0) return ans;

        // Start recursion from index 0 with empty current string
        helper(digits, ans, 0, "");
        return ans; // Return the result list
    }

}
