
public class BinaryTreeMaxPathSum {

	 /*
	 * 给出一棵二叉树，寻找一条路径使其路径和最大，路径可以在任一节点中开始和结束（路径和为两个节点之间所在路径上的节点权值之和）
	 * 样例
	 * 给出一棵二叉树：
	 *   1
	 *  / \
	 * 2   3
	 * 返回 6
     * @param root: The root of binary tree.
     * @return: An integer
     */
    public int maxPathSum(TreeNode root) {
        // write your code here
    	return 0;
    }
    
    /**
     * 算法思想： 不管这个路径在哪，在二叉树中它都肯定有一个位置最高的节点。
     * 我们不妨设这个位置最高的节点为A，以A为最高节点的路径的最大长度是多少呢？
     * A有左右两个孩子（当然孩子可以是空），以每个孩子为起点，向下延伸，可以得到很多条单向的路径，这其中当然有一个最大路径。
     * 我们将以左孩子为起点的最大路径的值记为left_val，将以右孩子为起点的最大路径的值记为right_val，
     * 显然以A为最高点的最大路径只可能有以下四种情况：
     * 1. 若left_val < 0, 且right_val < 0, 那最大路径为A节点本身：maxPathSum(A) = A.val
     * 2. 若left_val > 0, 且right_val < 0, 那最大路径为A节点和以A的左孩子为起点的最大路径：maxPathSum(A) = A.val + left_val
     * 3. 若left_val < 0, 且right_val > 0, 那最大路径为A节点和以A的右孩子为起点的最大路径：maxPathSum(A) = A.val + right_val
     * 3. 若left_val > 0, 且right_val > 0, 那最大路径为A节点和以A的左、右孩子为起点的最大路径三者的联合：
     * maxPathSum(A) = A.val + left_val + right_val
     * 
     * 综上，以A为最高点的最大路径和是上面四种情况的最大值。即maxPathSum(A) = Math.max(left_val, 0) + A.val + Math.max(right_val, 0)
     * 那我们遍历二叉树的所有节点，求以每个节点为最高节点的最大路径的最大值即可。
     * @param node
     * @param sumNode //使用sumNode.val来保存最大路径和，因为如果用整型只是保存的局部变量
     * @return
     */
    private int findMaxPathSum(TreeNode node, TreeNode sumNode) {
    	if(node == null) {
    		return 0;
    	}
    	int leftValue = findMaxPathSum(node.left, sumNode);
    	int rightValue = findMaxPathSum(node.right, sumNode);
    	//记录最大路径和
    	sumNode.val = Math.max((Math.max(leftValue, 0) + node.val + Math.max(rightValue, 0)), sumNode.val);
    	
    	if(leftValue < 0 && rightValue < 0) {
    		sumNode.val = Math.max(sumNode.val, node.val);
    	} else if(leftValue > 0 && rightValue < 0) {
    		sumNode.val = Math.max(sumNode.val, node.val + leftValue);
    	} else if(leftValue < 0 && rightValue > 0) {
    		sumNode.val = Math.max(sumNode.val, node.val + rightValue);
    	} else {
    		sumNode.val = Math.max(sumNode.val, leftValue + rightValue + node.val);
    	}
    	return sumNode.val;
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
