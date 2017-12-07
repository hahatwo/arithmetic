import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Map;

public class LFUCache {

	private final Map<Integer, CacheNode> cache;
	private final LinkedHashSet[] frequencyList;
	private int lowestFrequency;
	private int maxFrequency;
	private final int maxCacheSize;

	/*
	 * LFU��һ�������Ļ����㷨,ʵ��LFU�е�set �� get �㷨˼�룺 LFU��Least Frequently
	 * Used���㷨�������ݵ���ʷ����Ƶ������̭���ݣ� �����˼���ǡ�������ݹ�ȥ�����ʶ�Σ���ô���������ʵ�Ƶ��Ҳ���ߡ���
	 * LFU��ÿ�����ݿ鶼��һ�����ü������������ݿ鰴�����ü������򣬾�����ͬ���ü��������ݿ�����ʱ������ ����ʵ�����£�
	 * ���һ��LinkedHashSet����������frequencyList������Ϊcapacity*2+1
	 * ����ʵ��������Ϊ��0������ֵ������capacity*2+1�ȽϺ���̫С����LinkedHashSet��Ƶ��ɾ�����룬̫��ռ���ڴ�ռ�̫�ࣩ��
	 * LinkedHashSet���������������ü�������frequencyList[i]�����������ü���Ϊi�����ݿ�
	 * �����ݿ�����ü�����Ϊiʱ������ʱ���������ݿ���뵽frequencyList[i]��LinkHashSet�ܹ��Զ����ղ���˳�������
	 * lowestFrequency�������ü�����С��frequencyList[i]��i������
	 * maxFrequency������ʶ���ü�����ֵ�Ƿ񵽴����ޣ�����ﵽ����֮�����ü�����ֵ�Ͳ������ӣ�
	 * ������frequencyList[capacity*2]��������ĩβ�����շ���ʱ������Ⱥ󣬼�ÿ�������ݿ�һ�Σ�
	 * �ͽ����frequencyList
	 * [capacity*2]�����LinkedHashSet�е�ԭλ��ɾ�����ٲ��뵽ĩβ���Ӷ��ﵽ��װʱ�������Ŀ�ģ�
	 * 
	 * 1. �¼������ݣ�����δ��ʱ�����뵽LinkHashSet����β������Ϊ���ü���Ϊ1���� 2.1.
	 * �����е����ݱ����ʺ����ü������ӣ���ԭ����frequencyList
	 * [i-1]��ɾ���������ݿ����²��뵽���ü���Ϊi��frequencyList[i]; 2.2.
	 * ���ü�������maxFrequency֮�����в�������frequencyList[capacity*2]���У� 3.
	 * ��������������Ҫ��̭���ݣ��ҵ�frequencyList
	 * [lowestFrequency]����ʱfrequencyList[lowestFrequency]�����ü�������
	 * ���ĵ�һ���ʱ��˳����˵Ϊ���Ƚ��뻺��ģ�ɾ����һ��¼�������ݲ��뵽LinkHashSet����β������Ϊ���ü���Ϊ1����
	 * 
	 * @param capacity: An integer
	 */
	public LFUCache(int capacity) {
		// do initialization if necessary
		this.cache = new HashMap<Integer, CacheNode>(capacity);
		this.frequencyList = new LinkedHashSet[capacity * 2 + 1];
		this.maxCacheSize = capacity;
		this.maxFrequency = capacity * 2;
		this.lowestFrequency = 0;
		initFrequencyList(frequencyList);
	}

	/*
	 * ˼�룺 �ȼ��cache���Ƿ񱣴���key,�����ֱ�Ӹ���key��Ӧ��value ���û�У�����Ƿ�cache.size() ==
	 * maxCacheSize�����������̭���ݿ飬���������û� �¼�������ݲ��뵽LinkHashSet����β�� ����������ü���
	 * 
	 * @param key: An integer
	 * 
	 * @param value: An integer
	 * 
	 * @return: nothing
	 */
	public void set(int key, int value) {
		// write your code here
		CacheNode currentNode = cache.get(key);
		if (currentNode == null) {
			if (cache.size() == maxCacheSize) {
				// ���ݿ��û���ɾ�����ټ����������õ����ݿ�
				doEvictionData();
			}
			currentNode = new CacheNode(key, value, 0);
			LinkedHashSet<CacheNode> lowestNodeSet = frequencyList[0];
			cache.put(key, currentNode);
			lowestNodeSet.add(currentNode);
			lowestFrequency = 0;
		} else {
			currentNode.v = value;
		}
		// �������ü���
		addFrequency(currentNode);
	}

