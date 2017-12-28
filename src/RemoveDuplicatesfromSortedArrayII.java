public class RemoveDuplicatesfromSortedArrayII {

	/**
	 * ������ɾ���ظ����֡��� �������������������ظ�����δ���
	 * @param A: a array of integers
	 * @return : return an integer
	 */
	public int removeDuplicates(int[] nums) {
		// write your code here
		if(nums == null || nums.length == 0) {
			return 0;
		}
		
		int index = 1;
		int counter = 1;
		int count = 0;
		
		for(int i = 1; i < nums.length; i++) {
			if(nums[i] == nums[i-1]) {
				counter++;
			} else {
				counter = 1;
			}
			if(counter <= 2) {
				nums[index++] = nums[i++];
				count++;
			}
		}
		return count+1;
	}
}
