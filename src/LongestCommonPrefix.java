
public class LongestCommonPrefix {

	/*
	 * 算法思想：首先找到最短的那个字符串
	 * 			然后枚举该字符串的每个字母，
	 * 			如果字符串数组中的某个字符串不包含某个字母，则该字母之前的子串即为最长公共前缀
	 * 给k个字符串，求出他们的最长公共前缀(LCP)
	 * 样例
	 * 在 "ABCD" "ABEF" 和 "ACEF" 中,  LCP 为 "A"
	 * 在 "ABCDEFG", "ABCEFG", "ABCEFA" 中, LCP 为 "ABC"
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        // write your code here
    	if(strs.length == 0 || strs == null) {
    		return "";
    	}
    	String shortestStr = null; //记录strs字符串数组中最短字符串
    	int shortestIndex = 0;  //记录strs字符串数组中最短字符串的索引
    	for(int i = 0; i < strs.length; i++){
    		if(strs[i].length() > strs[shortestIndex].length()){
    			shortestIndex = i;
    		}
    	}
    	shortestStr = strs[shortestIndex];
    	String subString = "";
    	for(int i = 0; i < shortestStr.length(); i++) {
    		subString = shortestStr.substring(0, i+1);
    		for(int j = 0; j < strs.length; j++){
    			if(!strs[j].contains(subString)){
    				return subString.substring(0, i);
    			}
    		}
    	}
    	return subString;
    }
}
