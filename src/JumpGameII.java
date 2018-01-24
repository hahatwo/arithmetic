import java.util.Arrays;


public class JumpGameII {

	
	 /*
	  * 给出一个非负整数数组，你最初定位在数组的第一个位置。
	  * 数组中的每个元素代表你在那个位置可以跳跃的最大长度。　　　
	  * 你的目标是使用最少的跳跃次数到达数组的最后一个位置。
	  * 
	  * 样例
	  * 给出数组A = [2,3,1,1,4]，最少到达数组最后一个位置的跳跃次数是2(从数组下标0跳一步到数组下标1，然后跳3步到数组的最后一个位置，一共跳跃2次)
      * @param A: A list of integers
      * @return: An integer
      */
    public int jump(int[] A) {
        // write your code here
    	//int result = jump1(A);
    	if(A == null || A.length == 0) {
    		return -1;
    	}
    	int result = jump2(A);
    	return result;
    }
    
    /**
     * 算法思想： 贪心算法，假设当前(位置i)能够跳最远距离为curEnd, 
     *          curFarther是在[i, i+curEnd]这些位置范围内能够跳的最远距离。
     *        每次当前位置(i)跳跃到最远位置i+curEnd, 那么就会触发一次跳跃，并且设置curEnd等于curFarthest。
     *        按照上面的步骤，直到跳跃到最后一个位置为止。
     * @return
     */
    public int jump1(int[] A) {
    	int curEnd = 0;
    	int curFathest = 0;
    	int jumps = 0;  //跳跃次数
    	
    	for(int i = 0; i < A.length; i++) {
    		curFathest = Math.max(curFathest, i + A[i]);
    		if(i == curEnd) {
    			jumps++;
    			curEnd = curFathest;
    			if(curFathest >= A.length-1) 
    				return jumps;
    		}
    	}
    	return -1;
    }
    
    /**
     * 算法思想： 动态规划： 假设dp[i]为数组第1个位置到第i个位置的最小跳跃次数，则dp[A.length-1]即为问题所求解
     * 状态转移方程： dp[i] = min(dp[j]+1) for 0 <=j <= i-1(其中满足条件 j+A[j] >= i) 
     * 边界条件： dp[0] = 0
     * 样例说明： 对于数组 A = [2,3,1,1,4]， 有：       
     *          i=0,{2},dp[0]=0 (开始就在第一个位置) 
     *          i=1,{2,3},dp[1]=min(dp[0]+1)=1
     *          i=2,{2,3,1},dp[2]=min(dp[0]+1,dp[1]+1)=1
     *          i=3,{2,3,1,1}, dp[3] = min(dp[1]+1, dp[2]+1)=2 (因为j=0时，A[j]+j<i,所以不需要与dp[0]+1比较)
     * @param A
     * @return
     */
    public int jump2(int[] A) {
    	int[] dp = new int[A.length];
    	Arrays.fill(dp, A.length-1); //初始化dp数组
    	dp[0] = 0;
    	for(int i = 1; i < A.length; i++) {
    		for(int j = 0; j <= i-1; j++) {
    			if(A[j] + j >= i) {
    				dp[i] = Math.min(dp[i], dp[j]+1);
    			}
    		}
    	}
    	return dp[A.length-1];
    }
}
