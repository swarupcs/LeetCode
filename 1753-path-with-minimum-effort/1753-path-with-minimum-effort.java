class Solution {
    int[] delRow = {-1, 0, 1, 0};
    int[] delCol = {0, 1, 0, -1};

    private boolean isValid(int row, int col, int n, int m) {
        return row >= 0 && row < n && col >= 0 && col < m;
    } 
    public int minimumEffortPath(int[][] heights) {
        int n = heights.length;
        int m = heights[0].length;

        int[][] maxDiff = new int[n][m];

        for(int[] row : maxDiff) {
            Arrays.fill(row, Integer.MAX_VALUE);
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(Comparator.comparingInt(a -> a[0]));

        maxDiff[0][0] = 0;
        pq.add(new int[]{0, 0, 0});

        while(!pq.isEmpty()) {
            int[] p = pq.poll();
            int diff = p[0];
            int row = p[1];
            int col = p[2];

            if(row == n - 1 && col == m - 1) {
                return diff;
            }

            for(int i = 0; i < 4; i++) {
                int newRow = row + delRow[i];
                int newCol = col + delCol[i];

                if(isValid(newRow, newCol, n, m)) {
                    int currDiff = Math.abs(heights[newRow][newCol] - heights[row][col]);

                    int newEffort = Math.max(currDiff, diff);

                    if(newEffort < maxDiff[newRow][newCol]) {
                        maxDiff[newRow][newCol] = newEffort;
                        pq.add(new int[]{newEffort, newRow, newCol});
                    }
                }
            }
        }

        return -1;
        
    }
}