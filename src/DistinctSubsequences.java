
public class DistinctSubsequences {

	/*
	 * 给出字符串S和字符串T，计算S的不同的子序列中T出现的个数。
	 * 列字符串是原始字符串通过删除一些(或零个)产生的一个新的字符串，并且对剩下的字符的相对位置没有影响。
	 * (比如，“ACE”是“ABCDE”的子序列字符串,而“AEC”不是)。 
	 * 样例
	 * 给出S = "rabbbit", T = "rabbit"
	 * 返回 3
	 * 
     * @param : A string
     * @param : A string
     * @return: Count the number of distinct subsequences
     */
    public int numDistinct(String S, String T) {
        // write your code here
    	if(S == null || T == null || (S.length() == 0 && T.length() !=0)) {
    		return 0;
    	}
    	if(T.length() == 0) {
    		return 1;
    	}
    	
    	int result = optmizeNumDistinct(S, T);
    	return result;
    }
    
    /**
     * 算法思想： 动态规划， 假设dp[i][j] 为字符串S以索引i结尾，字符串T以索引j结尾时， 在S[0-i]有子序列与字符串T[0-j]匹配的次数
     *          则dp[S.length][T.length]为S的不同的子序列中T出现的个数。
     *          容易知道：
     *          情况一： 当S[i]!=T[j]时，在S[0-i]有子序列与字符串T[0-j]匹配的次数等于 在S[0-(i-1)]有子序列与字符串T[0-j]匹配的次数，即
     *          dp[i][j] = dp[i-1][j].
     *          比如说S=rabbbi, T=rab时， S[6]!=T[3],S的子序列有最后一个字符i对T都不会匹配，问题就可以转化为
     *          求在S[0-(i-1)]有子序列与字符串T[0-j]匹配的次数,得出dp[i][j] = dp[i-1][j].
     *          情况二： 当S[i]!=T[j]时，求S[0-i]有子序列与字符串T[0-j]匹配的次数时，首先考虑S[i]这个字符不在子序列中，
     *          那么此时有dp[i-1][j]个子序列匹配，其次，当S[i]这个字符在子序列中，只需要求字符串S[0-(i-1)]中有多少子序列匹配T[0-(j-1)]就可以，
     *          那么有dp[i-1][j-1]个子序列匹配，所以dp[i][j] = dp[i-1][j] + dp[i-1][j-1].
     *
     * 状态转移方程： dp[i][j] = dp[i-1][j] + dp[i-1][j-1] AND S[i]==T[j]
     *              dp[i][j] = dp[i-1][j] AND S[i]!=T[j]
     * 边界条件：  dp[i][0] = 1; (表示T为空字符串，空字符串为任意字符串的子序列)
     *           dp[0][j] = 0;
     *           dp[0][0] = 1;
     * @param S
     * @param T
     * @return
     */
    public int dpNumDistinct(String S, String T) {
    	int[][] dp = new int[S.length()+1][T.length()+1];
    	for(int i = 0; i <= S.length(); i++) {
    		dp[i][0] = 1;
    	}
    	for(int j = 0; j <= T.length(); j++) {
    		dp[0][j] = 0;
    	}
    	dp[0][0] = 1;
    	
    	for(int i = 1; i <= S.length(); i++) {
    		for(int j = 1; j <= T.length(); j++) {
    			if(S.charAt(i-1) != T.charAt(j-1)) { //字符串从0开始计算索引
    				dp[i][j] = dp[i-1][j];
    			} else {
    				dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
    			}
    		}
    	}
    	return dp[S.length()][T.length()];
    }
    
    /**
     * 算法思想： 在动态规划的基础上优化算法，优化空间为O(n)
     *          动态规划算法中dp[i][j]中i只与i-1相关，所以使用一个一维数组来滚动更新数组值，来做空间上面的优化
     *          假设dp[j]为T为索引j结尾时，在S中存在的子序列与T[0-j]匹配的次数
     * 初始化： dp[j] = 1.
     * @param S
     * @param T
     * @return
     */
    public int optmizeNumDistinct(String S, String T) {
    	int dp[] = new int[T.length()+1];
    	dp[0] = 1;
    	for(int i = 0; i < S.length(); i++) {
    		for(int j = T.length(); j > 0; j--) {
    			dp[j] += (S.charAt(i)==T.charAt(j-1)) ? dp[j-1] : 0;
    		}
    	}
    	return dp[T.length()];
    }
}
