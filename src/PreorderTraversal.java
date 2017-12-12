import java.util.ArrayList;
import java.util.List;


public class PreorderTraversal {

	/*
	 * 给出一棵二叉树，返回其节点值的前序遍历。
     * @param root: A Tree
     * @return: Preorder in ArrayList which contains node values.
     */
    public List<Integer> preorderTraversal(TreeNode root) {
        // write your code here
    	if(root == null) {
    		return null;
    	}
    	List<Integer> list = new ArrayList<Integer>();
    	preorderTraversalTree(root, list);
    	return list;
    }
    
    private void preorderTraversalTree(TreeNode node, List<Integer> list) {
    	if(node == null) {
    		return;
    	}
    	
    	list.add(node.val);
    	preorderTraversalTree(node.left, list);
    	preorderTraversalTree(node.right, list);
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
