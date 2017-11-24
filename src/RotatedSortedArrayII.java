
public class RotatedSortedArrayII {

	/*
	 * 假设有一个排序的按未知的旋转轴旋转的数组(比如，0 1 2 4 5 6 7 可能成为4 5 6 7 0 1 2)。给定一个目标值进行搜索，如果在数组中找到目标值返回数组中的索引位置，否则返回-1。
	 * 你可以假设数组中不存在重复的元素。
	 * 
	 * 跟进“搜索旋转排序数组”，假如有重复元素又将如何？
     * @param A: an integer ratated sorted array and duplicates are allowed
     * @param target: An integer
     * @return: a boolean 
     */
    public boolean search(int[] A, int target) {
        // write your code here
    	if(A == null || A.length == 0){
    		return false;
    	}
    	
    	for(int i = 0; i < A.length; i++){
    		if(A[i] == target){
    			return true;
    		}
    	}
    	
    	return false;
    }
	
}
