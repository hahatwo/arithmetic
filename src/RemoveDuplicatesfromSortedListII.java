
public class RemoveDuplicatesfromSortedListII {

	 /*
	 * 给定一个排序链表，删除所有重复的元素只留下原链表中没有重复的元素。
	 * 样例
	 * 给出 1->2->3->3->4->4->5->null，返回 1->2->5->null
	 * 给出 1->1->1->2->3->null，返回 2->3->null
     * @param head: head is the head of the linked list
     * @return: head of the linked list
     */
    public ListNode deleteDuplicates(ListNode head) {
        // write your code here
    	if(head == null || head.next == null) {
    		return head;
    	}
    	ListNode temp = head;
    	ListNode tempCount = head;
    	ListNode newHead = new ListNode(0);
    	newHead.next = head;
    	ListNode tempPre = newHead;
    	while(temp != null) {
    		if(temp.next != null) {
    			if(temp.val != temp.next.val) {
    				tempPre = temp;
    				temp = temp.next;
    			} else {
    				tempCount = temp;
    				while(tempCount.next != null && tempCount.val == tempCount.next.val) {
    					tempCount = tempCount.next;
    				}
    				temp = tempCount.next;
    				tempPre.next = temp;
    				tempCount.next = null;
    			}
    		} else {
    			temp = temp.next;
    		}
    	}
    	head = newHead.next;
    	newHead.next = null;
    	return head;
    }
    
    class ListNode {
		int val;
		ListNode next;

		ListNode(int x) {
			val = x;
			next = null;
		}
	}
}
