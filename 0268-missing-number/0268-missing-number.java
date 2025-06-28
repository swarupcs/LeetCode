class Solution {
    // Function to find the missing number in the range [0, n]
    public int missingNumber(int[] nums) {
        // Step 1: Get the length of the array which is 'n'
        int n = nums.length;

        // Step 2: Calculate the expected sum of first n natural numbers (0 to n)
        // Formula: n * (n + 1) / 2
        int expectedSum = (n * (n + 1)) / 2;

        // Step 3: Calculate the actual sum of elements in the array
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num; // add each number to the actual sum
        }

        // Step 4: The missing number is the difference between expected and actual sum
        int missingNumber = expectedSum - actualSum;

        // Step 5: Return the missing number
        return missingNumber;
    }
}
