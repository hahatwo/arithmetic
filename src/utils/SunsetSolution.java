package utils;

import java.util.ArrayList;
import java.util.Arrays;

public class SunsetSolution {

	public ArrayList<ArrayList<Integer>> subsets(int[] nums) {
		// write your code here

		ArrayList<ArrayList<Integer>> list = new ArrayList<ArrayList<Integer>>();
		if (nums == null) {
			return list;
		}
		if (nums.length == 0) {
			list.add(new ArrayList<Integer>());
			return list;
		}
		Arrays.sort(nums);
		ArrayList<Integer> arrayList = new ArrayList<Integer>();
		helper(list, nums, 0, arrayList);
		return list;
	}

	public void helper(ArrayList<ArrayList<Integer>> list, int[] nums,
			int startIndex, ArrayList<Integer> arrayList) {
		list.add(new ArrayList<Integer>(arrayList));
		for (int i = startIndex; i < nums.length; i++) {
			arrayList.add(nums[i]);
			helper(list, nums, i + 1, arrayList);
			arrayList.remove(arrayList.size() - 1);
		}
	}

}
