
// Disjoint Set class
class DisjointSet {
    int[] rank, parent, size;

    // Constructor to initialize the DSU
    DisjointSet(int n) {
        rank = new int[n + 1];
        parent = new int[n + 1];
        size = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i; // Initialize each node as its own parent
            size[i] = 1;   // Initialize size of each set as 1
        }
    }

    // Find function with path compression
    int findUPar(int node) {
        if (node == parent[node])
            return node; // If the node is its own parent, return it
        return parent[node] = findUPar(parent[node]); // Path compression for optimization
    }
    
        // Function to implement union by rank
    void unionByRank(int u, int v) {
        int ulp_u = findUPar(u);
        int ulp_v = findUPar(v);
        if (ulp_u == ulp_v) return;
        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v;
        }
        else if (rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u;
        }
        else {
            parent[ulp_v] = ulp_u;
            rank[ulp_u]++;
        }
    }

    // Union by size function
    void unionBySize(int u, int v) {
        int ulp_u = findUPar(u); // Find ultimate parent of u
        int ulp_v = findUPar(v); // Find ultimate parent of v
        if (ulp_u == ulp_v) return; // If they are already connected, return
        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v; // Attach smaller set to larger set
            size[ulp_v] += size[ulp_u];
        } else {
            parent[ulp_v] = ulp_u; // Attach smaller set to larger set
            size[ulp_u] += size[ulp_v];
        }
    }
}

class Solution {
    // Function to compute the maximum number of stones that can be removed
    public int removeStones(int[][] stones) {
        int maxRow = 0; // Variable to store the maximum row index
        int maxCol = 0; // Variable to store the maximum column index

        // Determine the maximum row and column index
        for (int[] stone : stones) {
            maxRow = Math.max(maxRow, stone[0]); // Get max row index
            maxCol = Math.max(maxCol, stone[1]); // Get max column index
        }

        // Create a Disjoint Set with size maxRow + maxCol + 1
        DisjointSet ds = new DisjointSet(maxRow + maxCol + 1);

        // Map to track the unique nodes (row and column indices)
        Map<Integer, Integer> stoneNodes = new HashMap<>();

        // Iterate over each stone to connect row and column indices in the Disjoint Set
        for (int[] stone : stones) {
            int nodeRow = stone[0]; // Extract row index
            int nodeCol = stone[1] + maxRow + 1; // Convert column index to avoid overlap

            // Perform union operation to merge row and column
            ds.unionBySize(nodeRow, nodeCol);

            // Mark these indices as visited in the stoneNodes map
            stoneNodes.put(nodeRow, 1);
            stoneNodes.put(nodeCol, 1);
        }

        int numComponents = 0; // Variable to count the number of unique connected components

        // Iterate over unique nodes in the stoneNodes map
        for (int key : stoneNodes.keySet()) {
            // If the node is a root of a component, count it
            if (ds.findUPar(key) == key) {
                numComponents++;
            }
        }

        // Maximum stones that can be removed is total stones - number of components
        return stones.length - numComponents;
    }
}

