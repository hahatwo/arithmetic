
public class SingleNumber {
	/*
     * @param A: An integer array
     * @return: An integer
     */
    public int singleNumber(int[] A) {
        // write your code here
        int result = 0;
    	if(A.length == 0 || A == null) {
    		return result;
    	}
    	
    	for(int i = 0; i < A.length; i++) {
    		result ^= A[i];   //异或满足交换律，两个数 a ^ a = 0;
    	}
    	return result;
    }
}
