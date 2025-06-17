<h2><a href="https://leetcode.com/problems/sum-of-subarray-ranges">2227. Sum of Subarray Ranges</a></h2><h3>Medium</h3><hr><p>You are given an integer array <code>nums</code>. The <strong>range</strong> of a subarray of <code>nums</code> is the difference between the largest and smallest element in the subarray.</p>

<p>Return <em>the <strong>sum of all</strong> subarray ranges of </em><code>nums</code><em>.</em></p>

<p>A subarray is a contiguous <strong>non-empty</strong> sequence of elements within an array.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0 
[2], range = 2 - 2 = 0
[3], range = 3 - 3 = 0
[1,2], range = 2 - 1 = 1
[2,3], range = 3 - 2 = 1
[1,2,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 1 + 1 + 2 = 4.</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,3,3]
<strong>Output:</strong> 4
<strong>Explanation:</strong> The 6 subarrays of nums are the following:
[1], range = largest - smallest = 1 - 1 = 0
[3], range = 3 - 3 = 0
[3], range = 3 - 3 = 0
[1,3], range = 3 - 1 = 2
[3,3], range = 3 - 3 = 0
[1,3,3], range = 3 - 1 = 2
So the sum of all ranges is 0 + 0 + 0 + 2 + 0 + 2 = 4.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [4,-2,-3,4,1]
<strong>Output:</strong> 59
<strong>Explanation:</strong> The sum of all subarray ranges of nums is 59.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 1000</code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow-up:</strong> Could you find a solution with <code>O(n)</code> time complexity?</p>



---

### ✅ **Intuition**

The **range of a subarray** is defined as:

```
range = max element - min element
```

So to get the **sum of all subarray ranges**, we can compute:

```
Total Range = Sum of maximums of all subarrays - Sum of minimums of all subarrays
```

---

### ✅ **Approach**

We calculate:

1. For every element `arr[i]`, how many subarrays exist where it is the **maximum**.
2. For every element `arr[i]`, how many subarrays exist where it is the **minimum**.

We use **monotonic stacks** to compute:

* **NGE**: Next Greater Element
* **PGEE**: Previous Greater or Equal Element
* **NSE**: Next Smaller Element
* **PSEE**: Previous Smaller or Equal Element

Using this, we calculate each element's contribution to:

* sum of subarray maximums
* sum of subarray minimums

---

### ✅ **Algorithm**

#### Step 1: Compute sum of subarray **maximums**

* For each `arr[i]`, find:

  * `left = i - PGEE[i]`
  * `right = NGE[i] - i`
* Total subarrays where `arr[i]` is max = `left * right`
* Contribution = `arr[i] * left * right`

#### Step 2: Compute sum of subarray **minimums**

* For each `arr[i]`, find:

  * `left = i - PSEE[i]`
  * `right = NSE[i] - i`
* Total subarrays where `arr[i]` is min = `left * right`
* Contribution = `arr[i] * left * right`

#### Step 3: Final answer

```
Answer = sumOfMax - sumOfMin
```

---

### ✅ **Dry Run**

Input: `arr = [1, 2, 3]`

**Subarrays & Ranges:**

* \[1] → max - min = 1-1 = 0
* \[2] → 0
* \[3] → 0
* \[1,2] → 2-1 = 1
* \[2,3] → 3-2 = 1
* \[1,2,3] → 3-1 = 2

**Sum = 0 + 0 + 0 + 1 + 1 + 2 = 4**

---

### ✅ **Java Code with Detailed Comments**

