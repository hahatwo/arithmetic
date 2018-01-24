
public class Triangle {

	 /*
	 * ����һ�����������Σ��ҵ��Ӷ������ײ�����С·���͡�ÿһ�������ƶ�������һ�е����������ϡ�
	 * ����
	 * ���磬�����������������Σ�
	 * [
     *   [2],
     *  [3,4],
     * [6,5,7],
     *[4,1,8,3]
     * ]
     * �Ӷ����ײ�����С·����Ϊ11 ( 2 + 3 + 5 + 1 = 11)�� 
     * 
     * �㷨˼�룺 ��̬�滮�� triangle[i][j]��ʾ��i�е�j������
     *          minSum[i][j]��ʾ��i�е�j�������ײ�����С·����
     *          minSum[1][1]���ʾ���������δӶ������ײ�����С·����
     *          ��ע�⣺�˴���minMax[1][1]��ʾ�߼��ϵ����������δӶ������ײ�����С·���ͣ�������ʵ������ʱ���0��ʼ��
     *          ��Ϊtriangle[0][0]��ʾ���������ζ�����
     * ״̬ת�Ʒ��̣� minSum[i][j] = Math.min(minSum[i+1][j], minSum[i+1][j+1])+triangle[i][j]
     * �߽������� minSum[i][j] = triangel[i][j] (��i==triangle.length-1ʱ������i���������εײ�ʱ)
     * @param triangle: a list of lists of integers
     * @return: An integer, minimum path sum
     */
    public int minimumTotal(int[][] triangle) {
        // write your code here
    	if(triangle == null || triangle.length == 0) {
    		return 0;
    	}
    	int[][] minSum = new int[triangle.length][triangle.length];
    	
    	for(int i = triangle.length-1; i>=0; i--) {
    		for(int j = 0; j < triangle[i].length; j++) {
    			if(i == triangle.length-1) {
        			minSum[i][j] = triangle[i][j];
        		} else {
        			minSum[i][j] = Math.min(minSum[i+1][j], minSum[i+1][j+1]) + triangle[i][j];
        		}
    		}	
    	}
    	return minSum[0][0];
    }
	
}
