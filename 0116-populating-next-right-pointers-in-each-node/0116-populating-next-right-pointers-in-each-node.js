/**
 * // Definition for a _Node.
 * function _Node(val, left, right, next) {
 *    this.val = val === undefined ? null : val;
 *    this.left = left === undefined ? null : left;
 *    this.right = right === undefined ? null : right;
 *    this.next = next === undefined ? null : next;
 * };
 */

/**
 * @param {_Node} root
 * @return {_Node}
 */
var connect = function(root) {
    if (root === null) {
        return null;
    }

    let levelStart = root;

    while (levelStart.left !== null) {
        let curr = levelStart;

        while (curr !== null) {
            curr.left.next = curr.right;

            if (curr.next !== null) {
                curr.right.next = curr.next.left;
            }

            curr = curr.next;
        }

        levelStart = levelStart.left;
    }

    return root;
};