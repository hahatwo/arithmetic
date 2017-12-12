import java.util.ArrayList;
import java.util.List;


public class ProductExcludeItself {

	/*
	 * 给定一个整数数组A。
	 * 定义B[i] = A[0] * ... * A[i-1] * A[i+1] * ... * A[n-1]， 计算B的时候请不要使用除法。
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
    
    //计算除了自身之外的数组乘积的值
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
