import java.util.HashMap;

public class TwoSum {

	/*
	 * ��һ���������飬�ҵ�������ʹ�����ǵĺ͵���һ���������� target�� ����Ҫʵ�ֵĺ���twoSum��Ҫ���������������±�,
	 * ���ҵ�һ���±�С�ڵڶ����±ꡣע�������±�ķ�Χ�� 1 �� n�������� 0 ��ͷ��
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
	 * �㷨˼�룺 ����HashMap, map��target-numbers[i]�Ĳ�ֵΪ������iΪֵ
	 *        ������numbersʱ�� ���map.get(numbers[i]) != null,֤����0->i-1��numbers[j](0<= j <= i+1)����numbers[j] + numbers[i] = target;
	 *        ����i+1 ��  j+1, j������map.get(numbers[i])�õ���
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
