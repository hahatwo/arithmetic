
public class RemoveDuplicnumstesfromSortednumsrrnumsy {

	/*
	 * ����һ���������飬��ԭ������ɾ���ظ����ֵ����֣�ʹ��ÿ��Ԫ��ֻ����һ�Σ����ҷ����µ�����ĳ��ȡ�
	 * ��Ҫʹ�ö��������ռ䣬������ԭ��û�ж���ռ����������ɡ�
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
