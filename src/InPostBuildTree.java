public class InPostBuildTree {

	/*
	 * ������������ͺ����������������� ע������ ����Լ������в�������ͬ��ֵ�Ľڵ�
	 * @param inorder: A list of integers that inorder traversal of a tree 
	 * @param postorder: A list of integers that postorder traversal of a tree
	 * @return: Root of a tree
	 */
	public TreeNode buildTree(int[] inorder, int[] postorder) {
		// write your code here
		TreeNode node = null;
		if (inorder.length == 0 || postorder.length == 0) {
			return node;
		}
		node = recursionBuildTree(inorder, postorder, 0, inorder.length - 1, 0, postorder.length - 1);
		return node;
	}

	/*
	 * �㷨˼�룺����Ȼ�����ǿ���֪����������ĸ��ڵ���Ǻ�������е����һλ�� Ȼ��������������б����ҵ�������ڵ����ڵ�λ�ã����Ǽ�Ϊpos��
	 * ��ô��posǰ��Ľڵ���������������нڵ㣬��pos����Ľڵ���������������нڵ㣬 �������������Ƴ�2���ؼ����������������Ľڵ������
	 * ��n=1ʱ��ֻ��һ������㣬���������кͺ������п���ȷ����ö�������
	 * �赱n=m-1ʱ���۳������������ĿΪm-1ʱ���������кͺ������п���Ψһȷ������������֤����n=mʱ���۳�����
	 * ����������ΪS1,S2,��,Sm,����������P1,P2,��,Pm��
	 * ������������һ��Ԫ��Pm�Ǹ����������������п��ҵ���Pm��ȵĽ�㣨��������и���㻥����ͬ��Si(1��i��m)��
	 * ������������������������ã�����Si�Ǹ���㣬S1,S2,��,Si-1�����������������У���Si+1,Si+2,��,Sm�����������������С�
	 * ��i=1����S1�Ǹ�����ʱ��������������Ϊ�գ��������Ľ������m-1,��{S2,S3,��,Sm}��{P1,P2,��,Pm-1}����Ψһȷ����������
	 * �Ӷ�Ҳȷ���˶�������
	 * ��i=m����Sm�Ǹ�����ʱ��������������Ϊ�գ��������Ľ������m-1����{S1,S2,��,Sm-1}��{P1,P2,��,Pm-
	 * 1}Ψһȷ�����������Ӷ�Ҳȷ���˶������� ��󣬵�1<i<mʱ��Si���������зֳ�{S1,S2,��,Si-1}��{Si+1,Si+2,��,Sm}��
	 * ���ں��������"������-������-�����"������{P1,P2,��,Pi-1}��{Pi,Pi+1,��Pm-1}�Ƕ����������������������ĺ���������С�
	 * �����
	 * {S1,S2,��,Si-1}��{P1,P2,��,Pi-1}��Ψһȷ��������������������{Si+1,Si+2,��,Sm}��{Pi,Pi+1,��,
	 * Pm-1}��Ψһȷ������������������
	 */
	/**
	 * �ݹ���ڣ���������������������еĿ�ʼλ�ô��ڽ���λ��
	 * @param inorder 		�����������
	 * @param postorder		�����������
	 * @param inStartPos	������������������еĿ�ʼλ��
	 * @param inEndPos		������������������еĽ���λ��
	 * @param postStartPos	�����ں�����������еĿ�ʼλ��
	 * @param postEndPos	�����ں�����������еĽ���λ��
	 * @return
	 */
	private TreeNode recursionBuildTree(int[] inorder, int[] postorder,
			int inStartPos, int inEndPos, int postStartPos, int postEndPos) {

		if (inStartPos > inEndPos) {
			return null;
		}
		int endPosNum = postorder[postEndPos];
		int pos = findRootPos(endPosNum, inorder); // ������������и��ڵ����ڵ�λ��
		TreeNode rootNode = new TreeNode(inorder[pos]);
		rootNode.left = recursionBuildTree(inorder, postorder, inStartPos,
				pos - 1, postStartPos, postStartPos + pos - 1 - inStartPos);
		rootNode.right = recursionBuildTree(inorder, postorder, pos + 1,
				inEndPos, postStartPos + pos - inStartPos, postEndPos - 1);
		return rootNode;
	}

	private int findRootPos(int num, int[] inorder) {
		for (int i = 0; i < inorder.length; i++) {
			if (inorder[i] == num)
				return i;
		}
		return -1;
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

