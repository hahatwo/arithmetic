public class LongestConsecutiveSequence {

	/**
	 * ����һ��δ������������飬�ҳ���������еĳ��ȡ� 
	 * ˵��
	 * Ҫ������㷨���Ӷ�ΪO(n)
	 * ���� 
	 * ��������[100, 4, 200, 1, 3, 2]������������������ [1, 2, 3, 4]���������󳤶� 4
	 * �㷨˼�룺 ��̬�滮
	 *          ����dp[i]Ϊ��������ǰi��������������еĳ���
	 *          ����һ����־tmp��ʼΪ1����num[i+1] == num[i]+1ʱ��tmp++;
	 *          ��num[i+1]!=num[i]+1ʱ����tmp������1,tmp=1;
	 *          ��״̬ת�Ʒ��� dp[i+1] = Max(dp[i], tmp)
	 *          �߽����� dp[0] = 0
	 *                  dp[i] = 1;
	 * @param num : A list of integers
	 * @return: An integer
	 */
	public int longestConsecutive(int[] num) {
		// write your code here
		if(num == null) {
			return 0;
		}
		if(num.length == 0 || num.length == 1) {
			return num.length;
		}
		sort(num);
		int[] dp = new int[num.length+1];
		dp[0] = 0;
		for(int i = 1; i < dp.length; i++) {
			dp[i] = 1;
		}
		dp[1] = 1;
		int tmp = 1;
		for(int i = 1; i < num.length; i++) {
			if(num[i] == num[i-1]+1)
				tmp++;
			else 
				tmp = 1;
			dp[i] = Math.max(dp[i-1], tmp);
		}
		
		return 0;
	}
	
	public void sort(int[] num) {
		quickSort(num, 0, num.length-1);
	}
	
	/**
	 * ��������
	 * @param num
	 */
	public void quickSort(int[] num, int start, int end) {
		int left = start;
		int right = end;
		int flag = num[left];
		while(left < right) {
			while(num[right] > flag && left < right) {
				right++;
			}
			if(left < right) {
				num[left] = num[right];
				left++;
			}
			
			while(num[left] < flag && left < right) {
				left++;
			}
			if(left < right) {
				num[right] = num[left];
				right--;
			}
		}
		num[left] = flag;
		quickSort(num, start, left-1);
		quickSort(num, right+1, end);
	}
}
