public class InPostBuildTree {

	/*
	 * 根据中序遍历和后序遍历树构造二叉树 注意事项 你可以假设树中不存在相同数值的节点
	 * @param inorder: A list of integers that inorder traversal of a tree 
	 * @param postorder: A list of integers that postorder traversal of a tree
	 * @return: Root of a tree
	 */
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		// write your code here
		TreeNode node = null;
		if (inorder.length == 0 || postorder.length == 0) {
			return node;
		}
		node = recursionBuildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
		return node;
	}

	/*
	 * 算法思想：很显然，我们可以知道，这棵树的根节点就是后序遍历中的最后一位。 然后，在中序遍历的列表中找到这个根节点所在的位置，我们记为pos。
	 * 那么，pos前面的节点就是左子树的所有节点，而pos后面的节点就是右子树的所有节点， 这样，我们能推出2个关键的量：左右子树的节点个数。
	 * 当n=1时，只有一个根结点，由中序序列和后序序列可以确定这棵二叉树。
	 * 设当n=m-1时结论成立，即结点数目为m-1时，中序序列和后序序列可以唯一确定二叉树。现证明当n=m时结论成立。
	 * 设中序序列为S1,S2,…,Sm,后序序列是P1,P2,…,Pm。
	 * 因后序序列最后一个元素Pm是根，则在中序序列中可找到与Pm相等的结点（设二叉树中各结点互不相同）Si(1≤i≤m)，
	 * 因中序序列是由中序遍历而得，所以Si是根结点，S1,S2,…,Si-1是左子树的中序序列，而Si+1,Si+2,…,Sm是右子树的中序序列。
	 * 若i=1，则S1是根，这时二叉树的左子树为空，右子树的结点数是m-1,则{S2,S3,…,Sm}和{P1,P2,…,Pm-1}可以唯一确定右子树，
	 * 从而也确定了二叉树。
	 * 若i=m，则Sm是根，这时二叉树的右子树为空，左子树的结点数是m-1，则{S1,S2,…,Sm-1}和{P1,P2,…,Pm-
	 * 1}唯一确定左子树，从而也确定了二叉树。 最后，当1<i<m时，Si把中序序列分成{S1,S2,…,Si-1}和{Si+1,Si+2,…,Sm}。
	 * 由于后序遍历是"左子树-右子树-根结点"，所以{P1,P2,…,Pi-1}和{Pi,Pi+1,…Pm-1}是二叉树的左子树和右子树的后序遍历序列。
	 * 因而由
	 * {S1,S2,…,Si-1}和{P1,P2,…,Pi-1}可唯一确定二叉树的左子树，由{Si+1,Si+2,…,Sm}和{Pi,Pi+1,…,
	 * Pm-1}可唯一确定二叉树的右子树。
	 */
	/**
	 * 递归出口：当子树在中序遍历序列中的开始位置大于结束位置
	 * @param inorder 		中序遍历序列
	 * @param postorder		后序遍历序列
	 * @param inStartPos	子树在中序遍历序列中的开始位置
	 * @param inEndPos		子树在中序遍历序列中的结束位置
	 * @param postStartPos	子树在后序遍历序列中的开始位置
	 * @param postEndPos	子树在后序遍历序列中的结束位置
	 * @return
	 */
	private TreeNode recursionBuildTree(int[] inorder, int[] postorder,
			int inStartPos, int inEndPos, int postStartPos, int postEndPos) {

		if (inStartPos > inEndPos) {
			return null;
		}
		int endPosNum = postorder[postEndPos];
		int pos = findRootPos(endPosNum, inorder); // 中序遍历序列中根节点所在的位置
		TreeNode rootNode = new TreeNode(inorder[pos]);
		rootNode.left = recursionBuildTree(inorder, postorder, inStartPos,
				pos - 1, postStartPos, postStartPos + pos - 1 - inStartPos);
		rootNode.right = recursionBuildTree(inorder, postorder, pos + 1,
				inEndPos, postStartPos + pos - inStartPos, postEndPos - 1);
		return rootNode;
	}

	private int findRootPos(int num, int[] inorder) {
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == num)
				return i;
		}
		return -1;
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

