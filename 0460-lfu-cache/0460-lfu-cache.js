class Node {
    constructor(key, value) {
        this.key = key;
        this.value = value;
        this.cnt = 1;
        this.prev = null;
        this.next = null;
    }
}

class List {
    constructor() {
        this.size = 0;
        this.head = new Node(0, 0);
        this.tail = new Node(0, 0);

        this.head.next = this.tail;
        this.tail.prev = this.head;
    }

    addFront(node) {
        node.next = this.head.next;
        node.prev = this.head;

        this.head.next.prev = node;
        this.head.next = node;

        this.size++;
    }

    remove(node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
        this.size--;
    }
}

/**
 * @param {number} capacity
 */
var LFUCache = function(capacity) {
    this.capacity = capacity;
    this.size = 0;
    this.minFreq = 0;

    this.keyMap = new Map();
    this.freqMap = new Map();
};

LFUCache.prototype.update = function(node) {
    const freq = node.cnt;
    const list = this.freqMap.get(freq);

    list.remove(node);

    if (freq === this.minFreq && list.size === 0) {
        this.minFreq++;
    }

    node.cnt++;

    if (!this.freqMap.has(node.cnt)) {
        this.freqMap.set(node.cnt, new List());
    }

    this.freqMap.get(node.cnt).addFront(node);
};

/** 
 * @param {number} key
 * @return {number}
 */
LFUCache.prototype.get = function(key) {
    if (!this.keyMap.has(key)) {
        return -1;
    }

    const node = this.keyMap.get(key);
    this.update(node);

    return node.value;
};

/** 
 * @param {number} key 
 * @param {number} value
 * @return {void}
 */
LFUCache.prototype.put = function(key, value) {
    if (this.capacity === 0) {
        return;
    }

    if (this.keyMap.has(key)) {
        const node = this.keyMap.get(key);
        node.value = value;
        this.update(node);
        return;
    }

    if (this.size === this.capacity) {
        const list = this.freqMap.get(this.minFreq);
        const node = list.tail.prev;

        list.remove(node);
        this.keyMap.delete(node.key);

        this.size--;
    }

    const node = new Node(key, value);

    this.minFreq = 1;

    if (!this.freqMap.has(1)) {
        this.freqMap.set(1, new List());
    }

    this.freqMap.get(1).addFront(node);
    this.keyMap.set(key, node);

    this.size++;
};