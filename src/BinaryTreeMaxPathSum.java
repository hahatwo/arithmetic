
public class BinaryTreeMaxPathSum {

	 /*
	 * ����һ�ö�������Ѱ��һ��·��ʹ��·�������·����������һ�ڵ��п�ʼ�ͽ�����·����Ϊ�����ڵ�֮������·���ϵĽڵ�Ȩֵ֮�ͣ�
	 * ����
	 * ����һ�ö�������
	 *   1
	 *  / \
	 * 2   3
	 * ���� 6
     * @param root: The root of binary tree.
     * @return: An integer
     */
    public int maxPathSum(TreeNode root) {
        // write your code here
    	return 0;
    }
    
    /**
     * �㷨˼�룺 �������·�����ģ��ڶ������������϶���һ��λ����ߵĽڵ㡣
     * ���ǲ��������λ����ߵĽڵ�ΪA����AΪ��߽ڵ��·������󳤶��Ƕ����أ�
     * A�������������ӣ���Ȼ���ӿ����ǿգ�����ÿ������Ϊ��㣬�������죬���Եõ��ܶ��������·���������е�Ȼ��һ�����·����
     * ���ǽ�������Ϊ�������·����ֵ��Ϊleft_val�������Һ���Ϊ�������·����ֵ��Ϊright_val��
     * ��Ȼ��AΪ��ߵ�����·��ֻ�������������������
     * 1. ��left_val < 0, ��right_val < 0, �����·��ΪA�ڵ㱾��maxPathSum(A) = A.val
     * 2. ��left_val > 0, ��right_val < 0, �����·��ΪA�ڵ����A������Ϊ�������·����maxPathSum(A) = A.val + left_val
     * 3. ��left_val < 0, ��right_val > 0, �����·��ΪA�ڵ����A���Һ���Ϊ�������·����maxPathSum(A) = A.val + right_val
     * 3. ��left_val > 0, ��right_val > 0, �����·��ΪA�ڵ����A�����Һ���Ϊ�������·�����ߵ����ϣ�
     * maxPathSum(A) = A.val + left_val + right_val
     * 
     * ���ϣ���AΪ��ߵ�����·����������������������ֵ����maxPathSum(A) = Math.max(left_val, 0) + A.val + Math.max(right_val, 0)
     * �����Ǳ��������������нڵ㣬����ÿ���ڵ�Ϊ��߽ڵ�����·�������ֵ���ɡ�
     * @param node
     * @param sumNode //ʹ��sumNode.val���������·���ͣ���Ϊ���������ֻ�Ǳ���ľֲ�����
     * @return
     */
    private int findMaxPathSum(TreeNode node, TreeNode sumNode) {
    	if(node == null) {
    		return 0;
    	}
    	int leftValue = findMaxPathSum(node.left, sumNode);
    	int rightValue = findMaxPathSum(node.right, sumNode);
    	//��¼���·����
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
