class Solution {
    public String pushDominoes(String dominoes) {
        int n = dominoes.length();

        // Arrays to store the closest L to the right and R to the left for each index
        int[] rightClosestL = new int[n];
        int[] leftClosestR = new int[n];

        // Step 1: Fill rightClosestL by scanning from right to left
        for (int i = n - 1; i >= 0; i--) {
            if (dominoes.charAt(i) == 'L') {
                // Current domino is L, so closest L is at i
                rightClosestL[i] = i;
            } else if (dominoes.charAt(i) == '.') {
                // Inherit closest L from the right neighbor
                rightClosestL[i] = i < n - 1 ? rightClosestL[i + 1] : -1;
            } else {
                // Current domino is R, no L to the right of it
                rightClosestL[i] = -1;
            }
        }

        // Step 2: Fill leftClosestR by scanning from left to right
        for (int i = 0; i < n; i++) {
            if (dominoes.charAt(i) == 'R') {
                // Current domino is R, so closest R is at i
                leftClosestR[i] = i;
            } else if (dominoes.charAt(i) == '.') {
                // Inherit closest R from the left neighbor
                leftClosestR[i] = i > 0 ? leftClosestR[i - 1] : -1;
            } else {
                // Current domino is L, no R to the left of it
                leftClosestR[i] = -1;
            }
        }

        // Step 3: Determine the final state of each domino
        StringBuilder result = new StringBuilder();

        for (int i = 0; i < n; i++) {
            int distRightL = rightClosestL[i] == -1 ? Integer.MAX_VALUE : Math.abs(i - rightClosestL[i]);
            int distLeftR = leftClosestR[i] == -1 ? Integer.MAX_VALUE : Math.abs(i - leftClosestR[i]);

            if (dominoes.charAt(i) != '.') {
                // Already pushed domino remains the same
                result.append(dominoes.charAt(i));
            } else if (distLeftR == distRightL) {
                // Forces are equal or no forces, remains upright
                result.append('.');
            } else if (distRightL < distLeftR) {
                // Closer to an L domino
                result.append('L');
            } else {
                // Closer to an R domino
                result.append('R');
            }
        }

        return result.toString();
    }
}
