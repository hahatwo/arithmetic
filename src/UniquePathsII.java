
public class UniquePathsII {

	/*
	 * "不同的路径" 的跟进问题：
	 * 现在考虑网格中有障碍物，那样将会有多少条不同的路径？
	 * 网格中的障碍和空位置分别用 1 和 0 来表示。
	 * 样例
	 * 如下所示在3x3的网格中有一个障碍物：
	 * [
	 * [0,0,0],
	 * [0,1,0],
	 * [0,0,0]
	 * ]
	 * 一共有2条不同的路径从左上角到右下角。
	 * * 算法思想： 动态规划 ，假设path[i][j]表示从左上角到达第i行第j列的点的路径数目，path[m][n]则为所求问题的解。
	 * 状态转移方程： path[i][j] = path[i-1][j] + path[i][j-1] (其中i!=1&&j!=1,即到达某点可以从上一点或者左一点两种方式到达)
	 * 边界条件： path[1][1] = 1 (注意：path[1][1]表示逻辑上的起点，而实际上求解时应该从path[0][0]开始，path[m-1][n-1]为问题的实际解) 
	 *          path[i][j] = path[i][j-1] (其中i==1,即处于第一行，只能选择从左边一点达到某点)
	 *          path[i][j] = path[i-1][j] (其中j==1,即处于第一列，只能选择从上边一点达到某点)
	 *          path[i][j] = 0(其中obstacleGrid[i][j]==1,即该点处存在障碍物，没有路径能够达到它)
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
    	if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
    		return 0;
    	}
    	if(obstacleGrid[0][0] == 1) { //起点为障碍物
    		return 0;
    	}
    	
    	int[][] path = new int[obstacleGrid.length][obstacleGrid[0].length];
    	path[0][0] = 1;
    	for(int i = 1; i < path.length; i++) {
    		if(obstacleGrid[i][0] == 1)
    			path[i][0] = 0;
    		else 
    		    path[i][0] = path[i-1][0];
    	}
    	for(int j = 1; j < path[0].length; j++) {
    		if(obstacleGrid[0][j] == 1)
    			path[0][j] = 0;
    		else 
    		    path[0][j] = path[0][j-1];
    	}
    	
    	for(int i = 1; i < path.length; i++) {
    		for(int j = 1; j < path[0].length; j++) {
    			if(obstacleGrid[i][j] == 1) {
    				path[i][j] = 0;
    			} else {
    				path[i][j] = path[i-1][j] + path[i][j-1];
    			}
    		}
    	}
    	return path[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
}
