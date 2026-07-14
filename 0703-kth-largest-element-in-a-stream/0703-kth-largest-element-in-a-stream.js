/**
 * @param {number} k
 * @param {number[]} nums
 */
var KthLargest = function(k, nums) {
    this.k = k;
    this.heap = [];

    for (const num of nums) {
        this.add(num);
    }
};

/**
 * @param {number} val
 * @return {number}
 */
KthLargest.prototype.add = function(val) {
    if (this.heap.length < this.k) {
        this.push(val);
    } else if (val > this.heap[0]) {
        this.pop();
        this.push(val);
    }

    return this.heap[0];
};

// Insert into min-heap
KthLargest.prototype.push = function(val) {
    this.heap.push(val);

    let i = this.heap.length - 1;

    while (i > 0) {
        let parent = Math.floor((i - 1) / 2);

        if (this.heap[parent] <= this.heap[i]) {
            break;
        }

        [this.heap[parent], this.heap[i]] =
            [this.heap[i], this.heap[parent]];

        i = parent;
    }
};

// Remove minimum element
KthLargest.prototype.pop = function() {
    const last = this.heap.pop();

    if (this.heap.length === 0) {
        return;
    }

    this.heap[0] = last;

    let i = 0;

    while (true) {
        let left = 2 * i + 1;
        let right = 2 * i + 2;
        let smallest = i;

        if (
            left < this.heap.length &&
            this.heap[left] < this.heap[smallest]
        ) {
            smallest = left;
        }

        if (
            right < this.heap.length &&
            this.heap[right] < this.heap[smallest]
        ) {
            smallest = right;
        }

        if (smallest === i) {
            break;
        }

        [this.heap[i], this.heap[smallest]] =
            [this.heap[smallest], this.heap[i]];

        i = smallest;
    }
};

/** 
 * Your KthLargest object will be instantiated and called as such:
 * var obj = new KthLargest(k, nums)
 * var param_1 = obj.add(val)
 */