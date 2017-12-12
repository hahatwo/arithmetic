
public class FindFirstBadVersion {

	/*
     * �����İ汾���Ǵ� 1 �� n��������
     * ĳһ�죬�����ύ�˴���汾�Ĵ��룬����������֮��汾�Ĵ����ڵ�Ԫ�����о��������ҳ���һ������İ汾�š�
     * �����ͨ�� isBadVersion �Ľӿ����жϰ汾��version�Ƿ��ڵ�Ԫ�����г�������ӿ�����͵��÷�����������ע�Ͳ��֡�
     * ����
     * ���� n=5
     * ����isBadVersion(3)���õ�false
     * ����isBadVersion(5)���õ�true
     * ����isBadVersion(4)���õ�true
     * ��ʱ���ǿ��Զ϶�4�ǵ�һ������İ汾��
     * @param n: An integer
     * @return: An integer which is the first bad version.
     */
    public int findFirstBadVersion(int n) {
        // write your code here
    	if(n < 0)
    		return n;
    	return find(1, n);	
    }
    
    public int find(int left, int right) {
    	int mid = 0;
    	while(left < right) {
    		mid = (right - left)/2 + left;
    		 
    		if (isBadVersion(mid) == true) {
    			if((mid - 1) >= 1 && isBadVersion(mid - 1) == false) {
    				return mid;
    			} else {
    				right = right - 1;
    			}
    		} else if(isBadVersion(mid) == false) {
    			left = left + 1;
    		} 
    	}
    	if(isBadVersion(left) == true)
    		return left;
    	return -1;
    }
    
    private boolean isBadVersion(int n) {
        return false;
    }
}
