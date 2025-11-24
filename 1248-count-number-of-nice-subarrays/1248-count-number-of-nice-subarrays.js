/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var numberOfSubarrays = function(nums, k) {
    return helper(nums, k) - helper(nums, k - 1);
};

function helper(nums, goal) {
        
        if (goal < 0) return 0;
        
        let l = 0, r = 0; 
        
        let sum = 0;      
        
        let count = 0;   

        while (r < nums.length) {
            sum += nums[r] % 2; 
            
            while (sum > goal) {
                sum -= nums[l] % 2;  
                
                l++;            
            }
            
            count += (r - l + 1);
            r++; 
        }
        
        return count;
    }