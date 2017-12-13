import java.util.ArrayList;
import java.util.List;


public class MinAdjustmentCost {

	/*
	 * ��һ���������飬����ÿ�����Ĵ�С��ʹ�����ڵ��������Ĳ����һ������������target��
	 * ����ÿ�����Ĵ���Ϊ����ǰ��Ĳ�ľ���ֵ�����������֮����С�Ƕ��١�
	 * ����
	 * ��������[1, 4, 2, 3]��target=1����С�ĵ��������ǵ���Ϊ[2, 3, 2, 3]����������֮����2������2��
	 * ע������
	 * ����Լ���������ÿ��������������������С�ڵ���100��
     * @param A: An integer array
     * @param target: An integer
     * @return: An integer
     */
    public int MinAdjustmentCost(List<Integer> A, int target) {
        // write your code here
    	if(A.size() == 0) {
    		return 0;
    	}
    	List<Integer> B = new ArrayList<Integer>(A);
    	//int minCost = findMinAdjustmentCost(A, target);
    	int minCost = findMinAdjustmentCostII(A, B, target, 0);
    	return minCost;
    }
    
    /**
     * �㷨˼�룺 ��̬�滮��һ��Ԫ��һ��Ԫ�صĵ�����������Ҫ������Ԫ�صĲ�ֵ������ֻ��ǰһ������Ԫ�ص�ֵ�йأ�����ֻ��Ҫ��¼��һ��������ֵ�Ϳ��ԡ�
     * ��ô��dp[i][j]��ʾ��������i����ʱ����ʱ����i����������ֵj,Ϊ���ۺ���С��
     * (��Ϊ��Ŀ��������ÿ��������������������С�ڵ���100����ômin <= j <= max,�˴�min=1,max=100)
     * dp[i+1][k]��ʾ��������i+1����ʱ����ʱ����i+1����������ֵk,Ϊ���ۺ���С��(min <= k <= max)
     * ͬʱj��k�ض������ϵ|k-j|<=target, ��ô j-target <= min <= k <=max <= j+target 
     * ״̬ת�Ʒ��̣�dp[i+1][k] = dp[i][j] + abs(k-A[i+1])
     * �߽������� dp[0][j] = abs(j-A[0])(�ӵ�һ������ʼ��min��max����)
     * ���ս�Ϊdp[A.size()][i](1<=i<A.size())����С��ֵ.
     * @param A
     * @param target
     * @return
     */
    private int findMinAdjustmentCost(List<Integer> A, int target) {
    	int size = A.size();
    	int min = 1;
    	int max = 100;
    	int[][] dp = new int[size][max+1];
    	for(int i = 0; i < size; i++) {
    		for(int j = min; j <= max; j++) {
    			dp[i][j] = Integer.MAX_VALUE;
    			if(i == 0) {	//�߽�����
    				dp[i][j] = Math.abs(j - A.get(i));
    			} else {
    				 /**����������|k-j|<=target������j��k���ж��ֿ���ȡֵ������ѭ������жϣ�
    				  * k��ʾǰһ������j��ʾ���ڵ���������jȷ������ôk��ȡֵ������һ����Χ�ڣ�
    				  * ��Ϊ��ֵ���ܳ���target����ôѭ����⣬�ҵ�����������һϵ��k��
    				  * ͨ��һϵ��k��ֵ���dp[i][j]��С����**/
    				for(int k = Math.max(min, j-target); k <= Math.min(max, j+target); k++) {
    					dp[i][j] = Math.min(dp[i][j], dp[i-1][k] + Math.abs(j-A.get(i)));
    				}
    			}
    		}
    	}
    	
    	int minCost = Integer.MAX_VALUE;
        for (int i = min; i <=max; i++) {
            min = Math.min(minCost, dp[dp.length - 1][i]);
        }
        return minCost;
    }
    
   /**
    * �㷨˼��2�� �ݹ����(ֻ�ʺ�С���ݣ���������ʱ�ᳬʱ)
    * 			����������ȱ�����������ͬ���ǣ����ݹ��������ף���������ȱ�����������ʱ�ͷ����ˡ�
    * 			1.B�տ�ʼ��A���������һ������B������ĳ����������֮���ֵ��
    * 			2.��һ��������Ϊmin-max��ȫ��������һ�飬����ĳ��ʱ�̵�һ��������Ϊi,
    * 			3.�ڶ���������Ϊ��������|j-i|<target��min'-max'��ȫ������һ��,��ʱ�ڶ�����������j
    * 			4.�ڶ���������Ϊ��������|k-j|<target��min'-max'��ȫ������һ��,��ʱ��������������k
    * 			  ...
    * 			n. һֱ����ĩβ��A.size()������
    * 			n+1. ���ݣ���B���ԭʼֵ�������С��������
    * 			Ȼ���һ��������Ϊi+1���������������ٵݹ����һ��
    * 			...
    * 			ֱ����һ��������Ϊmax,����
    * 		
    * @param A
    * @param B
    * @param target
    * @param index
    * @return
    */
    private int findMinAdjustmentCostII(List<Integer> A, List<Integer> B, int target, int index) {
    	int size = A.size();
    	int min = 1;
    	int max = 100;
    	int cost = 0;
    	int minCost = Integer.MAX_VALUE;
    	if(index >= size) {
    		return 0;
    	}
    	
    	for(int i = min; i <= max; i++) {
    		if(index != 0 && (Math.abs(i-B.get(index-1)) > target)) {
    			continue;
    		}
    		B.set(index, i);
    		cost = Math.abs(i-A.get(index));
    		cost += findMinAdjustmentCostII(A, B, target, index+1);
    		minCost = Math.min(cost, minCost);
    		B.set(index, A.get(index)); //����
    	}
    	return minCost;
    }
    
}
