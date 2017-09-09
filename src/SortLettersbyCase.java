public class SortLettersbyCase {

	/**
	 * 给定一个只包含字母的字符串，按照先小写字母后大写字母的顺序进行排序。
	 * 
	 * @param chars
	 *            : The letter array you should sort by Case
	 * @return: void
	 */
	public void sortLetters(char[] chars) {
		// write your code here

		if (chars == null || chars.length == 0) {
			return;
		}
		boolean flag = true;
		int k = chars.length;
		while (flag) {
			flag = false;
			for (int i = 1; i < k; i++) {
				if (chars[i - 1] > chars[i]) {
					swap(chars, i - 1, i);
					flag = true;
				}
			}
			k--;
		}

		int index = 0; // 记录最后一个大写字符的位置
		for (int i = 0; i < chars.length; i++) {
			if (chars[i] < 97) {
				index = i;
			} else {
				break;
			}
		}

		reverse(chars, 0, index);
		reverse(chars, index + 1, chars.length - 1);
		reverse(chars, 0, chars.length - 1);

	}

	private void swap(char[] chars, int i, int j) {
		char temp = chars[i];
		chars[i] = chars[j];
		chars[j] = temp;
	}

	private void reverse(char[] chars, int left, int right) {
		char temp;
		for (int i = left; i <= left + (right - left) / 2; i++) {
			temp = chars[i];
			chars[i] = chars[right - i + left];
			chars[right - i + left] = temp;
		}
	}

}
