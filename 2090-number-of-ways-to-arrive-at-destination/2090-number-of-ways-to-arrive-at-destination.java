class Solution {
    public int countPaths(int n, int[][] roads) {
        int mod = 1000000007; // Modulo to prevent overflow

        // Step 1: Create the adjacency list
        List<int[]>[] adj = new ArrayList[n]; // Each index stores a list of {neighbor, travelTime}
        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>(); // Initialize adjacency list for each node
        }

        // Step 2: Populate the adjacency list using the roads array
        for (int[] road : roads) {
            int u = road[0], v = road[1], time = road[2];
            adj[u].add(new int[]{v, time}); // Add road u -> v
            adj[v].add(new int[]{u, time}); // Add road v -> u (since it's bidirectional)
        }

        // Step 3: Initialize distance array (minTime) and ways array
        long[] minTime = new long[n];
        Arrays.fill(minTime, Long.MAX_VALUE); // Set all nodes' times to "infinity"

        int[] ways = new int[n]; // Stores the count of ways to reach each node

        // Step 4: Priority queue for Dijkstra’s algorithm (min-heap)
        PriorityQueue<long[]> pq = new PriorityQueue<>(Comparator.comparingLong(a -> a[0]));

        // Step 5: Initialize starting node (0)
        minTime[0] = 0; // Time to reach node 0 is 0
        ways[0] = 1; // There is 1 way to be at the start
        pq.add(new long[]{0, 0}); // Add {time, node} to priority queue

        // Step 6: Process nodes in the priority queue
        while (!pq.isEmpty()) {
            long[] curr = pq.poll(); // Extract the node with the smallest recorded time
            long time = curr[0]; // Time taken to reach the current node
            int node = (int) curr[1]; // Current node

            // Step 7: Traverse all neighbors of the current node
            for (int[] neighbor : adj[node]) {
                int adjNode = neighbor[0]; // Adjacent node
                int travelTime = neighbor[1]; // Time to travel to the adjacent node

                // Step 8: If a shorter path to adjNode is found
                if (minTime[adjNode] > time + travelTime) {
                    minTime[adjNode] = time + travelTime; // Update shortest time
                    ways[adjNode] = ways[node]; // Reset ways count
                    pq.add(new long[]{minTime[adjNode], adjNode}); // Push updated path
                }
                // Step 9: If another shortest path is found
                else if (minTime[adjNode] == time + travelTime) {
                    ways[adjNode] = (ways[adjNode] + ways[node]) % mod; // Add the ways
                }
            }
        }

        // Step 10: Return the number of ways to reach the destination node (n-1)
        return ways[n - 1] % mod;
    }
}
