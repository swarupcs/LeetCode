class DisjointSet {
    int[] rank, parent, size;

    // Constructor: Initializes the Disjoint Set with n elements
    DisjointSet(int n) {
        rank = new int[n + 1];   // Stores the rank (for Union by Rank)
        parent = new int[n + 1]; // Stores the parent of each node
        size = new int[n + 1];   // Stores the size (for Union by Size)

        // Initially, each node is its own parent (self-loop)
        for (int i = 0; i <= n; i++) {
            parent[i] = i;  // Self-parenting initially
            size[i] = 1;    // Each set is of size 1 initially
        }
    }

    // Find function with path compression (optimizes DSU operations)
    int findUPar(int node) {
        if (node == parent[node])
            return node; // If node is its own parent, return itself
        return parent[node] = findUPar(parent[node]); // Path compression
    }

    // Union by Rank (Attach smaller rank tree to larger rank tree)
    void unionByRank(int u, int v) {
        int ulp_u = findUPar(u); // Find the ultimate parent of u
        int ulp_v = findUPar(v); // Find the ultimate parent of v

        if (ulp_u == ulp_v) return; // Already in the same set, no need to merge

        // Attach the smaller tree under the larger tree based on rank
        if (rank[ulp_u] < rank[ulp_v]) {
            parent[ulp_u] = ulp_v; // Smaller rank gets merged to the larger rank
        } else if (rank[ulp_v] < rank[ulp_u]) {
            parent[ulp_v] = ulp_u; // Smaller rank gets merged to the larger rank
        } else {
            parent[ulp_v] = ulp_u; // Merge v under u and increase rank of u
            rank[ulp_u]++;
        }
    }

    // Union by Size (Attach smaller size tree to larger size tree)
    void unionBySize(int u, int v) {
        int ulp_u = findUPar(u); // Find the ultimate parent of u
        int ulp_v = findUPar(v); // Find the ultimate parent of v

        if (ulp_u == ulp_v) return; // Already in the same set, no need to merge

        // Attach the smaller tree under the larger tree based on size
        if (size[ulp_u] < size[ulp_v]) {
            parent[ulp_u] = ulp_v; // Merge u under v
            size[ulp_v] += size[ulp_u]; // Update size of v's tree
        } else {
            parent[ulp_v] = ulp_u; // Merge v under u
            size[ulp_u] += size[ulp_v]; // Update size of u's tree
        }
    }
}


class Solution {
    
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();  // Number of accounts
        DisjointSet ds = new DisjointSet(n);  // Disjoint Set initialization
        Map<String, Integer> mapMailNode = new HashMap<>();  // HashMap to store {email -> account index}

        // Step 1: Build Disjoint Set using email mapping
        for (int i = 0; i < n; i++) { // Iterate over each account
            for (int j = 1; j < accounts.get(i).size(); j++) { // Iterate over emails (skip first element as it's the name)
                String mail = accounts.get(i).get(j); // Get current email

                // If email is new, map it to the current account index
                if (!mapMailNode.containsKey(mail)) {
                    mapMailNode.put(mail, i);
                } else {
                    // If email exists, merge accounts using Disjoint Set
                    ds.unionBySize(i, mapMailNode.get(mail));
                }
            }
        }

        // Step 2: Group emails under the ultimate parent of each account
        List<List<String>> mergedMail = new ArrayList<>(n); // List to store merged emails
        for (int i = 0; i < n; i++) {
            mergedMail.add(new ArrayList<>()); // Initialize an empty list for each account
        }

        // Assign each email to the representative (ultimate parent) of its set
        for (Map.Entry<String, Integer> entry : mapMailNode.entrySet()) {
            String mail = entry.getKey(); // Get the email
            int node = ds.findUPar(entry.getValue()); // Find the ultimate parent account index
            mergedMail.get(node).add(mail); // Add email to the respective merged list
        }

        // Step 3: Sort and format the merged accounts
        List<List<String>> ans = new ArrayList<>(); // Final answer list
        for (int i = 0; i < n; i++) {
            if (mergedMail.get(i).isEmpty()) 
                continue;  // Ignore empty lists

            Collections.sort(mergedMail.get(i));  // Sort emails alphabetically
            List<String> temp = new ArrayList<>();
            temp.add(accounts.get(i).get(0));  // Add name from the original account
            temp.addAll(mergedMail.get(i));  // Add sorted emails
            ans.add(temp);
        }

        // Sort accounts by name before returning (Optional step)
        ans.sort(Comparator.comparing(list -> list.get(0)));
        return ans;
    }
}