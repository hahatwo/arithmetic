import java.util.ArrayList;
import java.util.List;


public class ProductExcludeItself {

	/*
	 * ����һ����������A��
	 * ����B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]�� ����B��ʱ���벻Ҫʹ�ó�����
     * @param nums: Given an integers array A
     * @return: A long long array B and B[i]= A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]
     */
    public List<Long> productExcludeItself(List<Integer> nums) {
        // write your code here
    	if(nums == null || nums.size() == 0){
    		return null;
    	}
    	List<Long> results = new ArrayList<Long>();
    	Long resultValue = (long) 1;
    	for(int i = 0; i < nums.size(); i++){
    		resultValue = calculateExcludeItself(nums, i);
    		results.add(resultValue);
    	}
    	return results;
    }
    
    //�����������֮�������˻���ֵ
    private Long calculateExcludeItself(List<Integer> nums, int k){
    	Long value = (long) 1;
    	for(int i = 0; i < nums.size(); i++){
    		if(i == k){
    			continue;
    		}
    		value *= nums.get(i);
    	}
    	return value;
    }
}
