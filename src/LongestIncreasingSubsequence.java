
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
    	int[] sequence = new int[nums.length + 1];  //记录最长上升子序列的个数
    	int index = 0; //记录binarySearch方法返回的值
    	sequence[0] = Integer.MIN_VALUE;
    	for(int i = 0; i < sequence.length; i++) {
    		sequence[i] = Integer.MAX_VALUE;
    	}
    	for(int i = 0; i < nums.length; i++) {
    		index = binarySearch(sequence, nums[i]);
    		sequence[index] = nums[i];
    	}
    	for(int i = sequence.length - 1 ; i >= 1; i--) { //从末尾向前找到第一个数不为Integer.Max_VALUE的下标，其为最长上升子序列的个数
    		if(sequence[i] != Integer.MAX_VALUE)
    			return i;
    	}
    	return 0;
    }
    
    /**
     * 算法思想： 找到sequence中第一个大于或者等于num的数组的下标
     * @param sequence  存储最长上升子序列的数组
     * @param num
     * @return 第一个大于num的数组的下标
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
