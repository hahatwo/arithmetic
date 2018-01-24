
public class DistinctSubsequences {

	/*
	 * �����ַ���S���ַ���T������S�Ĳ�ͬ����������T���ֵĸ�����
	 * ���ַ�����ԭʼ�ַ���ͨ��ɾ��һЩ(�����)������һ���µ��ַ��������Ҷ�ʣ�µ��ַ������λ��û��Ӱ�졣
	 * (���磬��ACE���ǡ�ABCDE�����������ַ���,����AEC������)�� 
	 * ����
	 * ����S = "rabbbit", T = "rabbit"
	 * ���� 3
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
     * �㷨˼�룺 ��̬�滮�� ����dp[i][j] Ϊ�ַ���S������i��β���ַ���T������j��βʱ�� ��S[0-i]�����������ַ���T[0-j]ƥ��Ĵ���
     *          ��dp[S.length][T.length]ΪS�Ĳ�ͬ����������T���ֵĸ�����
     *          ����֪����
     *          ���һ�� ��S[i]!=T[j]ʱ����S[0-i]�����������ַ���T[0-j]ƥ��Ĵ������� ��S[0-(i-1)]�����������ַ���T[0-j]ƥ��Ĵ�������
     *          dp[i][j] = dp[i-1][j].
     *          ����˵S=rabbbi, T=rabʱ�� S[6]!=T[3],S�������������һ���ַ�i��T������ƥ�䣬����Ϳ���ת��Ϊ
     *          ����S[0-(i-1)]�����������ַ���T[0-j]ƥ��Ĵ���,�ó�dp[i][j] = dp[i-1][j].
     *          ������� ��S[i]!=T[j]ʱ����S[0-i]�����������ַ���T[0-j]ƥ��Ĵ���ʱ�����ȿ���S[i]����ַ������������У�
     *          ��ô��ʱ��dp[i-1][j]��������ƥ�䣬��Σ���S[i]����ַ����������У�ֻ��Ҫ���ַ���S[0-(i-1)]���ж���������ƥ��T[0-(j-1)]�Ϳ��ԣ�
     *          ��ô��dp[i-1][j-1]��������ƥ�䣬����dp[i][j] = dp[i-1][j] + dp[i-1][j-1].
     *
     * ״̬ת�Ʒ��̣� dp[i][j] = dp[i-1][j] + dp[i-1][j-1] AND S[i]==T[j]
     *              dp[i][j] = dp[i-1][j] AND S[i]!=T[j]
     * �߽�������  dp[i][0] = 1; (��ʾTΪ���ַ��������ַ���Ϊ�����ַ�����������)
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
    			if(S.charAt(i-1) != T.charAt(j-1)) { //�ַ�����0��ʼ��������
    				dp[i][j] = dp[i-1][j];
    			} else {
    				dp[i][j] = dp[i-1][j] + dp[i-1][j-1];
    			}
    		}
    	}
    	return dp[S.length()][T.length()];
    }
    
    /**
     * �㷨˼�룺 �ڶ�̬�滮�Ļ������Ż��㷨���Ż��ռ�ΪO(n)
     *          ��̬�滮�㷨��dp[i][j]��iֻ��i-1��أ�����ʹ��һ��һά������������������ֵ�������ռ�������Ż�
     *          ����dp[j]ΪTΪ����j��βʱ����S�д��ڵ���������T[0-j]ƥ��Ĵ���
     * ��ʼ���� dp[j] = 1.
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
