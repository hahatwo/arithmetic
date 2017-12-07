
public class CopyOfFindFirstBadVersion {

	/*
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
