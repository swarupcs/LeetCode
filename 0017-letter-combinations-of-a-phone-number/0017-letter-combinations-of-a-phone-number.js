/**
 * @param {string} digits
 * @return {string[]}
 */
var letterCombinations = function(digits) {
    let ans = [];
    let current = "";
    helper(digits, ans, 0, current);
    return ans;    
};

function helper(digits, ans, index, current) {
    const mapp = ["", "", "abc", "def", "ghi", "jkl", "mno", "pqrs", "tuv", "wxyz"];
    if(index === digits.length) {
        ans.push(current);
        return;
    }

    let s = mapp[digits[index] - '0'];

    for(let i = 0; i < s.length; i++) {
        helper(digits, ans, index + 1, current + s[i]);
    }
}