import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class SingleNumberIII {

	/* 给出2*n + 2个的数字，除其中两个数字之外其他每个数字均出现两次，找到这两个数字。
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
    * 算法思想：	对数组中所有数据按异或求值，得到的值为唯二两个不重复数字的异或结果.
    * 			假设值用二进制表示，为xor, 通过xor - (xor & (xor - 1))找到xor最后位置为1的那个位置
    * 			说明： 	假设 xor = (......)1000...(1之后全为0,或者1就在末尾一个0也没有)
    * 				 	那么xor-1 = (......)011...1(0之后全为1，或者0就在末尾一个1也没有)
    * 					xor & (xor - 1) = (......)000..0, (......)不会发生改变
    * 					lastBit = xor - (xor & (xor - 1)) =(0...0)10...0 [(......)全部变为0，1之后也全部为0]
    * 			用diffNum1记录第一个不重复的数字，diffNum2记录第二个不重复的数字,初始化均为0
    * 			遍历数组，
    * 			1. 当 lastBit & A[i] = 0时，diffNum1 ^= A[i]
    * 			2. 当 lastBit & A[i] = 1时，diffNum2 ^= A[i]
    * 			遍历结束，diffNum1就为第一个不重复的数字， diffNum2就为第二个不重复的数字
    * 			说明： 无论lastBit & A[i] = 0 或是lastBit & A[i] = 1，对于某个重复的数字来说，两次求异或要么在第一步要么在第二步,
    * 			不会出现一次在第一步，一次在第二步的情况， 而A[i] ^ A[i]等于0，异或具备交换律，所以重复数字对异或求值的最终结果无影响;
    * 			对于两个不相同的不重复数字，至少他们的二进制有一位不相同， 容易知道他们第一位不相同二进制位就是lastbit二进制位为1的那个位置
    * 			假设两个不重复数字为a1,a2， a1的第一位不相同二进制位为0， a2的第一位不相同二进制为1
    * 			lastBit & A[i] = 0, diffNum1保存a1;
    *  			lastBit & A[i] = 1, diffNum2保存a2;
    *           反之亦然。
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
