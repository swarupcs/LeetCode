class Solution {
    public void merge(int[] nums1, int m, int[] nums2, int n) {
        // Pointer for nums1 (end of valid elements)
        int left = m - 1;
        
        // Pointer for nums2 (beginning of valid elements)
        int right = 0;
        
        /* Swap the elements until nums1[left]
        is smaller than nums2[right]*/
        while (left >= 0 && right < n) {
            if (nums1[left] > nums2[right]) {
                int temp = nums1[left];
                nums1[left] = nums2[right];
                nums2[right] = temp;
                left--;
                right++;
            } 
            //break otherwise
            else break;
        }
        
        // Sort nums1 from index 0 to m-1
        Arrays.sort(nums1, 0, m);
        
        // Sort nums2 from start to end
        Arrays.sort(nums2);
        
        // Put the elements of nums2 in nums1
        for (int i = m; i < m + n; i++) {
            nums1[i] = nums2[i - m];
        }
        
    }
}