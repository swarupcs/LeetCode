class Solution {
    public int findMin(int[] nums) {
 if (nums == null || nums.length == 0) { // 0 element
            return Integer.MAX_VALUE;
        }
        int left = 0;
        int right = nums.length - 1;
        while (left + 1 < right) { // at least 3 elements
            int mid = left + (right - left) / 2;
            if (nums[mid] > nums[left] && nums[mid] > nums[right]) {
                left = mid;
            } else {
                right = mid;
            }
        }
        return Math.min(nums[left], nums[right]);
    }
}