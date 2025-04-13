class Solution {
     // Function to reverse the array between start and end
    private void reverseArray(int[] nums, int start, int end) {

        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
    public void rotate(int[] nums, int k) {
        int n = nums.length;
        k = k % n; // Avoid extra rotations

        // Step 1: Reverse entire array
        reverseArray(nums, 0, n - 1);

        // Step 2: Reverse first k elements
        reverseArray(nums, 0, k - 1);

        // Step 3: Reverse remaining n-k elements
        reverseArray(nums, k, n - 1);
    }

}