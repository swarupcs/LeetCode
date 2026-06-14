/**
 * @param {number[]} height
 * @return {number}
 */
var trap = function(height) {
    let n = height.length;

    let total = 0;

    let leftMax = 0, rightMax = 0;
    
    let left = 0, right = n - 1;

    while (left < right) {

        if (height[left] <= height[right]) {
            
            if (leftMax > height[left]) {

                total += leftMax - height[left];
            }
            
            else leftMax = height[left];
            
            left = left + 1;
        }
        else {
            
            if (rightMax > height[right]) {
                
                // Update total water
                total += rightMax - height[right];
            }
            
            // Else update maximum height on right
            else rightMax = height[right];
            
            // Shift right by 1
            right = right - 1;
        }
    }
    
    // Return the result
    return total;
};