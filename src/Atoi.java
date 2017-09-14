public class Atoi {

	/*
	 * ʵ��atoi�����������һ���ַ���ת��Ϊ���������û�кϷ�������������0���������������32λ�����ķ�Χ������INT_MAX(2147483647)
	 * �����������������INT_MIN(-2147483648)����Ǹ�������
	 * 
	 * @param str: A string
	 * 
	 * @return: An integer
	 */
	public int atoi(String str) {
		// write your code here

		if (str == null || str.length() == 0) {
			return 0;
		}
		double result = 0;
		char symbol = '+';
		str = str.trim();
		int index = 0;
		if (str.charAt(index) == '-') {
			symbol = '-';
			index++;
		} else if (str.charAt(index) == '+') {
			index++;
		}

		for (int i = index; i < str.length(); i++) {
			if (str.charAt(i) >= '0' && str.charAt(i) <= '9') {
				result = result * 10 + str.charAt(i) - '0';
			} else {
				break;
			}
		}

		if (symbol == '-') {
			result = -result;
		}

		if (result > Integer.MAX_VALUE) {
			return Integer.MAX_VALUE;
		}
		if (result < Integer.MIN_VALUE){
			return Integer.MIN_VALUE;
		}

		return (int) result;
	}
}
