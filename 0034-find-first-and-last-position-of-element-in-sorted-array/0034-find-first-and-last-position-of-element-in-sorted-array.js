/**
 * @param {number[]} nums
 * @param {number} target
 * @return {number[]}
 */
var searchRange = function(nums, target) {
    let first = findFirstOccurence(nums, target);
    let last = lastOccurence(nums, target);

    return [first, last]
};


function findFirstOccurence(nums, target) {
    let n = nums.length;
    let low = 0;
    let high = n - 1;
    let first = -1;

    while(low <= high) {
        let mid = Math.floor((low + high) / 2);

        if(nums[mid] == target) {
            first = mid;
            high = mid - 1;
        } else if(nums[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return first;
}

function lastOccurence(nums, target) {
   let n = nums.length;
    let low = 0;
    let high = n - 1;
    let last = -1;

    while(low <= high) {
        let mid = Math.floor((low + high) / 2);

        if(nums[mid] == target) {
            last = mid;
            low = mid + 1;
        } else if(nums[mid] < target) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return last;
}