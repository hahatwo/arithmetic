public class PreInBuildTree {

	/**
	 * 前序遍历和中序遍历树构造二叉树
     *@param preorder : A list of integers that preorder traversal of a tree
     *@param inorder : A list of integers that inorder traversal of a tree
     *@return : Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
    	TreeNode node = null;
    	if(preorder.length == 0 || inorder.length == 0 || preorder.length  != inorder.length ) {
    		return node;
    	}
    	node = recursionBuildTree(inorder, preorder, 0, inorder.length - 1, 0, preorder.length - 1);
    	return node;
    }
	
    
    /**
     * 算法思想：和InpreBuildTree类中中序和后序构建二叉树类似
	 * 递归出口：当子树在中序遍历序列中的开始位置大于结束位置
	 * @param inorder 		中序遍历序列
	 * @param preorder		先序遍历序列
	 * @param inStartPos	子树在中序遍历序列中的开始位置
	 * @param inEndPos		子树在中序遍历序列中的结束位置
	 * @param preStartPos	子树在先序遍历序列中的开始位置
	 * @param preEndPos		子树在先序遍历序列中的结束位置
	 * @return
	 */
    private TreeNode recursionBuildTree(int[] inorder, int[] preorder,
			int inStartPos, int inEndPos, int preStartPos, int preEndPos) {

		if (inStartPos > inEndPos) {
			return null;
		}
		int prePosNum = preorder[preStartPos];
		int pos = findRootPos(prePosNum, inorder); 		// 中序遍历序列中根节点所在的位置
		TreeNode rootNode = new TreeNode(inorder[pos]);
		rootNode.left = recursionBuildTree(inorder, preorder, inStartPos,
				pos - 1, preStartPos + 1, preStartPos + pos - inStartPos);
		rootNode.right = recursionBuildTree(inorder, preorder, pos + 1,
				inEndPos, preStartPos + pos - inStartPos + 1, preEndPos);
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


