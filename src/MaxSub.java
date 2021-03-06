
public class MaxSub {
	
	 /**
     * 给定一个整数数组和一个整数 k，找出 k 个不重叠子数组使得它们的和最大。每个子数组的数字在数组中的位置应该是连续的。
     * 返回最大的和。
     * 
     */

	public int maxSubArray(int[] nums, int k) {
        // write your code here
		
		if(k > nums.length){
		    return 0;
		}
		
		int min = Integer.MIN_VALUE;
		/** localMax[i][j]记录数组前j个数的子数组被划分为i组， 且必须包含第j个数的最大和  **/
		int[][] localMax = new int[k + 1][nums.length + 1];
		
		/** globalMax[i][j]记录数组前j个数的子数组被划分为i组， 但不一定包含第j个数的最大和 **/
		int[][] globalMax = new int[k + 1][nums.length + 1];
		
		/**
		 * 最优子结构 localMax[i][j] = Max(localMax[i][j - 1] , globalMax[i-1][j-1]) + nums[j]
		 *        globalMax[i][j] = Max(localMax[i][j], globalMax[i][j -1]);
		 * 边界条件： localMax[0][j] = min (数值前j个数被划分为0组)
		 *            globalMax[0][j] = 0;
		 *        localMax[i][i-1] = min (数组前0个数被划分为i组)
		 *        globalMax[i][i-1] = 0
		 *        当i = j时， globalMax[i][j] = localMax[i][j]
		 */
		 for(int j = 0; j < nums.length + 1; j++){
			 localMax[0][j] = min;
			  globalMax[0][j] = 0;
		 }
		
		 for(int i = 1; i < k + 1; i++){
			 localMax[i][i-1] = min;
			 globalMax[i][i-1] = 0;
			 for(int j = i; j < nums.length + 1; j++){ //j<i,不能被划分成i个子数组
				 localMax[i][j] = Math.max(localMax[i][j - 1], globalMax[i-1][j-1]) + nums[j-1];
				 if(i == j){
				     globalMax[i][j] = localMax[i][j];
				 }else{
				     globalMax[i][j] = Math.max(localMax[i][j], globalMax[i][j -1]);
				 }
			 }
		 }	 
		return globalMax[k][nums.length];
    }
	
	
	
}
