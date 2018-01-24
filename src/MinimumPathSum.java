
public class MinimumPathSum {

	/*
	 * ����һ��ֻ���Ǹ�������m*n�����ҵ�һ�������Ͻǵ����½ǵĿ���ʹ���ֺ���С��·����
	 * ע������
	 * ����ͬһʱ��ֻ�����»��������ƶ�һ��
	 * 
	 * �㷨˼�룺 ��̬�滮�� grid[i][j]��ʾ��i�е�j�е����֣�
	 *          minSum[i][j]��ʾ��i�е�j�����ֵ������½ǵ����ֺ���С·��,
	 *          minSum[1][1]�ͱ�ʾ�����Ͻǵ����½ǵĿ���ʹ���ֺ���С��·��
	 *          ��ע�⣺�˴���minMax[1][1]��ʾ�߼��ϴ����Ͻǵ����½���С·���ͣ�������ʵ������ʱ���0��ʼ��
     *          ��Ϊgrid[0][0]��ʾ���Ͻǣ�
	 * ״̬ת�Ʒ��̣� 
	 *        minSum[i][j] = Math.min(minSum[i+1][j],minSum[i][j+1])+grid[i][j] (����j!=n����Ϊ��j!=nʱ���������»��������ƶ�һ��)��
	 *        minSum[i][j] = minSum[i+1][j] + grid[i][j] (����j=n����Ϊ��j=nʱ��ֻ�������ƶ�һ��)��
	 * �߽�������
	 *        minSum[i][j] = grid[i][j] (����i==m && j==n,����ʱ���־�Ϊ�����½ǵ�����)��
	 *        minSum[i][j] = grid[i][j+1] (����i==m && j!=n, ��ʱλ�����һ�У�ֻ�������ƶ�һ��)
     * @param grid: a list of lists of integers
     * @return: An integer, minimizes the sum of all numbers along its path
     */
    public int minPathSum(int[][] grid) {
        // write your code here
    	if(grid.length == 0 || grid == null) {
    		return 0;
    	}
    	
    	int[][] minSum = new int[grid.length][grid[0].length];
    	for(int i = grid.length-1; i>=0; i--) {
    		for(int j = grid[i].length-1; j>=0; j--) {
    			if(i == grid.length-1) {
    				if(j == grid[i].length-1) {
    					minSum[i][j] = grid[i][j];
    				} else {
    					minSum[i][j] = minSum[i][j+1] + grid[i][j];
    				}
    			} else {
    				if(j == grid[i].length-1) {
    					minSum[i][j] = minSum[i+1][j] + grid[i][j];
    				} else {
    					minSum[i][j] = Math.min(minSum[i+1][j],minSum[i][j+1]) + grid[i][j];
    				}
    			}
    		}
    	}
    	return minSum[0][0];
    }
	
}
