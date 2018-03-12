 
public class EditDistance {

	 /*
	 * 给出两个单词word1和word2，计算出将word1 转换为word2的最少操作次数。
	 * 你总共三种操作方法：
	 * 插入一个字符
	 * 删除一个字符
	 * 替换一个字符 
	 * 样例
	 * 给出 work1="mart" 和 work2="karma"
	 * 返回 3
     * @param word1: A string
     * @param word2: A string
     * @return: The minimum number of steps.
     */
    public int minDistance(String word1, String word2) {
        // write your code here
        if(word1 == null || word2 == null) {
        	return 0;
        }
        int result = dpMinDistance(word1, word2);
        return result;
    }
    
    /**
     * 算法思想： 动态规划
     * 			假设字符串 a, 共 m 位，从 a[1] 到 a[m]
     * 			字符串 b, 共 n 位，从 b[1] 到 b[n]
     * 			d[i][j] 表示字符串 a[1]-a[i] 转换为 b[1]-b[i] 的编辑距离
     * 			那么有如下递归规律（a[i] 和 b[j] 分别是字符串 a 和 b 的最后一位）：
     * 			情况一：当 a[i] 等于 b[j] 时，d[i][j] = d[i-1][j-1], 比如 fxy -> fay 的编辑距离等于 fx -> fa 的编辑距离
     * 			情况二：当 a[i] 不等于 b[j] 时，d[i][j] 等于如下 3 项的最小值：
     * 			d[i-1][j] + 1（删除 a[i]）， 比如 fxy -> fab 的编辑距离 = fx -> fab 的编辑距离 + 1
     * 			d[i][j-1] + 1（插入 b[j])， 比如 fxy -> fab 的编辑距离 = fxyb -> fab 的编辑距离 + 1 = fxy -> fa 的编辑距离 + 1
     * 			d[i-1][j-1] + 1（将 a[i] 替换为 b[j]）， 比如 fxy -> fab 的编辑距离 = fxb -> fab 的编辑距离 + 1 = fx -> fa 的编辑距离 + 1
     * 状态转移方程： dp[i][j] = d[i-1][j-1] AND a.charAt(i) == b.charAt(j)
     * 				dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1 AND a.charAt(i) != b.charAt(j)
     * 边界条件：         dp[i][0] = i, (字符串b为"",则需要删除i步)
     *              dp[0][j] = j, (字符串a为"", 则需要添加j步)
     * @param word1
     * @param word2
     * @return
     */
    public int dpMinDistance(String word1, String word2) {
    	int m = word1.length();
    	int n = word2.length();
    	int[][] dp = new int[m+1][n+1];
    	
    	for(int i = 0; i <= m; i++) {
    		dp[i][0] = i;
    	}
    	for(int j = 0; j <= n; j++) {
    		dp[0][j] = j;
    	}
    	
    	for(int i = 1; i <= m; i++) {
    		for(int j = 1; j <= n; j++) {
    			if(word1.charAt(i-1) == word2.charAt(j-1)) {
    				dp[i][j] = dp[i-1][j-1];
    			} else {
    				dp[i][j] = Math.min(Math.min(dp[i][j-1], dp[i-1][j]), dp[i-1][j-1]) + 1;
    			}
    		}
    	}
    	return dp[m][n];
    }
    
}
