
public class Triangle {

	 /*
	 * 给定一个数字三角形，找到从顶部到底部的最小路径和。每一步可以移动到下面一行的相邻数字上。
	 * 样例
	 * 比如，给出下列数字三角形：
	 * [
     *   [2],
     *  [3,4],
     * [6,5,7],
     *[4,1,8,3]
     * ]
     * 从顶到底部的最小路径和为11 ( 2 + 3 + 5 + 1 = 11)。 
     * 
     * 算法思想： 动态规划， triangle[i][j]表示第i行第j个数字
     *          minSum[i][j]表示第i行第j个数到底部的最小路径和
     *          minSum[1][1]则表示数字三角形从顶部到底部的最小路径和
     *          （注意：此处用minMax[1][1]表示逻辑上的数字三角形从顶部到底部的最小路径和，但是在实际求解的时候从0开始，
     *          因为triangle[0][0]表示数字三角形顶部）
     * 状态转移方程： minSum[i][j] = Math.min(minSum[i+1][j], minSum[i+1][j+1])+triangle[i][j]
     * 边界条件： minSum[i][j] = triangel[i][j] (当i==triangle.length-1时，即当i处于三角形底部时)
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
    	if(triangle == null || triangle.length == 0) {
    		return 0;
    	}
    	int[][] minSum = new int[triangle.length][triangle.length];
    	
    	for(int i = triangle.length-1; i>=0; i--) {
    		for(int j = 0; j < triangle[i].length; j++) {
    			if(i == triangle.length-1) {
        			minSum[i][j] = triangle[i][j];
        		} else {
        			minSum[i][j] = Math.min(minSum[i+1][j], minSum[i+1][j+1]) + triangle[i][j];
        		}
    		}	
    	}
    	return minSum[0][0];
    }
	
}
