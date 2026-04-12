class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;        // number of rows
        int m = mat[0].length;     // number of columns

        int low = 0;               // leftmost column index
        int high = m - 1;          // rightmost column index

        // Binary search on columns
        while (low <= high) {
            int mid = (low + high) / 2;    // Current column under consideration

            // Step 1: Find the row (index) of the maximum element in column `mid`
            int row = maxElement(mat, mid);
            // Get value of the left neighbor, or -inf if out of bounds
            int left = (mid - 1) >= 0 ? mat[row][mid - 1] : Integer.MIN_VALUE;
            // Get value of the right neighbor, or -inf if out of bounds
            int right = (mid + 1) < m ? mat[row][mid + 1] : Integer.MIN_VALUE;

            // Step 2: Check if current is peak
            if (mat[row][mid] > left && mat[row][mid] > right) {
                // Found a peak
                return new int[]{row, mid};
            } else if (mat[row][mid] < left) {
                // There is a bigger neighbor on the left; search left half
                high = mid - 1;
            } else {
                // There is a bigger neighbor on the right; search right half
                low = mid + 1;
            }
        }
        // Peak not found (not expected given constraints)
        return new int[] {-1, -1};
    }

    // Given a column index, return the row index of the max element in the column
    private int maxElement(int[][] mat, int col) {
        int n = mat.length;
        int maxVal = Integer.MIN_VALUE;
        int index = -1;
        // Scan all rows in this column to find the largest value
        for (int i = 0; i < n; i++) {
            if (mat[i][col] > maxVal) {
                maxVal = mat[i][col];
                index = i;
            }
        }
        return index;
    }
}
