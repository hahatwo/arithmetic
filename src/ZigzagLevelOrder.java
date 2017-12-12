import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class ZigzagLevelOrder {

	/*
	 * 给出一棵二叉树，返回其节点值的锯齿形层次遍历（先从左往右，下一层再从右往左，层与层之间交替进行）
	 * @param root: A Tree
	 * @return: A list of lists of integer include the zigzag level order
	 * traversal of its nodes' values.
	 */
	public List<List<Integer>> zigzagLevelOrder(TreeNode root) {
		// write your code here
		List<List<Integer>> list = new ArrayList<List<Integer>>();
		if(root == null) {
			return list;
		}
		Queue<TreeNode> queue = new LinkedList<TreeNode>();
		queue.offer(root);
		int evenFlag = 0;
		int size = 0;
		TreeNode node = null;
		while(!queue.isEmpty()) {
			size = queue.size();
			List<Integer> nodeList = new ArrayList<Integer>();
			for (int i = 0; i < size; i++) {
				node = queue.poll();
				nodeList.add(node.val);
				if(node.left != null)
					queue.offer(node.left);
				if(node.right != null)
					queue.offer(node.right);
			}
			evenFlag++;
			if(isEven(evenFlag)) { //层次从1开始计数，如果层次为偶数则翻转
				Collections.reverse(nodeList);
			}
			list.add(nodeList);
		}
	
		return list;
	}
	
	public boolean isEven(int num){
		if(num % 2 == 0)
			return true;
		else 
			return false;
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
