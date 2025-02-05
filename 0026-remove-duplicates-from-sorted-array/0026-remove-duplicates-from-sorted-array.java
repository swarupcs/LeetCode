class Solution {
    public int removeDuplicates(int[] nums) {
        // i will track the position of unique elements
        int i = 0;
        
        // j is used to traverse the array from the second element onwards
        for(int j = 1; j < nums.length; j++) {
            // If the current element is different from the last unique element
            if(nums[j] != nums[i]) {
                // Increment i to point to the next unique spot
                i++;
                // Place the unique element at the correct position
                nums[i] = nums[j];
            }
        }
        
        // i + 1 gives the number of unique elements
        return i + 1;
    }
}
