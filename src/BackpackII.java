
public class BackpackII {

	
	/**
     * 给出n个物品的体积A[i]和其价值V[i]，将他们装入一个大小为m的背包，最多能装入的总价值有多大？
	 * 注意事项
	 * A[i], V[i], n, m均为整数。你不能将物品进行切分。你所挑选的物品总体积需要小于等于给定的m。
	 * 样例
	 * 对于物品体积[2, 3, 5, 7]和对应的价值[1, 5, 2, 4], 假设背包大小为10的话，最大能够装入的价值为9。
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        // write your code here
    	if(m == 0 || A == null || A.length == 0 || V == null || V.length == 0) {
    		return 0;
    	}
        int[][] dp = new int[A.length+1][m+1];
        for(int i = 1; i < A.length+1; i++) {
        	for(int j = 1; j < m+1; j++) {
        		if(j >= A[i-1]) {
        			dp[i][j] = Math.max(dp[i-1][j-A[i-1]]+V[i-1], dp[i-1][j]);
        		} else {
        			dp[i][j] = dp[i-1][j];
        		}
        	}
        }
    	return dp[A.length][m];
    }
	
    /**
     * 优化算法： 空间为O(m)
     *          dp[i][j] = Math.max(dp[i-1][j-A[i-1]]+V[i-1], dp[i-1][j])(i=1->A.length; j=0->m)
     *          可以看到i跟i-1有关，跟其他无关
     *          设dp[j]为容量为j时,背包获得的最大价值
     *          dp[j] = Math.max(dp[j-A[i]]+V[i], dp[j-1])(i=1->A.length, j=m->0)
     *          for(i=1->A.length)
     *                 for(j=m->0) 
     *              dp[j] = Math.max(dp[j-A[i]]+V[i], dp[j])（j>=A[i]）
     *            
     *          每遍历一次都能够确定dp[k]的值(k=1->m),dp[k]的值不会再改变
     *          举例说明：
     *          1. 当遍历第一次时，j会从m一直求到1， 最后能够得出dp[j=m->1]的值，
     *             但是不难想象只有dp[0]...dp[min(A[i]-1)]将会恒等于0, dp[min(A[i])](dp[min(A[i])]=dp[0] or dp[0]+V[i],所以其值不会发生变化)的值在后续遍历中不会再改变；
     *          2. 当遍历第二次时，j会从m一直求到1， 最后能够得出dp[j=m->1]的值，
     *             但是不难想象只有dp[min(A[i]],dp[min(A[i]+1]...dp[secondMin(A[i])](dp[secondMin(A[i]]=dp[min(A[i]] or dp[min(A[i]]+V[i],所以其值不会发生变化)的值在后续遍历中不会再改变；
     *          3. 当遍历第三次时，j会从m一直求到1， 最后能够得出dp[j=m->1]的值，
     *             但是不难想象只有dp[min(A[i]],dp[min(A[i]+1]...dp[thirdMin(A[i])]的值在后续遍历中不会再改变；   
     *             ...
     *          n. 当遍历第n次时，dp[0]...dp[m]的值不会再改变，得到最终解
     *           
     *          
     * @param m
     * @param A
     * @param V
     * @return
     */
     public int opbackPackII(int m, int[] A, int[] V) {
         // write your code here
         if(m == 0 || A == null || A.length == 0 || V == null || V.length == 0) {
     		return 0;
     	}
         int[] dp = new int[m+1];
         for(int i = 0; i < A.length; i++) {
         	for(int j = m; j > 0; j--) {
         		if(j >= A[i]) {
         			dp[j] = Math.max(dp[j-A[i]]+V[i], dp[j]);
         		} 
         	}
         }
     	return dp[m];
     }
}
