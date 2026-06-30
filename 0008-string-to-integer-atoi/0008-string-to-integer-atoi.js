/**
 * @param {string} s
 * @return {number}
 */
var myAtoi = function(s) {
    let i = 0;
    const n = s.length;

    while (i < n && s[i] === " ") {
        i++;
    }

    let sign = 1;

    if (i < n && s[i] === "-") {
        sign = -1;
        i++;
    } else if (i < n && s[i] === "+") {
        i++;
    }

    let ans = 0;
    const INT_MAX = 2147483647;
    const INT_MIN = -2147483648;

    while (i < n && s[i] >= "0" && s[i] <= "9") {
        ans = ans * 10 + (s[i] - "0");

        if (ans * sign >= INT_MAX) {
            return INT_MAX;
        }

        if (ans * sign <= INT_MIN) {
            return INT_MIN;
        }

        i++;
    }

    return ans * sign;
};