	/*
	 * @param key: An integer
	 * 
	 * @return: An integer
	 */
	public int get(int key) {
		// write your code here
		CacheNode node = cache.get(key);
		if (node != null) {
			addFrequency(node);
			return node.v;
		}
		return -1;
	}

	/**
	 * �������ݿ�����ü����������䱣������Ӧ��frequencyList����Ӧ��λ�� 1. ���赱ǰ���ü�����ֵΪi�����i <
	 * maxFrequency,�����ݿ�Ӧ�ñ�����frequencyList[i+1]��; 2. ���i =
	 * maxFrequency,�����ݿ�Ӧ�ñ�����frequencyList[capacity*2]��
	 * �����ݿ鱻�ƶ���nextNodeSet�к����currentNodeSetΪ�ղ���lowestFrequency ==
	 * currentFrequency�� ����currentNodeSet����֮ǰ��frequencyList�����е�LinkedHashSet��Ϊ�գ�
	 * ��ôlowestFrequency��Ӧ��ָ��nextNodeSet
	 * 
	 * @param node
	 */
	private void addFrequency(CacheNode currentNode) {
		int currentFrequency = currentNode.frequency;
		if (currentFrequency < maxFrequency) {
			int nextFrequency = currentFrequency + 1;
			LinkedHashSet<CacheNode> currentNodeSet = frequencyList[currentFrequency];
			LinkedHashSet<CacheNode> nextNodeSet = frequencyList[nextFrequency];
			// �������ݿ�λ�ú����ü���
			moveCurrentToNext(currentNodeSet, nextNodeSet, currentNode,
					nextFrequency);
			if (lowestFrequency == currentFrequency && currentNodeSet.isEmpty()) {
				lowestFrequency = nextFrequency;
			}
		} else {
			LinkedHashSet<CacheNode> maxFrequencyNodeSet = frequencyList[currentFrequency];
			maxFrequencyNodeSet.remove(currentNode);
			maxFrequencyNodeSet.add(currentNode);
		}

	}

	/**
	 * �ƶ���ǰ���ݿ鵽��һ����Ӧλ�ú��������ü���
	 * 
	 * @param currentNodeSet
	 * @param nextNodeSet
	 * @param currentNode
	 * @param nextFrequency
	 */
	private void moveCurrentToNext(LinkedHashSet<CacheNode> currentNodeSet,
			LinkedHashSet<CacheNode> nextNodeSet, CacheNode currentNode,
			int nextFrequency) {
		currentNodeSet.remove(currentNode);
		nextNodeSet.add(currentNode);
		currentNode.frequency = nextFrequency;
	}

	/**
	 * ��̭���ټ������ȱ����õ����ݿ�
	 */
	private void doEvictionData() {
		LinkedHashSet<CacheNode> lowestFrequencyNodeSet = frequencyList[lowestFrequency];
		Iterator<CacheNode> iterator = lowestFrequencyNodeSet.iterator();
		CacheNode lowestFrequencyHeadNode = iterator.next();
		iterator.remove();
		cache.remove(lowestFrequencyHeadNode.k);
	}

	/**
	 * ��ʼ��frequencyList
	 * 
	 * @param frequencyList
	 */
	private void initFrequencyList(LinkedHashSet[] frequencyList) {
		for (int i = 0; i < frequencyList.length; i++) {
			frequencyList[i] = new LinkedHashSet<CacheNode>();
		}
	}

	public int remove(int key) {
		CacheNode currentNode = cache.remove(key);
		if (currentNode != null) {
			LinkedHashSet<CacheNode> nodes = frequencyList[currentNode.frequency];
			nodes.remove(currentNode);
			if (lowestFrequency == currentNode.frequency) {
				findNextLowestFrequency();
			}
			return currentNode.v;
		} 
		return -1;
	}

	public int frequencyOf(int key) {
		CacheNode node = cache.get(key);
		if (node != null) {
			return node.frequency;
		} 
		return 0;
	}

	public void clear() {
		for (int i = 0; i <= maxFrequency; i++) {
			frequencyList[i].clear();
		}
		cache.clear();
		lowestFrequency = 0;
	}

	public int size() {
		return cache.size();
	}

	public boolean isEmpty() {
		return this.cache.isEmpty();
	}

	public boolean containsKey(int key) {
		return this.cache.containsKey(key);
	}

	private void findNextLowestFrequency() {
		while (lowestFrequency <= maxFrequency
				&& frequencyList[lowestFrequency].isEmpty()) {
			lowestFrequency++;
		}
		if (lowestFrequency > maxFrequency) {
			lowestFrequency = 0;
		}
	}

	private class CacheNode {
		public final int k;
		public int v;
		public int frequency;

		public CacheNode(int k, int v, int frequency) {
			this.k = k;
			this.v = v;
			this.frequency = frequency;
		}
	}

}
