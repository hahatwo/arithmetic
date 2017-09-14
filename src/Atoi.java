public class Atoi {

	/*
	 * 实现atoi这个函数，将一个字符串转换为整数。如果没有合法的整数，返回0。如果整数超出了32位整数的范围，返回INT_MAX(2147483647)
	 * 如果是正整数，或者INT_MIN(-2147483648)如果是负整数。
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
