class Solution {
    public int majorityElement(int[] nums) {
        int n = nums.length; // Get the size of the array
        int count = 0; // Vote count
        int element = 0; // Majority candidate

        // Step 1: Apply Boyer-Moore Voting Algorithm
        for (int i = 0; i < n; i++) {
            if (count == 0) { // If count is 0, pick a new candidate
                count = 1;
                element = nums[i];
            } else if (element == nums[i]) { // If same as candidate, increase count
                count++;
            } else { // Otherwise, decrease count
                count--;
            }
        }

        // Step 2: Verify if 'element' is actually the majority element
        int count1 = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] == element) {
                count1++; // Count occurrences of the candidate
            }
        }

        // Step 3: Check if candidate appears more than n/2 times
        if (count1 > n / 2) {
            return element; // Return majority element
        }

        return -1; // Return -1 if no majority element found (though problem guarantees one exists)
    }
}
