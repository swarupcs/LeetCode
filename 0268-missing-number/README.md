<h2><a href="https://leetcode.com/problems/missing-number">268. Missing Number</a></h2><h3>Easy</h3><hr><p>Given an array <code>nums</code> containing <code>n</code> distinct numbers in the range <code>[0, n]</code>, return <em>the only number in the range that is missing from the array.</em></p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [3,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><code>n = 3</code> since there are 3 numbers, so all numbers are in the range <code>[0,3]</code>. 2 is the missing number in the range since it does not appear in <code>nums</code>.</p>
</div>

<p><strong class="example">Example 2:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">2</span></p>

<p><strong>Explanation:</strong></p>

<p><code>n = 2</code> since there are 2 numbers, so all numbers are in the range <code>[0,2]</code>. 2 is the missing number in the range since it does not appear in <code>nums</code>.</p>
</div>

<p><strong class="example">Example 3:</strong></p>

<div class="example-block">
<p><strong>Input:</strong> <span class="example-io">nums = [9,6,4,2,3,5,7,0,1]</span></p>

<p><strong>Output:</strong> <span class="example-io">8</span></p>

<p><strong>Explanation:</strong></p>

<p><code>n = 9</code> since there are 9 numbers, so all numbers are in the range <code>[0,9]</code>. 8 is the missing number in the range since it does not appear in <code>nums</code>.</p>
</div>



<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>n == nums.length</code></li>
	<li><code>1 &lt;= n &lt;= 10<sup>4</sup></code></li>
	<li><code>0 &lt;= nums[i] &lt;= n</code></li>
	<li>All the numbers of <code>nums</code> are <strong>unique</strong>.</li>
</ul>

<p>&nbsp;</p>
<p><strong>Follow up:</strong> Could you implement a solution using only <code>O(1)</code> extra space complexity and <code>O(n)</code> runtime complexity?</p>



---

### ✅ **Approach:**

Use the **mathematical formula** for the sum of the first `n` natural numbers.

### ✅ **Intuition:**

For an array of length `n`, the numbers should ideally be from `0` to `n` (inclusive). The sum of these `n+1` numbers is `n*(n+1)/2`. But one number is missing, so the actual sum of array elements will be less.
Hence, subtracting the actual sum from the expected sum gives us the missing number.

---

### ✅ **Algorithm (Step-by-step):**

1. Calculate `n = nums.length`
2. Compute the expected sum using the formula: `n * (n + 1) / 2`
3. Compute the actual sum by summing all the elements of `nums`
4. Subtract actual sum from expected sum to find the missing number

---

### ✅ **Dry Run Example:**

#### Input: `nums = [3, 0, 1]`

* `n = 3` (length of array)
* Expected sum = `3 * (3 + 1) / 2 = 6`
* Actual sum = `3 + 0 + 1 = 4`
* Missing number = `6 - 4 = 2`

✅ Output: `2`

---

### ✅ **Code with Line-by-Line Explanation:**

```java
class Solution {
    // Function to find the missing number in the range [0, n]
    public int missingNumber(int[] nums) {
        // Step 1: Get the length of the array which is 'n'
        int n = nums.length;

        // Step 2: Calculate the expected sum of first n natural numbers (0 to n)
        // Formula: n * (n + 1) / 2
        int expectedSum = (n * (n + 1)) / 2;

        // Step 3: Calculate the actual sum of elements in the array
        int actualSum = 0;
        for (int num : nums) {
            actualSum += num; // add each number to the actual sum
        }

        // Step 4: The missing number is the difference between expected and actual sum
        int missingNumber = expectedSum - actualSum;

        // Step 5: Return the missing number
        return missingNumber;
    }
}
```

---

### ✅ **Time & Space Complexity:**

* **Time Complexity:** `O(n)` – for the loop summing the array
* **Space Complexity:** `O(1)` – uses constant extra space

---


