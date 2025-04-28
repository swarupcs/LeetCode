class Solution {
    public void rotate(int[][] matrix) {
        int n = matrix.length; // Step 1: Get the size of the matrix (n x n)

        // Step 2: Transpose the matrix
        for (int i = 0; i < n; i++) { // Iterate over each row
            for (int j = 0; j < i; j++) { // Iterate only over elements before the diagonal
                // Swap elements across the main diagonal (i, j) ↔ (j, i)
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }

        // Step 3: Reverse each row
        for (int i = 0; i < n; i++) { // Iterate over each row
            for (int j = 0; j < n / 2; j++) { // Iterate till half the columns
                // Swap the elements at the start and end of the row
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n - 1 - j];
                matrix[i][n - 1 - j] = temp;
            }
        }
    }
}