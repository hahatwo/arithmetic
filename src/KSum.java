
public class KSum {

	/*
	 * ����n����ͬ��������������k��k < = n���Լ�һ��Ŀ�����֡���
	 * ����n���������ҳ�K������ʹ����K�����ĺ͵���Ŀ�����֣������ж����ַ�����
	 * 
	 * �㷨˼�룺 ��̬�滮
	 * 			��Ӳ����������Ƚ����ƣ��������в�ͬ��
	 * 			����dp[i][j]Ϊ��i����֮�͵���j��������ϵ��ܷ�����������ôdp[k][target]�ͱ�ʾ���������
	 * 			
	 * 			״̬ת�Ʒ��̣�dp[i][j] = dp[i-1][j-A[0]] + dp[i-1][j-A[1]]+ dp[i-1][j-A[2]]+ ... + dp[i-1][j - A[n-1]],
	 * 						��dp[i][j] += dp[i-1][j-A[x]] (j >= A[x]);
	 * 			�߽�������	dp[0][0] = 1;
	 * 						dp[0][j] = 0;
	 * 						dp[i][0] = 0;(��Ϊ����Ϊn����ͬ��������)
	 * 
	 * A[x]�����������㣬ͬʱ��������������м��㣬�����������м����������ظ����ۼӡ�
	 * 1. �����������Ұ��м��㣬ÿһ�лᱻ�ظ��ؼӣ��ͻ����ظ����㡣�����������Ҽ��㣬��sum������ᱻ�������һ��ֵ�ļ���Ͳ���׼ȷ�ˡ�
	 * ���ԣ�ֻҪ�������м��㣬����˳���Ǵ������󣬼�ʹ����ֻ��һ����ά�����ǵı�sum����Ҳ���Ա��ֽྻ��	
	 * 			
     * @param A: An integer array
     * @param k: A positive integer (k <= length(A))
     * @param target: An integer
     * @return: An integer
     */
    public int kSum(int[] A, int k, int target) {
        // write your code here
    	if(k > A.length) {
    		return 0;
    	}
    	int[][] dp = new int[k+1][target+1];
    	/** ��ʼ�� **/
    	dp[0][0] = 1;
    	for(int i = 1; i < k+1; i++) {
    		dp[i][0] = 0;
    	}
    	for(int j = 1; j < target+1; j++) {
    		dp[0][j] = 0;
    	}
    	
    	/**for(int i = 1; i <= k; i++) {  ����ļ��㣬�����ظ����ۼӣ�����ɽ���Ĳ���ȷ
    		for(int j = 1; j <= target; j++) {
    			for(int x = 0; x < A.length; x++) {
    				if(j >= A[x]) {
    					dp[i][j] += dp[i-1][j-A[x]];
    				}
    			}
    		}
    	}**/
    	
    	for(int x = 0; x < A.length; x++) {
    		for(int i = k; i >= 1; i--) {
    			for(int j = target; j >= 1; j--) {
    				if(j >= A[x]) {
    					dp[i][j] += dp[i-1][j-A[x]];
    				}
    			}
    		}
    	}
    	return dp[k][target]; 
    }
   
}
