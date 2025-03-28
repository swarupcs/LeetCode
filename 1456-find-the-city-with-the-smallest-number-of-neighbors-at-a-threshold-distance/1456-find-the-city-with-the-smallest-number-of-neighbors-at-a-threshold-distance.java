class Solution {
    public int findTheCity(int n, int[][] edges, int distanceThreshold) {

        // Step 1: Initialize the adjacency matrix with a large value (representing infinity)
        int[][] adjMat = new int[n][n];
        for(int[] row : adjMat) {
            Arrays.fill(row, (int)1e9); // Fill each cell with a large value (infinity)
        }

        // Step 2: Fill adjacency matrix with given edge weights
        for(int[] it : edges) {
            adjMat[it[0]][it[1]] = it[2]; // Set the weight for the direct edge
            adjMat[it[1]][it[0]] = it[2]; // Since the graph is bidirectional, set the reverse edge
        }

        // Step 3: Apply Floyd-Warshall Algorithm to compute the shortest distances between all pairs of cities
        for(int k = 0; k < n; k++) { // Intermediate city
            for(int i = 0; i < n; i++) { // Start city
                for(int j = 0; j < n; j++) { // End city
                    // Update the shortest distance between city i and city j using intermediate city k
                    adjMat[i][j] = Math.min(adjMat[i][j], adjMat[i][k] + adjMat[k][j]);
                }
            }
        }

        // Step 4: Find the city with the minimum number of reachable neighbors
        int minCount = (int)1e9; // Store the minimum count of reachable cities
        int ans = -1; // Store the city with the least number of reachable neighbors

        for(int i = 0; i < n; i++) { // Iterate through each city
            int count = 0; // Counter for cities reachable within the threshold
            for(int j = 0; j < n; j++) { // Check each other city
                if(i != j && adjMat[i][j] <= distanceThreshold) { // If reachable within the threshold
                    count++;
                }
            }

            // Step 5: Update answer if a city with fewer reachable neighbors is found
            if(count < minCount) {
                minCount = count; // Update minimum count
                ans = i; // Update answer to current city
            } else if(count == minCount) { 
                ans = i; // Prefer the city with a higher index in case of a tie
            }
        }

        // Step 6: Return the resulting city
        return ans;
    }
}
