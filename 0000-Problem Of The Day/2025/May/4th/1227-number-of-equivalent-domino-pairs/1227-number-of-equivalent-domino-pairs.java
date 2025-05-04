public class Solution {
    public int numEquivDominoPairs(int[][] dominoes) {
        // Step 1: Array to store frequency of each unique domino after normalization
        int[] count = new int[100];

        // Step 2: Result variable to store total number of equivalent pairs
        int result = 0;

        // Step 3: Iterate through each domino
        for (int[] d : dominoes) {
            int a = d[0];
            int b = d[1];

            // Step 3.1: Normalize the domino (put smaller number first)
            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            // Step 3.2: Create a unique key using normalized domino
            int key = a * 10 + b;

            // Step 3.3: Add count[key] to result
            // Because current domino can pair with all previous ones with same key
            result += count[key];

            // Step 3.4: Increment count of this key
            count[key]++;
        }

        // Step 4: Return total pairs
        return result;
    }
}
