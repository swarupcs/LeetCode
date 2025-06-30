class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
 // Step 1: Create a HashSet to store unique elements of nums1
        Set<Integer> set1 = new HashSet<>();
        for (int num : nums1) {
            set1.add(num);  // HashSet ensures only unique values
        }

        // Step 2: Create another HashSet to store the intersection result
        Set<Integer> resultSet = new HashSet<>();
        for (int num : nums2) {
            // If num is present in set1 and not already added, add it
            if (set1.contains(num)) {
                resultSet.add(num);  // Again HashSet ensures no duplicates
            }
        }

        // Step 3: Convert resultSet to int[] array
        int[] result = new int[resultSet.size()];
        int i = 0;
        for (int num : resultSet) {
            result[i++] = num;
        }

        // Step 4: Return the result array
        return result;
    }
}