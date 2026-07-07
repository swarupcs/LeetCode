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
var zigzagLevelOrder = function(root) {
    if (root === null) {
        return [];
    }

    const ans = [];
    const queue = [root];
    let front = 0;
    let leftToRight = true;

    while (front < queue.length) {
        const size = queue.length - front;
        const level = new Array(size);

        for (let i = 0; i < size; i++) {
            const node = queue[front++];

            const index = leftToRight ? i : size - 1 - i;
            level[index] = node.val;

            if (node.left) {
                queue.push(node.left);
            }

            if (node.right) {
                queue.push(node.right);
            }
        }

        ans.push(level);
        leftToRight = !leftToRight;
    }

    return ans;
};