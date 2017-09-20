import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ThreeSum {

	/*
	 * 给出一个有n个整数的数组S，在S中找到三个整数a, b, c，找到所有使得a + b + c = 0的三元组。
	 * 
	 * @param numbers: Give an array numbers of n integer
	 * 
	 * @return: Find all unique triplets in the array which gives the sum of
	 * zero.
	 */
	public List<List<Integer>> threeSum(int[] numbers) {
		// write your code here
		if (numbers == null || numbers.length < 3) {
			return null;
		}
		List<List<Integer>> results = new ArrayList<List<Integer>>();
		Arrays.sort(numbers);

		for(int i = 0; i < numbers.length - 2; i++){
			if(i > 0 && numbers[i] == numbers[i-1]){
				continue;
			}
			twoSum(results, numbers, i+1, numbers.length-1, -numbers[i]);
		}
		return results;
	}

	/**
	 * private void matchItem(List<List<Integer>> results, List<Integer> item,
	 * int[] numbers, int[] visited){ if(item.size() == 3){ int sum = 0; for(int
	 * e : item){ sum += e; } if(sum == 0){ results.add(new
	 * ArrayList<Integer>(item)); } return; } for(int i = 0; i < numbers.length;
	 * i++){ if(visited[i] == 1 || i!=0 && numbers[i] == numbers[i-1] &&
	 * visited[i-1] == 0){ continue; } visited[i] = 1; item.add(numbers[i]);
	 * matchItem(results, item, numbers, visited);
	 * item.remove(item.remove(item.size()-1)); visited[i] = 0; } }
	 * 
	 * private void quickSort(int[] numbers, int l, int r){ if(numbers == null
	 * || numbers.length == 0){ return; } if(l < r){ int left = l, right = r;
	 * int temp = numbers[l]; while(left < right){ while(left < right &&
	 * numbers[right] >= temp){ right--; } if(left < right){ numbers[left] =
	 * numbers[right]; right--; }
	 * 
	 * while(left < right && numbers[left] <= temp){ left++; } if(left < right){
	 * numbers[right] = numbers[left]; left++; } numbers[left] = temp;
	 * quickSort(numbers, l, left -1); quickSort(numbers, left + 1, r); } } }
	 **/

	/**
	 * 当numbers[left] + numbers[right] = target时，将-target,numbers[left]和numbers[right]加入List容器
	 * @param results
	 * @param numbers 有序数组
	 * @param left  数组的起始位
	 * @param right 数组的结束位
	 * @param target
	 */
	private void twoSum(List<List<Integer>> results, int[] numbers, int left,
			int right, int target) {
		while (left < right) {
			if (numbers[left] + numbers[right] == target) {
				List<Integer> item = new ArrayList<Integer>();
				item.add(-target);
				item.add(numbers[left]);
				item.add(numbers[right]);
				results.add(item);
				left++;
				right--;

				// 跳过数组中相同的数
				while (left < right && numbers[left] == numbers[left - 1]) {
					left++;
				}
				while (left < right && numbers[right] == numbers[right + 1]) {
					right--;
				}
			} else if (numbers[left] + numbers[right] < target) {
				left++;
			} else {
				right--;
			}
		}
	}
	
}
