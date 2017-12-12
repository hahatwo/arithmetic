
public class LongestCommonSubsequence {

	/* 
	 * 给出两个字符串，找到最长公共子序列(LCS)，返回LCS的长度
	 * 最长公共子序列的定义：
	 * 最长公共子序列问题是在一组序列（通常2个）中找到最长公共子序列（注意：不同于子串，LCS不需要是连续的子串）。
	 * 该问题是典型的计算机科学问题，是文件差异比较程序的基础，在生物信息学中也有所应用。
	 * 样例
	 * 给出"ABCD" 和 "EDCA"，这个LCS是 "A" (或 D或C)，返回1
	 * 给出 "ABCD" 和 "EACB"，这个LCS是"AC"返回 2
     * @param A: A string
     * @param B: A string
     * @return: The length of longest common subsequence of A and B
     */
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
    	if(A.length() == 0 || B.length() == 0) {
    		return 0;
    	}
    	//初始化L
    	int[][] L = new int[A.length()+1][B.length()+1];
    	for(int i = 0; i < B.length() + 1; i++) {
    		L[i][0] = 0;
    	}
    	for(int j = 0; j < A.length() + 1; j++) {
    		L[0][j] = 0;
    	}

    	
    	return calculateLongestCommonSubsequence(L, A, B);
    }
    
    /**
     * 算法思想： 动态规划
     * 假设L[i][j]为A的索引为i结尾的子字符串，B的索引为j结尾的子字符串的最长公共子序列
     * 最优子结构： L[i+1][j+1] = A.charAt(i+1) == B.charAt(j+1)? L[i][j]+1 : MAX(L[i][j-1],L[i-1][j])
     * 边界条件：  L[0][j] = A.charAt(0) == B.charAt(j)? 1 ： 0;
     * 			 L[i][0] = A.charAt(i) == B.charAt(0)? 1 : 0;
     * @param L
     * @param A
     * @param B
     * @return
     */
    private int calculateLongestCommonSubsequence(int[][] L, String A, String B) {
    	int longestCount = Integer.MIN_VALUE; //记录最长子序列的长度
    	for(int i = 1; i <= A.length(); i++) {
    		for(int j = 1; j <= B.length(); j++) {
    			if(A.charAt(i-1) == B.charAt(j-1))
    				L[i][j] = L[i-1][j-1] + 1;
    			else L[i][j] = Math.max(L[i][j-1], L[i-1][j]);
    			if(longestCount < L[i][j]) {
    				longestCount = L[i][j];
    			}
    		}
    	}
    	return longestCount;
    }
}
