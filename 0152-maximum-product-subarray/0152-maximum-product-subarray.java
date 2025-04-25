class Solution {
    public int maxProduct(int[] nums) {
       int n = nums.length;

        // Step 1: Initialize the answer with the smallest possible value
        int ans = Integer.MIN_VALUE;

        // Step 2: Initialize prefix and suffix products
        int prefix = 1;
        int suffix = 1;

        // Step 3: Traverse from both directions
        for (int i = 0; i < n; i++) {

            // Step 4: If prefix or suffix becomes 0, reset it to 1
            // Because product with 0 restarts the subarray
            if (prefix == 0) prefix = 1;
            if (suffix == 0) suffix = 1;

            // Step 5: Update prefix (left-to-right product)
            prefix *= nums[i];

            // Step 6: Update suffix (right-to-left product)
            suffix *= nums[n - i - 1];

            // Step 7: Update the answer with the max of current products
            ans = Math.max(ans, Math.max(prefix, suffix));
        }

        // Step 8: Return the maximum product found
        return ans;
    }
}