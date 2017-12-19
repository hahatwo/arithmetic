public class PartitionList {

	/*
	 * ����һ�����������ֵx����������ʹ������С��x�Ľڵ����ڴ��ڵ���x�Ľڵ�֮ǰ����Ӧ�ñ���������������ڵ�ԭ�е����˳�� ���� ��������
	 * 1->4->3->2->5->2->null������ x=3 ���� 1->2->2->4->3->5->null 
	 * @param head: The first node of linked list
	 * @param x: An integer
	 * @return: A ListNode
	 */
	public ListNode partition(ListNode head, int x) {
		// write your code here
		if (head == null || head.next == null) {
			return head;
		}

		ListNode largeXNode = head; // ָ��ǰ����X�Ľڵ�
		ListNode preLargeXNode = null; // ָ��largeXNode�ڵ��ǰһ���ڵ�
		ListNode smallXNode = null; // ָ��largeXNode�ڵ�֮���С��X�Ľڵ�
		ListNode preSmallNode = null; // ָ��smallNode�ڵ��ǰһ���ڵ�

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
	 * �����ڵ�˳�򣬽�С��x�Ľڵ���뵽largeXNode�ڵ�ǰ��ȥ
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
