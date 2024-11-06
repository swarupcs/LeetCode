class Solution {
    public boolean canSortArray(int[] nums) {
        // Current Segment
        int numOfSetBits = Integer.bitCount(nums[0]);
        int maxOfSegment = nums[0];
        int minOfSegment = nums[0];
        int maxOfPrevSegment = Integer.MIN_VALUE;

        for (int i = 1; i < nums.length; i++) {
            if (Integer.bitCount(nums[i]) == numOfSetBits) { // they belong to the same segment
                // Find max of current segment
                maxOfSegment = Math.max(maxOfSegment, nums[i]);
                // Find min of current segment
                minOfSegment = Math.min(minOfSegment, nums[i]);
            } else { // Element belongs to a new segment

                if (minOfSegment < maxOfPrevSegment) { // condition to check proper segment arrangement
                    return false;
                }

                // Update the previous segment's max
                maxOfPrevSegment = maxOfSegment;

                // New segment starts now
                maxOfSegment = nums[i];
                minOfSegment = nums[i];
                numOfSetBits = Integer.bitCount(nums[i]);
            }
        }

        // Final check for proper segment arrangement
        if (minOfSegment < maxOfPrevSegment) {
            return false;
        }
        return true;
    }
}