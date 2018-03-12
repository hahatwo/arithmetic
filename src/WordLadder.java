import java.util.LinkedList;
import java.util.Queue;
import java.util.Set;

import javax.print.attribute.standard.MediaSize.ISO;

public class WordLadder {

	/*
	 * �����������ʣ�start��end����һ���ֵ䣬�ҵ���start��end�����ת������ ���磺 ÿ��ֻ�ܸı�һ����ĸ��
	 * �任�����е��м䵥�ʱ������ֵ��г��֡� ע������ ���û��ת�������򷵻�0�� ���е��ʾ�����ͬ�ĳ��ȡ� ���е��ʶ�ֻ����Сд��ĸ�� ����
	 * �����������£� start = "hit" end = "cog" dict = ["hot","dot","dog","lot","log"]
	 * һ����̵ı任������ "hit" -> "hot" -> "dot" -> "dog" -> "cog"�� �������ĳ��� 5
	 * 
	 * �㷨˼�룺������ȱ���
	 *          ���ֵ���ÿ��string�����ڵ㣬���string֮�����Ϊ1˵�������ߣ�
	 *          �൱���ҵ���̵����߳��ȡ�
	 *          ���еķֲ㣬�������־��!���ֿ� 
	 * 
	 * @param start: a string
	 * @param end: a string
	 * @param dict: a set of string
	 * @return: An integer
	 */
	static public int ladderLength(String start, String end, Set<String> dict) {
		if(start.equals(end)) { //������� start == end
			return 1;
		}
		Queue<String> queue = new LinkedList<String>();  //ʹ��һ�����б���ڵ�
		boolean[] visited = new boolean[dict.size()];  //ʹ��boolean�����ʾĳһ�ڵ��Ƿ��Ѿ���������
		queue.add(start);
		queue.add("!");  //�������־�ֲ����
		Object[] a = dict.toArray();
		int count = 1; //count��ʼ��Ϊ1
		boolean solution = false; //����solution��ʾ�Ƿ����ת�����У���ʼ��Ϊfalse
		while(!queue.isEmpty()) {
			String temp = queue.poll();
			if(temp.equals("!")) { //���г��ֲַ�
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
			if(queue.peek().equals("!")) { //������ж�ͷΪ�ֲ���ţ�֤������һ��Ľڵ������ı���ڵ��Ѿ�ȫ��������У������ҪΪ�����ٴ���ӷֲ��־
				queue.add("!");
			}
		}
		
		if(solution) 
			return count;
		else return 0;
	}
	
	
	/**
	 * �ж������ַ���֮���Ƿ�ֻ��һ���ַ���ͬ
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
