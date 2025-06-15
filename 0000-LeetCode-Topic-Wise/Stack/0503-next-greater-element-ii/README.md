<h2><a href="https://leetcode.com/problems/next-greater-element-ii">503. Next Greater Element II</a></h2><h3>Medium</h3><hr><p>Given a circular integer array <code>nums</code> (i.e., the next element of <code>nums[nums.length - 1]</code> is <code>nums[0]</code>), return <em>the <strong>next greater number</strong> for every element in</em> <code>nums</code>.</p>

<p>The <strong>next greater number</strong> of a number <code>x</code> is the first greater number to its traversing-order next in the array, which means you could search circularly to find its next greater number. If it doesn&#39;t exist, return <code>-1</code> for this number.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,1]
<strong>Output:</strong> [2,-1,2]
Explanation: The first 1&#39;s next greater number is 2; 
The number 2 can&#39;t find next greater number. 
The second 1&#39;s next greater number needs to search circularly, which is also 2.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3,4,3]
<strong>Output:</strong> [2,3,4,-1,4]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-10<sup>9</sup> &lt;= nums[i] &lt;= 10<sup>9</sup></code></li>
</ul>



---

### ✅ **Intuition**

In a circular array, every element may have to search beyond the end and wrap back to the beginning to find the next greater element. To simulate this, we **loop through the array twice** and use a **stack** to keep track of elements for which we're still searching for the next greater element.

---

### ✅ **Algorithm**

1. Create a result array `ans` initialized to `-1`.
2. Use a **stack to store indices** of the elements.
3. Traverse the array **twice** (`2 * n - 1` to `0`) to simulate circular behavior.
4. For each index:

   * Use modulo `% n` to wrap around the array.
   * While the stack isn't empty and the top element in the stack is less than or equal to the current element, pop it.
   * If we are in the **first round** (i < n) and the stack is not empty, the top of the stack is the **next greater element**.
   * Push the current element index onto the stack.
5. Return the result.

---

### ✅ **Java Code with Detailed Comments**

```java
import java.util.*;

class Solution {

    /* Function to find the next greater element
       for each element in the circular array */
    public int[] nextGreaterElements(int[] arr) {
        int n = arr.length;

        // Initialize answer array with -1
        int[] ans = new int[n];
        Arrays.fill(ans, -1);

        // Stack to store indices in decreasing order
        Stack<Integer> st = new Stack<>();

        // Traverse the array twice for circular behavior
        for (int i = 2 * n - 1; i >= 0; i--) {
            // Use modulo to wrap around the array
            int idx = i % n;

            // Maintain a decreasing stack
            while (!st.isEmpty() && arr[st.peek()] <= arr[idx]) {
                st.pop(); // Remove smaller elements
            }

            // If we are in the first round, update the answer
            if (i < n) {
                if (!st.isEmpty()) {
                    ans[idx] = arr[st.peek()];
                }
            }

            // Push current index to stack
            st.push(idx);
        }

        return ans;
    }

    public static void main(String[] args) {
        int[] arr = {1, 2, 1};

        Solution sol = new Solution();

        int[] result = sol.nextGreaterElements(arr);

        System.out.println("The next greater elements are:");
        for (int val : result) {
            System.out.print(val + " ");
        }
    }
}
```

---

### ✅ **Dry Run:**

For `arr = [1, 2, 1]`, simulate two passes:

| i | idx | stack (top to bottom) | ans           |
| - | --- | --------------------- | ------------- |
| 5 | 2   | 2                     | \[-1, -1, -1] |
| 4 | 1   | 1                     | \[-1, -1, -1] |
| 3 | 0   | 1, 0                  | \[-1, -1, -1] |
| 2 | 2   | 1, 0, 2               | \[-1, -1, 2]  |
| 1 | 1   | 1, 0, 2, 1            | \[-1, -1, 2]  |
| 0 | 0   | 1, 0, 2, 1, 0         | \[2, -1, 2]   |

---

### ✅ **Time and Space Complexity**

* **Time:** O(n)
* **Space:** O(n) for the stack and answer array

---


