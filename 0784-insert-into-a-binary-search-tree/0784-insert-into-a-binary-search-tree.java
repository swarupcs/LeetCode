/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */
class Solution {
    public TreeNode insertIntoBST(TreeNode root, int val) {
        // Step 1: Handle the base case where the tree is empty
        if (root == null) {
            // Create a new node with the given value and return it
            return new TreeNode(val);
        }
        
        // Step 2: Initialize a pointer to traverse the tree
        TreeNode current = root;
        
        // Step 3: Traverse the tree to find the correct insertion point
        while (true) {
            // Step 3.1: If the value is less than the current node's value
            if (val < current.val) {
                // Check if the left child is null
                if (current.left == null) {
                    // Step 3.1.1: Insert the new value as the left child
                    current.left = new TreeNode(val);
                    break; // Exit the loop after insertion
                } else {
                    // Step 3.1.2: Move to the left child
                    current = current.left;
                }
            } else {
                // Step 3.2: If the value is greater than the current node's value
                // Check if the right child is null
                if (current.right == null) {
                    // Step 3.2.1: Insert the new value as the right child
                    current.right = new TreeNode(val);
                    break; // Exit the loop after insertion
                } else {
                    // Step 3.2.2: Move to the right child
                    current = current.right;
                }
            }
        }
        
        // Step 4: Return the root node of the modified tree
        return root;
    }
    
}
