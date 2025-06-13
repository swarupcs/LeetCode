class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length; // Size of array
        
        // To store the answer
        int[] ans = new int[n - k + 1];
        int ansIndex = 0;
        
        // Deque data structure
        Deque<Integer> dq = new LinkedList<>();
        
        // Traverse the array
        for (int i = 0; i < n; i++) {
            /*
            Removes elements from the front of the deque that are outside 
            the current window of size k. 
            dq.peekFirst() gets the index of the oldest element in the window. 
            If this index is outside the current window (i - k), 
            it's removed using dq.pollFirst().
            */
            // Update deque to maintain current window
            if (!dq.isEmpty() && dq.peekFirst() <= (i - k)) {
                dq.pollFirst();
            }
            
            /*
            Maintains the deque in decreasing order. 
            If the current element nums[i] is greater than the element 
            at the back of the deque (nums[dq.peekLast()]), then the elements 
            at the back are no longer the maximum in any future window and are 
            removed using dq.pollLast(). 
            This ensures that the deque always stores potentially maximum elements 
            in decreasing order.
            */
            
            /* Maintain the monotonic (decreasing) 
            order of elements in deque */
            while (!dq.isEmpty() && nums[dq.peekLast()] <= nums[i]) {
                dq.pollLast();
            }
            
            /*
            Adds the current element's index to the back of the deque.
            */
            // Add current element's index to the deque
            dq.offerLast(i);
            
            /*
            Once the window is of size k (i >= k - 1), 
            the maximum element of that window (which is at the front of the deque, 
            dq.peekFirst()) is added to the result array ans.
            */
            /* Store the maximum element from 
            the first window possible */
            if (i >= (k - 1)) {
                ans[ansIndex++] = nums[dq.peekFirst()];
            }
        }
        
        // Return the stored result
        return ans;
    }
}