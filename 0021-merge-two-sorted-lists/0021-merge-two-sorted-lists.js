/**
 * Definition for singly-linked list.
 * function ListNode(val, next) {
 *     this.val = (val===undefined ? 0 : val)
 *     this.next = (next===undefined ? null : next)
 * }
 */
/**
 * @param {ListNode} list1
 * @param {ListNode} list2
 * @return {ListNode}
 */
var mergeTwoLists = function(list1, list2) {
    let dummyNode = new ListNode(-1);
    let temp = dummyNode;
    let t1 = list1;
    let t2 = list2;

    while(t1 != null && t2 != null) {
        if(t1.val <= t2.val) {
            temp.next = t1;
            t1 = t1.next; 
        } else if(t1.val > t2.val) {
            temp.next = t2;
            t2 = t2.next
        }
        temp = temp.next;
    }

    if(t1 == null) {
        temp.next = t2;
    } else {
        temp.next = t1;
    }

    return dummyNode.next;

    
};