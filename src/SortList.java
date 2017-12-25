public class SortList {

	/*
	 * �� O(n log n) ʱ�临�ӶȺͳ������Ŀռ临�Ӷ��¸���������
	 * @param head: The head of linked list.
	 * @return: You should return the head of the sorted linked list, using
	 * constant space complexity.
	 */
	public ListNode sortList(ListNode head) {
		// write your code here
		if(head == null || head.next == null) {
			return head;
		}
		head = mergeSort(head);
		return head;
	}

	/**
	 * �鲢����
	 * @param head
	 * @return
	 */
	private ListNode mergeSort(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode middleNode = findMiddleNode(head);
		ListNode middleNextNode = middleNode.next; //�ҵ������м�ڵ�ĺ�һ���ڵ�,��Ϊ��һ����������
		middleNode.next = null; //��������Ϊ��������
		return mergeOrderList(mergeSort(head), mergeSort(middleNextNode));
	}
	
	/**
	 * �ҵ�������м�ڵ�
	 * �㷨˼�룺
	 * 		   �������ָ��evenNode, oddNode,��ʼ��ָ������ͷ�ڵ�
	 * 			��������evenNodeÿ��ָ��evenNode.next.next(�������ڵ�)
	 * 			��oddNodeÿ��ָ��oddNode.next(��һ���ڵ�),
	 * 			��ô��evenNodeָ������ĩβʱ�� oddNode��ָ��������м�ڵ�
	 * @param node
	 * @return
	 */
	private ListNode findMiddleNode(ListNode node) {
		ListNode evenNode = null;
		ListNode oddNode = null;
		evenNode = oddNode = node;
		while(evenNode.next != null && evenNode.next.next != null) {
			evenNode = evenNode.next.next;
			oddNode = oddNode.next;
		}
		return oddNode;
	}
	
	//�ϲ�������������
	private ListNode mergeOrderList(ListNode head1, ListNode head2) {
		ListNode newListHead = new ListNode(0);
		ListNode currentNode = newListHead;
		while(head1 != null && head2 != null) {
			if(head1.val < head2.val) {
				currentNode.next = head1;
				currentNode = currentNode.next;
				head1 = head1.next;
			} else {
				currentNode.next = head2;
				currentNode = currentNode.next;
				head2 = head2.next;
			}
		}
		currentNode.next = head1 == null ? head2 : head1;
		return newListHead.next;
	}
	
	
	/**
	 * ��������
	 * @param head
	 * @return
	 */
	private ListNode quickSort(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode leftDummy = new ListNode(0);
		ListNode rightDummy = new ListNode(0);
		ListNode medianDummy = new ListNode(0);
		ListNode leftTail = leftDummy;
		ListNode rightTail = rightDummy;
		ListNode medianTail = medianDummy;
		ListNode middleNode = findMiddleNode(head);
		while(head != null) {
			if(head.val < middleNode.val) {
				leftTail.next = head;
				leftTail = leftTail.next;
			} else if(head.val > middleNode.val) {
				rightTail.next = head;
				rightTail = rightTail.next;
			} else {
				medianTail.next = head;
				medianTail = medianTail.next;
			}
			head = head.next;
		}
		medianTail.next = null;
		leftTail.next = null;
		rightTail.next = null;
		return concat(quickSort(leftDummy.next), medianDummy.next, quickSort(rightDummy.next));
	}
	
	//�ϲ�����(3��)
	private ListNode concat(ListNode leftHead, ListNode medianHead, ListNode rightHead) {
		ListNode dummy = new ListNode(0);
		ListNode tail = dummy;
		tail.next = leftHead;
		tail = getTail(tail);
		tail.next = medianHead;
		tail = getTail(tail);
		tail.next = rightHead;
		return dummy.next;
	}
	
	//�ҵ�����ĩβ�ڵ�
	private ListNode getTail(ListNode head) {
		if(head == null) {
			return head;
		}
		while(head.next != null) {
			head = head.next;
		}
		return head;
	}
	
	class ListNode {
		int val;
		ListNode next;
		ListNode(int val) {
			this.val = val;
			this.next = null;
		}
	}
}
