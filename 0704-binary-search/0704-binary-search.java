class Solution {
    // Recursive helper function to perform binary search
    private int func(int[] nums, int low, int high, int target) {
        // Base case: if low > high, target not found
        if (low > high) return -1;

        // Calculate mid index to prevent overflow
        int mid = low + (high - low) / 2;

        // If middle element is the target, return its index
        if (nums[mid] == target) return mid;

        // If target is smaller, search in the left half
        else if (nums[mid] > target)
            return func(nums, low, mid - 1, target);

        // If target is greater, search in the right half
        else
            return func(nums, mid + 1, high, target);
    }

    // Public method to start binary search on full array
    public int search(int[] nums, int target) {
        int n = nums.length;
        return func(nums, 0, n - 1, target); // Search in range [0, n-1]
    }
}
