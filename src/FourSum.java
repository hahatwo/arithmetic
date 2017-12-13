import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FourSum {
	
	/*��һ������n��������������S����S���ҵ�����ʹ�ú�Ϊ��������target����Ԫ��(a, b, c, d)��
	 *  ע������
	 *  ��Ԫ��(a, b, c, d)�У���Ҫ����a <= b <= c <= d
	 *  ���в����԰����ظ�����Ԫ�顣
     * @param numbers: Give an array
     * @param target: An integer
     * @return: Find all unique quadruplets in the array which gives the sum of zero
     */
    public List<List<Integer>> fourSum(int[] numbers, int target) {
        // write your code here
    	List<List<Integer>> results = new ArrayList<List<Integer>>();
    	if(numbers == null || numbers.length < 4){
    		return results;
    	}
    	Arrays.sort(numbers);
    	for(int i = 0; i < numbers.length - 3; i++){
    		if(i > 0 && numbers[i] == numbers[i-1]){
    			continue;
    		}
    		
    		for(int j = i + 1; j < numbers.length - 2; j++){
    			
    			if(j != i + 1 && numbers[j] == numbers[j-1]){
    				continue;
    			}
    			
    			int left = j + 1;
    			int right = numbers.length - 1;
    			int sum;
    			while(left < right){
    				sum = numbers[i] + numbers[j] + numbers[left] + numbers[right];
    				if(sum == target){
    					List<Integer> item = new ArrayList<Integer>();
    					item.add(numbers[i]);
    					item.add(numbers[j]);
    					item.add(numbers[left]);
    					item.add(numbers[right]);
    					results.add(item);
    					left++;
    					right--;
    					
    					while(left < right && numbers[left] == numbers[left - 1]){
    						left++;
    					}
    					while(left < right && numbers[right] == numbers[right + 1]){
    						right--;
    					}
    				} else if(sum < target){
    					left++;
    				} else {
    					right--;
    				}
    			}
    		}
    		
    	}
    	return results;
    }
     
    
}
