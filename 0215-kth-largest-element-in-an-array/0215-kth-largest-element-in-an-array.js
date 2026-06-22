/**
 * @param {number[]} nums
 * @param {number} k
 * @return {number}
 */
var findKthLargest = function(nums, k) {
    const heap = [];

    const heapifyUp = (i) => {
        while (i > 0) {
            const p = Math.floor((i - 1) / 2);

            if (heap[p] <= heap[i]) break;

            [heap[p], heap[i]] = [heap[i], heap[p]];
            i = p;
        }
    };

    const heapifyDown = (i) => {
        const n = heap.length;

        while (true) {
            let smallest = i;
            const left = 2 * i + 1;
            const right = 2 * i + 2;

            if (left < n && heap[left] < heap[smallest]) {
                smallest = left;
            }

            if (right < n && heap[right] < heap[smallest]) {
                smallest = right;
            }

            if (smallest === i) break;

            [heap[i], heap[smallest]] = [heap[smallest], heap[i]];
            i = smallest;
        }
    };

    const push = (x) => {
        heap.push(x);
        heapifyUp(heap.length - 1);
    };

    const pop = () => {
        const top = heap[0];
        const last = heap.pop();

        if (heap.length) {
            heap[0] = last;
            heapifyDown(0);
        }

        return top;
    };

    for (const num of nums) {
        push(num);

        if (heap.length > k) {
            pop();
        }
    }

    return heap[0];
};