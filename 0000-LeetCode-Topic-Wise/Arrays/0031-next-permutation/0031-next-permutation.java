class Solution {
    // Function to get the next permutation of the given array in-place
    public void nextPermutation(int[] nums) {
        int n = nums.length; // Step 1: Get the size of the array

        int ind = -1; // Step 2: Initialize index variable to store the pivot index

        // Step 3: Traverse the array from right to left to find the first decreasing element
        // Find the rightmost index where nums[i] < nums[i + 1]
        for(int i = n - 2; i >= 0; i--) {
            if(nums[i] < nums[i + 1]) {
                ind = i; // Found the pivot index
                break;   // No need to check further
            }
        }

        // Step 4: If no such index found, the array is in descending order
        // Reverse the array to get the smallest (first) permutation
        if(ind == -1) {
            reverse(nums, 0, n - 1); // Reverse the whole array
            return; // Done
        }

        // Step 5: Find the next greater element than nums[ind] from the rightmost part of the array
        for(int i = n - 1; i > ind; i--) {
            if(nums[i] > nums[ind]) {
                // Swap this element with nums[ind] to make the next greater permutation
                swap(nums, i, ind);
                break; // Break as we only want to swap once
            }
        }

        // Step 6: Reverse the part of array from ind + 1 to end
        // Because we need the smallest permutation after the pivot
        reverse(nums, ind + 1, n - 1);
    }

    // Utility function to swap two elements in the array
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];  // Temporarily store nums[i]
        nums[i] = nums[j];   // Assign nums[j] to nums[i]
        nums[j] = temp;      // Assign the temp value to nums[j]
    }

    // Utility function to reverse the subarray from index start to end
    private void reverse(int[] nums, int start, int end) {
        // Continue swapping until the pointers meet
        while(start < end) {
            swap(nums, start, end); // Swap start and end elements
            start++; // Move start pointer right
            end--;   // Move end pointer left
        }
    }
}
