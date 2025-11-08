/**
 * @param {number[]} nums
 * @return {boolean}
 */
var canJump = function(nums) {
    let maxIndex = 0;

    for(let i = 0; i < nums.length; i++) {
        if(i > maxIndex) {
            return false;
        }

        maxIndex = Math.max(maxIndex, nums[i] + i);
    }

    return true;
    
};