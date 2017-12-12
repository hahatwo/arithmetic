public class RotatedSortedArray {

	/*
	 * 假设有一个排序的按未知的旋转轴旋转的数组(比如，0 1 2 4 5 6 7 可能成为4 5 6 7 0 1
	 * 2)。给定一个目标值进行搜索，如果在数组中找到目标值返回数组中的索引位置，否则返回-1。 你可以假设数组中不存在重复的元素。 给出[4, 5,
	 * 1, 2, 3]和target=1，返回 2 给出[4, 5, 1, 2, 3]和target=0，返回 -1
	 * 
	 * @param A: an integer rotated sorted array
	 * 
	 * @param target: an integer to be searched
	 * 
	 * @return: an integer
	 */
	public int search(int[] A, int target) {
		// write your code here

		if (A == null || A.length == 0) {
			return -1;
		}

		int index = -1; // 记录原始数组中被旋转到数组前面最后一个升序的索引
		for (int i = 0; i < A.length - 1; i++) {
			if (A[i] > A[i + 1]) {
				index = i;
				break;
			}
		}
		
		int result = -1;
		if (index != -1) {
			//rotateArray(A, 0, index);
			result = binarySearch(A, target, 0, index);
			if (result != -1) {
				return result;
			}
			result = binarySearch(A, target, index + 1, A.length - 1);
		} else {
			result = binarySearch(A, target, 0, A.length - 1);
		}
		return result;
	}

	private int binarySearch(int[] A, int target, int start, int end) {
		int mid;
		while (start + 1 < end) {
			mid = start + (end - start) / 2;
			if (A[mid] == target) {
				return mid;
			} else if (A[mid] < target) {
				start = mid;
			} else {
				end = mid;
			}
		}

		if (A[start] == target) {
			return start;
		} else if (A[end] == target) {
			return end;
		}
		return -1;
	}

	/**
	 * 
	 * @param A     待旋转的数组
	 * @param start 待旋转数组的起始位置
	 * @param end   待旋转数组的结束位置
	 */
	private void rotateArray(int[] A, int start, int end) {
		int temp;
		for (int i = start; i <= start + (end - start) / 2; i++) {
			temp = A[i];
			A[i] = A[end - i + start];
			A[end - i + start] = temp;
		}
	}

}
