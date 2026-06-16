/**
 * @param {number[]} nums
 * @return {number[][]}
 */
var subsetsWithDup = function(nums) {
    nums.sort((a, b) => a - b);

    const ans = [];

    const backtrack = (start, subset) => {
        ans.push([...subset]);

        for (let i = start; i < nums.length; i++) {
            if (i > start && nums[i] === nums[i - 1]) {
                continue;
            }

            subset.push(nums[i]);
            backtrack(i + 1, subset);
            subset.pop();
        }
    };

    backtrack(0, []);

    return ans;
};