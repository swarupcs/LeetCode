class Solution {

    // Main function to calculate the area of the largest rectangle of 1s
    public int maximalRectangle(char[][] matrix) {
        // Edge case: if matrix is empty
        if (matrix == null || matrix.length == 0 || matrix[0].length == 0)
            return 0;

        int n = matrix.length;     // number of rows
        int m = matrix[0].length;  // number of columns

        // Step 1: Build a matrix to represent heights of histograms row by row
        int[][] prefixSum = new int[n][m];

        // Traverse every column to compute height of consecutive '1's from top to bottom
        for (int j = 0; j < m; j++) {
            int sum = 0;
            for (int i = 0; i < n; i++) {
                // If matrix[i][j] is '0', reset height
                if (matrix[i][j] == '0') {
                    prefixSum[i][j] = 0;
                    sum = 0;
                } else {
                    // If it's '1', increment the height by 1 (from previous row)
                    sum += 1;
                    prefixSum[i][j] = sum;
                }
            }
        }

        int maxArea = 0;

        // Step 2: For every row, treat it as a histogram and compute the largest rectangle
        for (int i = 0; i < n; i++) {
            int area = largestRectangleArea(prefixSum[i]); // Histogram area of current row
            maxArea = Math.max(maxArea, area);             // Update max area if needed
        }

        return maxArea; // Final answer
    }

    // Helper function to calculate largest rectangle in histogram using stack
    private int largestRectangleArea(int[] heights) {
        int n = heights.length;
        Stack<Integer> st = new Stack<>(); // Stack to keep track of indices
        int largestArea = 0;               // Result variable to store maximum area

        // Step 1: Traverse through all bars
        for (int i = 0; i < n; i++) {
            // Step 1.1: Maintain a monotonic increasing stack
            while (!st.isEmpty() && heights[st.peek()] >= heights[i]) {
                int height = heights[st.pop()]; // Get the height at top of stack
                // Width is either full width from 0 to i if stack is empty,
                // or the distance between current index and index at new top of stack
                int width = st.isEmpty() ? i : i - st.peek() - 1;
                int area = height * width; // Calculate area using popped height
                largestArea = Math.max(largestArea, area); // Update max area
            }
            st.push(i); // Push current index into stack
        }

        // Step 2: Clean up any remaining bars in the stack
        while (!st.isEmpty()) {
            int height = heights[st.pop()];
            int width = st.isEmpty() ? n : n - st.peek() - 1;
            int area = height * width;
            largestArea = Math.max(largestArea, area);
        }

        return largestArea; // Return max histogram area
    }
}