```java
import java.util.*;

class Solution {

    // Function to find the Next Smaller Element (NSE) index for each element in the array
    private int[] findNSE(int[] arr) {
        int n = arr.length; // Get the length of the array
        int[] ans = new int[n]; // Initialize answer array
        Stack<Integer> st = new Stack<>(); // Stack to store indices

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            // Pop elements from stack while current element is smaller
            while (!st.isEmpty() && arr[st.peek()] >= arr[i]) {
                st.pop();
            }
            // If stack is not empty, top is the NSE index; else it's n
            ans[i] = !st.isEmpty() ? st.peek() : n;
            st.push(i); // Push current index to stack
        }
        return ans; // Return NSE index array
    }

    // Function to find the Previous Smaller or Equal Element (PSEE) index
    private int[] findPSEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from left to right
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] > arr[i]) {
                st.pop(); // Pop elements greater than current
            }
            ans[i] = !st.isEmpty() ? st.peek() : -1; // Store index or -1
            st.push(i); // Push current index
        }
        return ans; // Return PSEE index array
    }

    // Function to find the Next Greater Element (NGE) index
    private int[] findNGE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from right to left
        for (int i = n - 1; i >= 0; i--) {
            while (!st.isEmpty() && arr[st.peek()] <= arr[i]) {
                st.pop(); // Pop elements smaller than current
            }
            ans[i] = !st.isEmpty() ? st.peek() : n; // Store index or n
            st.push(i); // Push current index
        }
        return ans; // Return NGE index array
    }

    // Function to find the Previous Greater or Equal Element (PGEE) index
    private int[] findPGEE(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> st = new Stack<>();

        // Traverse from left to right
        for (int i = 0; i < n; i++) {
            while (!st.isEmpty() && arr[st.peek()] < arr[i]) {
                st.pop(); // Pop elements smaller than current
            }
            ans[i] = !st.isEmpty() ? st.peek() : -1; // Store index or -1
            st.push(i); // Push current index
        }
        return ans; // Return PGEE index array
    }

    // Function to calculate the sum of subarray minimums
    private long sumSubarrayMins(int[] arr) {
        int[] nse = findNSE(arr); // Get Next Smaller Element indices
        int[] psee = findPSEE(arr); // Get Previous Smaller or Equal indices
        long sum = 0; // Initialize sum

        for (int i = 0; i < arr.length; i++) {
            long left = i - psee[i]; // Elements to the left where arr[i] is min
            long right = nse[i] - i; // Elements to the right where arr[i] is min
            long count = left * right; // Total subarrays where arr[i] is min
            sum += arr[i] * count; // Add contribution of arr[i]
        }
        return sum; // Return total sum of subarray minimums
    }

    // Function to calculate the sum of subarray maximums
    private long sumSubarrayMaxs(int[] arr) {
        int[] nge = findNGE(arr); // Get Next Greater Element indices
        int[] pgee = findPGEE(arr); // Get Previous Greater or Equal indices
        long sum = 0; // Initialize sum

        for (int i = 0; i < arr.length; i++) {
            long left = i - pgee[i]; // Elements to the left where arr[i] is max
            long right = nge[i] - i; // Elements to the right where arr[i] is max
            long count = left * right; // Total subarrays where arr[i] is max
            sum += arr[i] * count; // Add contribution of arr[i]
        }
        return sum; // Return total sum of subarray maximums
    }

    // Main function to compute the sum of all subarray ranges
    public long subArrayRanges(int[] arr) {
        // Total range = sum of all max values - sum of all min values across subarrays
        return sumSubarrayMaxs(arr) - sumSubarrayMins(arr);
    }

    // Main method for testing
    public static void main(String[] args) {
        Solution sol = new Solution(); // Create Solution object

        // Test case 1
        int[] arr1 = {1, 2, 3};
        System.out.println("The sum of subarray ranges is: " + sol.subArrayRanges(arr1)); // Output: 4

        // Test case 2
        int[] arr2 = {1, 3, 3};
        System.out.println("The sum of subarray ranges is: " + sol.subArrayRanges(arr2)); // Output: 4

        // Test case 3
        int[] arr3 = {4, -2, -3, 4, 1};
        System.out.println("The sum of subarray ranges is: " + sol.subArrayRanges(arr3)); // Output: 59
    }
}

```



---

## 🔍 Time Complexity

### 1. **Monotonic Stack Methods**

Each of the following functions processes every element exactly once using a stack:

* `findNSE`
* `findPSEE`
* `findNGE`
* `findPGEE`

Each function:

* Pushes and pops each element at most once → **O(n)** per function.

So total time for all four stack-based functions:

```
= O(n) + O(n) + O(n) + O(n) = O(4n) = O(n)
```

### 2. **Calculating Contributions**

* `sumSubarrayMins()` → iterates once over `arr` → **O(n)**
* `sumSubarrayMaxs()` → iterates once over `arr` → **O(n)**
* `subArrayRanges()` just calls the above two → **O(n)**

### ✅ **Total Time Complexity** = **O(n)**

(where `n` = length of the input array)

---

## 🧠 Space Complexity

### 1. **Stack space**

Each monotonic stack function uses a `Stack<Integer>` of at most `n` size → **O(n)** per function, but they don’t run simultaneously.

### 2. **Auxiliary arrays**

Each helper returns an array of size `n`:

* `nse`, `psee`, `nge`, `pgee` → each is of size `n`

Total auxiliary space = `4 * n` → **O(n)**

### ✅ **Total Space Complexity** = **O(n)**

---

## ✅ Final Answer:

* **Time Complexity:** `O(n)`
* **Space Complexity:** `O(n)`

This is optimal for this problem because we're avoiding the brute-force `O(n^2)` solution by using **monotonic stacks** efficiently.
