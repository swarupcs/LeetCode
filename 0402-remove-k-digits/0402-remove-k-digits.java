class Solution {
    // Function to return the smallest number after removing k digits
    public String removeKdigits(String num, int k) {

        // Stack to store the digits of the final number
        Stack<Character> st = new Stack<>();

        // Step 1: Traverse each digit in the given number
        for (int i = 0; i < num.length(); i++) {

            char digit = num.charAt(i);  // Current digit

            // Step 2: Remove digits from stack if they are greater than current digit
            // and we still need to remove k digits
            while (!st.isEmpty() && k > 0 && st.peek() > digit) {
                st.pop();  // Remove the larger digit
                k--;       // Decrease count of digits to remove
            }

            // Step 3: Add the current digit to stack
            st.push(digit);
        }

        // Step 4: If there are still digits to remove, pop from the end (largest remaining)
        while (!st.isEmpty() && k > 0) {
            st.pop();
            k--;
        }

        // Step 5: Build the final number from stack (in reverse order)
        StringBuilder sb = new StringBuilder();
        while (!st.isEmpty()) {
            sb.append(st.pop());
        }

        // Step 6: Reverse the number to get the correct order
        sb.reverse();

        // Step 7: Remove leading zeros
        while (sb.length() > 0 && sb.charAt(0) == '0') {
            sb.deleteCharAt(0);
        }

        // Step 8: Handle empty result (when all digits removed)
        if (sb.length() == 0) return "0";

        // Step 9: Return final result
        return sb.toString();
    }
}