class Solution {
    public int[] nextGreaterElements(int[] nums) {
         int n = nums.length;

        // Initialize answer array with -1
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        // Stack to store indices in decreasing order
        Stack<Integer> st = new Stack<>();

        // Traverse the array twice for circular behavior
        for (int i = 2 * n - 1; i >= 0; i--) {
            // Use modulo to wrap around the array
            int idx = i % n;

            // Maintain a decreasing stack
            while (!st.isEmpty() && nums[st.peek()] <= nums[idx]) {
                st.pop(); // Remove smaller elements
            }

            // If we are in the first round, update the answer
            if (i < n) {
                if (!st.isEmpty()) {
                    ans[idx] = nums[st.peek()];
                }
            }

            // Push current index to stack
            st.push(idx);
        }

        return ans;
    }
}