public class SearchMatrixII {

	/*
	 * д��һ����Ч���㷨������m��n�����е�ֵ���������ֵ���ֵĴ����� �����������������ԣ� ÿ���е�����������������ġ�
	 * ÿһ�е��������ϵ���������ġ� ��ÿһ�л�ÿһ����û���ظ���������
	 * 
	 * @param matrix: A list of lists of integers
	 * 
	 * @param target: An integer you want to search in matrix
	 * 
	 * @return: An integer indicate the total occurrence of target in the given
	 * matrix
	 */
	public int searchMatrix(int[][] matrix, int target) {
		// write your code here

		if (matrix == null || matrix.length == 0) {
			return 0;
		}
		int count = 0; // ��¼target���ֵĴ���
		for (int i = 0; i < matrix.length; i++) {
			int len = matrix[i].length;
			if (matrix[i][0] > target || matrix[i][len - 1] < target) { // ���������������ĳһ�еĵ�һ�����ִ���target�������һ�е�����С��target,����һ���п϶�û��target
				continue;
			}
			if (judgmentValue(matrix, i, target) == true) {
				count++;
			}
		}
		return count++;
	}

	/**
	 * �ж��������Ƿ����targetֵ 
	 * @param matrix
	 * @param index
	 * @param target
	 * @return
	 */
	public boolean judgmentValue(int[][] matrix, int index, int target) {
		if (matrix == null || matrix[index].length == 0) {
			return false;
		}
		int start = 0;
		int end = matrix[index].length - 1;
		int mid;
		while (start <= end) {
			mid = start + (end - start) / 2;
			if (matrix[index][mid] == target) {
				return true;
			} else if (matrix[index][mid] < target) {
				start = mid + 1;
			} else if (matrix[index][mid] > target) {
				end = mid - 1;
			}
		}
		return false;
	}
}
