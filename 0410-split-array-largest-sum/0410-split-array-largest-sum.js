/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var splitArray = function(nums, k) {
    let low = Math.max(...nums);
    let high = nums.reduce((acc, curr) => acc + curr, 0);

    while(low <= high) {
        let mid = Math.floor((low + high) / 2);
        let partitions = countPartitions(nums, mid);

        if(partitions > k) {
            low = mid + 1;
        } else {
            high = mid - 1;
        }

    }
    return low;
    
};

function countPartitions(a, maxSum) {
    let n = a.length;
    let partitions = 1;
    let subarraySum = 0;

    for(let i = 0; i < n; i++) {
        if(subarraySum + a[i] <= maxSum) {
            subarraySum += a[i];
        } else {
            partitions++;
            subarraySum = a[i];
        }
    }

    return partitions;
}