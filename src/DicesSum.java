import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DicesSum {
	 /**
     * �� n �����ӣ������������֮��Ϊ S������ Given n�����г����п��ܵ� S ֵ������Ӧ�ĸ��ʡ�
     * ���� n = 1������ [ [1, 0.17], [2, 0.17], [3, 0.17], [4, 0.17], [5, 0.17], [6, 0.17]]��
     * 
     * �㷨˼�룺 ���ֻ��һ�����ӣ���ô���Ŀ���������1��6��
     * 			����������ӣ���ô���ܵ���������2��12�������3�����ӣ���ô���ܵ�������3��18��
     * 			�����n�����ӣ���ô���ܵ�������n��6*n��
     * 			����һ�����飬ÿһ�д���ÿһ���������һ�д���һ�����ӵ�������ڶ��д����������ӵ�����������д����������ӵ����...
     * 			���Ǽ�����n�����ӣ��ܵĵ�����Ϊsum��f(n, sum)����n�������ӳ�����֮��Ϊsum���������������������sum >= n��
     * 			��ô��ǰ��n-1�����ӵ���������һ��������Sum-1��Sum-2��Sum-3��Sum-4��Sum-5��Sum-6�����������
     * 			�������Ǹ�����������������
	 *			��n-1��sum-1������n�������ӳ���1����ͬn-1�������ӳ���sum-1�������
	 *			��n-1��sum-2������n�������ӳ���2����ͬn-1�������ӳ���sum-2�������
	 *			��n-1��sum-3������n�������ӳ���3����ͬn-1�������ӳ���sum-3�������
	 *			��n-1��sum-4������n�������ӳ���4����ͬn-1�������ӳ���sum-4�������
	 *			��n-1��sum-5������n�������ӳ���5����ͬn-1�������ӳ���sum-5�������
	 *			��n-1��sum-6������n�������ӳ���6����ͬn-1�������ӳ���sum-6�������
	 *			��ôn�������ӳ���sum����������������������ӡ�
	 *			������Ϊf(n, sum) = f(n-1��sum-1) + f(n-1��sum-2) + f(n-1��sum-3) + f(n-1��sum-4) + f(n-1��sum-5) + f(n-1��sum-6)
	 *			(������쳲��������У�ֻ����쳲���������ͨ��Ϊ����f(n) = f(n-1) + f(n-2))
	 *			 ���� n = 1ʱ��f(1,1) = f(1,2) = f(1,3) = f(1,4) = f(1,5) = f(1,6) = 1
     *                      �� n = 2ʱ��	f(2,2) = f(1,1) = 1
     *                       f(2,3) = f(1,2) + f(1,1) = 2
     *                       ...
	 * 					    f(2,6) = f(1,5) + f(1,4) + f(1,3) + f(1,2) + f(1,1)
	 *						f(2,7) = f(1,6) + f(1,5) + f(1,4) + f(1,3) + f(1,2) + f(1,1) = 6
	 *			��n = 3ʱ��   f(3,3) = f(2,1) + f(2,2)
	 *						...
	 *			�������ƣ�     
	 *			�����ӽṹ��f(n, sum) = f(n-1��sum-1) + f(n-1��sum-2) + f(n-1��sum-3) + f(n-1��sum-4) + f(n-1��sum-5) + f(n-1��sum-6)
	 *          �߽�������    f(1,1) = f(1,2) = f(1,3) = f(1,4) = f(1,5) = f(1,6) = 1
	 *          ����֮��Ϊsum�ĸ��� probability = sum���������������/������ = f(n, sum)/n^6(n��6�η�)			 			
     * @param n an integer
     * @return a list of Map.Entry<sum, probability>
     */
    public List<Map.Entry<Integer, Double>> dicesSum(int n) {
        // Write your code here
        // Ps. new AbstractMap.SimpleEntry<Integer, Double>(sum, pro)
        // to create the pair
        List<Map.Entry<Integer, Double>> result = new ArrayList<Map.Entry<Integer, Double>>();
        if(n < 1) {
        	return result;
        }
    	long[][] temp = new long[n+1][6*n+1]; //��¼f(n, sum)���е��������������
    	for(int i = 1; i <= 6; i++) {   //��ʼ��
    		temp[1][i] = 1;
    	}
    	for(int j = 7; j <= 6*n; j++) {
    		temp[1][j] = 0;
    	}
    	
    	long x1, x2, x3, x4, x5, x6;
    	double probability = 0;
    	for(int i = 2; i <= n; i++) {
    		x1 = x2 = x3 = x4 = x5 = x6 = 0;
    		for(int j = i; j <= 6*i; j++) {
    			if(j - 1 > 0)
    				x1 = temp[i-1][j-1];
    			if(j - 2 > 0)
    				x2 = temp[i-1][j-2];
    			if(j - 3 > 0)
    				x3 = temp[i-1][j-3];
    			if(j - 4 > 0)
    				x4 = temp[i-1][j-4];
    			if(j - 5 > 0)
    				x5 = temp[i-1][j-5];
    			if(j - 6 > 0)
    				x6 = temp[i-1][j-6];
    			temp[i][j] = x1 + x2 + x3 + x4 + x5 + x6;
    		}
    	}
    	for(int i = n; i <= 6*n; i++) {
    		probability = temp[n][i] / Math.pow(6, n);
    		Map.Entry<Integer, Double> entry = new AbstractMap.SimpleEntry<Integer, Double>(i, probability);
    		result.add(entry);
    	}
        return result;
    }
}
