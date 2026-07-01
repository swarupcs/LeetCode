/**
 * @param {string} haystack
 * @param {string} needle
 * @return {number}
 */
var strStr = function(haystack, needle) {
    if (needle.length === 0) {
        return 0;
    }

    const s = needle + "$" + haystack;
    const lps = new Array(s.length).fill(0);

    let i = 1;
    let j = 0;

    while (i < s.length) {
        if (s[i] === s[j]) {
            lps[i] = j + 1;
            i++;
            j++;
        } else {
            while (j > 0 && s[i] !== s[j]) {
                j = lps[j - 1];
            }

            if (s[i] === s[j]) {
                lps[i] = j + 1;
                j++;
            }

            i++;
        }
    }

    for (let i = needle.length + 1; i < s.length; i++) {
        if (lps[i] === needle.length) {
            return i - 2 * needle.length;
        }
    }

    return -1;
};