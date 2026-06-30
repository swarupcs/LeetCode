/**
 * @param {string[]} strs
 * @return {string}
 */
var longestCommonPrefix = function(strs) {
    if (strs.length === 0) {
        return "";
    }

    strs.sort();

    const first = strs[0];
    const last = strs[strs.length - 1];

    let ans = "";

    for (let i = 0; i < Math.min(first.length, last.length); i++) {
        if (first[i] !== last[i]) {
            break;
        }

        ans += first[i];
    }

    return ans;
};