import java.util.ArrayList;
import java.util.List;

public class LongestWords {

	/*
	 * ��һ���ʵ䣬�ҳ�����������ĵ��ʡ� ���� �ڴʵ� { "dog", "google", "facebook",
	 * "internationalization", "blabla" } ��, ��ĵ��ʼ���Ϊ ["internationalization"]
	 * �ڴʵ�
	 * { "like", "love", "hate", "yes" } �У���ĵ��ʼ���Ϊ ["like", "love", "hate"]
	 * @param dictionary: an array of strings
	 * 
	 * @return: an arraylist of strings
	 */
	public List<String> longestWords(String[] dictionary) {
		// write your code here
		List<String> result = new ArrayList<String>();
		if(dictionary == null || dictionary.length == 0) {
			return result;
		}
		int flag = 0; //�����ĵ��ʳ���
		for(int i = 0; i < dictionary.length; i++) {
			if(dictionary[i].length() < flag) {
				continue;
			} else if(dictionary[i].length() == flag) {
				result.add(dictionary[i]);
			} else {
				result.clear();
				result.add(dictionary[i]);
				flag = dictionary[i].length();
			}
		}
		return result;
	}
}
