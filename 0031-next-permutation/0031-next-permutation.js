/**
 * @param {number[]} nums
 * @return {void} Do not return anything, modify nums in-place instead.
 */
var nextPermutation = function(nums) {
    let n = nums.length;

    let ind = -1;

    for(let i = n - 2; i >= 0; i--) {
        if(nums[i] < nums[i + 1]) {
            ind = i;
            break;
        }
    }

    if(ind == -1) {
        nums.reverse();
        return;
    }

    for(let i = n - 1; i > ind; i--) {
        if(nums[i] > nums[ind]) {
            [nums[i], nums[ind]] = [nums[ind], nums[i]];
            break;
        }
    }

    let left = ind + 1, right = n - 1;

    while(left < right) {
        [nums[left], nums[right]] = [nums[right], nums[left]];
        left++;
        right--;
    }

    return;
    
};