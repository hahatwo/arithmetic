
public class LongestCommonSubstring {
	 /*
     * @param A: A string
     * @param B: A string
     * @return: the length of the longest common substring.
     */
     public int longestCommonSubstring(String A, String B) {
        // write your code here
    	if(A.length() == 0 || B.length() == 0) {
    		return 0;
    	}
    	//��ʼ��L
    	int[][] L = new int[A.length()+1][B.length()+1];
    	for(int i = 0; i <= B.length(); i++) {
    		L[0][i] = 0;
    	}
    	for(int j = 0; j <= A.length(); j++) {
    		L[0][j] = 0;
    	}
    	return calculateLongestCommonSubString(L, A, B);
    }
    
    /**
     * �㷨˼�룺 ��̬�滮
     * ����L[i][j]ΪA������Ϊi��β�����ַ�����B������Ϊj��β�����ַ�����������Ӵ�
     * �����ӽṹ�� L[i+1][j+1] = A.charAt(i+1) == B.charAt(j+1)? L[i][j]+1 : 0
     * �߽�������  L[0][j] = A.charAt(0) == B.charAt(j)? 1 �� 0;
     * 			 L[i][0] = A.charAt(i) == B.charAt(0)? 1 : 0;
     * @param L
     * @param A
     * @param B
     * @return
     */
    private int calculateLongestCommonSubString(int[][] L, String A, String B) {
    	int longestCount = Integer.MIN_VALUE; //��¼��Ӵ��ĳ���
    	for(int i = 1; i <= A.length(); i++) {
    		for(int j = 1; j <= B.length(); j++) {
    			if(A.charAt(i-1) == B.charAt(j-1))
    				L[i][j] = L[i-1][j-1] + 1;
    			else L[i][j] = 0;
    			if(longestCount < L[i][j]) {
    				longestCount = L[i][j];
    			}
    		}
    	}
    	return longestCount;
    }
}
