/**
 * @param {string} num
 * @param {number} k
 * @return {string}
 */

var removeKdigits = function(num, k) {
    let st = [];
    for(let i = 0; i < num.length; i++) {
        let digit = num[i];

        while(st.length > 0 && k > 0 && st[st.length - 1] > digit) {
            st.pop();
            k--;
        }

        st.push(digit);
    }

    while(st.length > 0 && k > 0) {
        st.pop();
        k--;
    }

    // Handling edge case
        if(st.length === 0) return "0";
        
        // To store the result
        let res = "";
        
        // Adding digits in stack to result
        while(st.length > 0) {
            res += st.pop();
        }
        
        // Trimming the zeroes at the back
        res = res.replace(/0+$/, '');
        
        // Reverse to get the actual number
        res = res.split('').reverse().join('');
        
        // Edge case
        if(res.length === 0) return "0";
        
        // Return the stored result
        return res;
};