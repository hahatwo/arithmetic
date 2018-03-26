import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

public class Djkstra {
	/*
	 * Dijkstra���·���� ����ͳ��ͼ��"����vs"������������������·����
	 */
	
	private int mEdgNum;        // �ߵ�����
    private char[] mVexs;       // ���㼯��
    private int[][] mMatrix;    // �ڽӾ��� mMatrix[i][j]��ʾ�ڵ�i���ڵ�j�ߵ�Ȩֵ
    private static final int INF = Integer.MAX_VALUE;   // ���ֵ
    private Set<Character> knownNodes;  //��¼��������·���Ķ���
    private Set<Character> unknownNodes; //��¼δ������·���Ķ���
    private Set<Character> visited;      
    /**
     * 
     * @param mVexs
     * @param mMatrix
     * @param node ��ʼ�ڵ�
     * @return keyToNode �������нڵ㵽node�ڵ�����·���ļ���
     */
    public Map<Character, Integer> djkstra(char[] mVexs, int[][] mMatrix, char node) {
    	Map<Character, Integer> keyToNode = new HashMap<Character, Integer>(); //�����洢�ڵ�key���ڵ�node�����·��ֵ
    	int position = getPostion(mVexs, node);
    	int min = INF;
    	if(position == -1) { //ͼ��û������ڵ�
    		return null;
    	}
    	
    	//��ʼ��
    	for(int i = 0; i < mVexs.length; i++) {
    		keyToNode.put(mVexs[i], mMatrix[position][i]);
    	}
    	knownNodes.add(node);
    	for(int i = 0; i < mVexs.length; i++) {
    		unknownNodes.add(mVexs[i]);
    	}
    	unknownNodes.remove(node);
    	//��ʼ��
    	
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
    			closestNodePosition = i;  //�ҵ�
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
