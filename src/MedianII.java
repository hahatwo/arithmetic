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
	 * �㷨˼�룺�����������ȼ����У��ֱ�ΪmaxHead��minHead
	 * 			maxHead�Խ�����е���ֵ��С�����򱣴�����
	 * 			minHead�Խ�����е���ֵ��С�����򱣴�����
	 * 			count��¼�����������ĸ���
	 * 			
	 * 			1. �������飬ÿ��nums[i]�Ƚ���maxHead(ע��count��0��ʼ)
	 * 			2. ��countΪż����count=0ʱ��nums[0]����maxHead, count += 1, 
	 * 				maxHead.peek()��Ϊ����������λ��������maxHead.peek()��medians��
	 * 			3. ��countΪ����ʱ��maxHead����ͷ��ֵpoll()��������minHead��
	 * 			4. ��countΪż����count != 0ʱ�� ���minHead.peek() < maxHead.peek()��
	 * 				�򽻻�minHead.peek()��maxHead,����maxHead.peek()��minHead
	 * 			5. count++;
	 * 			6. maxHead.peek()��Ϊ����������λ��������maxHead.peek()��medians.
	 * 			��������:    (1) 1����> (i=0)ִ�е�2��  ����> 6;
	 * 						(2) while(numsδ��ĩβ){ 1����> (3 or 4) ����> 5  ����> 6} ����> ����  
	 * 			���һ������maxHead�Ķ�ͷһֱ����������������λ����minHead�Ķ�ͷһֱ��������������λ������һλ
	 * 
	 * ����˵���� ����������nums =[x1, x2, x3, x4, ..., xn]
	 * 			1) ����һ����nums[0] = x1����������ʱ�� maxHead = [x1],��¼��λ��Ϊx1,count=1, medians = [x1];
	 * 			2) ��x2����������ʱ��maxHead = [x1,x2]����[x2,x1]��ִ�в���3(����x1 > x2)�� maxHead = [x2], minHead = [x1],
	 * 				ִ�в���5�� count = 2, medians = [x1, x2]
	 * 			3) ��x3����������ʱ�� maxHead = [x2,x3]����[x3,x2], ִ�в���4(����x3 > x1 > x2)�� 
	 * 				��Ϊx3 > x1 �� maxHead��minHead������ͷ��maxHead = [x1, x2](��ΪmaxHead����)�� minHead = [x3],
	 * 				ִ�в���5�� count = 3, medians = [x1, x2, x1]
	 * 				...
	 * 				���ڲ���3�Ĵ��ڣ���ô����������ĸ���Ϊż������maxHead��minHead�е���������һ���࣬����������ĸ���Ϊ������
	 * 				maxHead��minHead�����ݵ�������һ;
	 * 				���ڲ���4�Ĵ��ڣ�����һֱ����maxHead.peek() < minHead.peek();
	 * 				maxHead(����)��minHead(����)������maxHead.peek() < minHead.peek()��
	 * 				�����������ĸ���Ϊż������������maxHead.peek()����Ϊ��λ����
	 * @param medians ������������λ��������
	 * @param nums	����������
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
