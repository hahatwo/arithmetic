import java.util.ArrayList;
import java.util.List;

public class LongestWords {

	/*
	 * 给一个词典，找出其中所有最长的单词。 样例 在词典 { "dog", "google", "facebook",
	 * "internationalization", "blabla" } 中, 最长的单词集合为 ["internationalization"]
	 * 在词典
	 * { "like", "love", "hate", "yes" } 中，最长的单词集合为 ["like", "love", "hate"]
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
		int flag = 0; //标记最长的单词长度
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
