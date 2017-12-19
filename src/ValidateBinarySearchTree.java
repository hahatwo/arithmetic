public class ValidateBinarySearchTree {

	/*
	 * ����һ�����������ж����Ƿ��ǺϷ��Ķ��������(BST)
	 * һ��BST����Ϊ��
	 * �ڵ���������е�ֵҪ�ϸ�С�ڸýڵ��ֵ��
	 * �ڵ���������е�ֵҪ�ϸ���ڸýڵ��ֵ��
	 * ��������Ҳ�����Ƕ����������
	 * һ���ڵ����Ҳ�Ƕ����������
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
    	return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    /**
     * �㷨˼�룺 �ݹ����
     * 			root.val �϶���min��max֮��
     * 			root.left.val�϶���min��root.val֮��
     * 			root.right.val�϶���root.val��max֮��
     * @param root
     * @param min
     * @param max
     * @return
     */
    private boolean isValid(TreeNode root, int min, int max) {
    	if(root == null) 
    		return true;
    	if(root.val <= min || root.val >= max) {
    		return false;
    	}
    	return isValid(root.left, min, Math.min(max, root.val)) && isValid(root.right, Math.max(min, root.val), max);
    }
    
}
