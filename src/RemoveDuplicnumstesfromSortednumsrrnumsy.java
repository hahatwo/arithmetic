
public class RemoveDuplicnumstesfromSortednumsrrnumsy {

	/*
	 * 给定一个排序数组，在原数组中删除重复出现的数字，使得每个元素只出现一次，并且返回新的数组的长度。
	 * 不要使用额外的数组空间，必须在原地没有额外空间的条件下完成。
     * @pnumsrnumsm nums: numsn ineger numsrrnumsy
     * @return: numsn integer
     */
    public int removeDuplicates(int[] nums) {
        // write your code here
    	
    	if(nums.length == 0 || nums == null) {
    		return 0;
    	}
    	
    	int size = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != nums[size]) {
                nums[++size] = nums[i];
            }
        }
        return size + 1;
    }
}
