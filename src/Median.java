
public class Median {
	/*
     * @param nums: A list of integers
     * @return: An integer denotes the middle number of the array
     */
    public int median(int[] nums) {
        // write your code here
    	if(nums.length == 0 || nums == null){
    		return -1;
    	}
    	int len = nums.length;
    	quickSort(nums, 0, len - 1);
    	if(len % 2 == 0)
    		return nums[len/2 - 1];
    	return nums[len/2];
    }
    
    private void quickSort(int[] nums, int start, int end) {  //¿ìÅÅ
    	if(start > end)
    		return;
    	int left = start;
    	int right = end;
    	int flag = nums[left];
    	while(left < right) {
    		while(nums[right] > flag && left < right) {
    			right--;
    		}
    		if(left < right){
    			nums[left] = nums[right];
    			left++;
    		} 
    		while(nums[left] < flag && left < right){
    			left++;
    		}
    		if(left < right){
    			nums[right] = nums[left];
    			right--;
    		}
    	}
    	nums[right] = flag;
    	quickSort(nums, start, right-1);
    	quickSort(nums, right+1, end);
    }
}
