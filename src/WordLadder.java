import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.print.attribute.standard.MediaSize.ISO;

public class WordLadder {

	/*
	 * 给出两个单词（start和end）和一个字典，找到从start到end的最短转换序列 比如： 每次只能改变一个字母。
	 * 变换过程中的中间单词必须在字典中出现。 注意事项 如果没有转换序列则返回0。 所有单词具有相同的长度。 所有单词都只包含小写字母。 样例
	 * 给出数据如下： start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
	 * 一个最短的变换序列是 "hit" -> "hot" -> "dot" -> "dog" -> "cog"， 返回它的长度 5
	 * 
	 * 算法思想：广度优先遍历
	 *          把字典中每个string看作节点，如果string之间距离为1说明有连线，
	 *          相当于找到最短的连线长度。
	 *          队列的分层，用特殊标志”!”分开 
	 * 
	 * @param start: a string
	 * @param end: a string
	 * @param dict: a set of string
	 * @return: An integer
	 */
	static public int ladderLength(String start, String end, Set<String> dict) {
		if(start.equals(end)) { //特殊情况 start == end
			return 1;
		}
		Queue<String> queue = new LinkedList<String>();  //使用一个队列保存节点
		boolean[] visited = new boolean[dict.size()];  //使用boolean数组表示某一节点是否已经被遍历过
		queue.add(start);
		queue.add("!");  //用特殊标志分层队列
		Object[] a = dict.toArray();
		int count = 1; //count初始化为1
		boolean solution = false; //利用solution表示是否存在转换序列，初始化为false
		while(!queue.isEmpty()) {
			String temp = queue.poll();
			if(temp.equals("!")) { //队列出现分层
				count++;
				continue;
			}
			
			if(isOneDiff(temp, end)) {
				count++;
				solution = true;
				break;
			}
			
			for(int i = 0; i < visited.length; i++) {
				if((!visited[i]) && isOneDiff(a[i].toString(), temp)) {
					queue.add(a[i].toString());
					visited[i] = true;
				}
			}
			if(queue.peek().equals("!")) { //如果队列队头为分层符号，证明与上一层的节点相连的本层节点已经全部加入队列，因此需要为队列再次添加分层标志
				queue.add("!");
			}
		}
		
		if(solution) 
			return count;
		else return 0;
	}
	
	
	/**
	 * 判断两个字符串之间是否只有一个字符不同
	 * @param source
	 * @param target
	 * @return
	 */
	static boolean isOneDiff(String source, String target) {
		int result = 0;
		for(int i = 0; i < source.length(); i++) {
			if(source.charAt(i) != target.charAt(i)) {
				result++;
			}
		}
		if(result == 1)
			return true;
		else return false;
	}
}
