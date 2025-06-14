class Solution {
    public int trap(int[] height) {
        // Step 1: Get the length of the array
        int n = height.length;

        // Step 2: Edge case - if less than 3 bars, no water can be trapped
        if (n < 3) return 0;

        // Step 3: Initialize pointers and tracking variables
        int left = 0;            // Pointer starting from left
        int right = n - 1;       // Pointer starting from right
        int leftMax = 0;         // Highest bar from the left
        int rightMax = 0;        // Highest bar from the right
        int totalWater = 0;      // Total water trapped

        // Step 4: Traverse until pointers meet
        while (left < right) {
            // Step 4.1: Compare heights at left and right
            if (height[left] <= height[right]) {

                // Step 4.2: Water trapping logic for left pointer
                if (height[left] < leftMax) {
                    // Water can be trapped above current bar
                    totalWater += leftMax - height[left];
                } else {
                    // Update leftMax if current is greater
                    leftMax = height[left];
                }
                // Move left pointer to right
                left++;
            } else {

                // Step 4.3: Water trapping logic for right pointer
                if (height[right] < rightMax) {
                    // Water can be trapped above current bar
                    totalWater += rightMax - height[right];
                } else {
                    // Update rightMax if current is greater
                    rightMax = height[right];
                }
                // Move right pointer to left
                right--;
            }
        }

        // Step 5: Return total water trapped
        return totalWater;
    }
}