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
	 * 给出两个单词（start和end）和一个字典，找出所有从start到end的最短转换序列 比如： 每次只能改变一个字母。
	 * 变换过程中的中间单词必须在字典中出现。 注意事项 所有单词具有相同的长度。 所有单词都只包含小写字母。
	 * 样例 给出数据如下： start = "hit" end = "cog" dict =
	 * ["hot","dot","dog","lot","log"] 返回
	 * [["hit","hot","dot","dog","cog"],["hit","hot","lot","log","cog"]]
	 * 算法思想： (1) 先将start和end加入dict之中，将字典中的每个字符串看做一个节点，其中认为两个字符串只有一个字母不同当做两个节点相连，可以得到节点拓扑图;
	 *              求出每个点与其相连的节点，HashMap<String node, List<String> approximalNode>apprNodes用来保存每个点及其对应的相连的点； 
	 *          (2) 利用广度优先遍历思想，求出开始节点（字符串start）到终结点（字符串end）的最短距离假设为minDist，以及每个节点距离终结点（字符串end）的最短距离； 其中利用HashMap<String node, Integer dist>
	 *              nodeToEnd 用来保存每个节点距离终结点（字符串end）的最短距离 
	 *          (3) 利用深度优先遍历和回溯思想，设置一个List<String>temp用来临时保存遍历的节点， 
	 *              递归结束条件： temp.size() + nodeToEnd > minDist, return(从起始节点到某一节点的距离加上该节点到终结点距离已经不是最短距离了)
	 *              node == end, return (找到了终结点)
	 * @param start: a string
	 * @param end: a string
	 * @param dict: a set of string
	 * @return: a list of lists of string
	 */
	public List<List<String>> findLadders(String start, String end,
			Set<String> dict) {
		// write your code here
		Map<String, List<String>> apprNodes = new HashMap<String, List<String>>(); // 保存每个点及其对应的相连的点
		Set<String> visited = new HashSet<String>(); // 记录节点是否已经被遍历过
		Map<String, Integer> nodeToEnd = new HashMap<String, Integer>(); // 保存每个节点距离终结点（字符串end）的最短距离
		List<List<String>> results = new ArrayList<List<String>>(); // 记录最终结果
		Set<String> newDict = new HashSet<String>(dict); 
		newDict.add(start);
		newDict.add(end);
        for(String node : newDict) { //得到每一个节点的与其相连节点的集合
        	apprNodes.put(node, getApprNodes(node, newDict));
        }
		int minDist = bfs(apprNodes, visited, nodeToEnd, start, end);
		dfs(apprNodes, results, new ArrayList<String>(), visited, nodeToEnd, start, end, minDist);
		return results;
	}

	/**
	 * 广度优先遍历
	 * 求解start到end的最短距离
	 * 求解每一个节点到end的最短距离
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
