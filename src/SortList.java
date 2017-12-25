public class SortList {

	/*
	 * 在 O(n log n) 时间复杂度和常数级的空间复杂度下给链表排序。
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
	 * 归并排序
	 * @param head
	 * @return
	 */
	private ListNode mergeSort(ListNode head) {
		if(head == null || head.next == null) {
			return head;
		}
		ListNode middleNode = findMiddleNode(head);
		ListNode middleNextNode = middleNode.next; //找到链表中间节点的后一个节点,作为另一条链表的起点
		middleNode.next = null; //将链表拆分为两个链表
		return mergeOrderList(mergeSort(head), mergeSort(middleNextNode));
	}
	
	/**
	 * 找到链表的中间节点
	 * 算法思想：
	 * 		   设计两个指针evenNode, oddNode,开始都指向链表头节点
	 * 			遍历链表，evenNode每次指向evenNode.next.next(跳两个节点)
	 * 			而oddNode每次指向oddNode.next(跳一个节点),
	 * 			那么当evenNode指向链表末尾时， oddNode就指向链表的中间节点
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
	
	//合并两条有序链表
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
	 * 快速排序
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
	
	//合并链表(3条)
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
	
	//找到链表末尾节点
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
