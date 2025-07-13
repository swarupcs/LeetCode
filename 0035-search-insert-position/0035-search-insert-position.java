class Solution {
    public int searchInsert(int[] nums, int target) {
        int n = nums.length;
        int low = 0, high = n - 1;

        int ans = n; // Default insert at end if not found

        // Binary search loop
        while (low <= high) {
            int mid = (low + high) / 2; // Calculate mid index

            // If mid element is >= target, it could be the answer
            if (nums[mid] >= target) {
                ans = mid;        // Update answer with current mid
                high = mid - 1;   // Move to left half
            } else {
                // If mid element < target, move to right half
                low = mid + 1;
            }
        }

        // Return final answer (insert position or found index)
        return ans;
    }
}