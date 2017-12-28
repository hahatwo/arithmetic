public class CopyListWithRandomPointer {

	/**
	 * ����һ������ÿ���ڵ����һ���������ӵ����ָ�����ָ�������е��κνڵ��յĽڵ㡣
	 * ����һ�����������
	 * �㷨˼�룺 ���http://www.cppblog.com/yuech/archive/2011/04/02/143318.html
     * @param head: The head of linked list with a random pointer.
     * @return: A new head of a deep copy of the list.
     */
	public RandomListNode copyRandomList(RandomListNode head) {
		// write your code here
		if(head == null) {
			return null;
		}
		RandomListNode currentNode = head;
		RandomListNode tailNode = null;
		while(currentNode != null) {
			tailNode = currentNode.next;
			RandomListNode newNode = copyRandomListNode(currentNode);
			currentNode.next = newNode;
			currentNode = tailNode;
		}
		currentNode = head;
		RandomListNode newCurrentNode = null;
		while(currentNode != null) {
			tailNode = currentNode.next.next;
			newCurrentNode = currentNode.next;
			if(currentNode.random != null) {
				newCurrentNode.random = currentNode.random.next;
			} else {
				newCurrentNode.random = null;
			}
		    if(newCurrentNode.next != null) {
		    	newCurrentNode.next = newCurrentNode.next.next;
		    } else {
		    	newCurrentNode.next = null;
		    }
			currentNode = tailNode;
		}
		return head.next;
	}

	private RandomListNode copyRandomListNode(RandomListNode node) {
		RandomListNode newNode = new RandomListNode(node.label);
		newNode.next = node.next;
		return newNode;
	}
	
	
	
	class RandomListNode {
		int label;
		RandomListNode next, random;
		RandomListNode(int x) {
			this.label = x;
		}
	}
}
