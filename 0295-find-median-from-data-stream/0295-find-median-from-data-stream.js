/**
 * initialize your data structure here.
 */
var MedianFinder = function() {
    this.maxHeap = [];
    this.minHeap = [];
};

MedianFinder.prototype.maxHeapifyUp = function(i) {
    while (i > 0) {
        const p = Math.floor((i - 1) / 2);

        if (this.maxHeap[p] >= this.maxHeap[i]) break;

        [this.maxHeap[p], this.maxHeap[i]] =
            [this.maxHeap[i], this.maxHeap[p]];

        i = p;
    }
};

MedianFinder.prototype.maxHeapifyDown = function(i) {
    const n = this.maxHeap.length;

    while (true) {
        let largest = i;
        const left = 2 * i + 1;
        const right = 2 * i + 2;

        if (left < n && this.maxHeap[left] > this.maxHeap[largest]) {
            largest = left;
        }

        if (right < n && this.maxHeap[right] > this.maxHeap[largest]) {
            largest = right;
        }

        if (largest === i) break;

        [this.maxHeap[i], this.maxHeap[largest]] =
            [this.maxHeap[largest], this.maxHeap[i]];

        i = largest;
    }
};

MedianFinder.prototype.minHeapifyUp = function(i) {
    while (i > 0) {
        const p = Math.floor((i - 1) / 2);

        if (this.minHeap[p] <= this.minHeap[i]) break;

        [this.minHeap[p], this.minHeap[i]] =
            [this.minHeap[i], this.minHeap[p]];

        i = p;
    }
};

MedianFinder.prototype.minHeapifyDown = function(i) {
    const n = this.minHeap.length;

    while (true) {
        let smallest = i;
        const left = 2 * i + 1;
        const right = 2 * i + 2;

        if (left < n && this.minHeap[left] < this.minHeap[smallest]) {
            smallest = left;
        }

        if (right < n && this.minHeap[right] < this.minHeap[smallest]) {
            smallest = right;
        }

        if (smallest === i) break;

        [this.minHeap[i], this.minHeap[smallest]] =
            [this.minHeap[smallest], this.minHeap[i]];

        i = smallest;
    }
};

MedianFinder.prototype.maxPush = function(val) {
    this.maxHeap.push(val);
    this.maxHeapifyUp(this.maxHeap.length - 1);
};

MedianFinder.prototype.maxPop = function() {
    const top = this.maxHeap[0];
    const last = this.maxHeap.pop();

    if (this.maxHeap.length) {
        this.maxHeap[0] = last;
        this.maxHeapifyDown(0);
    }

    return top;
};

MedianFinder.prototype.minPush = function(val) {
    this.minHeap.push(val);
    this.minHeapifyUp(this.minHeap.length - 1);
};

MedianFinder.prototype.minPop = function() {
    const top = this.minHeap[0];
    const last = this.minHeap.pop();

    if (this.minHeap.length) {
        this.minHeap[0] = last;
        this.minHeapifyDown(0);
    }

    return top;
};

/** 
 * @param {number} num
 * @return {void}
 */
MedianFinder.prototype.addNum = function(num) {
    if (
        this.maxHeap.length === 0 ||
        num <= this.maxHeap[0]
    ) {
        this.maxPush(num);
    } else {
        this.minPush(num);
    }

    if (this.maxHeap.length > this.minHeap.length + 1) {
        this.minPush(this.maxPop());
    } else if (this.minHeap.length > this.maxHeap.length + 1) {
        this.maxPush(this.minPop());
    }
};

/**
 * @return {number}
 */
MedianFinder.prototype.findMedian = function() {
    if (this.maxHeap.length === this.minHeap.length) {
        return (this.maxHeap[0] + this.minHeap[0]) / 2;
    }

    return this.maxHeap.length > this.minHeap.length
        ? this.maxHeap[0]
        : this.minHeap[0];
};