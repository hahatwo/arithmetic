public class PartitionList {

	/*
	 * 给定一个单链表和数值x，划分链表使得所有小于x的节点排在大于等于x的节点之前。你应该保留两部分内链表节点原有的相对顺序。 样例 给定链表
	 * 1->4->3->2->5->2->null，并且 x=3 返回 1->2->2->4->3->5->null 
	 * @param head: The first node of linked list
	 * @param x: An integer
	 * @return: A ListNode
	 */
	public ListNode partition(ListNode head, int x) {
		// write your code here
		if (head == null || head.next == null) {
			return head;
		}

		ListNode largeXNode = head; // 指向当前大于X的节点
		ListNode preLargeXNode = null; // 指向largeXNode节点的前一个节点
		ListNode smallXNode = null; // 指向largeXNode节点之后的小于X的节点
		ListNode preSmallNode = null; // 指向smallNode节点的前一个节点

		while (largeXNode != null && largeXNode.val < x) {
			preLargeXNode = largeXNode;
			largeXNode = largeXNode.next;
		}
		smallXNode = largeXNode.next;
		while (smallXNode != null) {
			if(smallXNode.val >= x) {
				preSmallNode = smallXNode;
				smallXNode = smallXNode.next;
			} else {
				changeOrder(preLargeXNode, preLargeXNode, preSmallNode, smallXNode, head);
			}
		}
		return head;
	}

	/**
	 * 交换节点顺序，将小于x的节点插入到largeXNode节点前面去
	 * @param preLargeXNode
	 * @param preSmallNode
	 * @param smallXNode
	 */
	private void changeOrder(ListNode preLargeXNode, ListNode largeXNode, ListNode preSmallNode, ListNode smallXNode,ListNode head) {
		
		preSmallNode.next = smallXNode.next;
		smallXNode.next = largeXNode;
		if(preLargeXNode == null) {
			head = smallXNode;
		} else {
			preLargeXNode.next = smallXNode;
		}
		preLargeXNode = smallXNode;
		smallXNode = preSmallNode.next;
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
