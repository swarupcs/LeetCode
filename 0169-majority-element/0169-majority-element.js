/**
 * @param {number[]} nums
 * @return {number}
 */
var majorityElement = function(nums) {
    let n = nums.length;
        
    let cnt = 0;
    
    let el = 0;
    
    for (let num of nums) {
        if (cnt === 0) {
            cnt = 1;
            el = num;
        } else if (el === num) {
            cnt++;
        } else {
            cnt--;
        }
    }

    let cnt1 = nums.filter(num => num === el).length;
    
    if (cnt1 > Math.floor(n / 2)) {
        return el;
    }
    
    return -1;
};