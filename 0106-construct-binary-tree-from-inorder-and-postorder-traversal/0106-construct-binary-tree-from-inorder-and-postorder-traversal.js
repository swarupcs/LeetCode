/**
 * Definition for a binary tree node.
 * function TreeNode(val, left, right) {
 *     this.val = (val === undefined ? 0 : val)
 *     this.left = (left === undefined ? null : left)
 *     this.right = (right === undefined ? null : right)
 * }
 */

/**
 * @param {number[]} inorder
 * @param {number[]} postorder
 * @return {TreeNode}
 */
var buildTree = function(inorder, postorder) {
    const inMap = new Map();

    for (let i = 0; i < inorder.length; i++) {
        inMap.set(inorder[i], i);
    }

    const build = (inStart, inEnd, postStart, postEnd) => {
        if (inStart > inEnd || postStart > postEnd) {
            return null;
        }

        const rootVal = postorder[postEnd];
        const root = new TreeNode(rootVal);

        const inRoot = inMap.get(rootVal);
        const leftSize = inRoot - inStart;

        root.left = build(
            inStart,
            inRoot - 1,
            postStart,
            postStart + leftSize - 1
        );

        root.right = build(
            inRoot + 1,
            inEnd,
            postStart + leftSize,
            postEnd - 1
        );

        return root;
    };

    return build(
        0,
        inorder.length - 1,
        0,
        postorder.length - 1
    );
};