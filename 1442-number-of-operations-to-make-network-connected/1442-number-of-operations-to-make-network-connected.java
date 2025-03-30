class Solution {
    public int makeConnected(int n, int[][] connections) {
        int size = connections.length; // Number of edges in the graph

        // Step 1: If edges are less than (n-1), connection is impossible
        if (size < n - 1) return -1;

        // Step 2: Initialize Disjoint Set for `n` nodes
        DisjointSet ds = new DisjointSet(n);

        // Step 3: Process each edge and merge components
        for (int i = 0; i < size; i++) {
            ds.unionByRank(connections[i][0], connections[i][1]); // Connect edge nodes
        }

        // Step 4: Count number of connected components
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (ds.findUPar(i) == i) // If node is an ultimate parent, it's a component
                count++;
        }

        // Step 5: Return the required operations (components - 1)
        return count - 1;
    }
}

// Disjoint Set (Union-Find) Data Structure
class DisjointSet {
    int[] rank, parent, size;

    // Constructor: Initialize Disjoint Set for `n` nodes
    DisjointSet(int n) {
        rank = new int[n];
        parent = new int[n];
        size = new int[n];

        // Initially, every node is its own parent (self-loop)
        for (int i = 0; i < n; i++) {
            parent[i] = i;
            size[i] = 1; // Each node is its own component initially
        }
    }

    // Function to find the ultimate parent of a node (with path compression)
    int findUPar(int node) {
        if (node == parent[node]) // If node is its own parent, return it
            return node;
        return parent[node] = findUPar(parent[node]); // Path compression optimization
    }

    // Union by Rank: Merge two sets based on rank
    void unionByRank(int u, int v) {
        int ulp_u = findUPar(u); // Find ultimate parent of u
        int ulp_v = findUPar(v); // Find ultimate parent of v

        if (ulp_u == ulp_v) return; // Already connected, no need to merge

        // Merge the smaller tree under the larger one
        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v; // u's parent becomes v
        } else if (rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u; // v's parent becomes u
        } else {
            parent[ulp_v] = ulp_u; // If ranks are same, choose one and increase rank
            rank[ulp_u]++;
        }
    }
}