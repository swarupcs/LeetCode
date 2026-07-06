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
var diameterOfBinaryTree = function(root) {
    let diameter = 0;

    const height = (node) => {
        if (node === null) {
            return 0;
        }

        const left = height(node.left);
        const right = height(node.right);

        diameter = Math.max(diameter, left + right);

        return 1 + Math.max(left, right);
    };

    height(root);

    return diameter;
};