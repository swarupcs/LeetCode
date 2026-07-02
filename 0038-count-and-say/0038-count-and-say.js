/**
 * @param {number} n
 * @return {string}
 */
var countAndSay = function(n) {
    if (n === 1) {
        return "1";
    }

    const prev = countAndSay(n - 1);
    let ans = "";
    let count = 1;

    for (let i = 1; i < prev.length; i++) {
        if (prev[i] === prev[i - 1]) {
            count++;
        } else {
            ans += count.toString();
            ans += prev[i - 1];
            count = 1;
        }
    }

    ans += count.toString();
    ans += prev[prev.length - 1];

    return ans;
};