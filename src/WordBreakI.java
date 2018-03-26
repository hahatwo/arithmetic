import java.util.Set;


public class WordBreakI {

	
	 /*
     * ����һ���ַ���s��һ���ʵ䣬�ж��ַ���s�Ƿ���Ա��ո��зֳ�һ�������������ֵ��еĵ��ʡ�
	 * ����
	 * ���� s = "lintcode"  dict = ["lint","code"]
	 * ���� true ��Ϊ"lintcode"���Ա��ո��зֳ�"lint code"
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
     * �㷨˼�룺 ���ö�̬�滮,boolean����dp[s.length+1]����¼�Ƿ��ַ������Ա��з�
     *          dp[i]��¼�ַ���s������i��βʱ�Ƿ��ܹ����з�,��dp[s.length]=trueʱ֤���ܹ����з֣�dp[s.length]=false֤�����ܱ��з�
     *          str��ʾ�ֵ��е�ĳһ�ַ���
     * 			״̬ת�Ʒ��̣� dp[i+str.length] = true, (dp[i]==true��s.substring(i, i+str.length)==str)
     * 			�߽������� dp[0] == true
     * @param s     Դ�ַ���
     * @param dict  �ֵ�
     * @return
     */
    public boolean dpWordBreak(String s, Set<String> dict) {
    	int len = s.length();
    	boolean[] dp = new boolean[len+1]; //ʹ������dp����¼�Ƿ�����ֵ��е�ĳ���ַ���
    	for(int i = 1; i < dp.length; i++) {
    		dp[i] = false;
    	}
    	dp[0] = true; //��ʼ��dp
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
