public class RemoveNodeinBinarySearchTree {

	/*
	 * 给定一棵具有不同节点值的二叉查找树，删除树中与给定值相同的节点。如果树中没有相同值的节点，就不做任何处理。 
	 * 你应该保证处理之后的树仍是二叉查找树。
	 * 算法思想： http://blog.csdn.net/lcore/article/details/8889176
	 *  对于二叉查找树的删除操作（这里根据值删除，而非结点）分三种情况：
	 *  不过在此之前，我们应该确保根据给定的值找到了要删除的结点，如若没找到该结点不会执行删除操作！
	 *   下面三种情况假设已经找到了要删除的结点。
	 *   1.如果结点为叶子结点（没有左、右子树），此时删除该结点不会破坏树的结构  直接删除即可，并修改其父结点指向它的引用为null.
	 *   2.如果其结点只包含左子树，或者右子树的话，此时直接删除该结点，并将其左子树
	 *    或者右子树设置为其父结点的左子树或者右子树即可，此操作不会破坏树结构。
	 *   3. 当结点的左右子树都不空的时候，一般的删除策略是用其右子树的最小数据（容易找到）代替要删除的结点数据并递归删除该结点（此时为null），
	 *   因为 右子树的最小结点不可能有左孩子，所以第二次删除较为容易。
	 *   z的左子树和右子树均不空。找到z的后继y，因为y一定没有左子树，所以可以删除y，并让y的父亲节点成为y的右子树的父亲节点，并用y的值代替z的值.
	 * @param root: The root of the binary search tree.
	 * @param value: Remove the node with given value.
	 * @return: The root of the binary search tree after removal.
	 */
	public TreeNode removeNode(TreeNode root, int value) {
		// write your code here
		if(root == null) {
			return null;
		} else if(root.val < value) {
			root.right = removeNode(root.right, value);
		} else if(root.val > value) {
			root.left = removeNode(root.left, value); 
		} else if(root.left != null && root.right != null) {
			TreeNode minNode = findMinNode(root.right); //找到节点右子树的最小节点
			root.val = minNode.val;
			root.right = removeNode(root.right, minNode.val);
		} else if(root.left != null && root.right == null) { //节点只包含左子树
			root = root.left;
		} else if(root.left == null && root.right != null) { //节点只包含右子树
			root = root.right;
		} else { //节点为叶子节点
			root = null;
		}
		return root;
	}

	/**
	 * 找到node节点的最小值节点，即最左子节点
	 * @param node
	 * @return
	 */
	private TreeNode findMinNode(TreeNode node) {
		if(node == null) {
			return node;
		}
		while(node.left != null) {
			node = node.left;
		}
		return node;
	}
	
	
	class TreeNode {
		public int val;
		public TreeNode left, right;
		public TreeNode(int val) {
			this.val = val;
			this.left = this.right = null;
		}
	}
}
