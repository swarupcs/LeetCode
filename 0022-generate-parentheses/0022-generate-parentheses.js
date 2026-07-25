/**
 * @param {number} n
 * @return {string[]}
 */
var generateParenthesis = function(n) {
    let results = [];
    let current = "";
    generate(0, 0, current, n, results);
    return results;
};

function generate(open, close, current, n, results) {
    if(current.length === 2*n) {
        results.push(current);
        return;
    }

    if(open < n) {
        generate(open + 1, close, current + "(", n, results);
    }

    if(close < open) {
        generate(open, close + 1, current + ")", n, results);
    }
}