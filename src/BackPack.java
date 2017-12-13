public class BackPack {

	/**
	 * 在n个物品中挑选若干物品装入背包，最多能装多满？假设背包的大小为m，每个物品的大小为A[i] 
	 * 注意事项 你不可以将物品进行切割。 
	 * 样例
	 * 如果有4个物品[2, 3, 5, 7] 如果背包的大小为11，可以选择[2, 3, 5]装入背包，最多可以装满10的空间。
	 * 如果背包的大小为12，可以选择[2, 3, 7]装入背包，最多可以装满12的空间。 
	 * 函数需要返回最多能装满的空间大小。
	 * @param m  : An integer m denotes the size of a backpack
	 * @param A  : Given n items with size A[i]
	 * @return: The maximum size
	 */
	public int backPack(int m, int[] A) {
		// write your code here
		if(m == 0 || A.length == 0) {
			return 0;
		}
		int maxBackpack = findMaxBackPack(m, A);
		return maxBackpack;
	}
	
	/**
	 * 算法思想： 动态规划
	 * 			假设dp[i-1][j]表示前i-1个物品，背包容量为j时，背包装满的最大空间
	 * 			那么dp[i][j]就表示前i个物品，背包容量为j时，背包装满的最大空间
	 * 			此时有两种方案：1)第i个物品放入背包   2)第i个物品不放入背包
	 * 			因此状态转移方程： dp[i][j] = max(dp[i-1][j], dp[i-1][j-A[i]] + A[i])
	 * 			边界条件:	dp[i][0] = 0
	 * 					 	dp[0][j] = 0
	 * @param m
	 * @param A
	 * @return
	 */
	private int findMaxBackPack(int m, int[] A) {
		int[][] dp = new int[A.length+1][m+1];
		/**初始化**/
		for(int i = 0; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		for(int j = 0; j < m+1; j++) {
			dp[0][j] = 0;
		}
		
		for(int i = 1; i <= A.length; i++) {
			for(int j = 1; j <= m; j++) {
				if(j >= A[i-1]) { //数组A的下标是从0开始
					dp[i][j] = Math.max(dp[i-1][j-A[i-1]] + A[i-1], dp[i-1][j]);
				} else{
				    dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[A.length][m];
	}
}
