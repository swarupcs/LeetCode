class Solution {
    public int mySqrt(int x) {
        // Handle 0 and 1 directly: sqrt(0)=0, sqrt(1)=1
        if (x < 2) return x;

        int low = 1;
        int high = x / 2 + 1; // safe upper bound for x >= 2
        int ans = 1;          // best (floor) sqrt found so far

        while (low <= high) {
            int mid = low + (high - low) / 2; // avoid (low+high) overflow

            // Compare mid^2 <= x WITHOUT overflow by dividing
            if (mid <= x / mid) {   // equivalent to mid*mid <= x, but safe
                ans = mid;          // mid is a valid floor candidate
                low = mid + 1;      // try to find a larger sqrt
            } else {
                high = mid - 1;     // mid^2 > x, go smaller
            }
        }

        return ans; // floor(sqrt(x))
    }
}
