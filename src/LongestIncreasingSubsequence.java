
public class LongestIncreasingSubsequence {
	/* 
     * @param nums: An integer array
     * @return: The length of LIS (longest increasing subsequence)
     */
    public int longestIncreasingSubsequence(int[] nums) {
        // write your code here
    	if(nums == null || nums.length == 0) {
    		return 0;
    	}
    	int[] sequence = new int[nums.length + 1];  //��¼����������еĸ���
    	int index = 0; //��¼binarySearch�������ص�ֵ
    	sequence[0] = Integer.MIN_VALUE;
    	for(int i = 0; i < sequence.length; i++) {
    		sequence[i] = Integer.MAX_VALUE;
    	}
    	for(int i = 0; i < nums.length; i++) {
    		index = binarySearch(sequence, nums[i]);
    		sequence[index] = nums[i];
    	}
    	for(int i = sequence.length - 1 ; i >= 1; i--) { //��ĩβ��ǰ�ҵ���һ������ΪInteger.Max_VALUE���±꣬��Ϊ����������еĸ���
    		if(sequence[i] != Integer.MAX_VALUE)
    			return i;
    	}
    	return 0;
    }
    
    /**
     * �㷨˼�룺 �ҵ�sequence�е�һ�����ڻ��ߵ���num��������±�
     * @param sequence  �洢����������е�����
     * @param num
     * @return ��һ������num��������±�
     */
    private int binarySearch(int[] sequence, int num) {
    	int left = 1, right = sequence.length - 1;
    	int mid = 0;
    	while(left < right) {
    		mid = (right - left)/2 + left;
    		if(sequence[mid] < num) {
    			left = mid + 1;
    		} else {
    			right = mid;
    		}
    	}
    	return left;
    }
}
