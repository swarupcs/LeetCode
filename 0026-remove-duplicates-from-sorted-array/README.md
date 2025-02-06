<h2><a href="https://leetcode.com/problems/remove-duplicates-from-sorted-array">26. Remove Duplicates from Sorted Array</a></h2><h3>Easy</h3><hr><p>Given an integer array <code>nums</code> sorted in <strong>non-decreasing order</strong>, remove the duplicates <a href="https://en.wikipedia.org/wiki/In-place_algorithm" target="_blank"><strong>in-place</strong></a> such that each unique element appears only <strong>once</strong>. The <strong>relative order</strong> of the elements should be kept the <strong>same</strong>. Then return <em>the number of unique elements in </em><code>nums</code>.</p>

<p>Consider the number of unique elements of <code>nums</code> to be <code>k</code>, to get accepted, you need to do the following things:</p>

<ul>
	<li>Change the array <code>nums</code> such that the first <code>k</code> elements of <code>nums</code> contain the unique elements in the order they were present in <code>nums</code> initially. The remaining elements of <code>nums</code> are not important as well as the size of <code>nums</code>.</li>
	<li>Return <code>k</code>.</li>
</ul>

<p><strong>Custom Judge:</strong></p>

<p>The judge will test your solution with the following code:</p>

<pre>
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i &lt; k; i++) {
    assert nums[i] == expectedNums[i];
}
</pre>

<p>If all assertions pass, then your solution will be <strong>accepted</strong>.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> nums = [1,1,2]
<strong>Output:</strong> 2, nums = [1,2,_]
<strong>Explanation:</strong> Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> nums = [0,0,1,1,1,2,2,3,3,4]
<strong>Output:</strong> 5, nums = [0,1,2,3,4,_,_,_,_,_]
<strong>Explanation:</strong> Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>1 &lt;= nums.length &lt;= 3 * 10<sup>4</sup></code></li>
	<li><code>-100 &lt;= nums[i] &lt;= 100</code></li>
	<li><code>nums</code> is sorted in <strong>non-decreasing</strong> order.</li>
</ul>



### Approach & Intuition

The problem asks to remove duplicate elements from a sorted array in-place while keeping the order of the elements intact. The key observation is that, since the array is sorted, duplicate values will always be next to each other. This gives us the opportunity to use a two-pointer technique where:

- One pointer will iterate through the array (`j`), and
- The other pointer (`i`) will keep track of the position where the next unique element should be placed.

### Algorithm

1. Initialize a pointer `i` to track the position of unique elements in the array. It starts at index 0.
2. Iterate through the array using pointer `j`, starting from index 1.
3. If the element at `nums[j]` is different from `nums[i]`, it means we have found a new unique element.
    - Increment `i` (the pointer for unique elements).
    - Move `nums[j]` to `nums[i]` to place the new unique element in its correct position.
4. Once all elements are checked, the unique elements will be in the first `i + 1` positions of the array.
5. Return `i + 1`, which represents the number of unique elements.

### Code Explanation

```java
class Solution {
    public int removeDuplicates(int[] nums) {
        // i will track the position of unique elements
        int i = 0;
        
        // j is used to traverse the array from the second element onwards
        for(int j = 1; j < nums.length; j++) {
            // If the current element is different from the last unique element
            if(nums[j] != nums[i]) {
                // Increment i to point to the next unique spot
                i++;
                // Place the unique element at the correct position
                nums[i] = nums[j];
            }
        }
        
        // i + 1 gives the number of unique elements
        return i + 1;
    }
}
```

### Dry Run

Let's dry-run the code with an example.

#### Example 1: `nums = [1, 1, 2]`

1. **Initial setup**: `i = 0`, `nums = [1, 1, 2]`
2. **Iteration 1** (`j = 1`):
   - `nums[1] == nums[0]` → They are the same, no action taken.
   - `i` remains 0.
3. **Iteration 2** (`j = 2`):
   - `nums[2] != nums[0]` → They are different, so a unique element is found.
   - Increment `i` → `i = 1`.
   - Set `nums[i] = nums[j]` → `nums[1] = 2`.
   - `nums` becomes `[1, 2, 2]`.
4. **End of loop**: `i = 1`, `nums = [1, 2, 2]`
5. **Return value**: `i + 1 = 2`.

Output: The array is now `[1, 2, _]` where `_` represents the unimportant part, and the function returns `2` as the number of unique elements.

#### Example 2: `nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]`

1. **Initial setup**: `i = 0`, `nums = [0, 0, 1, 1, 1, 2, 2, 3, 3, 4]`
2. **Iteration 1** (`j = 1`):
   - `nums[1] == nums[0]` → They are the same, no action taken.
   - `i` remains 0.
3. **Iteration 2** (`j = 2`):
   - `nums[2] != nums[0]` → They are different.
   - Increment `i` → `i = 1`.
   - Set `nums[i] = nums[j]` → `nums[1] = 1`.
   - `nums` becomes `[0, 1, 1, 1, 1, 2, 2, 3, 3, 4]`.
4. **Iteration 3** (`j = 3`):
   - `nums[3] == nums[2]` → They are the same, no action taken.
   - `i` remains 1.
5. **Iteration 4** (`j = 4`):
   - `nums[4] == nums[2]` → They are the same, no action taken.
   - `i` remains 1.
6. **Iteration 5** (`j = 5`):
   - `nums[5] != nums[2]` → They are different.
   - Increment `i` → `i = 2`.
   - Set `nums[i] = nums[j]` → `nums[2] = 2`.
   - `nums` becomes `[0, 1, 2, 1, 1, 2, 2, 3, 3, 4]`.
7. **Iteration 6** (`j = 6`):
   - `nums[6] == nums[5]` → They are the same, no action taken.
   - `i` remains 2.
8. **Iteration 7** (`j = 7`):
   - `nums[7] != nums[5]` → They are different.
   - Increment `i` → `i = 3`.
   - Set `nums[i] = nums[j]` → `nums[3] = 3`.
   - `nums` becomes `[0, 1, 2, 3, 1, 2, 2, 3, 3, 4]`.
9. **Iteration 8** (`j = 8`):
   - `nums[8] == nums[7]` → They are the same, no action taken.
   - `i` remains 3.
10. **Iteration 9** (`j = 9`):
    - `nums[9] != nums[7]` → They are different.
    - Increment `i` → `i = 4`.
    - Set `nums[i] = nums[j]` → `nums[4] = 4`.
    - `nums` becomes `[0, 1, 2, 3, 4, 2, 2, 3, 3, 4]`.
11. **End of loop**: `i = 4`, `nums = [0, 1, 2, 3, 4, 2, 2, 3, 3, 4]`
12. **Return value**: `i + 1 = 5`.

Output: The array is now `[0, 1, 2, 3, 4, _, _, _, _, _]`, and the function returns `5`.

### Complexity Analysis

- **Time Complexity**: `O(n)`, where `n` is the length of the input array `nums`. We traverse the array only once.
- **Space Complexity**: `O(1)`, because we are modifying the array in place and using only a constant amount of extra space.

This solution is efficient in both time and space!


