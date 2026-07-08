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
 * @param {number[]} inorder
 * @return {TreeNode}
 */
var buildTree = function(preorder, inorder) {
    const inMap = new Map();

    for (let i = 0; i < inorder.length; i++) {
        inMap.set(inorder[i], i);
    }

    const build = (preStart, preEnd, inStart, inEnd) => {
        if (preStart > preEnd || inStart > inEnd) {
            return null;
        }

        const rootVal = preorder[preStart];
        const root = new TreeNode(rootVal);

        const inRoot = inMap.get(rootVal);
        const leftSize = inRoot - inStart;

        root.left = build(
            preStart + 1,
            preStart + leftSize,
            inStart,
            inRoot - 1
        );

        root.right = build(
            preStart + leftSize + 1,
            preEnd,
            inRoot + 1,
            inEnd
        );

        return root;
    };

    return build(0, preorder.length - 1, 0, inorder.length - 1);
};