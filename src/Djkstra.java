import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Djkstra {
	/*
	 * Dijkstra最短路径。 即，统计图中"顶点vs"到其它各个顶点的最短路径。
	 */
	
	private int mEdgNum;        // 边的数量
    private char[] mVexs;       // 顶点集合
    private int[][] mMatrix;    // 邻接矩阵 mMatrix[i][j]表示节点i到节点j边的权值
    private static final int INF = Integer.MAX_VALUE;   // 最大值
    private Set<Character> knownNodes;  //记录已求出最短路径的顶点
    private Set<Character> unknownNodes; //记录未求出最短路径的顶点
    private Set<Character> visited;      
    /**
     * 
     * @param mVexs
     * @param mMatrix
     * @param node 起始节点
     * @return keyToNode 返回所有节点到node节点的最短路径的集合
     */
    public Map<Character, Integer> djkstra(char[] mVexs, int[][] mMatrix, char node) {
    	Map<Character, Integer> keyToNode = new HashMap<Character, Integer>(); //用来存储节点key到节点node的最短路径值
    	int position = getPostion(mVexs, node);
    	int min = INF;
    	if(position == -1) { //图中没有这个节点
    		return null;
    	}
    	
    	//初始化
    	for(int i = 0; i < mVexs.length; i++) {
    		keyToNode.put(mVexs[i], mMatrix[position][i]);
    	}
    	knownNodes.add(node);
    	for(int i = 0; i < mVexs.length; i++) {
    		unknownNodes.add(mVexs[i]);
    	}
    	unknownNodes.remove(node);
    	//初始化
    	
    	int closestNodePosition = position;
    	for(int i = 1; i < mMatrix.length; i++) {
    		
    		Iterator<Character> iterator = unknownNodes.iterator();
    		char nextNode = mVexs[i];
    		while(iterator.hasNext()) {
    			if(keyToNode.get(iterator.next()) < min) {
    				min = keyToNode.get(iterator.next());
    				nextNode = iterator.next();
    			}
    		}
    		
    		
    		
    		if((!knownNodes.contains((Character)mVexs[i])) && keyToNode.get(mVexs[i]) < min) {
    			min = mMatrix[position][i];
    			closestNodePosition = i;  //找到
    		}
    	}
    	
    	return null;
    }
    
    private int getPostion(char[] mVexs, char node) {
    	int position = 0; 
    	for(int i = 0; i < mVexs.length; i++) {
    		if(node != mVexs[i]) {
    			position++;
    		} else {
    			return position;
    		}
    	}
    	return -1;
    }
}
