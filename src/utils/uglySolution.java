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
	 * �㷨˼�룺 targetHash�����¼target��Ӧ��ASCII�룬��ʼ����¼��Ҫ��¼target���ַ������ڶ�Ӧ��λ����Ϊ1��
	 * ��ôtargetHash����Ϊ[...1...0 1 ... 1...],ֵΪ1��λ�þ���target�е��ַ�
	 * ����source���ַ���ÿ����һ���ַ����俴��������targetHash��
	 * ����ֵ��1��targetHash[source.charAt(i)]--;
	 * ������ֵ��1��ʱ���������targetHash[source.charAt(i)] > 0��ֵΪ1��ʱ��sourceNum++;
	 * ��ʾ�Ӵ��Ѿ������target��Ӧ���ַ� ��sourceNum >= targetNumʱ�������Ӵ�����Ҫ��
	 * Ȼ��targetHash[source
	 * .charAt(i)]++,�ɿ������ַ���targetHash���߳�,j++,��ζ���¸��Ӵ���ͷ����һ���Ӵ��ĵڶ���λ�ÿ�ʼ
	 * �����κ��ƣ�ֱ��sourceNum<targetNumΪֹ sourceNum<targetNum��ζ��target��һ���ַ��������Ӵ���
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
		int targetNum = initTargetNum(targetHash, target); // target�ַ�����
		int sourceNum = 0; // ��¼�Ӵ����ַ�����
		int i = 0, j = 0; // i��¼�Ӵ�ĩβ��j��¼�Ӵ���ͷ
		for (i = 0; i < source.length(); i++) {
			if (targetHash[source.charAt(i)] > 0) {
				sourceNum++;
			}
			targetHash[source.charAt(i)]--;
			while (sourceNum >= targetNum) {
				if (ans > i - j + 1) {// �õ���С�Ӵ�
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
			cols.remove(cols.size() - 1); // ����
		}
	}

	/**
	 * @param cols
	 *            Ŀǰ����Ҫ���Q����λ�õ�ArrayList
	 * @param colums
	 *            ��Χ��0-->n, �ҵ���һ������Ҫ���Q����λ��
	 * @return
	 */
	private boolean isValid(ArrayList<Integer> cols, int column) {
		int row = cols.size(); // ��һ������Ҫ���Q����λ�ã���Ϊ�Ǵ�0��ʼ�ģ�
		for (int rowIndex = 0; rowIndex < cols.size(); rowIndex++) {
			if (cols.get(rowIndex) == column) { // ����Q��ͬ�л��������λ��
				return false;
			}
			if (rowIndex + cols.get(rowIndex) == row + column) { // ����Q�����ϻ�������
				return false;
			}
			if (rowIndex - cols.get(rowIndex) == row - column) {// ����Q�����»�������
				return false;
			}
		}
		return true;
	}

	/**
	 * ���Ʒ���Ҫ������̣����������Ҫ����ַ���
	 * 
	 * @param cols
	 *            ����Ҫ���Q����λ�õ�ArrayList����
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
