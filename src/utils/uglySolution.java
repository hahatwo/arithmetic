package utils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class uglySolution {

	private HashMap<String, Integer> map = new HashMap<String, Integer>();

	public int nthUglyNumber(int n) {
		// Write your code here
		findUgly(map, n);
		return map.get("num" + n);
	}

	private void findUgly(HashMap<String, Integer> map, int num) {

		int n;
		int needNum = 1;
		int counter = 0;
		while (true) {
			n = needNum;
			while (n % 2 == 0) {
				n = n / 2;
			}
			while (n % 3 == 0) {
				n = n / 3;
			}
			while (n % 5 == 0) {
				n = n / 5;
			}
			if (n == 1) {
				counter++;
				map.put("num" + counter, needNum);
			}
			if (counter == num) {
				break;
			}
			needNum++;
		}

	}

	/**
	 * 算法思想： targetHash数组记录target对应的ASCII码，初始化记录需要记录target的字符将其在对应的位置设为1；
	 * 那么targetHash类似为[...1...0 1 ... 1...],值为1的位置就是target中的字符
	 * 遍历source的字符，每遍历一个字符将其看作填入了targetHash中
	 * ，其值减1：targetHash[source.charAt(i)]--;
	 * 不过当值减1的时候，如果满足targetHash[source.charAt(i)] > 0即值为1的时候sourceNum++;
	 * 表示子串已经获得了target相应的字符 当sourceNum >= targetNum时，表明子串满足要求
	 * 然后将targetHash[source
	 * .charAt(i)]++,可看做将字符从targetHash中踢出,j++,意味着下个子串开头从上一个子串的第二个位置开始
	 * ，依次后推，直到sourceNum<targetNum为止 sourceNum<targetNum意味着target有一个字符不在新子串中
	 * 
	 * **/
	private int initTargetNum(int[] targetHash, String target) {
		int targetnum = 0;
		for (char ch : target.toCharArray()) {
			targetnum++;
			targetHash[ch]++;
		}
		return targetnum;

	}

	public String minWindow(String source, String target) {

		String minStr = "";
		int ans = Integer.MAX_VALUE;
		int[] targetHash = new int[256];
		int targetNum = initTargetNum(targetHash, target); // target字符总数
		int sourceNum = 0; // 记录子串的字符数量
		int i = 0, j = 0; // i记录子串末尾，j记录子串开头
		for (i = 0; i < source.length(); i++) {
			if (targetHash[source.charAt(i)] > 0) {
				sourceNum++;
			}
			targetHash[source.charAt(i)]--;
			while (sourceNum >= targetNum) {
				if (ans > i - j + 1) {// 得到最小子串
					ans = i - j + 1;
					minStr = source.substring(j, i + 1);
				}
				targetHash[source.charAt(i)]++;
				if (targetHash[source.charAt(i)] > 0) {
					sourceNum--;
				}
				j++;
			}
		}

		return minStr;
	}

	public ArrayList<ArrayList<String>> solveNQueens(int n) {
		// write your code here
		ArrayList<ArrayList<String>> result = new ArrayList<ArrayList<String>>();
		searchQueens(result, n, new ArrayList<Integer>());
		return result;
	}

	private void searchQueens(ArrayList<ArrayList<String>> result, int n,
			ArrayList<Integer> cols) {
		if (cols.size() == n) {
			ArrayList<String> item = drawChessBoard(cols);
			result.add(item);
		}
		for (int i = 0; i < n; i++) {
			if (!isValid(cols, i)) {
				continue;
			}
			cols.add(i);
			searchQueens(result, n, cols);
			cols.remove(cols.size() - 1); // 回溯
		}
	}

	/**
	 * @param cols
	 *            目前满足要求的Q的列位置的ArrayList
	 * @param colums
	 *            范围从0-->n, 找到下一个满足要求的Q的列位置
	 * @return
	 */
	private boolean isValid(ArrayList<Integer> cols, int column) {
		int row = cols.size(); // 下一个满足要求的Q的行位置（因为是从0开始的）
		for (int rowIndex = 0; rowIndex < cols.size(); rowIndex++) {
			if (cols.get(rowIndex) == column) { // 处于Q的同列或者自身的位置
				return false;
			}
			if (rowIndex + cols.get(rowIndex) == row + column) { // 处于Q的右上或者左下
				return false;
			}
			if (rowIndex - cols.get(rowIndex) == row - column) {// 处于Q的右下或者左上
				return false;
			}
		}
		return true;
	}

	/**
	 * 绘制符合要求的棋盘，即输出符合要求的字符串
	 * 
	 * @param cols
	 *            满足要求的Q的列位置的ArrayList对象
	 * @return
	 */
	private ArrayList<String> drawChessBoard(ArrayList<Integer> cols) {
		ArrayList<String> item = new ArrayList<String>();
		for (int i = 0; i < cols.size(); i++) {
			StringBuilder sb = new StringBuilder();
			for (int j = 0; j < cols.size(); j++) {
				sb.append(j == cols.get(i) ? "Q" : "");
			}
			item.add(sb.toString());
		}
		return item;
	}

	public void recoverRotatedSortedArray(List<Integer> nums) {
		// write your code here
		if (nums == null || nums.size() == 0) {
			return;
		}
		Integer[] list = new Integer[nums.size()];
		list = nums.toArray(new Integer[nums.size()]);
		int index = 0;
		for (int i = 0; i < list.length - 1; i++) {
			if (list[i] > list[i + 1]) {
				index = i;
				break;
			}
		}
		
		recover(list, 0, index);
		recover(list, index + 1, list.length -1);
		recover(list, 0, list.length - 1);
		nums = Arrays.asList(list);
		//return nums;
	}

	private void recover(Integer[] list, int m, int n) {
		int tag = 0;
		for (int i = m; i <= m + (n - m) / 2; i++) {
			tag = list[i];
			list[i] = list[n + m - i];
			list[n + m - i] = tag;
		}
	}

}
