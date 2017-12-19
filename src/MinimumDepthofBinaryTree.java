
public class MinimumDepthofBinaryTree {

	/*
	 * ����һ�����������ҳ�����С��ȡ�
	 * ����������С���Ϊ���ڵ㵽���Ҷ�ӽڵ�ľ��롣
	 * �㷨˼��: �ݹ�
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
