class Solution {

    // Recursive function to check if the word can be formed starting from (i, j)
    private boolean func(char[][] board, int i, int j, String word, int k) {
        // Base case: if all characters are matched
        if (k == word.length()) return true;

        // Check boundaries and character mismatch
        if (i < 0 || j < 0 || i >= board.length || j >= board[0].length || board[i][j] != word.charAt(k)) {
            return false;
        }

        // Store current character and mark cell as visited
        char temp = board[i][j];
        board[i][j] = ' '; // mark as visited

        // Recursive DFS: check all 4 directions
        boolean found = func(board, i + 1, j, word, k + 1) ||  // down
                        func(board, i - 1, j, word, k + 1) ||  // up
                        func(board, i, j + 1, word, k + 1) ||  // right
                        func(board, i, j - 1, word, k + 1);    // left

        // Backtrack: restore original character
        board[i][j] = temp;

        return found;
    }

    // Main function to check if the word exists in the grid
    public boolean exist(char[][] board, String word) {
        int m = board.length;
        int n = board[0].length;

        // Try every cell as starting point
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                // If first character matches, start DFS
                if (board[i][j] == word.charAt(0)) {
                    if (func(board, i, j, word, 0)) return true;
                }
            }
        }

        // If not found
        return false;
    }
}
