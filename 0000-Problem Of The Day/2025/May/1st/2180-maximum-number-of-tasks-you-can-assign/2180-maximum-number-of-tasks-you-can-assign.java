class Solution {

    public int maxTaskAssign(int[] tasks, int[] workers, int pills, int strength) {
        // Sort both arrays to allow greedy + binary search
        Arrays.sort(tasks);
        Arrays.sort(workers);

        int n = tasks.length, m = workers.length;
        int left = 1, right = Math.min(n, m), ans = 0;

        // Binary search to find max number of tasks we can assign
        while (left <= right) {
            int mid = (left + right) / 2;

            // Check if mid tasks can be assigned with given pills and strength
            if (check(tasks, workers, pills, strength, mid)) {
                ans = mid;         // mid is feasible, try to find more
                left = mid + 1;    // look in the higher half
            } else {
                right = mid - 1;   // mid not feasible, reduce search space
            }
        }
        return ans; // return max number of tasks that can be assigned
    }

    private boolean check(int[] tasks, int[] workers, int pills, int strength, int mid) {
        int p = pills;

        // TreeMap simulates a multiset of workers' strengths for fast search/remove
        TreeMap<Integer, Integer> ws = new TreeMap<>();

        // Add the strongest 'mid' workers into TreeMap
        for (int i = workers.length - mid; i < workers.length; ++i) {
            ws.put(workers[i], ws.getOrDefault(workers[i], 0) + 1);
        }

        // Try to assign the hardest 'mid' tasks to available workers
        for (int i = mid - 1; i >= 0; --i) {
            int task = tasks[i];

            // Try to find the strongest worker available
            Integer key = ws.lastKey();

            if (key >= task) {
                // Directly assign this worker to the task
                decrement(ws, key);
            } else {
                // Worker too weak, try to find a worker that can do it with a pill
                if (p == 0) return false; // no pills left
                key = ws.ceilingKey(task - strength); // min worker that with pill can do the task
                if (key == null) return false;        // no such worker exists
                decrement(ws, key); // assign this worker (using pill)
                --p; // consume one pill
            }
        }

        // All mid tasks successfully assigned
        return true;
    }

    // Helper function to remove a worker from TreeMap (simulate multiset)
    private void decrement(TreeMap<Integer, Integer> map, int key) {
        map.put(key, map.get(key) - 1);
        if (map.get(key) == 0) {
            map.remove(key);
        }
    }
}
