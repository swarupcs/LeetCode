/**
 * @param {number[]} nums
 * @return {number}
 */
var maxProduct = function(nums) {
    let ans = Number.MIN_SAFE_INTEGER;

    let n = nums.length;

    let prefix = 1;
    let suffix = 1;
    for(let i = 0; i < n; i++) {
        if(prefix == 0) {
            prefix = 1;
        }
        if(suffix == 0) {
            suffix = 1;
        }

        prefix = prefix * nums[i];
        suffix = suffix * nums[n - i -1];

        ans = Math.max(ans, prefix, suffix);
    }

    if(ans == -0) {
        return 0;
    }
    return ans;
};