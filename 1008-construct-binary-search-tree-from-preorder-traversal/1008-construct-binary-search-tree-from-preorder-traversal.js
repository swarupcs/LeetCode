/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val === undefined ? 0 : val)
 *     this.left = (left === undefined ? null : left)
 *     this.right = (right === undefined ? null : right)
 * }
 */

/**
 * @param {number[]} preorder
 * @return {TreeNode}
 */
var bstFromPreorder = function(preorder) {
    let index = 0;

    const build = (bound) => {
        if (index === preorder.length || preorder[index] > bound) {
            return null;
        }

        const root = new TreeNode(preorder[index++]);

        root.left = build(root.val);
        root.right = build(bound);

        return root;
    };

    return build(Infinity);
};