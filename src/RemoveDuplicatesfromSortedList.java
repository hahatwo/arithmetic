public class RemoveDuplicatesfromSortedList {

	/*
	 * ����һ����������ɾ�������ظ���Ԫ��ÿ��Ԫ��ֻ����һ����
	 * ����
	 * ���� 1->1->2->null������ 1->2->null
	 * ���� 1->1->2->3->3->null������ 1->2->3->null
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
