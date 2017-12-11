import java.util.ArrayList;
import java.util.List;


public class KSumII {

	/*
	 * Given n unique integers, number k (1<=k<=n) and target.
	 * Find all possible k integers where their sum is target.
	 * 样例
	 * 给出[1,2,3,4]，k=2， target=5，返回 [[1,4],[2,3]]
     * @param A: an integer array
     * @param k: a postive integer <= length(A)
     * @param targer: an integer
     * @return: A list of lists of integer
     */
    public List<List<Integer>> kSumII(int[] A, int k, int targer) {
        // write your code here
    	List<List<Integer>> result = new ArrayList<List<Integer>>();
    	if(k <= 0 || A.length == 0) {
    		return result;
    	}
    	List<Integer> element = new ArrayList<Integer>();
    	searchResult(A, k, targer, result, element, 0);
    	return result;
    }
    
    /**
     * 算法思想: 深度优先搜索，类似N皇后问题
     * @param A
     * @param k
     * @param target
     * @param list
     */
    private void searchResult(int[] A, int k, int target, List<List<Integer>> result, List<Integer> element, int index) {
    	if(isValid(element, k, target)) {
    		List<Integer> list = new ArrayList<Integer>(element);
    		result.add(list);
    		return;
    	}
    	if(element.size() > k) {
    		return;
    	}
    	int sum = 0;
    	for(int i = index; i < A.length; i++) {
    		sum = calculateSum(element);
    		if(sum + A[i] > target) {
    			continue;
    		}
    		element.add(A[i]);
    		searchResult(A, k, target, result, element, i+1);
    		element.remove(element.size()-1);
    	}
    	
    }
    
    private boolean isValid(List<Integer> element, int k, int target) {
    	if(element.size() != k) {
    		return false;
    	} else {
    		int sum = calculateSum(element);
    		if(sum != target) {
    			return false;
    		}
    	}
    	return true;
    }
    
    private int calculateSum(List<Integer> list) {
    	int sum = 0;
    	if(list != null) {
    		for(Integer ele : list) {
    			sum += ele;
    		}
    	}
		return sum;
    }
}
