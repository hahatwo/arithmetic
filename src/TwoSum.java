import java.util.HashMap;

public class TwoSum {

	/*
	 * 给一个整数数组，找到两个数使得他们的和等于一个给定的数 target。 你需要实现的函数twoSum需要返回这两个数的下标,
	 * 并且第一个下标小于第二个下标。注意这里下标的范围是 1 到 n，不是以 0 开头。
	 * 
	 * @param numbers: An array of Integer
	 * 
	 * @param target: target = numbers[index1] + numbers[index2]
	 * 
	 * @return: [index1 + 1, index2 + 1] (index1 < index2)
	 */
	public int[] twoSum(int[] numbers, int target) {
		// write your code here
		if (numbers == null || numbers.length < 2) {
			return null;
		}
		int[] result = new int[2];
		for (int i = 0; i < numbers.length; i++) {
			for (int j = i + 1; j < numbers.length; j++) {
				if (numbers[i] + numbers[j] == target) {
					result[0] = i + 1;
					result[1] = j + 1;
					return result;
				}
			}
		}
		return null;
	}
	
	
	
	/**
	 * 算法思想： 创建HashMap, map以target-numbers[i]的差值为键，以i为值
	 *        当遍历numbers时， 如果map.get(numbers[i]) != null,证明从0->i-1有numbers[j](0<= j <= i+1)满足numbers[j] + numbers[i] = target;
	 *        返回i+1 和  j+1, j可以由map.get(numbers[i])得到。
	 * @param numbers
	 * @param target
	 * @return
	 */
	public int[] twoSumOther(int[] numbers, int target) {
        HashMap<Integer,Integer> map = new HashMap<>(); 

        for (int i = 0; i < numbers.length; i++) {
            if (map.get(numbers[i]) != null) {
                int[] result = {map.get(numbers[i]) + 1, i + 1};
                return result;
            }
            map.put(target - numbers[i], i);
        }
        
        int[] result = {};
        return result;
    }
	
}
