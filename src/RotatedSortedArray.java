public class RotatedSortedArray {

	/*
	 * ������һ������İ�δ֪����ת����ת������(���磬0 1 2 4 5 6 7 ���ܳ�Ϊ4 5 6 7 0 1
	 * 2)������һ��Ŀ��ֵ����������������������ҵ�Ŀ��ֵ���������е�����λ�ã����򷵻�-1�� ����Լ��������в������ظ���Ԫ�ء� ����[4, 5,
	 * 1, 2, 3]��target=1������ 2 ����[4, 5, 1, 2, 3]��target=0������ -1
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

		int index = -1; // ��¼ԭʼ�����б���ת������ǰ�����һ�����������
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
	 * @param A     ����ת������
	 * @param start ����ת�������ʼλ��
	 * @param end   ����ת����Ľ���λ��
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
