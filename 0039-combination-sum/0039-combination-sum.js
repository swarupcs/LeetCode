/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum = function(candidates, target) {
    let ans = [];
    let combination = [];

    helper(0, candidates, combination, target, ans);
    return ans;
};

function helper(index, candidates, combination, target, ans) {
    if(target === 0) {
        ans.push([...combination]);
        return;
    }

    if(index === candidates.length || target < 0) {
        return;
    }

    combination.push(candidates[index]);

    helper(index, candidates, combination, target - candidates[index], ans);

    combination.pop();

    helper(index + 1, candidates, combination, target, ans);

}