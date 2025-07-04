class Solution {
    public List<List<Integer>> fourSum(int[] nums, int target) {
        // Initialize a list to store the final list of quadruplets
        List<List<Integer>> ans = new ArrayList<>();

        int n = nums.length;

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Start the first loop to fix the first element
        for (int i = 0; i < n; i++) {
            // Step 2a: Skip duplicate values for the first element
            if (i > 0 && nums[i] == nums[i - 1])
                continue;

            // Step 3: Start the second loop to fix the second element
            for (int j = i + 1; j < n; j++) {
                // Step 3a: Skip duplicate values for the second element
                if (j > i + 1 && nums[j] == nums[j - 1])
                    continue;

                // Step 4: Initialize two pointers
                int k = j + 1;
                int l = n - 1;

                // Step 5: Use two pointers to find the other two numbers
                while (k < l) {
                    // Step 5a: Calculate the sum, cast to long to avoid integer overflow
                    long sum = (long) nums[i] + nums[j] + nums[k] + nums[l];

                    // Step 5b: If sum matches the target, add the quadruplet
                    if (sum == target) {
                        // Add the quadruplet to the answer list
                        List<Integer> temp = Arrays.asList(nums[i], nums[j], nums[k], nums[l]);
                        ans.add(temp);

                        // Step 5c: Move both pointers and skip duplicate values
                        k++;
                        l--;

                        // Skip duplicates for the third number
                        while (k < l && nums[k] == nums[k - 1])
                            k++;
                        // Skip duplicates for the fourth number
                        while (k < l && nums[l] == nums[l + 1])
                            l--;
                    }
                    // Step 5d: If sum is smaller, move the left pointer to increase the sum
                    else if (sum < target) {
                        k++;
                    }
                    // Step 5e: If sum is larger, move the right pointer to decrease the sum
                    else {
                        l--;
                    }
                }
            }
        }

        // Step 6: Return the final list of quadruplets
        return ans;
    }
}
