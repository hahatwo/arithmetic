
public class LongestCommonPrefix {

	/*
	 * �㷨˼�룺�����ҵ���̵��Ǹ��ַ���
	 * 			Ȼ��ö�ٸ��ַ�����ÿ����ĸ��
	 * 			����ַ��������е�ĳ���ַ���������ĳ����ĸ�������ĸ֮ǰ���Ӵ���Ϊ�����ǰ׺
	 * ��k���ַ�����������ǵ������ǰ׺(LCP)
	 * ����
	 * �� "ABCD" "ABEF" �� "ACEF" ��,  LCP Ϊ "A"
	 * �� "ABCDEFG", "ABCEFG", "ABCEFA" ��, LCP Ϊ "ABC"
     * @param strs: A list of strings
     * @return: The longest common prefix
     */
    public String longestCommonPrefix(String[] strs) {
        // write your code here
    	if(strs.length == 0 || strs == null) {
    		return "";
    	}
    	String shortestStr = null; //��¼strs�ַ�������������ַ���
    	int shortestIndex = 0;  //��¼strs�ַ�������������ַ���������
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
