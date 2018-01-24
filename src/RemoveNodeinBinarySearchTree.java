public class RemoveNodeinBinarySearchTree {

	/*
	 * ����һ�þ��в�ͬ�ڵ�ֵ�Ķ����������ɾ�����������ֵ��ͬ�Ľڵ㡣�������û����ֵͬ�Ľڵ㣬�Ͳ����κδ��� 
	 * ��Ӧ�ñ�֤����֮��������Ƕ����������
	 * �㷨˼�룺 http://blog.csdn.net/lcore/article/details/8889176
	 *  ���ڶ����������ɾ���������������ֵɾ�������ǽ�㣩�����������
	 *  �����ڴ�֮ǰ������Ӧ��ȷ�����ݸ�����ֵ�ҵ���Ҫɾ���Ľ�㣬����û�ҵ��ý�㲻��ִ��ɾ��������
	 *   ����������������Ѿ��ҵ���Ҫɾ���Ľ�㡣
	 *   1.������ΪҶ�ӽ�㣨û����������������ʱɾ���ý�㲻���ƻ����Ľṹ  ֱ��ɾ�����ɣ����޸��丸���ָ����������Ϊnull.
	 *   2.�������ֻ�����������������������Ļ�����ʱֱ��ɾ���ý�㣬������������
	 *    ��������������Ϊ�丸�����������������������ɣ��˲��������ƻ����ṹ��
	 *   3. �������������������յ�ʱ��һ���ɾ����������������������С���ݣ������ҵ�������Ҫɾ���Ľ�����ݲ��ݹ�ɾ���ý�㣨��ʱΪnull����
	 *   ��Ϊ ����������С��㲻���������ӣ����Եڶ���ɾ����Ϊ���ס�
	 *   z���������������������ա��ҵ�z�ĺ��y����Ϊyһ��û�������������Կ���ɾ��y������y�ĸ��׽ڵ��Ϊy���������ĸ��׽ڵ㣬����y��ֵ����z��ֵ.
	 * @param root: The root of the binary search tree.
	 * @param value: Remove the node with given value.
	 * @return: The root of the binary search tree after removal.
	 */
	public TreeNode removeNode(TreeNode root, int value) {
		// write your code here
		if(root == null) {
			return null;
		} else if(root.val < value) {
			root.right = removeNode(root.right, value);
		} else if(root.val > value) {
			root.left = removeNode(root.left, value); 
		} else if(root.left != null && root.right != null) {
			TreeNode minNode = findMinNode(root.right); //�ҵ��ڵ�����������С�ڵ�
			root.val = minNode.val;
			root.right = removeNode(root.right, minNode.val);
		} else if(root.left != null && root.right == null) { //�ڵ�ֻ����������
			root = root.left;
		} else if(root.left == null && root.right != null) { //�ڵ�ֻ����������
			root = root.right;
		} else { //�ڵ�ΪҶ�ӽڵ�
			root = null;
		}
		return root;
	}

	/**
	 * �ҵ�node�ڵ����Сֵ�ڵ㣬�������ӽڵ�
	 * @param node
	 * @return
	 */
	private TreeNode findMinNode(TreeNode node) {
		if(node == null) {
			return node;
		}
		while(node.left != null) {
			node = node.left;
		}
		return node;
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
