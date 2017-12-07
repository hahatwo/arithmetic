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
	 * LFU是一个著名的缓存算法,实现LFU中的set 和 get 算法思想： LFU（Least Frequently
	 * Used）算法根据数据的历史访问频率来淘汰数据， 其核心思想是“如果数据过去被访问多次，那么将来被访问的频率也更高”。
	 * LFU的每个数据块都有一个引用计数，所有数据块按照引用计数排序，具有相同引用计数的数据块则按照时间排序。 具体实现如下：
	 * 设计一个LinkedHashSet数组假设叫做frequencyList，容量为capacity*2+1
	 * （其实容量可以为除0外任意值，不过capacity*2+1比较合理，太小会在LinkedHashSet中频繁删除插入，太大占据内存空间太多），
	 * LinkedHashSet数组用来按照引用计数排序，frequencyList[i]用来保存引用计数为i的数据块
	 * 当数据块的引用计数都为i时，则按照时间排序将数据块插入到frequencyList[i]（LinkHashSet能够自动按照插入顺序遍历）
	 * lowestFrequency保存引用计数最小的frequencyList[i]中i的索引
	 * maxFrequency用来标识引用计数的值是否到达上限，如果达到上限之后，引用计数的值就不再增加，
	 * 而是在frequencyList[capacity*2]（数组最末尾）按照访问时间次序先后，即每访问数据块一次，
	 * 就将其从frequencyList
	 * [capacity*2]代表的LinkedHashSet中的原位置删除，再插入到末尾，从而达到安装时间排序的目的；
	 * 
	 * 1. 新加入数据（缓存未满时）插入到LinkHashSet数组尾部（因为引用计数为1）； 2.1.
	 * 队列中的数据被访问后，引用计数增加，从原来的frequencyList
	 * [i-1]中删除，将数据块重新插入到引用计数为i的frequencyList[i]; 2.2.
	 * 引用计数到达maxFrequency之后，所有操作都在frequencyList[capacity*2]进行； 3.
	 * 当缓存已满，需要淘汰数据，找到frequencyList
	 * [lowestFrequency]，此时frequencyList[lowestFrequency]的引用计数最少
	 * 它的第一项按照时间顺序来说为最先进入缓存的，删除第一项，新加入的数据插入到LinkHashSet数组尾部（因为引用计数为1）。
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
	 * 思想： 先检查cache中是否保存有key,如果有直接更新key对应的value 如果没有，检测是否cache.size() ==
	 * maxCacheSize，如果是则淘汰数据块，进行数据置换 新加入的数据插入到LinkHashSet数组尾部 最后，增加引用计数
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
				// 数据块置换，删除最少计数最先引用的数据块
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
		// 增加引用计数
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
	 * 增加数据块的引用计数，并将其保存在相应的frequencyList中相应的位置 1. 假设当前引用计数的值为i，如果i <
	 * maxFrequency,则数据块应该保存在frequencyList[i+1]中; 2. 如果i =
	 * maxFrequency,则数据块应该保存在frequencyList[capacity*2]中
	 * 当数据块被移动到nextNodeSet中后，如果currentNodeSet为空并且lowestFrequency ==
	 * currentFrequency， 表明currentNodeSet和它之前的frequencyList数组中的LinkedHashSet均为空，
	 * 那么lowestFrequency就应该指向nextNodeSet
	 * 
	 * @param node
	 */
	private void addFrequency(CacheNode currentNode) {
		int currentFrequency = currentNode.frequency;
		if (currentFrequency < maxFrequency) {
			int nextFrequency = currentFrequency + 1;
			LinkedHashSet<CacheNode> currentNodeSet = frequencyList[currentFrequency];
			LinkedHashSet<CacheNode> nextNodeSet = frequencyList[nextFrequency];
			// 更新数据块位置和引用计数
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
	 * 移动当前数据块到下一个响应位置和增加引用计数
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
	 * 淘汰最少计数最先被引用的数据块
	 */
	private void doEvictionData() {
		LinkedHashSet<CacheNode> lowestFrequencyNodeSet = frequencyList[lowestFrequency];
		Iterator<CacheNode> iterator = lowestFrequencyNodeSet.iterator();
		CacheNode lowestFrequencyHeadNode = iterator.next();
		iterator.remove();
		cache.remove(lowestFrequencyHeadNode.k);
	}

	/**
	 * 初始化frequencyList
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
