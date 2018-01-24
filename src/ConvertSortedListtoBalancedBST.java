
public class ConvertSortedListtoBalancedBST {

	/*
	 * 给出一个所有元素以升序排序的单链表，将它转换成一棵高度平衡的二分查找树
     * @param head: The first node of linked list.
     * @return: a tree node
     */
    public TreeNode sortedListToBST(ListNode head) {
        // write your code here
    	TreeNode root = divideToBST(head);
    	return root;
    }
	
    /**
     * 算法思想1：	这个题目本质上就是一个找中间值的过程。不断地找中间值。
     * 				利用递归的思想，再将中间值拆分，再（左边的链接）这个中间值（root->left）。
     * 				然后中间值->next（右边的链表）的中间值(root->right)。
     * 				每次递归直接返回root。
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
    	ListNode preNode = null; //中间节点的前一个节点
    	while(left < mid) { //找到链表中间节点
    		preNode = medianNode;
    		medianNode = medianNode.next;
    		left++;
    	}
    	root = new TreeNode(medianNode.val);
    	preNode.next = null; //把链表从medianNode断开
    	root.left = divideToBST(head);
    	root.right = divideToBST(medianNode.next);
    	return root;
    }
    
    /**
     * 找到单链表的链表长度
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
