
public class MaxSub {
	
	 /**
     * ����һ�����������һ������ k���ҳ� k �����ص�������ʹ�����ǵĺ����ÿ��������������������е�λ��Ӧ���������ġ�
     * �������ĺ͡�
     * 
     */

	public int maxSubArray(int[] nums, int k) {
        // write your code here
		
		if(k > nums.length){
		    return 0;
		}
		
		int min = Integer.MIN_VALUE;
		/** localMax[i][j]��¼����ǰj�����������鱻����Ϊi�飬 �ұ��������j����������  **/
		int[][] localMax = new int[k + 1][nums.length + 1];
		
		/** globalMax[i][j]��¼����ǰj�����������鱻����Ϊi�飬 ����һ��������j���������� **/
		int[][] globalMax = new int[k + 1][nums.length + 1];
		
		/**
		 * �����ӽṹ localMax[i][j] = Max(localMax[i][j - 1] , globalMax[i-1][j-1]) + nums[j]
		 *        globalMax[i][j] = Max(localMax[i][j], globalMax[i][j -1]);
		 * �߽������� localMax[0][j] = min (��ֵǰj����������Ϊ0��)
		 *            globalMax[0][j] = 0;
		 *        localMax[i][i-1] = min (����ǰ0����������Ϊi��)
		 *        globalMax[i][i-1] = 0
		 *        ��i = jʱ�� globalMax[i][j] = localMax[i][j]
		 */
		 for(int j = 0; j < nums.length + 1; j++){
			 localMax[0][j] = min;
			  globalMax[0][j] = 0;
		 }
		
		 for(int i = 1; i < k + 1; i++){
			 localMax[i][i-1] = min;
			 globalMax[i][i-1] = 0;
			 for(int j = i; j < nums.length + 1; j++){ //j<i,���ܱ����ֳ�i��������
				 localMax[i][j] = Math.max(localMax[i][j - 1], globalMax[i-1][j-1]) + nums[j-1];
				 if(i == j){
				     globalMax[i][j] = localMax[i][j];
				 }else{
				     globalMax[i][j] = Math.max(localMax[i][j], globalMax[i][j -1]);
				 }
			 }
		 }	 
		return globalMax[k][nums.length];
    }
	
	
	
}
