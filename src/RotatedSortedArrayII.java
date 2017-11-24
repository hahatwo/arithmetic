
public class RotatedSortedArrayII {

	/*
	 * ������һ������İ�δ֪����ת����ת������(���磬0 1 2 4 5 6 7 ���ܳ�Ϊ4 5 6 7 0 1 2)������һ��Ŀ��ֵ����������������������ҵ�Ŀ��ֵ���������е�����λ�ã����򷵻�-1��
	 * ����Լ��������в������ظ���Ԫ�ء�
	 * 
	 * ������������ת�������顱���������ظ�Ԫ���ֽ���Σ�
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
