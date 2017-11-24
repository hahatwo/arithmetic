import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class LevelOrderBottom {
	/*
	 * 给出一棵二叉树，返回其节点值从底向上的层次序遍历（按从叶节点所在层到根节点所在的层遍历，然后逐层从左往右遍历）
	 * @param root: A tree
	 * @return: buttom-up level order a list of lists of integer
	 */
	public List<List<Integer>> levelOrderBottom(TreeNode root) {
		// write your code here
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if(root == null) {
			return list;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int size = 0;
		TreeNode node = null;
		while(!queue.isEmpty()) {
			size = queue.size();
			List<Integer> nodeList = new ArrayList<Integer>();
			for(int i = 0; i < size; i++) {
				node = queue.poll();
				nodeList.add(node.val);
				if(node.left != null)
					queue.offer(node.left);
				if(node.right != null)
					queue.offer(node.right);
			}
			list.add(nodeList);
		}
		Collections.reverse(list);
		return list;
	}
}

class TreeNode {
	public int val;
	public TreeNode left, right;

	public TreeNode(int val) {
		this.val = val;
		this.left = this.right = null;
	}
}