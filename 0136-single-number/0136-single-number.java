class Solution {
    public int singleNumber(int[] nums) {
        int n = nums.length;
        int xorr = 0;
        //xor of all the elements
        for(int i=0; i<n; i++) {
            xorr = xorr^nums[i];
        }
        return xorr;
    }
}