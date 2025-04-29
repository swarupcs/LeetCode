class Solution {
    public List<Integer> getRow(int rowIndex) {
        // Step 1: Create an ArrayList to store the result row
        List<Integer> row = new ArrayList<>();
        
        // Step 2: Add the first element which is always 1
        row.add(1);  // row 0: [1]

        // Step 3: Loop from 1 to rowIndex to build each row
        for (int i = 1; i <= rowIndex; i++) {
            // Traverse backwards to avoid overwriting elements
            // Start from the end and move towards the beginning
            for (int j = i - 1; j >= 1; j--) {
                // Update the element using the sum of two numbers above it
                row.set(j, row.get(j) + row.get(j - 1));
            }

            // Step 4: Add 1 at the end of the current row
            row.add(1);
        }

        // Step 5: Return the computed row
        return row;
    }
}
