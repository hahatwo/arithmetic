public class RemoveDuplicatesfromSortedList {

	/*
	 * 给定一个排序链表，删除所有重复的元素每个元素只留下一个。
	 * 样例
	 * 给出 1->1->2->null，返回 1->2->null
	 * 给出 1->1->2->3->3->null，返回 1->2->3->null
	 * @param head: head is the head of the linked list
	 * @return: head of linked list
	 */
	public ListNode deleteDuplicates(ListNode head) {
		// write your code here
		if(head == null || head.next == null) {
			return head;
		}
		ListNode temp = head;
		while(temp != null) {
			if(temp.next != null) {
				if(temp.val == temp.next.val) {
					temp.next = temp.next.next;
				} else {
					temp = temp.next;
				}
			} else {
			    temp = temp.next;
			}
		}
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
