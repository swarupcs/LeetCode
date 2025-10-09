/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var twoSum = function(nums, target) {
    let n = nums.length;
    let eleIndex = [];

    // Step 1: store [value, index]
    for (let i = 0; i < n; i++) {
        eleIndex.push([nums[i], i]);
    }

    // Step 2: sort by value
    eleIndex.sort((a, b) => a[0] - b[0]);

    // Step 3: two-pointer approach
    let left = 0, right = n - 1;

    while (left < right) {
        let sum = eleIndex[left][0] + eleIndex[right][0];

        if (sum === target) {
            const i1 = eleIndex[left][1];
            const i2 = eleIndex[right][1];
            // Return in non-decreasing order
            return i1 < i2 ? [i1, i2] : [i2, i1];
        } else if (sum < target) {
            left++;
        } else {
            right--;
        }
    }

    return [-1, -1]; // fallback
};
