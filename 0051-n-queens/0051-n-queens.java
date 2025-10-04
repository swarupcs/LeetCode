class Solution {
    // Main function to solve the N-Queens problem
    public List<List<String>> solveNQueens(int n) {
        List<List<String>> ans = new ArrayList<>(); // To store all valid board configurations
        List<String> board = new ArrayList<>(); // To represent current board state

        // Initialize an empty board with all '.'
        String s = ".".repeat(n); // Creates a string like "...."
        for(int i = 0; i < n; i++) {
            board.add(s); // Add n rows to the board
        }

        // Start the recursive backtracking from the first column
        func(0, ans, board);

        // Return the list of all possible valid configurations
        return ans;
    }

    // Recursive backtracking function to place queens column by column
    private void func(int col, List<List<String>> ans, List<String> board){
        // Base Case: All queens placed successfully
        if(col == board.size()) {
            ans.add(new ArrayList<>(board)); // Add a deep copy of current board to answer
            return;
        }

        // Try placing a queen in each row of the current column
        for(int row = 0; row < board.size(); row++) {
            if(safe(board, row, col)) { // Check if it's safe to place a queen
                // Place the queen
                char[] charArray = board.get(row).toCharArray(); // Convert row string to char array
                charArray[col] = 'Q'; // Place queen at (row, col)
                board.set(row, new String(charArray)); // Update the board with the placed queen

                // Recurse to place queen in the next column
                func(col + 1, ans, board);

                // Backtrack: remove the queen and try the next row
                charArray[col] = '.'; // Remove the queen
                board.set(row, new String(charArray)); // Update board back to original state
            }
        }
    }

    // Utility function to check if placing a queen at (row, col) is safe
    private boolean safe(List<String> board, int row, int col) {
        int r = row, c = col;

        // Check upper-left diagonal
        while (r >= 0 && c >= 0) {
            if (board.get(r).charAt(c) == 'Q') return false;
            r--;
            c--;
        }

        // Check left side of the current row
        r = row; c = col;
        while (c >= 0) {
            if (board.get(r).charAt(c) == 'Q') return false;
            c--;
        }

        // Check lower-left diagonal
        r = row; c = col;
        while (r < board.size() && c >= 0) {
            if (board.get(r).charAt(c) == 'Q') return false;
            r++;
            c--;
        }

        // If no queens are found attacking, it's safe to place
        return true;
    }
}