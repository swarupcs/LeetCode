<h2><a href="https://leetcode.com/problems/asteroid-collision">735. Asteroid Collision</a></h2><h3>Medium</h3><hr><p>We are given an array <code>asteroids</code> of integers representing asteroids in a row. The indices of the asteriod in the array represent their relative position in space.</p>

<p>For each asteroid, the absolute value represents its size, and the sign represents its direction (positive meaning right, negative meaning left). Each asteroid moves at the same speed.</p>

<p>Find out the state of the asteroids after all collisions. If two asteroids meet, the smaller one will explode. If both are the same size, both will explode. Two asteroids moving in the same direction will never meet.</p>

<p>&nbsp;</p>
<p><strong class="example">Example 1:</strong></p>

<pre>
<strong>Input:</strong> asteroids = [5,10,-5]
<strong>Output:</strong> [5,10]
<strong>Explanation:</strong> The 10 and -5 collide resulting in 10. The 5 and 10 never collide.
</pre>

<p><strong class="example">Example 2:</strong></p>

<pre>
<strong>Input:</strong> asteroids = [8,-8]
<strong>Output:</strong> []
<strong>Explanation:</strong> The 8 and -8 collide exploding each other.
</pre>

<p><strong class="example">Example 3:</strong></p>

<pre>
<strong>Input:</strong> asteroids = [10,2,-5]
<strong>Output:</strong> [10]
<strong>Explanation:</strong> The 2 and -5 collide resulting in -5. The 10 and -5 collide resulting in 10.
</pre>

<p>&nbsp;</p>
<p><strong>Constraints:</strong></p>

<ul>
	<li><code>2 &lt;= asteroids.length &lt;= 10<sup>4</sup></code></li>
	<li><code>-1000 &lt;= asteroids[i] &lt;= 1000</code></li>
	<li><code>asteroids[i] != 0</code></li>
</ul>


---

## ✅ Intuition:

Asteroids moving in opposite directions may **collide**. To simulate this:

* Use a **stack** to keep track of active (unexploded) asteroids.
* When a **right-moving** asteroid comes, just **push** it to the stack.
* When a **left-moving** asteroid comes, compare it with the top of the stack (recent right-mover).

  * If the top asteroid is smaller, it explodes.
  * If equal, both explode.
  * If top is bigger, current one explodes.
  * If the stack is empty or top is also left-moving, just add it to the stack.

---

## 🔍 Approach:

* Use a **List** as a stack (can use `Stack` or `Deque` too).
* Traverse the asteroid array.
* Handle **collisions** using while loops to simulate repeated explosions.
* Return the remaining stack as the final state.

---

## ⚙️ Algorithm:

1. Initialize an empty stack.
2. Traverse through each asteroid.
3. If asteroid is **positive** (right-moving), push it.
4. If asteroid is **negative** (left-moving):

   * While the top of the stack is **positive** and smaller than its size, pop the top (it explodes).
   * If the top is the same size (but opposite sign), pop both (both explode).
   * If stack is empty or top is also negative, push the current asteroid.
5. Convert the stack to an array and return it.

---

## 🧠 Example: Dry Run for `[10, 2, -5]`

* Stack = `[]`
* `10`: right → push → `[10]`
* `2`: right → push → `[10, 2]`
* `-5`: left → check with top `2`:

  * `2 < 5` → `2` explodes → `[10]`
  * `10 > 5` → `-5` explodes → `[10]`
* Final: `[10]`

---

## 💡 Java Code with Comments and Line-by-Line Algorithm

```java
class Solution {
    public int[] asteroidCollision(int[] asteroids) {
        // Step 1: Get the size of the input array
        int n = asteroids.length;

        // Step 2: Use a list as a stack to store the current state of asteroids
        List<Integer> st = new ArrayList<>();

        // Step 3: Traverse each asteroid in the input array
        for (int i = 0; i < n; i++) {

            // Step 4: If current asteroid is moving to the right, add to stack
            if (asteroids[i] > 0) {
                st.add(asteroids[i]);
            } 
            // Step 5: If current asteroid is moving to the left
            else {
                // Step 6: Keep destroying smaller right-moving asteroids on top of stack
                while (!st.isEmpty() && st.get(st.size() - 1) > 0 &&
                       st.get(st.size() - 1) < Math.abs(asteroids[i])) {
                    // Pop smaller asteroid (it explodes)
                    st.remove(st.size() - 1);
                }

                // Step 7: If top of stack is equal in size but opposite direction, destroy both
                if (!st.isEmpty() && st.get(st.size() - 1) == Math.abs(asteroids[i])) {
                    // Pop both equal-sized asteroids
                    st.remove(st.size() - 1);
                } 
                // Step 8: If no more right-moving asteroid or top is left-moving, push current
                else if (st.isEmpty() || st.get(st.size() - 1) < 0) {
                    // Push current left-moving asteroid
                    st.add(asteroids[i]);
                }
                // Step 9: Else, it means current left-moving asteroid is destroyed (do nothing)
            }
        }

        // Step 10: Convert the list to an array and return the result
        int[] result = new int[st.size()];
        for (int i = 0; i < st.size(); i++) {
            result[i] = st.get(i);
        }

        return result;
    }
}
```


---

## ⏱ Time Complexity: **O(n)**

* `n` is the number of asteroids.
* Each asteroid is **pushed and popped at most once**:

  * Right-moving asteroids are pushed once.
  * Left-moving asteroids may trigger popping of smaller right-moving asteroids (each popped only once).
* So the total number of operations (push + pop) is proportional to `n`.

✅ **Total Time Complexity = O(n)**

---

## 🧠 Space Complexity: **O(n)**

* In the **worst case**, no asteroids collide, and we store all of them in the stack.
* The final result array also takes O(n) space.

✅ **Total Space Complexity = O(n)**

---

### ✅ Summary

| Resource  | Complexity |
| --------- | ---------- |
| **Time**  | O(n)       |
| **Space** | O(n)       |





