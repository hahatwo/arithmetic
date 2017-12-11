
public class KSum {

	/*
	 * 给定n个不同的正整数，整数k（k < = n）以及一个目标数字。　
	 * 在这n个数里面找出K个数，使得这K个数的和等于目标数字，求问有多少种方案？
	 * 
	 * 算法思想： 动态规划
	 * 			跟硬币找零问题比较类似，不过稍有不同。
	 * 			假设dp[i][j]为共i个数之和等于j的所有组合的总方案个数，那么dp[k][target]就表示问题的最后解
	 * 			
	 * 			状态转移方程：dp[i][j] = dp[i-1][j-A[0]] + dp[i-1][j-A[1]]+ dp[i-1][j-A[2]]+ ... + dp[i-1][j - A[n-1]],
	 * 						即dp[i][j] += dp[i-1][j-A[x]] (j >= A[x]);
	 * 			边界条件：	dp[0][0] = 1;
	 * 						dp[0][j] = 0;
	 * 						dp[i][0] = 0;(因为数组为n个不同的正整数)
	 * 
	 * A[x]必须放在最外层，同时必须从下往上逐行计算，从右往左逐列计算来避免重复的累加。
	 * 1. 如果你从左往右按列计算，每一列会被重复地加，就会有重复计算。如果你从左往右计算，被sum的区域会被填掉，下一个值的计算就不会准确了。
	 * 所以，只要我们逐列计算，并且顺序是从右往左，即使我们只有一个二维表，我们的被sum区域也可以保持洁净。	
	 * 			
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        // write your code here
    	if(k > A.length) {
    		return 0;
    	}
    	int[][] dp = new int[k+1][target+1];
    	/** 初始化 **/
    	dp[0][0] = 1;
    	for(int i = 1; i < k+1; i++) {
    		dp[i][0] = 0;
    	}
    	for(int j = 1; j < target+1; j++) {
    		dp[0][j] = 0;
    	}
    	
    	/**for(int i = 1; i <= k; i++) {  错误的计算，由于重复的累加，会造成结果的不正确
    		for(int j = 1; j <= target; j++) {
    			for(int x = 0; x < A.length; x++) {
    				if(j >= A[x]) {
    					dp[i][j] += dp[i-1][j-A[x]];
    				}
    			}
    		}
    	}**/
    	
    	for(int x = 0; x < A.length; x++) {
    		for(int i = k; i >= 1; i--) {
    			for(int j = target; j >= 1; j--) {
    				if(j >= A[x]) {
    					dp[i][j] += dp[i-1][j-A[x]];
    				}
    			}
    		}
    	}
    	return dp[k][target]; 
    }
   
}
