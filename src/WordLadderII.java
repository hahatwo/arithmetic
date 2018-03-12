import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;

public class WordLadderII {

	/*
	 * �����������ʣ�start��end����һ���ֵ䣬�ҳ����д�start��end�����ת������ ���磺 ÿ��ֻ�ܸı�һ����ĸ��
	 * �任�����е��м䵥�ʱ������ֵ��г��֡� ע������ ���е��ʾ�����ͬ�ĳ��ȡ� ���е��ʶ�ֻ����Сд��ĸ��
	 * ���� �����������£� start = "hit" end = "cog" dict =
	 * ["hot","dot","dog","lot","log"] ����
	 * [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
	 * �㷨˼�룺 (1) �Ƚ�start��end����dict֮�У����ֵ��е�ÿ���ַ�������һ���ڵ㣬������Ϊ�����ַ���ֻ��һ����ĸ��ͬ���������ڵ����������Եõ��ڵ�����ͼ;
	 *              ���ÿ�������������Ľڵ㣬HashMap<String node, List<String> approximalNode>apprNodes��������ÿ���㼰���Ӧ�������ĵ㣻 
	 *          (2) ���ù�����ȱ���˼�룬�����ʼ�ڵ㣨�ַ���start�����ս�㣨�ַ���end������̾������ΪminDist���Լ�ÿ���ڵ�����ս�㣨�ַ���end������̾��룻 ��������HashMap<String node, Integer dist>
	 *              nodeToEnd ��������ÿ���ڵ�����ս�㣨�ַ���end������̾��� 
	 *          (3) ����������ȱ����ͻ���˼�룬����һ��List<String>temp������ʱ��������Ľڵ㣬 
	 *              �ݹ���������� temp.size() + nodeToEnd > minDist, return(����ʼ�ڵ㵽ĳһ�ڵ�ľ�����ϸýڵ㵽�ս������Ѿ�������̾�����)
	 *              node == end, return (�ҵ����ս��)
	 * @param start: a string
	 * @param end: a string
	 * @param dict: a set of string
	 * @return: a list of lists of string
	 */
	public List<List<String>> findLadders(String start, String end,
			Set<String> dict) {
		// write your code here
		Map<String, List<String>> apprNodes = new HashMap<String, List<String>>(); // ����ÿ���㼰���Ӧ�������ĵ�
		Set<String> visited = new HashSet<String>(); // ��¼�ڵ��Ƿ��Ѿ���������
		Map<String, Integer> nodeToEnd = new HashMap<String, Integer>(); // ����ÿ���ڵ�����ս�㣨�ַ���end������̾���
		List<List<String>> results = new ArrayList<List<String>>(); // ��¼���ս��
		Set<String> newDict = new HashSet<String>(dict); 
		newDict.add(start);
		newDict.add(end);
        for(String node : newDict) { //�õ�ÿһ���ڵ�����������ڵ�ļ���
        	apprNodes.put(node, getApprNodes(node, newDict));
        }
		int minDist = bfs(apprNodes, visited, nodeToEnd, start, end);
		dfs(apprNodes, results, new ArrayList<String>(), visited, nodeToEnd, start, end, minDist);
		return results;
	}

	/**
	 * ������ȱ���
	 * ���start��end����̾���
	 * ���ÿһ���ڵ㵽end����̾���
	 * @param apprNodes
	 * @param visited
	 * @param nodeToEnd
	 * @param start
	 * @param end
	 * @return
	 */
	private int bfs(Map<String, List<String>> apprNodes,
			Set<String> visited, Map<String, Integer> nodeToEnd,
			String start, String end) {
		nodeToEnd.put(end, 1);
        Queue<String> queue = new LinkedList<String>();
        queue.add(end);
        visited.add(end);
        while(!queue.isEmpty()) {
        	String currentNode = queue.poll();
        	if(apprNodes.containsKey(currentNode)) {
        		for(String node : apprNodes.get(currentNode)) {
        			if(visited.contains(node))
        				continue;
        			visited.add(node);
        			queue.add(node);
        			nodeToEnd.put(node, nodeToEnd.get(currentNode)+1);
        		}
        	} 
        }
		visited.clear();
		return nodeToEnd.get(start);
	}
	
	
	private void dfs(Map<String, List<String>> apprNodes, List<List<String>> results, List<String> temp,
			Set<String> visited, Map<String, Integer> nodeToEnd,
			String start, String end, int minDist) {
		if(temp.size() + nodeToEnd.get(start) > minDist) 
			return;
		if(start.equals(end)) {
			results.add(new ArrayList<String>(temp));
			results.get(results.size()-1).add(end);
			return;
		}
		temp.add(start);
		visited.add(start);
		for(String node : apprNodes.get(start)) {
			if(visited.contains(node)) 
				continue;
			dfs(apprNodes, results, temp, visited, nodeToEnd, node, end, minDist);
		}
		temp.remove(temp.size()-1);
		visited.remove(start);
	}
	
	
	
	private String replace(String s, int index, char c) {
        char[] chars = s.toCharArray();
        chars[index] = c;
        return new String(chars);
    }
    
    // get connections with given node.
    // for example, given word = 'hot', dict = {'hot', 'hit', 'hog'}
    // it will return ['hit', 'hog']
    private ArrayList<String> getApprNodes(String word, Set<String> dict) {
        ArrayList<String> nextWords = new ArrayList<String>();
        for (char c = 'a'; c <= 'z'; c++) {
            for (int i = 0; i < word.length(); i++) {
                if (c == word.charAt(i)) {
                    continue;
                }
                String nextWord = replace(word, i, c);
                if (dict.contains(nextWord)) {
                    nextWords.add(nextWord);
                }
            }
        }
        return nextWords;
    }


}
