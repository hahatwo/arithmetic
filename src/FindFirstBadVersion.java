
public class FindFirstBadVersion {

	/*
     * 代码库的版本号是从 1 到 n的整数。
     * 某一天，有人提交了错误版本的代码，因此造成自身及之后版本的代码在单元测试中均出错。请找出第一个错误的版本号。
     * 你可以通过 isBadVersion 的接口来判断版本号version是否在单元测试中出错，具体接口详情和调用方法请见代码的注释部分。
     * 样例
     * 给出 n=5
     * 调用isBadVersion(3)，得到false
     * 调用isBadVersion(5)，得到true
     * 调用isBadVersion(4)，得到true
     * 此时我们可以断定4是第一个错误的版本号
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
