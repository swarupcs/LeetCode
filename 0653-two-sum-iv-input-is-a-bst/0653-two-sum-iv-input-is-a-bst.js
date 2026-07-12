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
 * @param {number} k
 * @return {boolean}
 */

class BSTIterator {
    constructor(root, reverse) {
        this.stack = [];
        this.reverse = reverse;
        this.pushAll(root);
    }

    pushAll(node) {
        while (node !== null) {
            this.stack.push(node);
            node = this.reverse ? node.right : node.left;
        }
    }

    next() {
        const node = this.stack.pop();

        if (this.reverse) {
            this.pushAll(node.left);
        } else {
            this.pushAll(node.right);
        }

        return node.val;
    }

    hasNext() {
        return this.stack.length > 0;
    }
}

var findTarget = function(root, k) {
    if (root === null) {
        return false;
    }

    const left = new BSTIterator(root, false);
    const right = new BSTIterator(root, true);

    let i = left.next();
    let j = right.next();

    while (i < j) {
        const sum = i + j;

        if (sum === k) {
            return true;
        }

        if (sum < k) {
            if (!left.hasNext()) break;
            i = left.next();
        } else {
            if (!right.hasNext()) break;
            j = right.next();
        }
    }

    return false;
};