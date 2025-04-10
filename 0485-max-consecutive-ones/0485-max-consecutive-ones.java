class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        // Step 1: Initialize variables
        int count = 0;     // Tracks the current streak of consecutive 1s
        int maximum = 0;   // Stores the maximum streak of consecutive 1s

        // Step 2: Traverse through the array
        for(int i = 0; i < nums.length; i++) {
            // Step 3: If the current element is 1, increase the count
            if(nums[i] == 1) {
                count++; // Increment the count for consecutive 1s
                
                // Step 4: Update the maximum if the current streak is greater
                maximum = Math.max(count, maximum);
            } else {
                // Step 5: If we encounter a 0, reset the count to 0
                count = 0;
            }
        }

        // Step 6: Return the maximum count of consecutive 1s found
        return maximum;
    }
}
