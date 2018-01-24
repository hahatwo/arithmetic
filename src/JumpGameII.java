import java.util.Arrays;


public class JumpGameII {

	
	 /*
	  * ����һ���Ǹ��������飬�������λ������ĵ�һ��λ�á�
	  * �����е�ÿ��Ԫ�ش��������Ǹ�λ�ÿ�����Ծ����󳤶ȡ�������
	  * ���Ŀ����ʹ�����ٵ���Ծ����������������һ��λ�á�
	  * 
	  * ����
	  * ��������A = [2,3,1,1,4]�����ٵ����������һ��λ�õ���Ծ������2(�������±�0��һ���������±�1��Ȼ����3������������һ��λ�ã�һ����Ծ2��)
      * @param A: A list of integers
      * @return: An integer
      */
    public int jump(int[] A) {
        // write your code here
    	//int result = jump1(A);
    	if(A == null || A.length == 0) {
    		return -1;
    	}
    	int result = jump2(A);
    	return result;
    }
    
    /**
     * �㷨˼�룺 ̰���㷨�����赱ǰ(λ��i)�ܹ�����Զ����ΪcurEnd, 
     *          curFarther����[i, i+curEnd]��Щλ�÷�Χ���ܹ�������Զ���롣
     *        ÿ�ε�ǰλ��(i)��Ծ����Զλ��i+curEnd, ��ô�ͻᴥ��һ����Ծ����������curEnd����curFarthest��
     *        ��������Ĳ��裬ֱ����Ծ�����һ��λ��Ϊֹ��
     * @return
     */
    public int jump1(int[] A) {
    	int curEnd = 0;
    	int curFathest = 0;
    	int jumps = 0;  //��Ծ����
    	
    	for(int i = 0; i < A.length; i++) {
    		curFathest = Math.max(curFathest, i + A[i]);
    		if(i == curEnd) {
    			jumps++;
    			curEnd = curFathest;
    			if(curFathest >= A.length-1) 
    				return jumps;
    		}
    	}
    	return -1;
    }
    
    /**
     * �㷨˼�룺 ��̬�滮�� ����dp[i]Ϊ�����1��λ�õ���i��λ�õ���С��Ծ��������dp[A.length-1]��Ϊ���������
     * ״̬ת�Ʒ��̣� dp[i] = min(dp[j]+1) for 0 <=j <= i-1(������������ j+A[j] >= i) 
     * �߽������� dp[0] = 0
     * ����˵���� �������� A = [2,3,1,1,4]�� �У�       
     *          i=0,{2},dp[0]=0 (��ʼ���ڵ�һ��λ��) 
     *          i=1,{2,3},dp[1]=min(dp[0]+1)=1
     *          i=2,{2,3,1},dp[2]=min(dp[0]+1,dp[1]+1)=1
     *          i=3,{2,3,1,1}, dp[3] = min(dp[1]+1, dp[2]+1)=2 (��Ϊj=0ʱ��A[j]+j<i,���Բ���Ҫ��dp[0]+1�Ƚ�)
     * @param A
     * @return
     */
    public int jump2(int[] A) {
    	int[] dp = new int[A.length];
    	Arrays.fill(dp, A.length-1); //��ʼ��dp����
    	dp[0] = 0;
    	for(int i = 1; i < A.length; i++) {
    		for(int j = 0; j <= i-1; j++) {
    			if(A[j] + j >= i) {
    				dp[i] = Math.min(dp[i], dp[j]+1);
    			}
    		}
    	}
    	return dp[A.length-1];
    }
}
