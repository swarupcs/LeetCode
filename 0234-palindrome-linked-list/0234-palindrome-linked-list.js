/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val === undefined ? 0 : val);
 *     this.next = (next === undefined ? null : next);
 * }
 */

/**
 * @param {ListNode} head
 * @return {boolean}
 */
var isPalindrome = function(head) {
    if (head === null || head.next === null) {
        return true;
    }

    let slow = head;
    let fast = head;

    while (fast.next !== null && fast.next.next !== null) {
        slow = slow.next;
        fast = fast.next.next;
    }

    let newHead = reverseLinkedList(slow.next);

    let first = head;
    let second = newHead;

    while (second !== null) {
        if (first.val !== second.val) {
            slow.next = reverseLinkedList(newHead);
            return false;
        }

        first = first.next;
        second = second.next;
    }

    slow.next = reverseLinkedList(newHead);

    return true;
};

function reverseLinkedList(head) {
    let prev = null;
    let curr = head;

    while (curr !== null) {
        let nextNode = curr.next;
        curr.next = prev;
        prev = curr;
        curr = nextNode;
    }

    return prev;
}