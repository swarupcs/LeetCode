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
 * @return {number[]}
 */
var inorderTraversal = function(root) {
    const ans = [];
    let curr = root;

    while (curr !== null) {
        if (curr.left === null) {
            ans.push(curr.val);
            curr = curr.right;
        } else {
            let prev = curr.left;

            while (prev.right !== null && prev.right !== curr) {
                prev = prev.right;
            }

            if (prev.right === null) {
                prev.right = curr;
                curr = curr.left;
            } else {
                prev.right = null;
                ans.push(curr.val);
                curr = curr.right;
            }
        }
    }

    return ans;
};