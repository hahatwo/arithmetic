public class CompareStrings {

	/*
	 * �Ƚ������ַ���A��B��ȷ��A���Ƿ����B�����е��ַ����ַ���A��B�е��ַ����� ��д��ĸ �㷨˼�룺 ����һ��int[] ����
	 * arr[26]��ʼ��Ϊ0,��¼A��ÿ���ַ����ֵĴ���,��B��ÿ����һ��ĳ���ַ�����Ӧ��arr[i]��value��һ ��ĳ��arr[i] <
	 * 0ʱ��֤��B�е�ĳ���ַ�����A�У����������ֵĴ�������A�и��࣬��ôA������B
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
