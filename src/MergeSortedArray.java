
public class MergeSortedArray {

	/*�ϲ������������������A��B���һ���µ����顣
	 * Notice  ����Լ���A�����㹻�Ŀռ䣨A����Ĵ�С���ڻ����m+n��ȥ���B�е�Ԫ�ء�
	 * ���� A = [1, 2, 3, empty, empty], B = [4, 5]
	 * �ϲ�֮�� A ����� [1,2,3,4,5]
     * @param A: sorted integer array A which has m elements, but size of A is m+n
     * @param m: An integer
     * @param B: sorted integer array B which has n elements
     * @param n: An integer
     * @return: nothing
     */
    public void mergeSortedArray(int[] A, int m, int[] B, int n) {
        // write your code here
    	if(A == null || A.length < n + m){
    		return;
    	}
    	
    	int[] C = new int[n + m];
    	int i = 0, j = 0, k = 0;
    	while(i < m && j < n) {
    		if(A[i] < B[j]){
    			C[k] = A[i];
    			k++;
    			i++;
    		} else if (A[i] >= B[j]){
    			C[k] = B[j];
    			k++;
    			j++;
    		}
    	}
    	
    	while(i < m){
    		C[k] = A[i];
    		k++;
    		i++;
    	}
    	
    	while(j < n){
    		C[k] = B[j];
    		k++;
    		j++;
    	}
    	
    	for(int index = 0; index < k; index++){
    		A[index] = C[index];
    	}
    }
	
}
