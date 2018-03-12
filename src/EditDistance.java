 
public class EditDistance {

	 /*
	 * ������������word1��word2���������word1 ת��Ϊword2�����ٲ���������
	 * ���ܹ����ֲ���������
	 * ����һ���ַ�
	 * ɾ��һ���ַ�
	 * �滻һ���ַ� 
	 * ����
	 * ���� work1="mart" �� work2="karma"
	 * ���� 3
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
     * �㷨˼�룺 ��̬�滮
     * 			�����ַ��� a, �� m λ���� a[1] �� a[m]
     * 			�ַ��� b, �� n λ���� b[1] �� b[n]
     * 			d[i][j] ��ʾ�ַ��� a[1]-a[i] ת��Ϊ b[1]-b[i] �ı༭����
     * 			��ô�����µݹ���ɣ�a[i] �� b[j] �ֱ����ַ��� a �� b �����һλ����
     * 			���һ���� a[i] ���� b[j] ʱ��d[i][j] = d[i-1][j-1], ���� fxy -> fay �ı༭������� fx -> fa �ı༭����
     * 			��������� a[i] ������ b[j] ʱ��d[i][j] �������� 3 �����Сֵ��
     * 			d[i-1][j] + 1��ɾ�� a[i]���� ���� fxy -> fab �ı༭���� = fx -> fab �ı༭���� + 1
     * 			d[i][j-1] + 1������ b[j])�� ���� fxy -> fab �ı༭���� = fxyb -> fab �ı༭���� + 1 = fxy -> fa �ı༭���� + 1
     * 			d[i-1][j-1] + 1���� a[i] �滻Ϊ b[j]���� ���� fxy -> fab �ı༭���� = fxb -> fab �ı༭���� + 1 = fx -> fa �ı༭���� + 1
     * ״̬ת�Ʒ��̣� dp[i][j] = d[i-1][j-1] AND a.charAt(i) == b.charAt(j)
     * 				dp[i][j] = min(dp[i-1][j], dp[i][j-1], dp[i-1][j-1])+1 AND a.charAt(i) != b.charAt(j)
     * �߽�������         dp[i][0] = i, (�ַ���bΪ"",����Ҫɾ��i��)
     *              dp[0][j] = j, (�ַ���aΪ"", ����Ҫ���j��)
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
