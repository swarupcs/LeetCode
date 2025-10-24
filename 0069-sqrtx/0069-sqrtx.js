/**
 * @param {number} x
 * @return {number}
 */
var mySqrt = function(x) {
     if (x === 0 || x === 1) return x;
    let low = 1;
    let high = x;
    let ans = 0;
    while(low <= high) {
        let mid = Math.floor((low + high) / 2);
        let val = mid * mid;

        if(val == x) {
            return mid;
        } else if(val < x) {
            ans = mid;
            low = mid + 1;
        } else {
            high = mid - 1;
        }
    }

    return ans;
};