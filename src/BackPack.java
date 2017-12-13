public class BackPack {

	/**
	 * ��n����Ʒ����ѡ������Ʒװ�뱳���������װ���������豳���Ĵ�СΪm��ÿ����Ʒ�Ĵ�СΪA[i] 
	 * ע������ �㲻���Խ���Ʒ�����и 
	 * ����
	 * �����4����Ʒ[2, 3, 5, 7] ��������Ĵ�СΪ11������ѡ��[2, 3, 5]װ�뱳����������װ��10�Ŀռ䡣
	 * ��������Ĵ�СΪ12������ѡ��[2, 3, 7]װ�뱳����������װ��12�Ŀռ䡣 
	 * ������Ҫ���������װ���Ŀռ��С��
	 * @param m  : An integer m denotes the size of a backpack
	 * @param A  : Given n items with size A[i]
	 * @return: The maximum size
	 */
	public int backPack(int m, int[] A) {
		// write your code here
		if(m == 0 || A.length == 0) {
			return 0;
		}
		int maxBackpack = findMaxBackPack(m, A);
		return maxBackpack;
	}
	
	/**
	 * �㷨˼�룺 ��̬�滮
	 * 			����dp[i-1][j]��ʾǰi-1����Ʒ����������Ϊjʱ������װ�������ռ�
	 * 			��ôdp[i][j]�ͱ�ʾǰi����Ʒ����������Ϊjʱ������װ�������ռ�
	 * 			��ʱ�����ַ�����1)��i����Ʒ���뱳��   2)��i����Ʒ�����뱳��
	 * 			���״̬ת�Ʒ��̣� dp[i][j] = max(dp[i-1][j], dp[i-1][j-A[i]] + A[i])
	 * 			�߽�����:	dp[i][0] = 0
	 * 					 	dp[0][j] = 0
	 * @param m
	 * @param A
	 * @return
	 */
	private int findMaxBackPack(int m, int[] A) {
		int[][] dp = new int[A.length+1][m+1];
		/**��ʼ��**/
		for(int i = 0; i < dp.length; i++) {
			dp[i][0] = 0;
		}
		for(int j = 0; j < m+1; j++) {
			dp[0][j] = 0;
		}
		
		for(int i = 1; i <= A.length; i++) {
			for(int j = 1; j <= m; j++) {
				if(j >= A[i-1]) { //����A���±��Ǵ�0��ʼ
					dp[i][j] = Math.max(dp[i-1][j-A[i-1]] + A[i-1], dp[i-1][j]);
				} else{
				    dp[i][j] = dp[i-1][j];
				}
			}
		}
		return dp[A.length][m];
	}
}
