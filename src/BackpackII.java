
public class BackpackII {

	
	/**
     * ����n����Ʒ�����A[i]�����ֵV[i]��������װ��һ����СΪm�ı����������װ����ܼ�ֵ�ж��
	 * ע������
	 * A[i], V[i], n, m��Ϊ�������㲻�ܽ���Ʒ�����з֡�������ѡ����Ʒ�������ҪС�ڵ��ڸ�����m��
	 * ����
	 * ������Ʒ���[2, 3, 5, 7]�Ͷ�Ӧ�ļ�ֵ[1, 5, 2, 4], ���豳����СΪ10�Ļ�������ܹ�װ��ļ�ֵΪ9��
     * @param m: An integer m denotes the size of a backpack
     * @param A: Given n items with size A[i]
     * @param V: Given n items with value V[i]
     * @return: The maximum value
     */
    public int backPackII(int m, int[] A, int[] V) {
        // write your code here
    	if(m == 0 || A == null || A.length == 0 || V == null || V.length == 0) {
    		return 0;
    	}
        int[][] dp = new int[A.length+1][m+1];
        for(int i = 1; i < A.length+1; i++) {
        	for(int j = 1; j < m+1; j++) {
        		if(j >= A[i-1]) {
        			dp[i][j] = Math.max(dp[i-1][j-A[i-1]]+V[i-1], dp[i-1][j]);
        		} else {
        			dp[i][j] = dp[i-1][j];
        		}
        	}
        }
    	return dp[A.length][m];
    }
	
    /**
     * �Ż��㷨�� �ռ�ΪO(m)
     *          dp[i][j] = Math.max(dp[i-1][j-A[i-1]]+V[i-1], dp[i-1][j])(i=1->A.length; j=0->m)
     *          ���Կ���i��i-1�йأ��������޹�
     *          ��dp[j]Ϊ����Ϊjʱ,������õ�����ֵ
     *          dp[j] = Math.max(dp[j-A[i]]+V[i], dp[j-1])(i=1->A.length, j=m->0)
     *          for(i=1->A.length)
     *                 for(j=m->0) 
     *              dp[j] = Math.max(dp[j-A[i]]+V[i], dp[j])��j>=A[i]��
     *            
     *          ÿ����һ�ζ��ܹ�ȷ��dp[k]��ֵ(k=1->m),dp[k]��ֵ�����ٸı�
     *          ����˵����
     *          1. ��������һ��ʱ��j���mһֱ��1�� ����ܹ��ó�dp[j=m->1]��ֵ��
     *             ���ǲ�������ֻ��dp[0]...dp[min(A[i]-1)]��������0, dp[min(A[i])](dp[min(A[i])]=dp[0] or dp[0]+V[i],������ֵ���ᷢ���仯)��ֵ�ں��������в����ٸı䣻
     *          2. �������ڶ���ʱ��j���mһֱ��1�� ����ܹ��ó�dp[j=m->1]��ֵ��
     *             ���ǲ�������ֻ��dp[min(A[i]],dp[min(A[i]+1]...dp[secondMin(A[i])](dp[secondMin(A[i]]=dp[min(A[i]] or dp[min(A[i]]+V[i],������ֵ���ᷢ���仯)��ֵ�ں��������в����ٸı䣻
     *          3. ������������ʱ��j���mһֱ��1�� ����ܹ��ó�dp[j=m->1]��ֵ��
     *             ���ǲ�������ֻ��dp[min(A[i]],dp[min(A[i]+1]...dp[thirdMin(A[i])]��ֵ�ں��������в����ٸı䣻   
     *             ...
     *          n. ��������n��ʱ��dp[0]...dp[m]��ֵ�����ٸı䣬�õ����ս�
     *           
     *          
     * @param m
     * @param A
     * @param V
     * @return
     */
     public int opbackPackII(int m, int[] A, int[] V) {
         // write your code here
         if(m == 0 || A == null || A.length == 0 || V == null || V.length == 0) {
     		return 0;
     	}
         int[] dp = new int[m+1];
         for(int i = 0; i < A.length; i++) {
         	for(int j = m; j > 0; j--) {
         		if(j >= A[i]) {
         			dp[j] = Math.max(dp[j-A[i]]+V[i], dp[j]);
         		} 
         	}
         }
     	return dp[m];
     }
}
