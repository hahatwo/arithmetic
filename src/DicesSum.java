import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public class DicesSum {
	 /**
     * 扔 n 个骰子，向上面的数字之和为 S。给定 Given n，请列出所有可能的 S 值及其相应的概率。
     * 给定 n = 1，返回 [ [1, 0.17], [2, 0.17], [3, 0.17], [4, 0.17], [5, 0.17], [6, 0.17]]。
     * 
     * 算法思想： 如果只有一个骰子，那么它的可能区间是1到6，
     * 			如果两个骰子，那么可能的区间是是2到12，如果是3个骰子，那么可能的区间是3到18，
     * 			如果是n个骰子，那么可能的区间是n到6*n。
     * 			创建一个数组，每一行代表每一种情况，第一行代表一个骰子的情况，第二行代表两个骰子的情况，第三行代表三个骰子的情况...
     * 			我们假设有n个骰子，总的点数和为sum，f(n, sum)代表n个骰子扔出数字之和为sum的排列情况的总数（其中sum >= n）
     * 			那么在前面n-1个骰子的情况，最后一个可以有Sum-1、Sum-2、Sum-3、Sum-4、Sum-5、Sum-6的六种情况，
     * 			而最后的那个骰子有下面的情况：
	 *			（n-1，sum-1）：第n个骰子扔出了1，等同n-1个骰子扔出了sum-1的情况。
	 *			（n-1，sum-2）：第n个骰子扔出了2，等同n-1个骰子扔出了sum-2的情况。
	 *			（n-1，sum-3）：第n个骰子扔出了3，等同n-1个骰子扔出了sum-3的情况。
	 *			（n-1，sum-4）：第n个骰子扔出了4，等同n-1个骰子扔出了sum-4的情况。
	 *			（n-1，sum-5）：第n个骰子扔出了5，等同n-1个骰子扔出了sum-5的情况。
	 *			（n-1，sum-6）：第n个骰子扔出了6，等同n-1个骰子扔出了sum-6的情况。
	 *			那么n个骰子扔出了sum的情况等于上面六种情况相加。
	 *			可以认为f(n, sum) = f(n-1，sum-1) + f(n-1，sum-2) + f(n-1，sum-3) + f(n-1，sum-4) + f(n-1，sum-5) + f(n-1，sum-6)
	 *			(类似于斐波那契数列，只不过斐波那契数列通过为两项f(n) = f(n-1) + f(n-2))
	 *			 其中 n = 1时：f(1,1) = f(1,2) = f(1,3) = f(1,4) = f(1,5) = f(1,6) = 1
     *                      而 n = 2时：	f(2,2) = f(1,1) = 1
     *                       f(2,3) = f(1,2) + f(1,1) = 2
     *                       ...
	 * 					    f(2,6) = f(1,5) + f(1,4) + f(1,3) + f(1,2) + f(1,1)
	 *						f(2,7) = f(1,6) + f(1,5) + f(1,4) + f(1,3) + f(1,2) + f(1,1) = 6
	 *			当n = 3时：   f(3,3) = f(2,1) + f(2,2)
	 *						...
	 *			依次类推：     
	 *			最优子结构：f(n, sum) = f(n-1，sum-1) + f(n-1，sum-2) + f(n-1，sum-3) + f(n-1，sum-4) + f(n-1，sum-5) + f(n-1，sum-6)
	 *          边界条件：    f(1,1) = f(1,2) = f(1,3) = f(1,4) = f(1,5) = f(1,6) = 1
	 *          数字之和为sum的概率 probability = sum的排列情况的总数/总排列 = f(n, sum)/n^6(n的6次方)			 			
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
    	long[][] temp = new long[n+1][6*n+1]; //记录f(n, sum)所有的排列情况的总数
    	for(int i = 1; i <= 6; i++) {   //初始化
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
