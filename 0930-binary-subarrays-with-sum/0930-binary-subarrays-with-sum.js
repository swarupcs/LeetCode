/**
 * @param {number[]} nums
 * @param {number} goal
 * @return {number}
 */
var numSubarraysWithSum = function(nums, goal) {
    return numSubarraysWithSumLessEqualToGoal(nums, goal) - numSubarraysWithSumLessEqualToGoal(nums, goal - 1);
};

 /* Helper function to find the number of 
    subarrays with sum less than or equal to `goal` */
    function numSubarraysWithSumLessEqualToGoal(nums, goal) {
        
        /* If goal is negative, there 
        can't be any valid subarray sum */
        if (goal < 0) return 0;
        
        // Pointers to maintain the current window
        let l = 0, r = 0; 
        
        /* Variable to track the current 
        sum of elements in the window*/
        let sum = 0;      
        
        // Variable to count the number of subarrays
        let count = 0;   
        
        // Iterate through the array 
        while (r < nums.length) {
            sum += nums[r]; 
            
            /* Shrink the window from the left
            side if the sum exceeds `goal` */
            while (sum > goal) {
                sum -= nums[l];  
                
                // Move the left pointer `l` forward
                l++;            
            }
            
            /* Count all subarrays ending at
            `r` that satisfy the sum condition */
            count += (r - l + 1);
            
            // Move the right pointer `r` forward 
            r++; 
        }
        
        // Return the total count of subarrays
        return count;
    }