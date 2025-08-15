class Solution {
    public int[] searchRange(int[] nums, int target) {
        int[] arr = new int[2];
        int n = nums.length;
        int start = 0;
        int end = n - 1;

        while (start <= end) {
            int mid = start + (end - start) / 2;
            if (target > nums[mid]) {
                start = mid + 1;
            } else if (target < nums[mid]) {
                end = mid - 1;
            } else {
                arr[0] = findStart(nums, target, start, mid);
                arr[1] = findEnd(nums, target, mid, end);
                return arr;
            }
        }
        return new int[] { -1, -1 };

    }

    int findStart(int[] nums, int target, int start, int end) {
        while (start < end) {
            int mid = start + (end - start) / 2;
            if (nums[mid] == target) {
                end = mid;
            } else {
                start = mid + 1;
            }
        }
        return start;
    }

    int findEnd(int[] nums, int target, int start, int end) {
        while (start < end) {
            int mid = start + (end - start) / 2 + 1; 
            if (nums[mid] == target) {
                start = mid;
            } else {
                end = mid - 1;
            }
        }
        return end;
    }
}