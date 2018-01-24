
public class PalindromePartitioningII {

	 /**
	 * 给定一个字符串s，将s分割成一些子串，使每个子串都是回文。
	 * 返回s符合要求的的最少分割次数。
	 * 样例
	 * 比如，给出字符串s = "aab"，
	 * 返回 1， 因为进行一次分割可以将字符串s分割成["aa","b"]这样两个回文子串
     * @param s a string
     * @return an integer
     */
    public int minCut(String s) {
        // write your code here
       if(s == null || s.length() == 0) {
    	   return 0;
       }
       boolean[][] dp = new boolean[s.length()][s.length()];
       int[] count = new int[s.length()];
       setPalindromePartition(s, dp);
       int result = getMinCountOfPP(s, dp, count);
       return result;
    }
    
    /**
     * 建立回文字符串的Boolean数组
     * 算法思想： 动态规划，dp[i][j]表示字符串从索引i开始至索引j结束是否属于回文字符串
     *          dp[i][j] = true 表示 s.charAt(i)-s.charAt(j)为回文字符串
     *          dp[i][j] = false 表示 s.charAt(i)-s.charAt(j)不为回文字符串
     *          那么应该先     s[i, i] 都为回文字符串
     *              然后求解 s[i, i+1] 是否为回文字符串
     *              然后求解 s[i, i+2] 是否为回文字符串
     *              ...
     *              然后求解 s[i, s.length-1] 是否为回文字符串
     * 状态转移方程： dp[i][j] = dp[i+1][j-1] && (s.charAt(i+1) == s.charAt(j-1)) 
     * 				(其中j=i+len-1,len为从i到j的字符串中字符的个数, len>2,即从i到j字符串至少包含3个字符)
     * 边界条件： dp[i][j] = true (其中len=1, 意思为即从i到j的字符串其实就为单个字符，理所当然dp[i][j]=true)
     * 			dp[i][j] = (s.charAt(i) == s.charAt(j)) (其中len=2,即从i到j只包含两个字符，如果两个字符相同则为回文字符串，否则不是回文字符串)
     * @param s
     * @param dp 记录dp[i][j]是否属于回文字符串
     */
    public void setPalindromePartition(String s, boolean[][] dp) {
    	for(int len = 1; len <= s.length(); len++) {
    		for(int i = 0; i <= s.length() - len; i++) {
    			int j = i + len - 1; 
    			if(len == 1) {
    				dp[i][j] = true;
    			} else if(len == 2) {
    				dp[i][j] = (s.charAt(i) == s.charAt(j));
    			} else {
    				dp[i][j] = (dp[i+1][j-1] && (s.charAt(i) == s.charAt(j)));
    			}
    		}
    	}
    }
    
    /**
     * 算法思想： 动态规划思想： count[i]表示字符串s[i, s.length-1]从i开始到字符串s末尾的回文字符串的最小分割次数
     *          假设字符串s[i, s.length-1]之间能够被j分割（i<j<=s.length-1），
     *          s[i,j-1]为回文字符串 ，s[j, s.length-1]能够被分割为若干个回文字符串
     *          则
     * 状态转移方程：         count[i] = Math.min(count[i], count[j]+1) (s[i,j-1])为回文字符串
     * 边界条件： 		if dp[i][s.length-1] == true   //代表从i到s末尾的字符串为回文字符串
     * 						count[i] = 0
     * 					else count[i] = Integer.MAX_VALUE
     * count[0]即代表字符串s的最少分割次数
     * @param dp
     * @param count
     * @return
     */
    public int getMinCountOfPP(String s, boolean[][] dp, int[] count) {
    	for(int i = s.length()-1; i >= 0; i--) {
    		if(dp[i][s.length()-1]) {
    			count[i] = 0;
    			continue;
    		}
    		count[i] = Integer.MAX_VALUE;
    		for(int j = i+1; j < s.length(); j++) {
    			if(dp[i][j-1]) { //s[i,j-1])为回文字符串
    				count[i] = (count[i] > count[j]+1 ? count[j]+1 : count[i]);
    			}
    		}
    	}
    	return count[0];
    }
}
