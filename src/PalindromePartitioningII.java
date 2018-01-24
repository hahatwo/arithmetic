
public class PalindromePartitioningII {

	 /**
	 * ����һ���ַ���s����s�ָ��һЩ�Ӵ���ʹÿ���Ӵ����ǻ��ġ�
	 * ����s����Ҫ��ĵ����ٷָ������
	 * ����
	 * ���磬�����ַ���s = "aab"��
	 * ���� 1�� ��Ϊ����һ�ηָ���Խ��ַ���s�ָ��["aa","b"]�������������Ӵ�
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
     * ���������ַ�����Boolean����
     * �㷨˼�룺 ��̬�滮��dp[i][j]��ʾ�ַ���������i��ʼ������j�����Ƿ����ڻ����ַ���
     *          dp[i][j] = true ��ʾ s.charAt(i)-s.charAt(j)Ϊ�����ַ���
     *          dp[i][j] = false ��ʾ s.charAt(i)-s.charAt(j)��Ϊ�����ַ���
     *          ��ôӦ����     s[i, i] ��Ϊ�����ַ���
     *              Ȼ����� s[i, i+1] �Ƿ�Ϊ�����ַ���
     *              Ȼ����� s[i, i+2] �Ƿ�Ϊ�����ַ���
     *              ...
     *              Ȼ����� s[i, s.length-1] �Ƿ�Ϊ�����ַ���
     * ״̬ת�Ʒ��̣� dp[i][j] = dp[i+1][j-1] && (s.charAt(i+1) == s.charAt(j-1)) 
     * 				(����j=i+len-1,lenΪ��i��j���ַ������ַ��ĸ���, len>2,����i��j�ַ������ٰ���3���ַ�)
     * �߽������� dp[i][j] = true (����len=1, ��˼Ϊ����i��j���ַ�����ʵ��Ϊ�����ַ���������Ȼdp[i][j]=true)
     * 			dp[i][j] = (s.charAt(i) == s.charAt(j)) (����len=2,����i��jֻ���������ַ�����������ַ���ͬ��Ϊ�����ַ����������ǻ����ַ���)
     * @param s
     * @param dp ��¼dp[i][j]�Ƿ����ڻ����ַ���
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
     * �㷨˼�룺 ��̬�滮˼�룺 count[i]��ʾ�ַ���s[i, s.length-1]��i��ʼ���ַ���sĩβ�Ļ����ַ�������С�ָ����
     *          �����ַ���s[i, s.length-1]֮���ܹ���j�ָi<j<=s.length-1����
     *          s[i,j-1]Ϊ�����ַ��� ��s[j, s.length-1]�ܹ����ָ�Ϊ���ɸ������ַ���
     *          ��
     * ״̬ת�Ʒ��̣�         count[i] = Math.min(count[i], count[j]+1) (s[i,j-1])Ϊ�����ַ���
     * �߽������� 		if dp[i][s.length-1] == true   //�����i��sĩβ���ַ���Ϊ�����ַ���
     * 						count[i] = 0
     * 					else count[i] = Integer.MAX_VALUE
     * count[0]�������ַ���s�����ٷָ����
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
    			if(dp[i][j-1]) { //s[i,j-1])Ϊ�����ַ���
    				count[i] = (count[i] > count[j]+1 ? count[j]+1 : count[i]);
    			}
    		}
    	}
    	return count[0];
    }
}
