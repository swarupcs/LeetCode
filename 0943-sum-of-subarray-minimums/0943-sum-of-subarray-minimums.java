class Solution {

    // Function to find the indices of Next Smaller Element (NSE)
    private int[] findNSE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop all elements greater or equal to current
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            // If stack is empty, there's no smaller to the right
            ans[i] = st.isEmpty() ? n : st.peek();

            // Push current index
            st.push(i);
        }

        return ans;
    }

    // Function to find indices of Previous Smaller or Equal Element (PSEE)
    private int[] findPSEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from left to right
        for (int i = 0; i < n; i++) {
            // Pop all strictly greater elements
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            // If stack is empty, there's no smaller on the left
            ans[i] = st.isEmpty() ? -1 : st.peek();

            // Push current index
            st.push(i);
        }

        return ans;
    }

    // Main function to calculate the sum of minimums of all subarrays
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int mod = (int)1e9 + 7;

        // Step 1: Get next and previous smaller elements' indices
        int[] nse = findNSE(arr);   // NSE[i] is the first index after i where arr[j] < arr[i]
        int[] psee = findPSEE(arr); // PSEE[i] is the last index before i where arr[j] <= arr[i]

        long sum = 0;

        // Step 2: Calculate the contribution of each element
        for (int i = 0; i < n; i++) {
            int left = i - psee[i];      // number of elements on the left
            int right = nse[i] - i;      // number of elements on the right

            long freq = (long)left * right; // total subarrays where arr[i] is min
            long contribution = (freq * arr[i]) % mod;

            sum = (sum + contribution) % mod;
        }

        return (int)sum;
    }

}
