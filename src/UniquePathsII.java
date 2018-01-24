
public class UniquePathsII {

	/*
	 * "��ͬ��·��" �ĸ������⣺
	 * ���ڿ������������ϰ�����������ж�������ͬ��·����
	 * �����е��ϰ��Ϳ�λ�÷ֱ��� 1 �� 0 ����ʾ��
	 * ����
	 * ������ʾ��3x3����������һ���ϰ��
	 * [
	 * [0,0,0],
	 * [0,1,0],
	 * [0,0,0]
	 * ]
	 * һ����2����ͬ��·�������Ͻǵ����½ǡ�
	 * * �㷨˼�룺 ��̬�滮 ������path[i][j]��ʾ�����Ͻǵ����i�е�j�еĵ��·����Ŀ��path[m][n]��Ϊ��������Ľ⡣
	 * ״̬ת�Ʒ��̣� path[i][j] = path[i-1][j] + path[i][j-1] (����i!=1&&j!=1,������ĳ����Դ���һ�������һ�����ַ�ʽ����)
	 * �߽������� path[1][1] = 1 (ע�⣺path[1][1]��ʾ�߼��ϵ���㣬��ʵ�������ʱӦ�ô�path[0][0]��ʼ��path[m-1][n-1]Ϊ�����ʵ�ʽ�) 
	 *          path[i][j] = path[i][j-1] (����i==1,�����ڵ�һ�У�ֻ��ѡ������һ��ﵽĳ��)
	 *          path[i][j] = path[i-1][j] (����j==1,�����ڵ�һ�У�ֻ��ѡ����ϱ�һ��ﵽĳ��)
	 *          path[i][j] = 0(����obstacleGrid[i][j]==1,���õ㴦�����ϰ��û��·���ܹ��ﵽ��)
     * @param obstacleGrid: A list of lists of integers
     * @return: An integer
     */
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        // write your code here
    	if(obstacleGrid == null || obstacleGrid.length == 0 || obstacleGrid[0].length == 0) {
    		return 0;
    	}
    	if(obstacleGrid[0][0] == 1) { //���Ϊ�ϰ���
    		return 0;
    	}
    	
    	int[][] path = new int[obstacleGrid.length][obstacleGrid[0].length];
    	path[0][0] = 1;
    	for(int i = 1; i < path.length; i++) {
    		if(obstacleGrid[i][0] == 1)
    			path[i][0] = 0;
    		else 
    		    path[i][0] = path[i-1][0];
    	}
    	for(int j = 1; j < path[0].length; j++) {
    		if(obstacleGrid[0][j] == 1)
    			path[0][j] = 0;
    		else 
    		    path[0][j] = path[0][j-1];
    	}
    	
    	for(int i = 1; i < path.length; i++) {
    		for(int j = 1; j < path[0].length; j++) {
    			if(obstacleGrid[i][j] == 1) {
    				path[i][j] = 0;
    			} else {
    				path[i][j] = path[i-1][j] + path[i][j-1];
    			}
    		}
    	}
    	return path[obstacleGrid.length-1][obstacleGrid[0].length-1];
    }
}
