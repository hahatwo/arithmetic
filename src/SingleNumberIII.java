import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SingleNumberIII {

	/* ����2*n + 2�������֣���������������֮������ÿ�����־��������Σ��ҵ����������֡�
     * @param A: An integer array
     * @return: An integer array
     */
    public List<Integer> singleNumberIII(int[] A) {
        // write your code here
    	List<Integer> list = new ArrayList<Integer>();
    	if(A.length < 2 || A == null){
    		return list;
    	}
    	findDifferentNum(list, A);
    	return list;
    }
    
   /**
    * �㷨˼�룺	���������������ݰ������ֵ���õ���ֵΪΨ���������ظ����ֵ������.
    * 			����ֵ�ö����Ʊ�ʾ��Ϊxor, ͨ��xor - (xor & (xor - 1))�ҵ�xor���λ��Ϊ1���Ǹ�λ��
    * 			˵���� 	���� xor = (......)1000...(1֮��ȫΪ0,����1����ĩβһ��0Ҳû��)
    * 				 	��ôxor-1 = (......)011...1(0֮��ȫΪ1������0����ĩβһ��1Ҳû��)
    * 					xor & (xor - 1) = (......)000..0, (......)���ᷢ���ı�
    * 					lastBit = xor - (xor & (xor - 1)) =(0...0)10...0 [(......)ȫ����Ϊ0��1֮��Ҳȫ��Ϊ0]
    * 			��diffNum1��¼��һ�����ظ������֣�diffNum2��¼�ڶ������ظ�������,��ʼ����Ϊ0
    * 			�������飬
    * 			1. �� lastBit & A[i] = 0ʱ��diffNum1 ^= A[i]
    * 			2. �� lastBit & A[i] = 1ʱ��diffNum2 ^= A[i]
    * 			����������diffNum1��Ϊ��һ�����ظ������֣� diffNum2��Ϊ�ڶ������ظ�������
    * 			˵���� ����lastBit & A[i] = 0 ����lastBit & A[i] = 1������ĳ���ظ���������˵�����������Ҫô�ڵ�һ��Ҫô�ڵڶ���,
    * 			�������һ���ڵ�һ����һ���ڵڶ���������� ��A[i] ^ A[i]����0�����߱������ɣ������ظ����ֶ������ֵ�����ս����Ӱ��;
    * 			������������ͬ�Ĳ��ظ����֣��������ǵĶ�������һλ����ͬ�� ����֪�����ǵ�һλ����ͬ������λ����lastbit������λΪ1���Ǹ�λ��
    * 			�����������ظ�����Ϊa1,a2�� a1�ĵ�һλ����ͬ������λΪ0�� a2�ĵ�һλ����ͬ������Ϊ1
    * 			lastBit & A[i] = 0, diffNum1����a1;
    *  			lastBit & A[i] = 1, diffNum2����a2;
    *           ��֮��Ȼ��
    * 			
    * @param list
    * @param A
    */
    private void findDifferentNum(List<Integer> list, int[] A) {
    	int xor = 0;
    	for(int i = 0; i < A.length; i++) {
    		xor ^= A[i];
    	}
    	int lastBit = xor - (xor & (xor - 1));
    	int diffNum1 = 0, diffNum2 = 0;
    	for(int i = 0; i < A.length; i++) {
    		if((lastBit & A[i]) == 0) {
    			diffNum1 ^= A[i];
    		} else {
    			diffNum2 ^= A[i];
    		}
    	}
    	list.add(diffNum1);
    	list.add(diffNum2);
    }
    
}
