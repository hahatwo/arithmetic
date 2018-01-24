
public class MinimumPathSum {

	/*
	 * 给定一个只含非负整数的m*n网格，找到一条从左上角到右下角的可以使数字和最小的路径。
	 * 注意事项
	 * 你在同一时间只能向下或者向右移动一步
	 * 
	 * 算法思想： 动态规划， grid[i][j]表示第i行第j列的数字，
	 *          minSum[i][j]表示第i行第j个数字到最右下角的数字和最小路径,
	 *          minSum[1][1]就表示从左上角到右下角的可以使数字和最小的路径
	 *          （注意：此处用minMax[1][1]表示逻辑上从左上角到右下角最小路径和，但是在实际求解的时候从0开始，
     *          因为grid[0][0]表示左上角）
	 * 状态转移方程： 
	 *        minSum[i][j] = Math.min(minSum[i+1][j],minSum[i][j+1])+grid[i][j] (其中j!=n，因为当j!=n时，才能向下或者向右移动一步)；
	 *        minSum[i][j] = minSum[i+1][j] + grid[i][j] (其中j=n，因为当j=n时，只能向下移动一步)；
	 * 边界条件：
	 *        minSum[i][j] = grid[i][j] (其中i==m && j==n,即此时数字就为最右下角的数字)；
	 *        minSum[i][j] = grid[i][j+1] (其中i==m && j!=n, 此时位于最后一行，只能向右移动一步)
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        // write your code here
    	if(grid.length == 0 || grid == null) {
    		return 0;
    	}
    	
    	int[][] minSum = new int[grid.length][grid[0].length];
    	for(int i = grid.length-1; i>=0; i--) {
    		for(int j = grid[i].length-1; j>=0; j--) {
    			if(i == grid.length-1) {
    				if(j == grid[i].length-1) {
    					minSum[i][j] = grid[i][j];
    				} else {
    					minSum[i][j] = minSum[i][j+1] + grid[i][j];
    				}
    			} else {
    				if(j == grid[i].length-1) {
    					minSum[i][j] = minSum[i+1][j] + grid[i][j];
    				} else {
    					minSum[i][j] = Math.min(minSum[i+1][j],minSum[i][j+1]) + grid[i][j];
    				}
    			}
    		}
    	}
    	return minSum[0][0];
    }
	
}
