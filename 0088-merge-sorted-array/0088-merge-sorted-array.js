/**
 * @param {number[]} nums1
 * @param {number} m
 * @param {number[]} nums2
 * @param {number} n
 * @return {void} Do not return anything, modify nums1 in-place instead.
 */
var merge = function(nums1, m, nums2, n) {
    let left = m - 1;

    let right = 0;
    
    while (left >= 0 && right < n) {
        if (nums1[left] > nums2[right]) {
            [nums1[left], nums2[right]] = [nums2[right], nums1[left]];
            left--;
            right++;
        } else {
            break;
        }
    }
    
    let sortedSlice = nums1.slice(0, m).sort((a, b) => a - b);

    nums1.splice(0, sortedSlice.length, ...sortedSlice);
        
    nums2.sort((a, b) => a - b);

    for (let i = m; i < m + n; i++) {
        nums1[i] = nums2[i - m];
    }
};