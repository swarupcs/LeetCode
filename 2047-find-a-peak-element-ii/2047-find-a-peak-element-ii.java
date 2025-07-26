class Solution {
    public int[] findPeakGrid(int[][] mat) {
        int n = mat.length;
        int m = mat[0].length;

        int low = 0;
        int high = m - 1;

        while(low <= high) {
            int mid = (low + high) / 2;
            int row = maxElement(mat, mid);
            int left = (mid - 1) >= 0 ? mat[row][mid - 1] : Integer.MIN_VALUE;
            int right = (mid + 1) < m ? mat[row][mid + 1] : Integer.MIN_VALUE;

            if(mat[row][mid] > left && mat[row][mid] > right) {
                return new int[]{row, mid};
            } else if(mat[row][mid] < left) {
                high = mid + 1;
            } else {
               low = mid + 1;
            }

        }

        return new int[] {-1, -1};
        
    }

    private int maxElement(int[][] mat, int col) {
        int n = mat.length;
        int maxVal = Integer.MIN_VALUE;
        int index = -1;

        for(int i = 0; i < n; i++) {
            if(mat[i][col] > maxVal) {
                maxVal = mat[i][col];
                index = i;
            }
        }

        return index;


    }
}