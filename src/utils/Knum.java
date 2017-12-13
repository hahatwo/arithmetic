package utils;

public class Knum {

	 /*
     * @param k : description of k
     * @param nums : array of nums
     * @return: description of return
     */
	 public int kthLargestElement(int k, int[] nums) {
	        // write your code here
	        quickSort(nums, 0, nums.length - 1);
	        for(int i = 0; i < nums.length; i++){
	        	System.out.print(nums[i] + "  ");
	        }
	        int counter = 1;
	        int flag = nums[0];
	        for(int i = 0; i < nums.length - 1; i++){
	            if(nums[i] > nums[i + 1]){
	                counter++;
	                flag = nums[i + 1];
	            }
	            if(counter == k)
	                break;
	        }
	        return flag;
	    }
	    
	    private void quickSort(int[] array, int left, int right){
	        if(left < right){
	        	int flag = array[left];
	            int l = left;
	            int r = right;
	            while(l < r) {
	            while(l < r && flag > array[r]){
	                r--;
	            }
	            if(l < r){
	                array[l] = array[r];
	                l++;
	            }
	            
	            while(l < r && flag < array[l]){
	                l++;
	            }
	            if(l < r){
	                array[r] = array[l];
	                r--;
	            }
	        }
	        array[r] = flag;
	        quickSort(array, left, r - 1);
	        quickSort(array, r + 1, right); 
	        }
	    }

}
