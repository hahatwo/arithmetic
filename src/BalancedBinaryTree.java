public class BalancedBinaryTree {

	/*
	 * ����һ��������,ȷ�����Ǹ߶�ƽ��ġ������������,һ�ø߶�ƽ��Ķ������Ķ����ǣ�
	 * һ�ö�������ÿ���ڵ������������������ᳬ��1��
	 * @param root: The root of binary tree.
	 * @return: True if this Binary tree is Balanced, or false.
	 */
	public boolean isBalanced(TreeNode root) {
		// write your code here
		if (root == null) {
			return true;
		}
		if (!isBalanced(root.left)) {
			return false;
		}
		if (!isBalanced(root.right)) {
			return false;
		}
		int leftHigh = highTree(root.left);
		int rightHigh = highTree(root.right);
		return Math.abs(rightHigh - leftHigh) > 1 ? false : true;
	}

	/**
	 * ������������߶�
	 * 
	 * @param node
	 * @return
	 */
	private int highTree(TreeNode node) {
		if (node == null) {
			return 0;
		}
		int high = Math.max(highTree(node.left), highTree(node.right)) + 1;
		return high;
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
