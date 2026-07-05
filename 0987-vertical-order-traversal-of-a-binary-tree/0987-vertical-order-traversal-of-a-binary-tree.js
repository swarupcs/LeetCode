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
var verticalTraversal = function(root) {
    if (!root) {
        return [];
    }

    const map = new Map();
    const queue = [[root, 0, 0]];
    let front = 0;

    while (front < queue.length) {
        const [node, x, y] = queue[front++];

        if (!map.has(x)) {
            map.set(x, new Map());
        }

        if (!map.get(x).has(y)) {
            map.get(x).set(y, []);
        }

        map.get(x).get(y).push(node.val);

        if (node.left) {
            queue.push([node.left, x - 1, y + 1]);
        }

        if (node.right) {
            queue.push([node.right, x + 1, y + 1]);
        }
    }

    const ans = [];
    const xKeys = [...map.keys()].sort((a, b) => a - b);

    for (const x of xKeys) {
        const yMap = map.get(x);
        const yKeys = [...yMap.keys()].sort((a, b) => a - b);

        const column = [];

        for (const y of yKeys) {
            const nodes = yMap.get(y);
            nodes.sort((a, b) => a - b);
            column.push(...nodes);
        }

        ans.push(column);
    }

    return ans;
};