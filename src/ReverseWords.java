public class ReverseWords {

	/*
	 * ����һ���ַ����������ת�ַ����е�ÿ�����ʡ� ���ʵĹ��ɣ��޿ո���ĸ����һ������
	 * �����ַ����Ƿ����ǰ������β��ո񣿿��԰��������Ƿ�ת����ַ����ܰ��� ��δ����������ʼ�Ķ���ո��ڷ�ת�ַ����м�ո���ٵ�ֻ��һ��
	 * 
	 * @param s: A string
	 * 
	 * @return: A string
	 */
	public String reverseWords(String s) {
		// write your code here
		s = s.trim();
		if (s == "") {
			return "";
		}
		String[] array = s.split(" ");
		int len = array.length;
		String result = "";
		for(int i = len -1; i > 0; i--){
			result += array[i] + " ";
		}
		result += array[0];
		return result;
	}
}
