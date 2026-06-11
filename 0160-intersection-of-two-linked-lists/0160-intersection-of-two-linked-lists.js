/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */

/**
 * @param {ListNode} headA
 * @param {ListNode} headB
 * @return {ListNode}
 */
var getIntersectionNode = function(headA, headB) {
    if (headA === null || headB === null) return null;

    let d1 = headA, d2 = headB;

    while (d1 !== d2) {
        d1 = d1 ? d1.next : headB;
        d2 = d2 ? d2.next : headA;
    }

    return d1;
};