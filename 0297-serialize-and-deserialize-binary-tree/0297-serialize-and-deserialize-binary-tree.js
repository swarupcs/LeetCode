/**
 * Definition for a binary tree node.
 * function TreeNode(val) {
 *     this.val = val;
 *     this.left = this.right = null;
 * }
 */

/**
 * Encodes a tree to a single string.
 *
 * @param {TreeNode} root
 * @return {string}
 */
var serialize = function(root) {
    if (root === null) {
        return "";
    }

    const res = [];
    const queue = [root];
    let front = 0;

    while (front < queue.length) {
        const node = queue[front++];

        if (node === null) {
            res.push("#");
        } else {
            res.push(node.val);
            queue.push(node.left);
            queue.push(node.right);
        }
    }

    return res.join(",");
};

/**
 * Decodes your encoded data to tree.
 *
 * @param {string} data
 * @return {TreeNode}
 */
var deserialize = function(data) {
    if (data === "") {
        return null;
    }

    const nodes = data.split(",");
    const root = new TreeNode(Number(nodes[0]));

    const queue = [root];
    let front = 0;
    let index = 1;

    while (front < queue.length) {
        const node = queue[front++];

        if (nodes[index] !== "#") {
            node.left = new TreeNode(Number(nodes[index]));
            queue.push(node.left);
        }
        index++;

        if (nodes[index] !== "#") {
            node.right = new TreeNode(Number(nodes[index]));
            queue.push(node.right);
        }
        index++;
    }

    return root;
};

/**
 * Your functions will be called as such:
 * deserialize(serialize(root));
 */