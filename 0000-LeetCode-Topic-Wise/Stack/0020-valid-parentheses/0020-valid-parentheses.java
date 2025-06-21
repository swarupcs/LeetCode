import java.util.Stack;

class Solution {

    // Helper function to check if open and close brackets match
    private boolean isMatched(char open, char close) {
        // Match pairs of brackets
        return (open == '(' && close == ')') ||
               (open == '[' && close == ']') ||
               (open == '{' && close == '}');
    }

    // Main function to check if the input string is valid
    public boolean isValid(String str) {

        // Step 1: Create a stack to keep track of opening brackets
        Stack<Character> st = new Stack<>();

        // Step 2: Traverse each character in the string
        for (int i = 0; i < str.length(); i++) {
            char currentChar = str.charAt(i);

            // Step 3: If it's an opening bracket, push to stack
            if (currentChar == '(' || currentChar == '[' || currentChar == '{') {
                st.push(currentChar);
            } 
            // Step 4: If it's a closing bracket
            else {
                // Step 4.1: If stack is empty, it's an unmatched closing bracket
                if (st.isEmpty()) return false;

                // Step 4.2: Pop the top element (opening bracket)
                char top = st.pop();

                // Step 4.3: Check if brackets match
                if (!isMatched(top, currentChar)) return false;
            }
        }

        // Step 5: If stack is empty, all brackets matched, return true
        return st.isEmpty();
    }

}
