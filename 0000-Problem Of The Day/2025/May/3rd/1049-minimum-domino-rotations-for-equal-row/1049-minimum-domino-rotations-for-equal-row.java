class Solution {

    // Helper method to check the number of swaps needed to make all tops or bottoms equal to 'val'
    private int find(int[] tops, int[] bottoms, int val) {
        int swapTop = 0;     // Counts the swaps needed to make tops equal to 'val'
        int swapBottom = 0;  // Counts the swaps needed to make bottoms equal to 'val'
        int n = tops.length;

        for (int i = 0; i < n; i++) {
            // If neither top nor bottom has 'val', it's impossible to make that row uniform
            if (tops[i] != val && bottoms[i] != val) {
                return -1;
            }
            // If top doesn't have 'val', we need to swap this domino to bring 'val' to top
            else if (tops[i] != val) {
                swapTop++;
            }
            // If bottom doesn't have 'val', we need to swap this domino to bring 'val' to bottom
            else if (bottoms[i] != val) {
                swapBottom++;
            }
        }

        // Return the minimum swaps required (either all tops or all bottoms equal to 'val')
        return Math.min(swapTop, swapBottom);
    }

    public int minDominoRotations(int[] tops, int[] bottoms) {
        int result = Integer.MAX_VALUE; // Initialize result with a large number

        // Try all possible values (1 to 6) as potential candidates for uniform row
        for (int val = 1; val <= 6; val++) {
            int swaps = find(tops, bottoms, val); // Check minimum swaps for current value
            if (swaps != -1) {
                result = Math.min(result, swaps); // Keep track of minimum among valid results
            }
        }

        // If result is still MAX_VALUE, it means no value could make the rows uniform
        return result == Integer.MAX_VALUE ? -1 : result;
    }
}
