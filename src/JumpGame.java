
public class JumpGame {

	
	/*
	 * ����һ���Ǹ��������飬�������λ������ĵ�һ��λ�á�������
	 * �����е�ÿ��Ԫ�ش��������Ǹ�λ�ÿ�����Ծ����󳤶ȡ���������
	 * �ж����Ƿ��ܵ�����������һ��λ�á�
	 * ����
	 * A = [2,3,1,1,4]������ true.
	 * A = [3,2,1,0,4]������ false.
	 * 
	 * �㷨˼�룺 ��̬�滮������dp[i] ��ʾ�������һ��λ���Ƿ��ܹ��ﵽ��i��λ��
	 *          dp[i] = true��ʾ�ܹ������i��λ��
	 *          dp[i] = false��ʾ���ܵ����i��λ��
	 *          ��dp[A.length-1]��Ϊ���Ľ��
	 * ״̬ת�Ʒ��̣� dp[j] = true (����Math.max(0, i-A[i])<= j <= Math.min(len-1, i+A[i]) && dp[i]==true)
	 * �߽������� dp[0] = true
     * @param A: A list of integers
     * @return: A boolean
     */
    public boolean canJump(int[] A) {
        // write your code here
    	if(A == null || A.length == 0) {
    		return false;
    	}
    	boolean[] dp = new boolean[A.length];
    	dp[0] = true;
    	int len = A.length;
    	for(int i = 0; i < len; i++) {
    		if(dp[i]) {
    			for(int j = Math.max(0, i-A[i]); j <= Math.min(len-1, i+A[i]); j++) {
    				dp[j] = true;
    				if(dp[len-1]) {
    					return true;
    				}
    			}
    		} else {
    			continue;
    		}
    	}
    	return false;
    }
}
