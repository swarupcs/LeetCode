/**
 * @param {number[]} candidates
 * @param {number} target
 * @return {number[][]}
 */
var combinationSum = function(candidates, target) {
    let ans = [];
    let current = [];
    generate(0, ans, current, candidates, target);
    return ans;
};

function generate(index, ans, current, candidates, target) {
    if (target === 0) {
        ans.push([...current]); // copy of current
        return;
    }

    if (index === candidates.length || target < 0) return;

    // Choice 1: Skip current candidate → move to next index
    generate(index + 1, ans, current, candidates, target);

    // Choice 2: Take current candidate (can reuse same index)
    current.push(candidates[index]);
    generate(index, ans, current, candidates, target - candidates[index]);
    current.pop(); // backtrack
}
