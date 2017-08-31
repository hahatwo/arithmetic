
public class MaxSub {
	
	 /**
     * 给定一个整数数组和一个整数 k，找出 k 个不重叠子数组使得它们的和最大。每个子数组的数字在数组中的位置应该是连续的。
     * 返回最大的和。
     * 
     */

	public int maxSubArray(int[] nums, int k) {
        // write your code here
		
		/** localMax[i][j]记录数组前j个数的子数组被划分为i组， 且必须包含第j个数的最大和  **/
		int[][] localMax = new int[k + 1][nums.length + 1];
		
		/** globalMax[i][j]记录数组前j个数的子数组被划分为i组， 但不一定包含第j个数的最大和 **/
		int[][] globalMax = new int[k + 1][nums.length + 1];
		
		/**
		 * 最优子结构 localMax[i][j] = Max(localMax[i][j - 1])
		 * 
		 * 
		 */
		
		return 0;
    }
	
	
	
}
