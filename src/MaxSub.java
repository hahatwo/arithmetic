
public class MaxSub {
	
	 /**
     * ����һ�����������һ������ k���ҳ� k �����ص�������ʹ�����ǵĺ����ÿ��������������������е�λ��Ӧ���������ġ�
     * �������ĺ͡�
     * 
     */

	public int maxSubArray(int[] nums, int k) {
        // write your code here
		
		/** localMax[i][j]��¼����ǰj�����������鱻����Ϊi�飬 �ұ��������j����������  **/
		int[][] localMax = new int[k + 1][nums.length + 1];
		
		/** globalMax[i][j]��¼����ǰj�����������鱻����Ϊi�飬 ����һ��������j���������� **/
		int[][] globalMax = new int[k + 1][nums.length + 1];
		
		/**
		 * �����ӽṹ localMax[i][j] = Max(localMax[i][j - 1])
		 * 
		 * 
		 */
		
		return 0;
    }
	
	
	
}
