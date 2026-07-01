/**
 * @param {string} s
 * @return {number}
 */
var minInsertions = function(s) {
    const t = s.split("").reverse().join("");
    const n = s.length;

    let prev = new Array(n + 1).fill(0);
    let curr = new Array(n + 1).fill(0);

    for (let i = 1; i <= n; i++) {
        for (let j = 1; j <= n; j++) {
            if (s[i - 1] === t[j - 1]) {
                curr[j] = 1 + prev[j - 1];
            } else {
                curr[j] = Math.max(prev[j], curr[j - 1]);
            }
        }

        [prev, curr] = [curr, new Array(n + 1).fill(0)];
    }

    return n - prev[n];
};