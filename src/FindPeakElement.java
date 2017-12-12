
public class FindPeakElement {

	
	/*
	 * 你给出一个整数数组(size为n)，其具有以下特点：
	 * 相邻位置的数字是不同的
	 * A[0] < A[1] 并且 A[n - 2] > A[n - 1]
	 * 假定P是峰值的位置则满足A[P] > A[P-1]且A[P] > A[P+1]，返回数组中任意一个峰值的位置。
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
    	if(A.length == 0 || A == null) 
    		return -1;
    	return findPeakElement(A, 1, A.length-2); //峰值位置索引应该在1——A.length-2之间
    }
    
    private int findPeakElement(int[] A, int start, int end) {
    	int mid = 0;
    	while(start < end) {
    		mid = (end -start)/2 + start;
    		if(A[mid] < A[mid-1]) {
    			end = end - 1;
    		} else if(A[mid] < A[mid+1]) {
    			start = mid + 1;
    		} else {
    			return mid;
    		}
    	}
    	
    	return start;
    }
}
