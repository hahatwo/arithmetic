
public class MinimumDepthofBinaryTree {

	/*
	 * 给定一个二叉树，找出其最小深度。
	 * 二叉树的最小深度为根节点到最近叶子节点的距离。
	 * 算法思想: 递归
     * @param root: The root of binary tree
     * @return: An integer
     */
    public int minDepth(TreeNode root) {
        // write your code here
        if(root == null) {
            return 0;
        }
        
        if(root.left != null && root.right != null) {
            return Math.min(minDepth(root.left), minDepth(root.right))+1;
        } else if(root.left != null) {
            return minDepth(root.left)+1;
        } else {
            return minDepth(root.right)+1;
        }
    }
}
