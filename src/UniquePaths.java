
public class UniquePaths {

	/*
	 * ��һ�������˵�λ��һ�� m �� n ���������Ͻǡ�
	 * ������ÿһʱ��ֻ�����»��������ƶ�һ������������ͼ�ﵽ��������½ǡ�
	 * ���ж�������ͬ��·����
	 * ����
	 * ���� m = 3 �� n = 3, ���� 6.
	 * ���� m = 4 �� n = 5, ���� 35.
	 * 
	 * �㷨˼�룺 ��̬�滮 ������path[i][j]��ʾ�����Ͻǵ����i�е�j�еĵ��·����Ŀ��path[m][n]��Ϊ��������Ľ⡣
	 * ״̬ת�Ʒ��̣� path[i][j] = path[i-1][j] + path[i][j-1] (����i!=1&&j!=1,������ĳ����Դ���һ�������һ�����ַ�ʽ����)
	 * �߽������� path[1][1] = 1 (ע�⣺path[1][1]��ʾ�߼��ϵ���㣬��ʵ�������ʱӦ�ô�path[0][0]��ʼ��path[m-1][n-1]Ϊ�����ʵ�ʽ�) 
	 *          path[i][j] = path[i][j-1] (����i==1,�����ڵ�һ�У�ֻ��ѡ������һ��ﵽĳ��)
	 *          path[i][j] = path[i-1][j] (����j==1,�����ڵ�һ�У�ֻ��ѡ����ϱ�һ��ﵽĳ��)
     * @param m: positive integer (1 <= m <= 100)
     * @param n: positive integer (1 <= n <= 100)
     * @return: An integer
     */
    public int uniquePaths(int m, int n) {
        // write your code here
    	if(m == 0 || n == 0) {
    		return 0;
    	}
    	if(m == 1 || n == 1) {
    		return 1;
    	}
    	int[][] path = new int[m][n];
    	path[0][0] = 1;
    	for(int i = 1; i < m; i++) {
    		path[i][0] = path[i-1][0];
    	}
    	for(int j = 1; j < n; j++) {
    		path[0][j] = path[0][j-1];
    	}
    	for(int i = 1; i < m; i++) {
    		for(int j = 1; j < n; j++) {
    			path[i][j] = path[i-1][j] + path[i][j-1];
    		}
    	}
    	return path[m-1][n-1];
    }
}
