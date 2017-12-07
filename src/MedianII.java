import java.util.Collections;
import java.util.PriorityQueue;


public class MedianII {

	/**
     * @param nums: A list of integers.
     * @return: the median of numbers
     */
    public int[] medianII(int[] nums) {
		int[] medians = null;
		if(nums.length == 0 || nums == null){
			return medians;
		}
		
		int size = nums.length;
		PriorityQueue<Integer> maxHead = new PriorityQueue<>(size, Collections.reverseOrder());
		PriorityQueue<Integer> minHead = new PriorityQueue<>(size);
		medians = new int[nums.length];
		medians = addMedians(medians, nums);
		return medians;
	}
	
	/**
	 * 算法思想：设置两个优先级队列，分别为maxHead和minHead
	 * 			maxHead以进入队列的数值大小的逆序保存数据
	 * 			minHead以进入队列的数值大小的升序保存数据
	 * 			count记录进入数据流的个数
	 * 			
	 * 			1. 遍历数组，每次nums[i]先进入maxHead(注意count从0开始)
	 * 			2. 当count为偶数且count=0时，nums[0]进入maxHead, count += 1, 
	 * 				maxHead.peek()即为数据流的中位数，加入maxHead.peek()到medians；
	 * 			3. 当count为奇数时，maxHead将队头的值poll()，并传入minHead；
	 * 			4. 当count为偶数且count != 0时， 如果minHead.peek() < maxHead.peek()，
	 * 				则交换minHead.peek()到maxHead,交换maxHead.peek()到minHead
	 * 			5. count++;
	 * 			6. maxHead.peek()即为数据流的中位数，加入maxHead.peek()到medians.
	 * 			计算流程:    (1) 1——> (i=0)执行第2步  ——> 6;
	 * 						(2) while(nums未到末尾){ 1——> (3 or 4) ——> 5  ——> 6} ——> 结束  
	 * 			如此一来，则maxHead的队头一直保持着数据流的中位数，minHead的队头一直保持着数据流中位数后面一位
	 * 
	 * 举例说明： 假设数据流nums =[x1, x2, x3, x4, ..., xn]
	 * 			1) 当第一个数nums[0] = x1进入数据流时， maxHead = [x1],记录中位数为x1,count=1, medians = [x1];
	 * 			2) 当x2进入数据流时，maxHead = [x1,x2]或者[x2,x1]，执行步骤3(假设x1 > x2)， maxHead = [x2], minHead = [x1],
	 * 				执行步骤5， count = 2, medians = [x1, x2]
	 * 			3) 当x3进入数据流时， maxHead = [x2,x3]或者[x3,x2], 执行步骤4(假设x3 > x1 > x2)， 
	 * 				因为x3 > x1 ， maxHead和minHead交换队头，maxHead = [x1, x2](因为maxHead逆序)， minHead = [x3],
	 * 				执行步骤5， count = 3, medians = [x1, x2, x1]
	 * 				...
	 * 				由于步骤3的存在，那么如果数据流的个数为偶数，则maxHead和minHead中的数据数量一样多，如果数据流的个数为奇数，
	 * 				maxHead比minHead中数据的数量多一;
	 * 				由于步骤4的存在，将会一直满足maxHead.peek() < minHead.peek();
	 * 				maxHead(逆序)，minHead(升序)，而且maxHead.peek() < minHead.peek()，
	 * 				无论数据流的个数为偶数还是奇数，maxHead.peek()总是为中位数。
	 * @param medians 保存数据流中位数的数组
	 * @param nums	数据流数组
	 * @return
	 */
	public int[] addMedians(int[] medians, int[] nums) {
		int count = 0;
		int size = nums.length;
		PriorityQueue<Integer> maxHead = new PriorityQueue<>(size, Collections.reverseOrder());
		PriorityQueue<Integer> minHead = new PriorityQueue<>(size);
		int flag = 0;
		for(int i = 0; i < size; i++) {
			maxHead.add(nums[i]);
			if(count % 2 == 0) {
				if(count == 0) {
					count++;
					medians[i] = maxHead.peek();
					continue;
				} else {
					if(minHead.peek() < maxHead.peek()) {
						minHead.add(maxHead.poll());
						maxHead.add(minHead.poll());
					}
				}
			} else {
				minHead.add(maxHead.poll());
			}
			count++;
			medians[i] = maxHead.peek();
		}
		return medians;
	}
}
