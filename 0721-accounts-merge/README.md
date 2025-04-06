<h2><a href="https://leetcode.com/problems/accounts-merge">721. Accounts Merge</a></h2><h3>Medium</h3><hr><p>Given a list of <code>accounts</code> where each element <code>accounts[i]</code> is a list of strings, where the first element <code>accounts[i][0]</code> is a name, and the rest of the elements are <strong>emails</strong> representing emails of the account.</p>

<p>Now, we would like to merge these accounts. Two accounts definitely belong to the same person if there is some common email to both accounts. Note that even if two accounts have the same name, they may belong to different people as people could have the same name. A person can have any number of accounts initially, but all of their accounts definitely have the same name.</p>

<p>After merging the accounts, return the accounts in the following format: the first element of each account is the name, and the rest of the elements are emails <strong>in sorted order</strong>. The accounts themselves can be returned in <strong>any order</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> accounts = [[&quot;John&quot;,&quot;johnsmith@mail.com&quot;,&quot;john_newyork@mail.com&quot;],[&quot;John&quot;,&quot;johnsmith@mail.com&quot;,&quot;john00@mail.com&quot;],[&quot;Mary&quot;,&quot;mary@mail.com&quot;],[&quot;John&quot;,&quot;johnnybravo@mail.com&quot;]]
<strong>Output:</strong> [[&quot;John&quot;,&quot;john00@mail.com&quot;,&quot;john_newyork@mail.com&quot;,&quot;johnsmith@mail.com&quot;],[&quot;Mary&quot;,&quot;mary@mail.com&quot;],[&quot;John&quot;,&quot;johnnybravo@mail.com&quot;]]
<strong>Explanation:</strong>
The first and second John&#39;s are the same person as they have the common email &quot;johnsmith@mail.com&quot;.
The third John and Mary are different people as none of their email addresses are used by other accounts.
We could return these lists in any order, for example the answer [[&#39;Mary&#39;, &#39;mary@mail.com&#39;], [&#39;John&#39;, &#39;johnnybravo@mail.com&#39;], 
[&#39;John&#39;, &#39;john00@mail.com&#39;, &#39;john_newyork@mail.com&#39;, &#39;johnsmith@mail.com&#39;]] would still be accepted.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> accounts = [[&quot;Gabe&quot;,&quot;Gabe0@m.co&quot;,&quot;Gabe3@m.co&quot;,&quot;Gabe1@m.co&quot;],[&quot;Kevin&quot;,&quot;Kevin3@m.co&quot;,&quot;Kevin5@m.co&quot;,&quot;Kevin0@m.co&quot;],[&quot;Ethan&quot;,&quot;Ethan5@m.co&quot;,&quot;Ethan4@m.co&quot;,&quot;Ethan0@m.co&quot;],[&quot;Hanzo&quot;,&quot;Hanzo3@m.co&quot;,&quot;Hanzo1@m.co&quot;,&quot;Hanzo0@m.co&quot;],[&quot;Fern&quot;,&quot;Fern5@m.co&quot;,&quot;Fern1@m.co&quot;,&quot;Fern0@m.co&quot;]]
<strong>Output:</strong> [[&quot;Ethan&quot;,&quot;Ethan0@m.co&quot;,&quot;Ethan4@m.co&quot;,&quot;Ethan5@m.co&quot;],[&quot;Gabe&quot;,&quot;Gabe0@m.co&quot;,&quot;Gabe1@m.co&quot;,&quot;Gabe3@m.co&quot;],[&quot;Hanzo&quot;,&quot;Hanzo0@m.co&quot;,&quot;Hanzo1@m.co&quot;,&quot;Hanzo3@m.co&quot;],[&quot;Kevin&quot;,&quot;Kevin0@m.co&quot;,&quot;Kevin3@m.co&quot;,&quot;Kevin5@m.co&quot;],[&quot;Fern&quot;,&quot;Fern0@m.co&quot;,&quot;Fern1@m.co&quot;,&quot;Fern5@m.co&quot;]]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= accounts.length &lt;= 1000</code></li>
	<li><code>2 &lt;= accounts[i].length &lt;= 10</code></li>
	<li><code>1 &lt;= accounts[i][j].length &lt;= 30</code></li>
	<li><code>accounts[i][0]</code> consists of English letters.</li>
	<li><code>accounts[i][j] (for j &gt; 0)</code> is a valid email.</li>
</ul>

# **Approach and Intuition**

- The problem requires merging accounts based on common emails.
- We can use **Disjoint Set Union (DSU) / Union-Find** to efficiently group accounts having common emails.
- Each email is unique and can be mapped to one account index.
- If two emails appear in different accounts, their accounts must be merged.
- Once merged, the emails must be sorted and returned with the respective name.

---

# **Algorithm**

1. **Initialize a Disjoint Set (DSU)**
    - Each account (0 to N-1) is considered as a separate component initially.
2. **Map emails to accounts using a HashMap**
    - If an email is encountered for the first time, associate it with the current account.
    - If an email is already mapped, union the current account with the previously stored account (meaning they belong to the same user).
3. **Group emails by their ultimate parent**
    - Iterate through the emails and find the root parent of each email’s associated account.
    - Collect all emails under their root parent.
4. **Sort and format the merged accounts**
    - Each group of merged emails is sorted.
    - The account name is taken from the original list.
    - The formatted list is returned.

---

# **Code with Detailed Comments and Explanation**

```java
import java.util.*;

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

// Solution class to merge accounts
class Solution {
    
    public static List<List<String>> accountsMerge(List<List<String>> accounts) {
        int n = accounts.size();  // Number of accounts
        DisjointSet ds = new DisjointSet(n);  // Disjoint Set initialization
        Map<String, Integer> mapMailNode = new HashMap<>();  // HashMap to store {email -> account index}

        // Step 1: Build Disjoint Set using email mapping
        for (int i = 0; i < n; i++) { // Iterate over each account
		        // Iterate over emails (skip first element as it's the name)
            for (int j = 1; j < accounts.get(i).size(); j++) { 
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

```

---

# **Dry Run with Example**

### **Input:**

```java
N = 4
accounts = [
    ["John", "johnsmith@mail.com", "john_newyork@mail.com"],
    ["John", "johnsmith@mail.com", "john00@mail.com"],
    ["Mary", "mary@mail.com"],
    ["John", "johnnybravo@mail.com"]
]

```

---

### **Step 1: Mapping Emails to Accounts**

| Email | Account Index |
| --- | --- |
| [johnsmith@mail.com](mailto:johnsmith@mail.com) | 0 -> Union with 1 |
| [john_newyork@mail.com](mailto:john_newyork@mail.com) | 0 |
| [john00@mail.com](mailto:john00@mail.com) | 1 |
| [mary@mail.com](mailto:mary@mail.com) | 2 |
| [johnnybravo@mail.com](mailto:johnnybravo@mail.com) | 3 |

Union 0 and 1 as they share `johnsmith@mail.com`

---

### **Step 2: Grouping Emails by Parent**

| Parent | Emails |
| --- | --- |
| 0 | [johnsmith@mail.com](mailto:johnsmith@mail.com), [john_newyork@mail.com](mailto:john_newyork@mail.com), [john00@mail.com](mailto:john00@mail.com) |
| 2 | [mary@mail.com](mailto:mary@mail.com) |
| 3 | [johnnybravo@mail.com](mailto:johnnybravo@mail.com) |

---

### **Step 3: Sorting and Formatting Output**

```java
[
    ["John", "john00@mail.com", "john_newyork@mail.com", "johnsmith@mail.com"],
    ["Mary", "mary@mail.com"],
    ["John", "johnnybravo@mail.com"]
]

```

---

# **Time Complexity Analysis**

- **Building the Disjoint Set:** O(N×M)O(N \times M) where MM is max emails in an account.
- **Merging Sets:** O(N×α(N))O(N \times \alpha(N)), where α\alpha is inverse Ackermann (almost constant).
- **Sorting Emails:** O(Nlog⁡M)O(N \log M).
- **Overall Complexity:** **O(N×M×α(N)+Nlog⁡M)O(N \times M \times \alpha(N) + N \log M) ≈ O(NM)O(NM) in practical cases.**