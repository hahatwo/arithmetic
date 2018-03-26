import java.util.Set;


public class WordBreakI {

	
	 /*
     * 给出一个字符串s和一个词典，判断字符串s是否可以被空格切分成一个或多个出现在字典中的单词。
	 * 样例
	 * 给出 s = "lintcode"  dict = ["lint","code"]
	 * 返回 true 因为"lintcode"可以被空格切分成"lint code"
     * @param s: A string
     * @param dict: A dictionary of words dict
     * @return: A boolean
     */
     public boolean wordBreak(String s, Set<String> dict) {
        // write your code here
    	if((s.length() == 0 || s == null) && (dict.size() == 0 || dict == null)) {
    		return true;
    	} 
    	boolean result = dpWordBreak(s, dict);
    	return result;
    }
    
    /**
     * 算法思想： 利用动态规划,boolean数组dp[s.length+1]来记录是否字符串可以被切分
     *          dp[i]记录字符串s以索引i结尾时是否能够被切分,当dp[s.length]=true时证明能够被切分，dp[s.length]=false证明不能被切分
     *          str表示字典中的某一字符串
     * 			状态转移方程： dp[i+str.length] = true, (dp[i]==true且s.substring(i, i+str.length)==str)
     * 			边界条件： dp[0] == true
     * @param s     源字符串
     * @param dict  字典
     * @return
     */
    public boolean dpWordBreak(String s, Set<String> dict) {
    	int len = s.length();
    	boolean[] dp = new boolean[len+1]; //使用数组dp来记录是否包含字典中的某个字符串
    	for(int i = 1; i < dp.length; i++) {
    		dp[i] = false;
    	}
    	dp[0] = true; //初始化dp
    	for(int i = 0; i < dp.length; i++) {
    		if(!dp[i]) {
    			continue;
    		}
    		for(String str : dict) {
    			if(i + str.length() > s.length()) {
    				continue;
    			}
                if(s.substring(i, i+str.length()).equals(str)) {
                	dp[i+str.length()] = true;
                }
    		}
    	}
    	return dp[len];
    }
}
