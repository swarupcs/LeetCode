/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} head
 * @param {number} k
 * @return {ListNode}
 */
var rotateRight = function(head, k) {
    if(head == null || head.next == null || k == 0) {
        return head;
    }

    let temp = head;

    let length = 1;
    while(temp.next != null) {
        length++;
        temp = temp.next;
    }

    k = k % length;

    temp.next = head;

    let end = length - k;

    while(end != 0) {
        temp = temp.next;
        end--;
    }

    head = temp.next;
    temp.next = null;
    return head;
    
};