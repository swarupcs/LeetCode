/**
 * @param {number[]} nums1
 * @param {number[]} nums2
 * @return {number}
 */
var findMedianSortedArrays = function(nums1, nums2) {
    if (nums1.length > nums2.length) {
        return findMedianSortedArrays(nums2, nums1);
    }

    const n1 = nums1.length;
    const n2 = nums2.length;
    const total = n1 + n2;
    const left = Math.floor((total + 1) / 2);

    let low = 0;
    let high = n1;

    while (low <= high) {
        const mid1 = Math.floor((low + high) / 2);
        const mid2 = left - mid1;

        const l1 = mid1 > 0 ? nums1[mid1 - 1] : -Infinity;
        const r1 = mid1 < n1 ? nums1[mid1] : Infinity;

        const l2 = mid2 > 0 ? nums2[mid2 - 1] : -Infinity;
        const r2 = mid2 < n2 ? nums2[mid2] : Infinity;

        if (l1 <= r2 && l2 <= r1) {
            if (total % 2 === 1) {
                return Math.max(l1, l2);
            }

            return (
                Math.max(l1, l2) + Math.min(r1, r2)
            ) / 2;
        }

        if (l1 > r2) {
            high = mid1 - 1;
        } else {
            low = mid1 + 1;
        }
    }

    return 0;
};