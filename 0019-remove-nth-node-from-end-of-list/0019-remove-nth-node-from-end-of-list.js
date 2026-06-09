/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} n
 * @return {ListNode}
 */
var removeNthFromEnd = function(head, n) {
    let fastp = head;
    let slowp = head;

    for (let i = 0; i < n; i++) {
        fastp = fastp.next;
    }

    if (fastp === null) {
        return head.next;
    }

    while (fastp.next !== null) {
        fastp = fastp.next;
        slowp = slowp.next;
    }

    slowp.next = slowp.next.next;
    return head;
};