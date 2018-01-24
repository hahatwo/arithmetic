
public class ClimbingStairs {

	/**
	 * ������������¥�ݣ���Ҫn������ܵ��ﶥ������ÿ����ֻ����һ�����������������ж����ֲ�ͬ�ķ�������¥������
	 * ����
	 * ����n=3��1+1+1=1+2=2+1=3������3�ֲ�ͬ�ķ���
	 * ���� 3
	 * 
	 * �㷨˼�룺 dp[i]��ʾ¥��̨����Ϊi,����¥�����õķ�������
	 * ״̬ת�Ʒ��̣� dp[i] = dp[i-1] + dp[i-2];
	 * �߽������� dp[0] = 0, dp[1] = 1, dp[2] = 2;
     * @param n: An integer
     * @return: An integer
     */
    public int climbStairs(int n) {
        // write your code here
        if(n == 0) {
            return 0;
        }
        if(n == 1) {
            return 1;
        }
        if(n == 2) {
            return 2;
        }
        int[] dp = new int[n+1];
        dp[0] = 0;
        dp[1] = 1;
        dp[2] = 2;
        for(int i = 3; i <= n; i++) {
        	dp[i] = dp[i-1] + dp[i-2];
        }
        return dp[n];
    }
}
