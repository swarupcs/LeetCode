import java.util.*;

class DisjointSet {
    int[] parent, size; // Arrays to store parent and size of each set

    // Constructor to initialize the Disjoint Set (Union-Find)
    DisjointSet(int n) {
        parent = new int[n]; // Parent array: stores the parent of each node
        size = new int[n];   // Size array: stores the size of each component
        for (int i = 0; i < n; i++) {
            parent[i] = i; // Initially, each node is its own parent (self-loop)
            size[i] = 1;    // Each node is a component of size 1 initially
        }
    }

    // Find function with path compression to optimize lookup
    int find(int node) {
        if (parent[node] == node) return node; // Base case: if node is its own parent
        return parent[node] = find(parent[node]); // Path compression to speed up future lookups
    }

    // Union by size (merges smaller component into larger one)
    void union(int u, int v) {
        int rootU = find(u); // Find root of node u
        int rootV = find(v); // Find root of node v
        if (rootU != rootV) { // If they belong to different sets, merge them
            if (size[rootU] < size[rootV]) { // Attach smaller tree to larger one
                parent[rootU] = rootV;
                size[rootV] += size[rootU]; // Update size of the new parent
            } else { 
                parent[rootV] = rootU; // Attach rootV to rootU
                size[rootU] += size[rootV]; // Update size of the new parent
            }
        }
    }
}

class Solution {
    // Directions array to explore up, right, down, and left neighbors
    private static final int[] delRow = {-1, 0, 1, 0};
    private static final int[] delCol = {0, 1, 0, -1};

    public int largestIsland(int[][] grid) {
        int n = grid.length; // Get the size of the grid (n x n)
        DisjointSet ds = new DisjointSet(n * n); // Create Disjoint Set for all n*n cells

        // Step 1: Connect all adjacent land cells (1s) using DSU
        for (int row = 0; row < n; row++) { // Loop through each row
            for (int col = 0; col < n; col++) { // Loop through each column
                if (grid[row][col] == 1) { // If the current cell is land
                    for (int k = 0; k < 4; k++) { // Check all 4 neighbors
                        int newRow = row + delRow[k]; // Calculate new row index
                        int newCol = col + delCol[k]; // Calculate new column index
                        // Check if the neighbor is within bounds and is also land
                        if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1) {
                            int node = row * n + col; // Convert (row, col) to 1D index
                            int adjNode = newRow * n + newCol; // Convert neighbor to 1D index
                            ds.union(node, adjNode); // Merge the two land components
                        }
                    }
                }
            }
        }

        // Step 2: Try flipping each water cell (0) to land and compute the new island size
        int maxIsland = 0; // Variable to store the maximum island size found
        for (int row = 0; row < n; row++) { // Loop through all rows
            for (int col = 0; col < n; col++) { // Loop through all columns
                if (grid[row][col] == 0) { // If the current cell is water (0)
                    Set<Integer> uniqueIslands = new HashSet<>(); // Set to store unique island roots
                    int totalSize = 1; // Start with size 1 (flipping this 0 to 1)

                    for (int k = 0; k < 4; k++) { // Check all 4 neighbors
                        int newRow = row + delRow[k]; // Calculate neighbor row index
                        int newCol = col + delCol[k]; // Calculate neighbor column index
                        // If neighbor is valid and is land
                        if (newRow >= 0 && newRow < n && newCol >= 0 && newCol < n && grid[newRow][newCol] == 1) {
                            int root = ds.find(newRow * n + newCol); // Find root of the neighbor island
                            if (!uniqueIslands.contains(root)) { // If this island has not been counted
                                uniqueIslands.add(root); // Add to unique set
                                totalSize += ds.size[root]; // Add its size to total
                            }
                        }
                    }
                    maxIsland = Math.max(maxIsland, totalSize); // Update max island size
                }
            }
        }

        // Step 3: If there are no water cells, return the largest existing island
        for (int cell = 0; cell < n * n; cell++) { // Check all land cells
            maxIsland = Math.max(maxIsland, ds.size[ds.find(cell)]); // Get max component size
        }

        return maxIsland; // Return the largest island found
    }
}
