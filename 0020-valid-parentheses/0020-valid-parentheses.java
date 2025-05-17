import java.util.Stack;

class Solution {
    // Main function to validate brackets
    public boolean isValid(String s) {
        // Step 1: Initialize a stack to keep track of opening brackets
        Stack<Character> stack = new Stack<>();

        // Step 2: Traverse the string character by character
        for (int i = 0; i < s.length(); i++) {
            char currentChar = s.charAt(i);

            // Step 3: If current character is an opening bracket, push onto stack
            if (currentChar == '(' || currentChar == '{' || currentChar == '[') {
                stack.push(currentChar);
            } 
            // Step 4: If current character is a closing bracket
            else {
                // Step 4a: If stack is empty, no matching opening bracket exists
                if (stack.isEmpty()) {
                    return false;
                }

                // Step 4b: Pop the top opening bracket from the stack
                char topChar = stack.pop();

                // Step 4c: Check if the opening and closing brackets match using helper function
                if (!isMatched(topChar, currentChar)) {
                    return false;
                }
            }
        }

        // Step 5: If stack is empty at the end, all brackets matched properly
        return stack.isEmpty();
    }

    // Helper function to check if a pair of brackets match
    private boolean isMatched(char open, char close) {
        // Match corresponding opening and closing brackets
        return (open == '(' && close == ')') ||
               (open == '{' && close == '}') ||
               (open == '[' && close == ']');
    }
}
