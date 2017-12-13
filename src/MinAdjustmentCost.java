import java.util.ArrayList;
import java.util.List;


public class MinAdjustmentCost {

	/*
	 * 给一个整数数组，调整每个数的大小，使得相邻的两个数的差不大于一个给定的整数target，
	 * 调整每个数的代价为调整前后的差的绝对值，求调整代价之和最小是多少。
	 * 样例
	 * 对于数组[1, 4, 2, 3]和target=1，最小的调整方案是调整为[2, 3, 2, 3]，调整代价之和是2。返回2。
	 * 注意事项
	 * 你可以假设数组中每个整数都是正整数，且小于等于100。
     * @param A: An integer array
     * @param target: An integer
     * @return: An integer
     */
    public int MinAdjustmentCost(List<Integer> A, int target) {
        // write your code here
    	if(A.size() == 0) {
    		return 0;
    	}
    	List<Integer> B = new ArrayList<Integer>(A);
    	//int minCost = findMinAdjustmentCost(A, target);
    	int minCost = findMinAdjustmentCostII(A, B, target, 0);
    	return minCost;
    }
    
    /**
     * 算法思想： 动态规划，一个元素一个元素的调整，由于是要求相邻元素的差值，所以只和前一个相邻元素的值有关，所以只需要记录上一个调整的值就可以。
     * 那么。dp[i][j]表示调整到第i个数时，此时，第i个数调整到值j,为代价和最小。
     * (因为题目中数组中每个整数都是正整数，且小于等于100，那么min <= j <= max,此处min=1,max=100)
     * dp[i+1][k]表示调整到第i+1个数时，此时，第i+1个数调整到值k,为代价和最小。(min <= k <= max)
     * 同时j与k必定满足关系|k-j|<=target, 那么 j-target <= min <= k <=max <= j+target 
     * 状态转移方程：dp[i+1][k] = dp[i][j] + abs(k-A[i+1])
     * 边界条件： dp[0][j] = abs(j-A[0])(从第一个数开始从min到max调整)
     * 最终解为dp[A.size()][i](1<=i<A.size())中最小的值.
     * @param A
     * @param target
     * @return
     */
    private int findMinAdjustmentCost(List<Integer> A, int target) {
    	int size = A.size();
    	int min = 1;
    	int max = 100;
    	int[][] dp = new int[size][max+1];
    	for(int i = 0; i < size; i++) {
    		for(int j = min; j <= max; j++) {
    			dp[i][j] = Integer.MAX_VALUE;
    			if(i == 0) {	//边界条件
    				dp[i][j] = Math.abs(j - A.get(i));
    			} else {
    				 /**需满足条件|k-j|<=target，由于j和k都有多种可能取值，所以循环求解判断，
    				  * k表示前一个数，j表示现在的数，假设j确定，那么k的取值就是在一个范围内，
    				  * 因为差值不能超过target。那么循环求解，找到满足条件的一系列k，
    				  * 通过一系列k的值求出dp[i][j]最小代价**/
    				for(int k = Math.max(min, j-target); k <= Math.min(max, j+target); k++) {
    					dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + Math.abs(j-A.get(i)));
    				}
    			}
    		}
    	}
    	
    	int minCost = Integer.MAX_VALUE;
        for (int i = min; i <=max; i++) {
            min = Math.min(minCost, dp[dp.length - 1][i]);
        }
        return minCost;
    }
    
   /**
    * 算法思想2： 递归求解(只适合小数据，数据量大时会超时)
    * 			类似深度优先遍历，不过不同的是，本递归会遍历到底，而深度优先遍历满足条件时就返回了。
    * 			1.B刚开始和A保存的数据一样，用B来保存某个数被调整之后的值。
    * 			2.第一个数调整为min-max，全部都遍历一遍，假设某个时刻第一个数调整为i,
    * 			3.第二个数调整为满足条件|j-i|<target的min'-max'，全部遍历一遍,此时第二个数遍历到j
    * 			4.第二个数调整为满足条件|k-j|<target的min'-max'，全部遍历一遍,此时第三个数遍历到k
    * 			  ...
    * 			n. 一直到最末尾第A.size()数结束
    * 			n+1. 回溯，将B变回原始值，求出最小调整代价
    * 			然后第一个数调整为i+1，按照上述步骤再递归遍历一次
    * 			...
    * 			直到第一个数调整为max,结束
    * 		
    * @param A
    * @param B
    * @param target
    * @param index
    * @return
    */
    private int findMinAdjustmentCostII(List<Integer> A, List<Integer> B, int target, int index) {
    	int size = A.size();
    	int min = 1;
    	int max = 100;
    	int cost = 0;
    	int minCost = Integer.MAX_VALUE;
    	if(index >= size) {
    		return 0;
    	}
    	
    	for(int i = min; i <= max; i++) {
    		if(index != 0 && (Math.abs(i-B.get(index-1)) > target)) {
    			continue;
    		}
    		B.set(index, i);
    		cost = Math.abs(i-A.get(index));
    		cost += findMinAdjustmentCostII(A, B, target, index+1);
    		minCost = Math.min(cost, minCost);
    		B.set(index, A.get(index)); //回溯
    	}
    	return minCost;
    }
    
}
