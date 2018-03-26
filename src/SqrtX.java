
public class SqrtX {

	 /**
	 * 实现 int sqrt(int x) 函数，计算并返回 x 的平方根。
     * @param x: An integer
     * @return: The sqrt of x
     */
    public int sqrt(int x) {
        // write your code here
    	if(x == 0 || x == 1) {
    		return x;
    	}
    	int left = 0; 
    	int right = x;
    	int middle = 0;
    	while(left + 1 < right) {
    		middle = left + (right-left)/2;
    		if(middle * middle == x) {
    			return middle;
    		} else if(middle * middle < x) {
    			left = middle;
    		} else {
    			right = middle;
    		}
    	}
    	return left;
    }
}
