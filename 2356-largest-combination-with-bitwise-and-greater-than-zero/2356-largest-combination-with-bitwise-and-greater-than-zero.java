class Solution {
    public int largestCombination(int[] candidates) {
        int result = 0;

        for (int i = 0; i < 24; i++) { // O(24)
            int countIth = 0;

            for (int num : candidates) { // O(n)
                if ((num & (1 << i)) != 0) {
                    countIth++;
                }
            }

            result = Math.max(result, countIth);
        }

        return result;
    }
}