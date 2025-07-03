class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // List to store the final triplets
        List<List<Integer>> ans = new ArrayList<>();

        // Get the number of elements
        int n = nums.length;

        // Step 1: Sort the array
        Arrays.sort(nums);

        // Step 2: Traverse each element as the first number of triplet
        for (int i = 0; i < n; i++) {

            // Step 2.1: Skip duplicate first elements to avoid duplicate triplets
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // Step 3: Initialize two pointers
            int j = i + 1;     // Left pointer
            int k = n - 1;     // Right pointer

            // Step 4: Find pairs (j, k) such that nums[i] + nums[j] + nums[k] == 0
            while (j < k) {
                int sum = nums[i] + nums[j] + nums[k];

                if (sum < 0) {
                    // Step 4.1: Sum too small ➔ Move left pointer to right
                    j++;
                } else if (sum > 0) {
                    // Step 4.2: Sum too big ➔ Move right pointer to left
                    k--;
                } else {
                    // Step 4.3: Found a triplet
                    List<Integer> temp = new ArrayList<>();
                    temp.add(nums[i]);
                    temp.add(nums[j]);
                    temp.add(nums[k]);
                    ans.add(temp);

                    // Step 5: Move both pointers to next unique elements
                    j++;
                    k--;

                    // Skip duplicate numbers at j
                    while (j < k && nums[j] == nums[j - 1]) j++;

                    // Skip duplicate numbers at k
                    while (j < k && nums[k] == nums[k + 1]) k--;
                }
            }
        }

        // Step 6: Return the list of triplets
        return ans;
    }
}