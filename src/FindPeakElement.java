
public class FindPeakElement {

	
	/*
	 * �����һ����������(sizeΪn)������������ص㣺
	 * ����λ�õ������ǲ�ͬ��
	 * A[0] < A[1] ���� A[n - 2] > A[n - 1]
	 * �ٶ�P�Ƿ�ֵ��λ��������A[P] > A[P-1]��A[P] > A[P+1]����������������һ����ֵ��λ�á�
     * @param A: An integers array.
     * @return: return any of peek positions.
     */
    public int findPeak(int[] A) {
        // write your code here
    	if(A.length == 0 || A == null) 
    		return -1;
    	return findPeakElement(A, 1, A.length-2); //��ֵλ������Ӧ����1����A.length-2֮��
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
