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
 * @return {number[][]}
 */
var levelOrder = function(root) {
    if (root === null) {
        return [];
    }

    const ans = [];
    const queue = [root];
    let front = 0;

    while (front < queue.length) {
        const size = queue.length - front;
        const level = [];

        for (let i = 0; i < size; i++) {
            const node = queue[front++];

            level.push(node.val);

            if (node.left) {
                queue.push(node.left);
            }

            if (node.right) {
                queue.push(node.right);
            }
        }

        ans.push(level);
    }

    return ans;
};