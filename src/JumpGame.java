
public class JumpGame {

	
	/*
	 * 给出一个非负整数数组，你最初定位在数组的第一个位置。　　　
	 * 数组中的每个元素代表你在那个位置可以跳跃的最大长度。　　　　
	 * 判断你是否能到达数组的最后一个位置。
	 * 样例
	 * A = [2,3,1,1,4]，返回 true.
	 * A = [3,2,1,0,4]，返回 false.
	 * 
	 * 算法思想： 动态规划，假设dp[i] 表示从数组第一个位置是否能够达到第i个位置
	 *          dp[i] = true表示能够到达第i个位置
	 *          dp[i] = false表示不能到达第i个位置
	 *          则dp[A.length-1]即为求解的结果
	 * 状态转移方程： dp[j] = true (其中Math.max(0, i-A[i])<= j <= Math.min(len-1, i+A[i]) && dp[i]==true)
	 * 边界条件： dp[0] = true
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJump(int[] A) {
        // write your code here
    	if(A == null || A.length == 0) {
    		return false;
    	}
    	boolean[] dp = new boolean[A.length];
    	dp[0] = true;
    	int len = A.length;
    	for(int i = 0; i < len; i++) {
    		if(dp[i]) {
    			for(int j = Math.max(0, i-A[i]); j <= Math.min(len-1, i+A[i]); j++) {
    				dp[j] = true;
    				if(dp[len-1]) {
    					return true;
    				}
    			}
    		} else {
    			continue;
    		}
    	}
    	return false;
    }
}
