public class SearchMatrixII {

	/*
	 * 写出一个高效的算法来搜索m×n矩阵中的值，返回这个值出现的次数。 这个矩阵具有以下特性： 每行中的整数从左到右是排序的。
	 * 每一列的整数从上到下是排序的。 在每一行或每一列中没有重复的整数。
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
		int count = 0; // 记录target出现的次数
		for (int i = 0; i < matrix.length; i++) {
			int len = matrix[i].length;
			if (matrix[i][0] > target || matrix[i][len - 1] < target) { // 如果二次项数组中某一行的第一个数字大于target或者最后一列的数字小于target,则这一行中肯定没有target
				continue;
			}
			if (judgmentValue(matrix, i, target) == true) {
				count++;
			}
		}
		return count++;
	}

	/**
	 * 判断数组中是否存在target值 
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
