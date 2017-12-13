public class LowestCommonAncestor {

	/*
	 * ����һ�ö��������ҵ������ڵ������������ڵ�(LCA)��
	 * ������������������ڵ�Ĺ��������Ƚڵ��Ҿ��������ȡ�
	 * @param root: The root of the binary search tree.
	 * @param A: A TreeNode in a Binary.
	 * @param B: A TreeNode in a Binary.
	 * @return: Return the least common ancestor(LCA) of the two nodes.
	 */
	public TreeNode lowestCommonAncestor(TreeNode root, TreeNode A, TreeNode B) {
		// write your code here
		if(root == null) {
			return null;
		}
		if(root == A || root == B) {
			return root;
		}
		root.left = lowestCommonAncestor(root.left, A, B);
		root.right = lowestCommonAncestor(root.right, A, B);
		if(root.left != null && root.right != null) {
			return root;
		} else if(root.left != null) {
			return root.left;
		} else if(root.right != null) {
			return root.right;
		} 
		return null;
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
