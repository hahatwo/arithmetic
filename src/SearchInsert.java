
public class SearchInsert {

	
	/*给定一个排序数组和一个目标值，如果在数组中找到目标值则返回索引。如果没有，返回到它将会被按顺序插入的位置。
	 * 你可以假设在数组中无重复元素。
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: An integer
     */
    public int searchInsert(int[] A, int target) {
        // write your code here
    	if(A == null || A.length == 0){
    		return 0;
    	}
    	
    	int start = 0;
    	int end = A.length - 1;
    	int mid = 0;
    	while(start <  end){
    		mid = (end - start) / 2 + start;
    		if(A[mid] == target){
    			return mid;
    		} else if(A[mid] > target){
    			end = mid - 1;
    		} else {
    			start = mid + 1;
    		}
    	}
    	
    	if(A[start] >= target){
    		return start;
    	} else if(A[end] >= target){
    		return end;
    	}else {
    		return end + 1;
    	}
    }
	
	
	
	
}
