import java.util.ArrayList;

public class MinSub {

	/**
	 * ����һ���������飬�ҵ�һ��������С�͵������顣��������С�͡�
	 * 
	 * @param nums
	 *            : a list of integers
	 * @return: A integer indicate the sum of minimum subarray
	 */
	public int minSubArray(ArrayList<Integer> nums) {
		// write your code
		if (nums == null || nums.size() == 0) {
			return 0;
		}
		int min = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < nums.size(); i++) {
			sum += nums.get(i);
			min = Math.min(sum, min);
			sum = Math.min(sum, 0);
		}

		return min;
	}

	/*
	 * ����һ���������飬�ҳ��������ص���������A��B��ʹ����������͵Ĳ�ľ���ֵ|SUM(A) - SUM(B)|���
	 * 
	 * @param nums: A list of integers
	 * 
	 * @return: An integer indicate the value of maximum difference between two
	 * substrings
	 */
	public int maxDiffSubArrays(int[] nums) {
		// write your code here

		if (nums == null || nums.length == 0) {
			return 0;
		}
		int[] leftMin = new int[nums.length]; // leftMin[i]��¼��������ǰi��������С������֮��
		int[] rightMin = new int[nums.length]; // rightMin[i]��¼���������right.length
												// - i��������С������֮��
		int min = Integer.MAX_VALUE;
		int sum = 0;
		for (int i = 0; i < nums.length; i++) {
			sum += nums[i];
			min = Math.min(sum, min);
			sum = Math.min(sum, 0);
			leftMin[i] = min;
		}

		min = Integer.MAX_VALUE;
		sum = 0;
		for (int i = nums.length - 1; i >= 0; i--) {
			sum += nums[i];
			min = Math.min(sum, min);
			sum = Math.min(sum, 0);
			rightMin[i] = min;
		}

		int[] leftMax = new int[nums.length]; // leftMin[i]��¼��������ǰi���������������֮��
		int[] rightMax = new int[nums.length]; // rightMin[i]��¼���������right.length
												// - i���������������֮��
		int max = Integer.MIN_VALUE;
		sum = 0;
		for (int i = 0; i < nums.length - 1; i++) {
			sum += nums[i];
			max = Math.max(sum, max);
			sum = Math.max(sum, 0);
			leftMax[i] = max;
		}

		max = Integer.MIN_VALUE;
		sum = 0;
		for (int i = nums.length - 1; i >= 0; i--) {
			sum += nums[i];
			max = Math.max(sum, max);
			sum = Math.max(sum, 0);
			rightMax[i] = max;
		}

		int maxDiff = Integer.MIN_VALUE;
		for (int i = 0; i < nums.length - 1; i++) {
			maxDiff = Math.max(maxDiff, Math.abs(leftMin[i] - rightMax[i + 1]));
			maxDiff = Math.max(maxDiff, Math.abs(leftMax[i] - rightMin[i + 1]));
		}
		return maxDiff;
	}

}
