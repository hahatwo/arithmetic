public class ValidateBinarySearchTree {

	/*
	 * 给定一个二叉树，判断它是否是合法的二叉查找树(BST)
	 * 一棵BST定义为：
	 * 节点的左子树中的值要严格小于该节点的值。
	 * 节点的右子树中的值要严格大于该节点的值。
	 * 左右子树也必须是二叉查找树。
	 * 一个节点的树也是二叉查找树。
     * @param root: The root of binary tree.
     * @return: True if the binary tree is BST, or false
     */
    public boolean isValidBST(TreeNode root) {
        // write your code here
    	return isValid(root, Integer.MIN_VALUE, Integer.MAX_VALUE);
    }
    
    /**
     * 算法思想： 递归遍历
     * 			root.val 肯定在min和max之间
     * 			root.left.val肯定在min和root.val之间
     * 			root.right.val肯定在root.val和max之间
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
