/**
 * initialize your data structure here.
 */
var StockSpanner = function() {
    this.stack = [];
    this.index = -1;
};

/** 
 * @param {number} price
 * @return {number}
 */
StockSpanner.prototype.next = function(price) {
    this.index++;

    while (
        this.stack.length &&
        this.stack[this.stack.length - 1][0] <= price
    ) {
        this.stack.pop();
    }

    const span = this.stack.length === 0
        ? this.index + 1
        : this.index - this.stack[this.stack.length - 1][1];

    this.stack.push([price, this.index]);

    return span;
};