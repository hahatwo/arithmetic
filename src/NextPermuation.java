
public class NextPermuation {

	/*
	 * 给定一个整数数组来表示排列，找出其之后的一个排列。
	 * 样例
	 * 给出排列[1,3,2,3]，其下一个排列是[1,3,3,2]
	 * 给出排列[4,3,2,1]，其下一个排列是[1,2,3,4]
     * @param nums: A list of integers
     * @return: A list of integers
     */
    public int[] nextPermutation(int[] nums) {
        // write your code here
    	if(nums == null || nums.length == 0){
    		return null;
    	}
    	int indexFirstGreaterMin = -1;
    	int indexGreaterIndexFirst = -1;
    	int len = nums.length;
    	for(int i = len - 1; i > 0; i--){
    		if(nums[i-1] < nums[i]){
    			indexFirstGreaterMin = i - 1;
    			break;
    		}
    	}
    	
    	if(indexFirstGreaterMin < 0){  //数组为升序
    		for(int i = 0; i < len/2; i++){
        		swap(nums, i, len - i - 1);
        	}
        	return nums;
    	}
    	
    	for(int j = indexFirstGreaterMin + 1; j < len; j++){
    		if(nums[j] <= nums[indexFirstGreaterMin]){
    			indexGreaterIndexFirst = j - 1;
    			break;
    		}
    		indexGreaterIndexFirst = j;
    	}
    	
    	swap(nums, indexFirstGreaterMin, indexGreaterIndexFirst);
    	int start = indexFirstGreaterMin + 1;
    	for(int i = start; i < (len - start)/2 + start; i++){
    		swap(nums, i, len - i + indexFirstGreaterMin);
    	}
    	return nums;
    }
    
    private void swap(int[] nums, int left, int right){
    	int temp = nums[left];
    	nums[left] = nums[right];
    	nums[right] = temp;
    }
}
