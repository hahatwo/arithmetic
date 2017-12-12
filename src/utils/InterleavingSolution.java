package utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

public class InterleavingSolution {
	public void quickSort(int[] nums, int low, int high) {
		if (low > high) {
			return;
		}
		int flag = nums[low];
		int left = low;
		int right = high;
		while (left < right) {
			while (left < right && nums[right] > flag) {
				right--;
			}
			if (left < right) {
				nums[left] = nums[right];
				left++;
			}
			while (left < right && nums[left] < flag) {
				left++;
			}
			if (left < right) {
				nums[right] = nums[left];
				right--;
			}
		}
		nums[left] = flag;
		quickSort(nums, low, left - 1);
		quickSort(nums, left + 1, high);
	}

	public String minWindow(String source, String target) {
		// write your code
		if (source.equals("") || target.equals("")) {
			return "";
		}
		HashMap<Integer, String> map = new HashMap<Integer, String>();
		for (int i = 0; i < source.length(); i++) {
			if (!target.contains(String.valueOf(source.charAt(i)))) {
				continue;
			}
			int index = 0;
			
			for (int j = i; j < source.length(); j++) {
				if (target.contains(String.valueOf(source.charAt(j)))) {
					index++;
					if (index >= target.length()) {
						String subString = source.substring(i, j + 1);
						map.put(i, subString);
						break;
					}
				}
			}
		}

		if (map.size() == 0) {
			return "";
		}
		List<Map.Entry<Integer, String>> list = new ArrayList<Map.Entry<Integer, String>>(
				map.entrySet());
		Collections.sort(list, new Comparator<Map.Entry<Integer, String>>() {
			public int compare(Map.Entry<Integer, String> o1,
					Map.Entry<Integer, String> o2) {
				if ((o2.getValue() != null && o1.getValue() != null && 
						o2.getValue().length() > o1.getValue().length())
						|| (o2.getKey() > o1.getKey())) {
					return 1;
				} else {
					return -1;
				}
			}
		});
		System.out.println(list);
        Map.Entry<Integer, String> subMap = list.get(0);
		return subMap.getValue();
	}
}
