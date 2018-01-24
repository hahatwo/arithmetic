
public class UniquePaths {

	/*
	 * 有一个机器人的位于一个 m × n 个网格左上角。
	 * 机器人每一时刻只能向下或者向右移动一步。机器人试图达到网格的右下角。
	 * 问有多少条不同的路径？
	 * 样例
	 * 给出 m = 3 和 n = 3, 返回 6.
	 * 给出 m = 4 和 n = 5, 返回 35.
	 * 
	 * 算法思想： 动态规划 ，假设path[i][j]表示从左上角到达第i行第j列的点的路径数目，path[m][n]则为所求问题的解。
	 * 状态转移方程： path[i][j] = path[i-1][j] + path[i][j-1] (其中i!=1&&j!=1,即到达某点可以从上一点或者左一点两种方式到达)
	 * 边界条件： path[1][1] = 1 (注意：path[1][1]表示逻辑上的起点，而实际上求解时应该从path[0][0]开始，path[m-1][n-1]为问题的实际解) 
	 *          path[i][j] = path[i][j-1] (其中i==1,即处于第一行，只能选择从左边一点达到某点)
	 *          path[i][j] = path[i-1][j] (其中j==1,即处于第一列，只能选择从上边一点达到某点)
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
    	if(m == 0 || n == 0) {
    		return 0;
    	}
    	if(m == 1 || n == 1) {
    		return 1;
    	}
    	int[][] path = new int[m][n];
    	path[0][0] = 1;
    	for(int i = 1; i < m; i++) {
    		path[i][0] = path[i-1][0];
    	}
    	for(int j = 1; j < n; j++) {
    		path[0][j] = path[0][j-1];
    	}
    	for(int i = 1; i < m; i++) {
    		for(int j = 1; j < n; j++) {
    			path[i][j] = path[i-1][j] + path[i][j-1];
    		}
    	}
    	return path[m-1][n-1];
    }
}
