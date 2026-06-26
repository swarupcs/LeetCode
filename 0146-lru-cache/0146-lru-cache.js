/**
 * @param {number} capacity
 */
var LRUCache = function(capacity) {
    this.capacity = capacity;
    this.cache = new Map();

    this.head = {
        key: -1,
        val: -1,
        prev: null,
        next: null
    };

    this.tail = {
        key: -1,
        val: -1,
        prev: null,
        next: null
    };

    this.head.next = this.tail;
    this.tail.prev = this.head;
};

LRUCache.prototype.remove = function(node) {
    node.prev.next = node.next;
    node.next.prev = node.prev;
};

LRUCache.prototype.insert = function(node) {
    node.next = this.head.next;
    node.prev = this.head;

    this.head.next.prev = node;
    this.head.next = node;
};

/** 
 * @param {number} key
 * @return {number}
 */
LRUCache.prototype.get = function(key) {
    if (!this.cache.has(key)) {
        return -1;
    }

    const node = this.cache.get(key);

    this.remove(node);
    this.insert(node);

    return node.val;
};

/** 
 * @param {number} key 
 * @param {number} value
 * @return {void}
 */
LRUCache.prototype.put = function(key, value) {
    if (this.cache.has(key)) {
        const node = this.cache.get(key);

        node.val = value;

        this.remove(node);
        this.insert(node);

        return;
    }

    if (this.cache.size === this.capacity) {
        const lru = this.tail.prev;

        this.remove(lru);
        this.cache.delete(lru.key);
    }

    const node = {
        key,
        val: value,
        prev: null,
        next: null
    };

    this.insert(node);
    this.cache.set(key, node);
};