package utils;

import java.util.ArrayList;
import java.util.List;

public class StringSolution {

	public List<List<Integer>> permuteUnique(int[] nums) {
		// write your code here
		ArrayList<List<Integer>> rts = new ArrayList<List<Integer>>();
		if (nums == null) {
			return rts;
		}
		if (nums.length == 0) {
			rts.add(new ArrayList<Integer>());
			return rts;
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		ArrayList<Integer> indexList = new ArrayList<Integer>();                                                                                   
		helper(nums, list, indexList, rts);
		return rts;
	}

	private void helper(int[] nums, ArrayList<Integer> list,
			ArrayList<Integer> indexList, ArrayList<List<Integer>> rts) {
		if (list.size() == nums.length) {
			if (!rts.contains(list)) {
				rts.add(new ArrayList<Integer>(list));
			}
		}
		for (int i = 0; i < nums.length; i++) {
			if (indexList.contains(i)) {
				continue;
			}
			list.add(nums[i]);
			indexList.add(i);
			System.out.print(list + "    vvvvvv    ");
			System.out.print(indexList);
			System.out.println();
			helper(nums, list, indexList, rts);
			list.remove(list.size() - 1);
			indexList.remove(indexList.get(indexList.size() - 1));

		}
	}

}
