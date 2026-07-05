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
var widthOfBinaryTree = function(root) {
    if (root === null) {
        return 0;
    }

    let maxWidth = 0;
    const queue = [[root, 0]];
    let front = 0;

    while (front < queue.length) {
        const size = queue.length - front;
        const minIndex = queue[front][1];

        let first = 0;
        let last = 0;

        for (let i = 0; i < size; i++) {
            const [node, index] = queue[front++];
            const curr = index - minIndex;

            if (i === 0) {
                first = curr;
            }

            if (i === size - 1) {
                last = curr;
            }

            if (node.left) {
                queue.push([node.left, 2 * curr + 1]);
            }

            if (node.right) {
                queue.push([node.right, 2 * curr + 2]);
            }
        }

        maxWidth = Math.max(maxWidth, last - first + 1);
    }

    return maxWidth;
};