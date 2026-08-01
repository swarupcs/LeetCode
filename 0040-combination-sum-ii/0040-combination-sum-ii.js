/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum2 = function(candidates, target) {
    let ans = [];
    let candidate = [];
    candidates.sort((a, b) => a - b);
    helper(0, target, candidates, candidate, ans);
    return ans;
};


function helper(index, target, candidates, candidate, ans) {
    if(target === 0) {
        ans.push([...candidate]);
        return;
    }

    if(target < 0 || index === candidates.length) return;

    for(let i = index; i < candidates.length; i++) {
        if(i > index && candidates[i] === candidates[i - 1]) continue;

        candidate.push(candidates[i]);

        helper(i + 1, target - candidates[i], candidates, candidate, ans);

        candidate.pop();
    }
}