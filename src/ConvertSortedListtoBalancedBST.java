
public class ConvertSortedListtoBalancedBST {

	/*
	 * ����һ������Ԫ������������ĵ���������ת����һ�ø߶�ƽ��Ķ��ֲ�����
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {
        // write your code here
    	TreeNode root = divideToBST(head);
    	return root;
    }
	
    /**
     * �㷨˼��1��	�����Ŀ�����Ͼ���һ�����м�ֵ�Ĺ��̡����ϵ����м�ֵ��
     * 				���õݹ��˼�룬�ٽ��м�ֵ��֣��٣���ߵ����ӣ�����м�ֵ��root->left����
     * 				Ȼ���м�ֵ->next���ұߵ��������м�ֵ(root->right)��
     * 				ÿ�εݹ�ֱ�ӷ���root��
     * @param head
     * @return
     */
    private TreeNode divideToBST(ListNode head) {
    	if(head == null) {
    		return null;
    	}
    	int len = lengthOfList(head);
    	TreeNode root = null;
    	if(len == 1) {
    		root = new TreeNode(head.val);
    		return root;
    	}
    	int mid = len / 2;
    	int left = 0;
    	ListNode medianNode = head;
    	ListNode preNode = null; //�м�ڵ��ǰһ���ڵ�
    	while(left < mid) { //�ҵ������м�ڵ�
    		preNode = medianNode;
    		medianNode = medianNode.next;
    		left++;
    	}
    	root = new TreeNode(medianNode.val);
    	preNode.next = null; //�������medianNode�Ͽ�
    	root.left = divideToBST(head);
    	root.right = divideToBST(medianNode.next);
    	return root;
    }
    
    /**
     * �ҵ��������������
     * @param head
     * @return
     */
    private int lengthOfList(ListNode head) {
    	int count = 0;
    	if(head == null) {
    		return count;
    	}
    	ListNode currentNode = head;
    	while(currentNode != null) {
    		count++;
    		currentNode = currentNode.next;
    	}
    	return count;
    }
    
    
    class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
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
