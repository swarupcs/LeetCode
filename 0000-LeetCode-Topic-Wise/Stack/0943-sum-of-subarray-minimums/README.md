<h2><a href="https://leetcode.com/problems/sum-of-subarray-minimums">943. Sum of Subarray Minimums</a></h2><h3>Medium</h3><hr><p>Given an array of integers arr, find the sum of <code>min(b)</code>, where <code>b</code> ranges over every (contiguous) subarray of <code>arr</code>. Since the answer may be large, return the answer <strong>modulo</strong> <code>10<sup>9</sup> + 7</code>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> arr = [3,1,2,4]
<strong>Output:</strong> 17
<strong>Explanation:</strong> 
Subarrays are [3], [1], [2], [4], [3,1], [1,2], [2,4], [3,1,2], [1,2,4], [3,1,2,4]. 
Minimums are 3, 1, 2, 4, 1, 1, 2, 1, 1, 1.
Sum is 17.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> arr = [11,81,94,43,3]
<strong>Output:</strong> 444
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= arr.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>1 &lt;= arr[i] &lt;= 3 * 10<sup>4</sup></code></li>
</ul>


---

## ✅ Problem: Sum of Subarray Minimums

---

## ✅ **Approach & Intuition**

For every element in the array, we want to know:

* How many subarrays exist in which this element is the **minimum**.
* Then, we multiply the element's value with the count of such subarrays (its frequency as min), and sum all such contributions.

💡 To find this efficiently:

* Use **monotonic stacks** to compute:

  * **Next Smaller Element (NSE)** → for right boundaries.
  * **Previous Smaller or Equal Element (PSEE)** → for left boundaries.

🔢 For each index `i`:

* Number of subarrays where `arr[i]` is the minimum =
  `(i - PSEE[i]) * (NSE[i] - i)`

Then,

```java
Contribution = arr[i] * (i - PSEE[i]) * (NSE[i] - i)
```

---

## ✅ **Java Code with Step-by-Step Comments**

```java
import java.util.*;

class Solution {

    // Function to find the indices of Next Smaller Element (NSE)
    private int[] findNSE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop all elements greater or equal to current
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }

            // If stack is empty, there's no smaller to the right
            ans[i] = st.isEmpty() ? n : st.peek();

            // Push current index
            st.push(i);
        }

        return ans;
    }

    // Function to find indices of Previous Smaller or Equal Element (PSEE)
    private int[] findPSEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from left to right
        for (int i = 0; i < n; i++) {
            // Pop all strictly greater elements
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop();
            }

            // If stack is empty, there's no smaller on the left
            ans[i] = st.isEmpty() ? -1 : st.peek();

            // Push current index
            st.push(i);
        }

        return ans;
    }

    // Main function to calculate the sum of minimums of all subarrays
    public int sumSubarrayMins(int[] arr) {
        int n = arr.length;
        int mod = (int)1e9 + 7;

        // Step 1: Get next and previous smaller elements' indices
        int[] nse = findNSE(arr);   // NSE[i] is the first index after i where arr[j] < arr[i]
        int[] psee = findPSEE(arr); // PSEE[i] is the last index before i where arr[j] <= arr[i]

        long sum = 0;

        // Step 2: Calculate the contribution of each element
        for (int i = 0; i < n; i++) {
            int left = i - psee[i];      // number of elements on the left
            int right = nse[i] - i;      // number of elements on the right

            long freq = (long)left * right; // total subarrays where arr[i] is min
            long contribution = (freq * arr[i]) % mod;

            sum = (sum + contribution) % mod;
        }

        return (int)sum;
    }

    // Test case
    public static void main(String[] args) {
        Solution sol = new Solution();
        int[] arr1 = {3, 1, 2, 4};
        int[] arr2 = {11, 81, 94, 43, 3};

        System.out.println("Output 1: " + sol.sumSubarrayMins(arr1)); // 17
        System.out.println("Output 2: " + sol.sumSubarrayMins(arr2)); // 444
    }
}
```

---

## ✅ Dry Run on Example

### Input: `[3, 1, 2, 4]`

All subarrays and their mins:

* `[3]` → 3
* `[1]` → 1
* `[2]` → 2
* `[4]` → 4
* `[3,1]` → 1
* `[1,2]` → 1
* `[2,4]` → 2
* `[3,1,2]` → 1
* `[1,2,4]` → 1
* `[3,1,2,4]` → 1

Min sum: **3 + 1 + 2 + 4 + 1 + 1 + 2 + 1 + 1 + 1 = 17**

✅ Code will correctly return `17`.

---

## ✅ Time and Space Complexity

* **Time:** `O(n)` — each element is pushed/popped once in the monotonic stack
* **Space:** `O(n)` — for stacks and helper arrays

---

