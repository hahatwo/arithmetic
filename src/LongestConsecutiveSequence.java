public class LongestConsecutiveSequence {

	/**
	 * 给定一个未排序的整数数组，找出最长连续序列的长度。 
	 * 说明
	 * 要求你的算法复杂度为O(n)
	 * 样例 
	 * 给出数组[100, 4, 200, 1, 3, 2]，这个最长的连续序列是 [1, 2, 3, 4]，返回所求长度 4
	 * 算法思想： 动态规划
	 *          假设dp[i]为排序数组前i个数的最长连续序列的长度
	 *          设置一个标志tmp初始为1，当num[i+1] == num[i]+1时，tmp++;
	 *          当num[i+1]!=num[i]+1时，将tmp重新置1,tmp=1;
	 *          则状态转移方程 dp[i+1] = Max(dp[i], tmp)
	 *          边界条件 dp[0] = 0
	 *                  dp[i] = 1;
	 * @param num : A list of integers
	 * @return: An integer
	 */
	public int longestConsecutive(int[] num) {
		// write your code here
		if(num == null) {
			return 0;
		}
		if(num.length == 0 || num.length == 1) {
			return num.length;
		}
		sort(num);
		int[] dp = new int[num.length+1];
		dp[0] = 0;
		for(int i = 1; i < dp.length; i++) {
			dp[i] = 1;
		}
		dp[1] = 1;
		int tmp = 1;
		for(int i = 1; i < num.length; i++) {
			if(num[i] == num[i-1]+1)
				tmp++;
			else 
				tmp = 1;
			dp[i] = Math.max(dp[i-1], tmp);
		}
		
		return 0;
	}
	
	public void sort(int[] num) {
		quickSort(num, 0, num.length-1);
	}
	
	/**
	 * 快速排序
	 * @param num
	 */
	public void quickSort(int[] num, int start, int end) {
		int left = start;
		int right = end;
		int flag = num[left];
		while(left < right) {
			while(num[right] > flag && left < right) {
				right++;
			}
			if(left < right) {
				num[left] = num[right];
				left++;
			}
			
			while(num[left] < flag && left < right) {
				left++;
			}
			if(left < right) {
				num[right] = num[left];
				right--;
			}
		}
		num[left] = flag;
		quickSort(num, start, left-1);
		quickSort(num, right+1, end);
	}
}
