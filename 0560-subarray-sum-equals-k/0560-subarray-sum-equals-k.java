import java.util.HashMap;

class Solution {
    public int subarraySum(int[] nums, int k) {
        // Initialize a map to store prefix sum frequencies
        HashMap<Integer, Integer> prefixSumFreq = new HashMap<>();
        
        prefixSumFreq.put(0, 1); // Base case: sum 0 occurs once (empty prefix)
        
        int sum = 0;    // Cumulative/prefix sum
        int count = 0;  // Number of subarrays found

        // Traverse the array
        for (int i = 0; i < nums.length; i++) {
            sum += nums[i]; // Add current element to prefix sum

            // If (sum - k) exists in map, there exists a subarray ending at i with sum k
            if (prefixSumFreq.containsKey(sum - k)) {
                count += prefixSumFreq.get(sum - k);
            }

            // Update the frequency of current prefix sum in the map
            prefixSumFreq.put(sum, prefixSumFreq.getOrDefault(sum, 0) + 1);
        }

        // Return total count of subarrays with sum == k
        return count;
    }
}
