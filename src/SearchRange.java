
public class SearchRange {

	
	/*
	 * ����һ������ n ���������������飬�ҳ�����Ŀ��ֵ target ����ʼ�ͽ���λ�á�
	 * ���Ŀ��ֵ���������У��򷵻�[-1, -1]
     * @param A: an integer sorted array
     * @param target: an integer to be inserted
     * @return: a list of length 2, [index1, index2]
     */
    public int[] searchRange(int[] A, int target) {
        // write your code here
    	int[] results = {-1, -1};
    	if(A == null || A.length == 0){
    		return results;
    	}
    	
    	int start = 0;
    	int end = A.length - 1;
    	int mid = start + (end - start)/2;
    	boolean flag = false; //��¼target�Ƿ������������
    	while(start + 1 < end){
    		mid = start + (end - start)/2;
    		if(A[mid] == target){
    			flag = true;
    			break;
    		} else if(A[mid] > target){
    			end = mid;
    		} else {
    			start = mid;
    		}
    	}
    	
    	if(flag == true){
    		int left = mid;
    		int right = mid;
    		for(int i = mid - 1; i >= 0; i--){
    			if(A[i] != target){
    				left = i + 1;
    				break;
    			}
    			left = i;
    		}
    		results[0] = left;
    		
    		for(int j = mid + 1; j < A.length; j++){
    			if(A[j] != target){
    				right = j - 1;
    				break;
    			}
    			right = j;
    		}
    		results[1] = right;
    		return results;
    	}
    	
    	if(A[start] == target){
    		return new int[]{start, start};
    	}
    	if(A[end] == target){
    		return new int[]{end, end};
    	}
    	return results;
    }
    
}
