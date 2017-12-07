import java.util.List;


public class PreviousPermuation {

	/*
	 * 给定一个整数数组来表示排列，找出其上一个排列。
	 * 样例
	 * 给出排列[1,3,2,3]，其上一个排列是[1,2,3,3]
	 * 给出排列[1,2,3,4]，其上一个排列是[4,3,2,1]
     * @param nums: A list of integers
     * @return: A list of integers that's previous permuation
     */
    public List<Integer> previousPermuation(List<Integer> nums) {
        // write your code here
    	if(nums == null || nums.size() == 0){
    		return null;
    	}
    	int indexFirstGreaterMin = -1;
    	int indexGreaterIndexFirst = -1;
    	int len = nums.size();
    	for(int i = len - 1; i > 0; i--){
    		if(nums.get(i - 1) > nums.get(i)){
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
    		if(nums.get(j) >= nums.get(indexFirstGreaterMin)){
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
    
    private void swap(List<Integer> nums, int left, int right){
    	int temp = nums.get(left);
    	nums.set(left, nums.get(right));
    	nums.set(right, temp);
    }
}
