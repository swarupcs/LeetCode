/**
 * @param {number[]} nums
 * @return {number}
 */
var findMaxConsecutiveOnes = function(nums) {
    let maximum = 0;
    let count = 0;
    for(let i = 0; i < nums.length; i++) {
        if(nums[i] === 1) {
            count++;
            maximum = Math.max(maximum, count);
        } else {
            
            count = 0;
        }
    }

    return maximum;
};