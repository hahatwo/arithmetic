
public class ReorderList {

	/*
	 * ����һ��������L: L0��L1������Ln-1��Ln,
	 * �������к�Ϊ��L0��Ln��L1��Ln-1��L2��Ln-2����
	 * �����ڲ��ı�ڵ�ֵ������½���ԭ�ز�����
	 * ����
	 * �������� 1->2->3->4->null���������к�Ϊ1->4->2->3->null��
     * @param head: The head of linked list.
     * @return: nothing
     */
    public void reorderList(ListNode head) {
        // write your code here
    	if(head == null || head.next == null) {
    		return;
    	}
    	ListNode medianNode = findMedianNode(head);
    	ListNode medianNextNode = reverseList(medianNode.next);
    	medianNode.next = null;
    	mergeList(head, medianNextNode);
    }
    
 
    
    private ListNode findMedianNode(ListNode head) {
    	if(head == null || head.next == null) {
    		return head;
    	}
    	ListNode evenTail = head;
    	ListNode oddTail = head;
    	while(evenTail.next != null && evenTail.next.next != null) {
    		evenTail = evenTail.next.next;
    		oddTail = oddTail.next;
    	}
    	return oddTail;
    }
    
    private ListNode reverseList(ListNode head) {
    	ListNode dummy = null;
    	ListNode temp = null;
    	while(head != null) {
    		temp = head.next;
    		head.next = dummy;
    		dummy = head;
    		head = temp;
    	}
    	return dummy;
    }
    
    //�ϲ���������
    private ListNode mergeList(ListNode head1, ListNode head2) {
    	int index = 1;
    	ListNode dummy = new ListNode(0);
    	ListNode tail = dummy;
    	ListNode leftTail = head1;
    	ListNode rightTail = head2;
    	while(leftTail != null && rightTail != null) {
    		if(index%2 == 0) {
    			tail.next = rightTail;
    			tail = tail.next;
    			rightTail = rightTail.next;
    		} else {
    			tail.next = leftTail;
    			tail = tail.next;
    			leftTail = leftTail.next;
    		}
    		index++;
    	}
    	if(leftTail != null) {
    		tail.next = leftTail;
    	} else {
    		tail.next = rightTail;
    	}
    	return dummy.next;
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
