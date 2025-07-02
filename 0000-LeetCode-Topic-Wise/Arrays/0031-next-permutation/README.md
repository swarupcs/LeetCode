<h2><a href="https://leetcode.com/problems/next-permutation">31. Next Permutation</a></h2><h3>Medium</h3><hr><p>A <strong>permutation</strong> of an array of integers is an arrangement of its members into a sequence or linear order.</p>

<ul>
	<li>For example, for <code>arr = [1,2,3]</code>, the following are all the permutations of <code>arr</code>: <code>[1,2,3], [1,3,2], [2, 1, 3], [2, 3, 1], [3,1,2], [3,2,1]</code>.</li>
</ul>

<p>The <strong>next permutation</strong> of an array of integers is the next lexicographically greater permutation of its integer. More formally, if all the permutations of the array are sorted in one container according to their lexicographical order, then the <strong>next permutation</strong> of that array is the permutation that follows it in the sorted container. If such arrangement is not possible, the array must be rearranged as the lowest possible order (i.e., sorted in ascending order).</p>

<ul>
	<li>For example, the next permutation of <code>arr = [1,2,3]</code> is <code>[1,3,2]</code>.</li>
	<li>Similarly, the next permutation of <code>arr = [2,3,1]</code> is <code>[3,1,2]</code>.</li>
	<li>While the next permutation of <code>arr = [3,2,1]</code> is <code>[1,2,3]</code> because <code>[3,2,1]</code> does not have a lexicographical larger rearrangement.</li>
</ul>

<p>Given an array of integers <code>nums</code>, <em>find the next permutation of</em> <code>nums</code>.</p>

<p>The replacement must be <strong><a href="http://en.wikipedia.org/wiki/In-place_algorithm" target="_blank">in place</a></strong> and use only constant extra memory.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,2,3]
<strong>Output:</strong> [1,3,2]
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [3,2,1]
<strong>Output:</strong> [1,2,3]
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,5]
<strong>Output:</strong> [1,5,1]
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 100</code></li>
	<li><code>0 &lt;= nums[i] &lt;= 100</code></li>
</ul>



* ✅ **Approach**
* 💡 **Intuition**
* 🧠 **Algorithm**
* 🧪 **Example with Dry Run**
* 💻 **Code with Step-by-Step Comments**
* ⏱ **Time & Space Complexity**

---

## ✅ Approach

To find the **next lexicographical permutation** of a given array:

1. Traverse from **right to left** to find the first element `nums[i]` such that `nums[i] < nums[i+1]`. This is the **pivot**.
2. If no such element is found, the array is in descending order (last permutation) → **reverse the whole array** to get the **first permutation**.
3. If such a pivot is found:

   * Find the **smallest element greater than `nums[i]`** on the right side (say at index `j`) and **swap `nums[i]` and `nums[j]`**.
   * Then **reverse** the portion of the array from `i+1` to the end.

---

## 💡 Intuition

We're trying to get the *next larger permutation*. Think of permutations as numbers in a dictionary.
For example, if we're at `123`, we want `132`. If we're at `321`, we wrap back to `123`.

To get the next permutation:

* Increase the leftmost number that can be increased while keeping order.
* Then, sort everything after that in ascending order to get the *minimum possible change*.

---

## 🧠 Algorithm

1. Let `n` be the length of the array.
2. Find the **first decreasing element** from the end:

   * Traverse from right to left and find `i` such that `nums[i] < nums[i+1]`.
3. If no such `i` exists → reverse entire array.
4. Else:

   * Traverse from right to left again to find the **smallest element greater than `nums[i]`**, say `j`.
   * Swap `nums[i]` and `nums[j]`.
   * Reverse the subarray `nums[i+1:]`.

---

## 🧪 Example with Dry Run

Input: `nums = [1, 2, 3]`

1. Find pivot:

   * `i = 1`, `nums[1] = 2 < 3` → pivot found at index 1
2. Find just greater element from end:

   * `j = 2`, `nums[2] = 3 > 2` → swap `nums[1]` and `nums[2]`
     → `nums = [1, 3, 2]`
3. Reverse from index `2` to end → remains same

✅ Final Output: `[1, 3, 2]`

---

## 💻 Code with Step-by-Step Comments

```java
class Solution {
    // Function to compute the next permutation in-place
    public void nextPermutation(int[] nums) {
        int n = nums.length;  // Get array size

        int ind = -1; // To store the index of the pivot

        // Step 1: Find the first element from right which is smaller than next
        for (int i = n - 2; i >= 0; i--) {
            if (nums[i] < nums[i + 1]) {
                ind = i; // Found the pivot index
                break;
            }
        }

        // Step 2: If pivot is not found, array is in descending order
        if (ind == -1) {
            reverse(nums, 0, n - 1); // Reverse to get smallest permutation
            return;
        }

        // Step 3: Find the next greater element than nums[ind] from right end
        for (int i = n - 1; i > ind; i--) {
            if (nums[i] > nums[ind]) {
                swap(nums, i, ind); // Swap it with pivot
                break;
            }
        }

        // Step 4: Reverse the elements to the right of pivot
        reverse(nums, ind + 1, n - 1);
    }

    // Utility function to swap two elements
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];    // Store first value
        nums[i] = nums[j];     // Swap values
        nums[j] = temp;
    }

    // Utility function to reverse part of array from start to end
    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            swap(nums, start, end); // Swap current ends
            start++;
            end--;
        }
    }
}
```

---

## ⏱ Time & Space Complexity

* **Time Complexity**: `O(n)`

  * One pass to find pivot.
  * One pass to find just greater element.
  * One pass to reverse subarray.
* **Space Complexity**: `O(1)`

  * All operations are in-place.

---


