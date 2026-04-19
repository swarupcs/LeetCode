/**
 * @param {number[]} nums
 * @return {number}
 */
var reversePairs = function(nums) {
    return mergeSort(nums, 0, nums.length - 1);
};

// Merge sort function
    function mergeSort(nums, low, high) {
        if (low >= high) return 0;

        let mid = Math.floor((low + high) / 2);
        let cnt = 0;

        cnt += mergeSort(nums, low, mid);
        cnt += mergeSort(nums, mid + 1, high);
        cnt += countPairs(nums, low, mid, high);
        merge(nums, low, mid, high);

        return cnt;
    }

    // Count reverse pairs
    function countPairs(nums, low, mid, high) {
        let cnt = 0, right = mid + 1;

        for (let i = low; i <= mid; i++) {
            while (right <= high && nums[i] > 2 * nums[right]) {
                right++;
            }
            cnt += right - (mid + 1);
        }

        return cnt;
    }

    // Merge two sorted halves
    function merge(nums, low, mid, high) {
        let temp = [];
        let left = low, right = mid + 1;

        while (left <= mid && right <= high) {
            if (nums[left] <= nums[right]) {
                temp.push(nums[left++]);
            } else {
                temp.push(nums[right++]);
            }
        }

        while (left <= mid) temp.push(nums[left++]);
        while (right <= high) temp.push(nums[right++]);

        for (let i = low; i <= high; i++) {
            nums[i] = temp[i - low];
        }
    }