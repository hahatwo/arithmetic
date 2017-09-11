import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class MajorityNumberII {

	public MajorityNumberII() {
		// TODO Auto-generated constructor stub
	}

	/*
	 * 给定一个整型数组，找到主元素，它在数组中的出现次数严格大于数组元素个数的三分之一。
	 * 
	 * @param nums: a list of integers
	 * 
	 * @return: The majority number that occurs more than 1/3
	 */
	public int majorityNumber(List<Integer> nums, int k) {
		// write your code here

		if (nums == null || nums.size() == 0) {
			return 0;
		}

		HashMap<Integer, Integer> hash = new HashMap<Integer, Integer>();
		int val = 0;
		for (int i = 0; i < nums.size(); i++) {
			if (!hash.containsKey(nums.get(i))) {
				hash.put(nums.get(i), 1);
			} else {
				val = hash.get(nums.get(i)) + 1;
				hash.remove(nums.get(i));
				hash.put(nums.get(i), val);
			}
		}

		int master = 0;
		int max = Integer.MIN_VALUE;

		for (Map.Entry<Integer,Integer> entry : hash.entrySet()) {
			if (max < entry.getValue()) {
				max = entry.getValue();
				master = entry.getKey();
			}
		}
		return master;
	}
	

}
