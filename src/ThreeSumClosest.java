import java.util.Arrays;


public class ThreeSumClosest {
	
	
	/*给一个包含 n 个整数的数组 S, 找到和与给定整数 target 最接近的三元组，返回这三个数的和。
	 * 注意事项
	 * 只需要返回三元组之和，无需返回三元组本身
     * @param numbers: Give an array numbers of n integer
     * @param target: An integer
     * @return: return the sum of the three integers, the sum closest target.
     */
    public int threeSumClosest(int[] numbers, int target) {
        // write your code here
    	
    	if(numbers == null || numbers.length < 3){
    		return 0;
    	}
    	int numCloset = Integer.MAX_VALUE;
    	Arrays.sort(numbers);
    	int[] array = new int[numbers.length - 2];
    	for(int i = 0; i < array.length; i++){
    		array[i] = Integer.MAX_VALUE;
    	}
    	
    	for(int i = 0; i < numbers.length - 2; i++){
    		if(i > 0 && numbers[i] == numbers[i - 1]){
    			continue;
    		}
    		int left = i + 1;
    		int right = numbers.length - 1;
    		
    		while(left < right){
    			int sum = numbers[i] + numbers[left] + numbers[right];
    			if(Math.abs(sum - target) == 0 ){
    				return target;
    			}
    			numCloset = Math.abs(numCloset - target) > Math.abs(sum - target) ? sum : numCloset;
    			
    			if(sum < target){
    				left++;
    			} else {
    				right--;
    			}
    			
    		}
    	}
        return numCloset;
    }
}
