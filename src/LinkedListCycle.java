public class LinkedListCycle {

	/*
	 * ����һ�������ж����Ƿ��л���
	 * ����
	 * ���� -21->10->4->5, tail connects to node index 1������ true
     * @param head: The first node of linked list.
     * @return: True if it has a cycle, or false
     */
    public boolean hasCycle(ListNode head) {
        // write your code here
    	return false;
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
