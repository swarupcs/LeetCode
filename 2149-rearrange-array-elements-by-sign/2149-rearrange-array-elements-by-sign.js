/**
 * @param {number[]} nums
 * @return {number[]}
 */
var rearrangeArray = function(nums) {
    let posInd = 0;
    let negInd = 1;
    let ans = [];
    for(let i = 0; i < nums.length; i++) {
        if(nums[i] < 0) {
            ans[negInd] = nums[i];
            negInd += 2;
        } else {
            ans[posInd] = nums[i];
            posInd += 2;
        }
    }

    return ans;
};