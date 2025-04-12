class Solution {
    public void sortColors(int[] nums) {
        int low = 0;           // pointer for 0's boundary
        int mid = 0;           // pointer to traverse the array
        int high = nums.length - 1; // pointer for 2's boundary

        // Traverse the array till mid passes high
        while (mid <= high) {
            if (nums[mid] == 0) {
                // Swap nums[mid] and nums[low]
                int temp = nums[low];
                nums[low] = nums[mid];
                nums[mid] = temp;
                low++;  // increment low as 0 is placed at the front
                mid++;  // increment mid to move to next element
            } else if (nums[mid] == 1) {
                // Just move mid ahead, 1 is in correct position
                mid++;
            } else {
                // nums[mid] == 2
                // Swap nums[mid] and nums[high]
                int temp = nums[mid];
                nums[mid] = nums[high];
                nums[high] = temp;
                high--; // decrement high as 2 is placed at the end
                // don't increment mid here
            }
        }
    }
}
