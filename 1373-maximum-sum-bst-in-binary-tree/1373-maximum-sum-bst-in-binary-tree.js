/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val === undefined ? 0 : val)
 *     this.left = (left === undefined ? null : left)
 *     this.right = (right === undefined ? null : right)
 * }
 */

/**
 * @param {TreeNode} root
 * @return {number}
 */
var maxSumBST = function(root) {
    let maxSum = 0;

    const dfs = (node) => {
        // isBST, min, max, sum
        if (node === null) {
            return [true, Infinity, -Infinity, 0];
        }

        const [leftBST, leftMin, leftMax, leftSum] = dfs(node.left);
        const [rightBST, rightMin, rightMax, rightSum] = dfs(node.right);

        if (
            leftBST &&
            rightBST &&
            node.val > leftMax &&
            node.val < rightMin
        ) {
            const sum = leftSum + rightSum + node.val;
            maxSum = Math.max(maxSum, sum);

            return [
                true,
                Math.min(leftMin, node.val),
                Math.max(rightMax, node.val),
                sum
            ];
        }

        return [false, -Infinity, Infinity, 0];
    };

    dfs(root);

    return maxSum;
};