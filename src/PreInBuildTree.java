public class PreInBuildTree {

	/**
	 * ǰ�������������������������
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
     * �㷨˼�룺��InpreBuildTree��������ͺ��򹹽�����������
	 * �ݹ���ڣ���������������������еĿ�ʼλ�ô��ڽ���λ��
	 * @param inorder 		�����������
	 * @param preorder		�����������
	 * @param inStartPos	������������������еĿ�ʼλ��
	 * @param inEndPos		������������������еĽ���λ��
	 * @param preStartPos	������������������еĿ�ʼλ��
	 * @param preEndPos		������������������еĽ���λ��
	 * @return
	 */
    private TreeNode recursionBuildTree(int[] inorder, int[] preorder,
			int inStartPos, int inEndPos, int preStartPos, int preEndPos) {

		if (inStartPos > inEndPos) {
			return null;
		}
		int prePosNum = preorder[preStartPos];
		int pos = findRootPos(prePosNum, inorder); 		// ������������и��ڵ����ڵ�λ��
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


