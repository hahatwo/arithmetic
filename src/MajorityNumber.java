import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class MajorityNumber {

	/*
	 * 给定一个整型数组，找出主元素，它在数组中的出现次数严格大于数组元素个数的二分之一。
     * @param nums: a list of integers
     * @return: find a  majority number
     */
    public int majorityNumber(List<Integer> nums) {
        // write your code here
    	if(nums == null || nums.size() == 0){
    		return 0;
    	}
    	
    	HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
    	int val = 0;
    	for(int i = 0; i < nums.size(); i++){
    		if(!hash.containsKey(nums.get(i))){
    			hash.put(nums.get(i), 1);
    		}else {
    			val = hash.get(nums.get(i)) + 1;
    			if(val > nums.size()/2){
    				return nums.get(i);
    			}
    			hash.remove(nums.get(i));
    			hash.put(nums.get(i), val);
    		}
    	}
    	return 0;
    }
	
}
