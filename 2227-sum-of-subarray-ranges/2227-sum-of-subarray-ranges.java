class Solution {

    // Function to find the Next Smaller Element (NSE) index for each element in the array
    private int[] findNSE(int[] arr) {
        int n = arr.length; // Get the length of the array
        int[] ans = new int[n]; // Initialize answer array
        Stack<Integer> st = new Stack<>(); // Stack to store indices

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements from stack while current element is smaller
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            // If stack is not empty, top is the NSE index; else it's n
            ans[i] = !st.isEmpty() ? st.peek() : n;
            st.push(i); // Push current index to stack
        }
        return ans; // Return NSE index array
    }

    // Function to find the Previous Smaller or Equal Element (PSEE) index
    private int[] findPSEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from left to right
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop(); // Pop elements greater than current
            }
            ans[i] = !st.isEmpty() ? st.peek() : -1; // Store index or -1
            st.push(i); // Push current index
        }
        return ans; // Return PSEE index array
    }

    // Function to find the Next Greater Element (NGE) index
    private int[] findNGE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop(); // Pop elements smaller than current
            }
            ans[i] = !st.isEmpty() ? st.peek() : n; // Store index or n
            st.push(i); // Push current index
        }
        return ans; // Return NGE index array
    }

    // Function to find the Previous Greater or Equal Element (PGEE) index
    private int[] findPGEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from left to right
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop(); // Pop elements smaller than current
            }
            ans[i] = !st.isEmpty() ? st.peek() : -1; // Store index or -1
            st.push(i); // Push current index
        }
        return ans; // Return PGEE index array
    }

    // Function to calculate the sum of subarray minimums
    private long sumSubarrayMins(int[] arr) {
        int[] nse = findNSE(arr); // Get Next Smaller Element indices
        int[] psee = findPSEE(arr); // Get Previous Smaller or Equal indices
        long sum = 0; // Initialize sum

        for (int i = 0; i < arr.length; i++) {
            long left = i - psee[i]; // Elements to the left where arr[i] is min
            long right = nse[i] - i; // Elements to the right where arr[i] is min
            long count = left * right; // Total subarrays where arr[i] is min
            sum += arr[i] * count; // Add contribution of arr[i]
        }
        return sum; // Return total sum of subarray minimums
    }

    // Function to calculate the sum of subarray maximums
    private long sumSubarrayMaxs(int[] arr) {
        int[] nge = findNGE(arr); // Get Next Greater Element indices
        int[] pgee = findPGEE(arr); // Get Previous Greater or Equal indices
        long sum = 0; // Initialize sum

        for (int i = 0; i < arr.length; i++) {
            long left = i - pgee[i]; // Elements to the left where arr[i] is max
            long right = nge[i] - i; // Elements to the right where arr[i] is max
            long count = left * right; // Total subarrays where arr[i] is max
            sum += arr[i] * count; // Add contribution of arr[i]
        }
        return sum; // Return total sum of subarray maximums
    }

    // Main function to compute the sum of all subarray ranges
    public long subArrayRanges(int[] arr) {
        // Total range = sum of all max values - sum of all min values across subarrays
        return sumSubarrayMaxs(arr) - sumSubarrayMins(arr);
    }

}
