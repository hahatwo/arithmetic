public class CompareStrings {

	/*
	 * 比较两个字符串A和B，确定A中是否包含B中所有的字符。字符串A和B中的字符都是 大写字母 算法思想： 给出一个int[] 数组
	 * arr[26]初始化为0,记录A中每个字符出现的次数,当B中每出现一次某个字符，对应的arr[i]的value减一 当某个arr[i] <
	 * 0时，证明B中的某个字符不在A中，或者它出现的次数比在A中更多，那么A不包含B
	 * 
	 * @param A: A string
	 * 
	 * @param B: A string
	 * 
	 * @return: if string A contains all of the characters in B return true else
	 * return false
	 */
	public boolean compareStrings(String A, String B) {
		// write your code here
		if (B.length() > A.length()) {
			return false;
		}
		int[] arr = new int[26];
		for (int i = 0; i < 26; i++) {
			arr[i] = 0;
		}
		for (int i = 0; i < A.length(); i++) {
			arr[A.charAt(i) - 'A']++;
		}

		for (int i = 0; i < B.length(); i++) {
			arr[B.charAt(i) - 'A']--;
			if (arr[B.charAt(i) - 'A'] < 0) {
				return false;
			}
		}
		return true;
	}
}
