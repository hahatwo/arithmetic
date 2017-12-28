public class RemoveDuplicatesfromSortedArrayII {

	/**
	 * 跟进“删除重复数字”： 如果可以允许出现两次重复将如何处理？
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
