/**
 * @param {number} n
 * @param {number} k
 * @return {string}
 */
var getPermutation = function(n, k) {
    const nums = [];
    
    for (let i = 1; i <= n; i++) {
        nums.push(i);
    }

    const fact = Array(n).fill(1);

    for (let i = 1; i < n; i++) {
        fact[i] = fact[i - 1] * i;
    }

    k--;

    let ans = "";

    for (let i = n - 1; i >= 0; i--) {
        const index = Math.floor(k / fact[i]);

        ans += nums[index];

        nums.splice(index, 1);

        k %= fact[i];
    }

    return ans;
};